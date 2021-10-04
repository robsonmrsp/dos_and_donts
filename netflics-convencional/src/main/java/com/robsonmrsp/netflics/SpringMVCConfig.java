package com.robsonmrsp.netflics;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.robsonmrsp.netflics.commonlibs.CustomLocalDateSerializer;

@Configuration
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    Jackson2ObjectMapperBuilder builder =
        new Jackson2ObjectMapperBuilder() //
            .indentOutput(true) //
            .serializerByType(LocalDate.class, new CustomLocalDateSerializer()) //
        ; //
    converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
  }
}
