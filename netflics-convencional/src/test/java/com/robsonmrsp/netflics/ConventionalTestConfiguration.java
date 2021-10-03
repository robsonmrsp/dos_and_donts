package com.robsonmrsp.netflics;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories
@Profile("test")
@TestConfiguration
public class ConventionalTestConfiguration {}


criar aqui a base de daods para rodas os testes