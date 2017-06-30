/*
 * Copyright 2017-Present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package message_generation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class MessageGenerationApplication {

    private Log log = LogFactory.getLog(MessageGenerationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MessageGenerationApplication.class, args);
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="salutation", defaultValue="Hello") String salutation, @RequestParam(value="name", defaultValue="Bob") String name) {
        log.info(String.format("Now saying \"%s\" to %s", salutation, name));
        return new Greeting(salutation, name);
    }

    private static class Greeting {

        private static final String template = "%s, %s!";

        private String message;

        public Greeting(String salutation, String name) {
            this.message = String.format(template, salutation, name);
        }

        public String getMessage() {
            return this.message;
        }

    }

}
