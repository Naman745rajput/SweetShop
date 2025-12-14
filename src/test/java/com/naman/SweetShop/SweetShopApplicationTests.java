package com.naman.SweetShop;

import com.naman.SweetShop.model.Sweet;
import com.naman.SweetShop.model.User;
import com.naman.SweetShop.repo.SweetRepository;
import com.naman.SweetShop.repo.UserRepository;
import com.naman.SweetShop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SweetShopApplicationTests {

	@Autowired
	private SweetRepository sweetRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		assertNotNull(sweetRepository);
		assertNotNull(orderService);
	}

	@Test
	void testInventoryReduction() {

		Sweet brownie = new Sweet();
		brownie.setName("Test Brownie");
		brownie.setPrice(BigDecimal.valueOf(5.00));
		brownie.setQuantity(10);
		brownie.setCategory("Test");
		sweetRepository.save(brownie);

		User testUser = new User();
		testUser.setUsername("testbuyer");
		testUser.setPassword("pass");
		testUser.setRole("ROLE_USER");
		userRepository.save(testUser);

		Long sweetId = brownie.getId();

		orderService.placeOrder("testbuyer", sweetId, 3);
		Sweet updatedBrownie = sweetRepository.findById(sweetId).orElseThrow();

		System.out.println("Old Stock: 10");
		System.out.println("New Stock: " + updatedBrownie.getQuantity());

		assertEquals(7, updatedBrownie.getQuantity(), "Stock should decrease by purchase amount");
	}

	@Test
	void testSoftDelete() {

		Sweet candy = new Sweet();
		candy.setName("Delete Me");
		candy.setPrice(BigDecimal.TEN);
		candy.setQuantity(100);
		sweetRepository.save(candy);
		Long id = candy.getId();

		sweetRepository.deleteById(id);

		assertTrue(sweetRepository.findById(id).isEmpty(), "Sweet should be hidden from normal queries");
	}
}