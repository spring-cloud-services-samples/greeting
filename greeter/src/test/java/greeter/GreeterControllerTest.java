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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GreeterController.class)
public class GreeterControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private GreeterService greeterService;

    @WithMockUser(value = "Cookie")
	@Test
	public void helloDefault() throws Exception {
		given(this.greeterService.greet("Hello", "Bob"))
				.willReturn(new Greeting("Hello Bob"));
		this.mvc.perform(get("/hello")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello Bob"));
	}

    @WithMockUser(value = "Cookie")
	@Test
	public void helloWithParams() throws Exception {
		given(this.greeterService.greet("Hi", "Tom"))
				.willReturn(new Greeting("Hi Tom"));
		this.mvc.perform(get("/hello?salutation=Hi&name=Tom")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("Hi Tom"));
	}

}
