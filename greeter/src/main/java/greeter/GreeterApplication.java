package greeter;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GreeterApplication {

    @Autowired
    private RestTemplate rest;

    public static void main(String[] args) {
        SpringApplication.run(GreeterApplication.class, args);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value="salutation", defaultValue="Hello") String salutation, @RequestParam(value="name", defaultValue="Bob") String name) {
        URI uri = UriComponentsBuilder.fromUriString("http://message-generation/greeting")
            .queryParam("salutation", salutation)
            .queryParam("name", name)
            .build()
            .toUri();

        Greeting greeting = rest.getForObject(uri, Greeting.class);
        return greeting.getMessage();
    }

    private static class Greeting {

        private final String message;

        @JsonCreator
        public Greeting(@JsonProperty("message") String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

    }

}
