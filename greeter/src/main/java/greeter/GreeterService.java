/*
 * Copyright 2017-Present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package greeter;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GreeterService {

	private static final String URI_TEMPLATE = UriComponentsBuilder.fromUriString("//greeter-messages/greeting")
			.queryParam("salutation", "{salutation}")
			.queryParam("name", "{name}")
			.build()
			.toUriString();

	private final RestTemplate rest;

	public GreeterService(RestTemplate restTemplate) {
		this.rest = restTemplate;
	}

	public Greeting greet(String salutation, String name) {
		Assert.notNull(salutation, "salutation is required");
		Assert.notNull(name, "name is required");
		return rest.getForObject(URI_TEMPLATE, Greeting.class, salutation, name);
	}

}
