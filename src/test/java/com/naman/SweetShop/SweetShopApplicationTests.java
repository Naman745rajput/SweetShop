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
	void shouldLoginAndReturnToken() throws Exception {

		String uniqueUser = "login_test_" + System.currentTimeMillis();
		String password = "password123";

		String registerJson = String.format("""
            {
                "username": "%s",
                "password": "%s",
                "role": "USER"
            }
        """, uniqueUser, password);


		mockMvc.perform(post("/api/auth/register")
						.contentType("application/json")
						.content(registerJson))
				.andExpect(status().isCreated());

		String loginJson = String.format("""
            {
                "username": "%s",
                "password": "%s"
            }
        """, uniqueUser, password);


		mockMvc.perform(post("/api/auth/login")
						.contentType("application/json")
						.content(loginJson))
				.andExpect(status().isOk())
				.andExpect(result -> {
					String responseToken = result.getResponse().getContentAsString();
					if (responseToken.isEmpty()) {
						throw new AssertionError("Token response was empty!");
					}
				});
	}

}
