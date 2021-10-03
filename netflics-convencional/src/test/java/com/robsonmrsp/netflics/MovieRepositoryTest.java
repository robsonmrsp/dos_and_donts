package com.robsonmrsp.netflics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.robsonmrsp.netflics.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(classes = {ConventionalTestConfiguration.class, MovieRepositoryTest.TestConfig.class})
@Import(ConventionalTestConfiguration.class)
@ActiveProfiles({"test"})
public class MovieRepositoryTest {

  @Autowired MovieRepository movies;

  @

  @ComponentScan
  static class TestConfig {}
}
