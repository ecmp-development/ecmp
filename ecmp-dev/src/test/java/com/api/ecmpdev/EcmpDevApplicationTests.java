package com.api.ecmpdev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EcmpDevApplicationTests extends AbstractContainerBaseTest {

	@Test
	void contextLoads() {
		new ApplicationContextRunner()
				.run((context) -> Assertions.assertTrue(context.isRunning()));
	}

}
