package greeter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("greeter-messages")
interface MessagesClient {
  @RequestMapping(value = "/greeting", method = GET)
  Greeting greeting(@RequestParam("name") String name, @RequestParam("salutation") String salutation);
}
