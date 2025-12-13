package com.naman.SweetShop;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SweetShopApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldRegisterNewUser() throws Exception {
		String newUserJson = """
				{
					"username": "candyman",
					"password": "password123",
					"role": "USER"
				}
			""";
		mockMvc.perform(post("/api/auth/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(newUserJson))
				.andExpect(status().isCreated());

	}

}
