package camelboy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class CamelboyApplication {

    @Autowired
    ExpeditionClient expedition;

    @RequestMapping(value = "/hello", method = GET)
    public String greeting(@RequestParam(value="salutation", defaultValue="Hello") String salutation, @RequestParam(value="name", defaultValue="camel boy") String name) {
      Greeting greeting =  expedition.greeting(name, salutation);
      return greeting.getMessage();
    }

    public static void main(String[] args) {
        SpringApplication.run(CamelboyApplication.class, args);
    }

    private static class Greeting {

        private String message;

        @JsonCreator
        public Greeting(@JsonProperty("message") String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

    }

    @FeignClient("http://expedition")
    interface ExpeditionClient {
      @RequestMapping(value = "/greeting", method = GET)
      Greeting greeting(@RequestParam("name") String name, @RequestParam("salutation") String salutation);
    }
}
