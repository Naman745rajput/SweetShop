package com.naman.SweetShop;

import com.naman.SweetShop.model.Sweet;
import com.naman.SweetShop.repo.SweetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class SweetShopApplicationTests {

	@Autowired
	private MockMvc mockMvc;

    @Autowired
    private SweetRepository sweetRepository;


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

	@Test
	@WithMockUser(username = "boss", roles = {"ADMIN"})
	void testDeleteSweet() throws Exception {

		Sweet sweet = new Sweet();
		sweet.setName("Delete Me");
		sweet.setPrice(BigDecimal.valueOf(1.00));

		sweet = sweetRepository.save(sweet);

		mockMvc.perform(delete("/api/sweets/" + sweet.getId())
						.with(csrf()))
				.andExpect(status().isNoContent());
		assertFalse(sweetRepository.findById(sweet.getId()).isPresent());
	}

}
