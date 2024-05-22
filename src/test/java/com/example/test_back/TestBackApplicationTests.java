package com.example.test_back;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestBackApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Test
//	void dbNameIsH2(@Value("${spring.datasource.url}") String url) {
//		assertEquals("h2", url.split(":")[1]);
//	}
//
//	@Test
//	void dbUserIsSa(@Value("${spring.datasource.username}") String username) {
//		assertEquals("sa", username);
//	}
}
