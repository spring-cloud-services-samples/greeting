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

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class GreeterServiceTest {

	@Mock
	private RestTemplate rest;

	private GreeterService greeter;

	@BeforeEach
	public void setUp() {
		this.greeter = new GreeterService(rest);
	}

	@Test
	public void greet() {
		given(this.rest.getForObject("//greeter-messages/greeting?salutation={salutation}&name={name}", Greeting.class, "Hello", "Bob"))
				.willReturn(new Greeting("Hello Bob"));
		assertThat(this.greeter.greet("Hello", "Bob"))
				.isNotNull()
				.isEqualToComparingFieldByField(new Greeting("Hello Bob"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void greetNulls() {
		this.greeter.greet(null, null);
	}

}