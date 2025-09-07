package com.example.bankapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.bankapp.service.AccountService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = {
  "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
  "spring.jpa.hibernate.ddl-auto=none",
  "spring.sql.init.mode=never",
  "spring.main.web-application-type=none"
})
class BankappApplicationTests {

  // Satisfies securityConfig’s dependency so Spring DOES NOT create the real AccountService
  @MockBean
  private AccountService accountService;

  @Test
  void contextLoads() {}
}
