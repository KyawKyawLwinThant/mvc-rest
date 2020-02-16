package com.example.demorest.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@Configuration
public class MVCConfig implements WebMvcConfigurer {


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Optional<HttpMessageConverter<?>>  fondConverter=converters
                .stream()
                .filter(converter ->
                converter instanceof MappingJackson2HttpMessageConverter)
                .findFirst();

        if(fondConverter.isPresent()){
        AbstractJackson2HttpMessageConverter  converter=
                (AbstractJackson2HttpMessageConverter) fondConverter.get();
         converter.getObjectMapper()
                 .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
         converter.getObjectMapper()
                 .addMixIn(Object.class,RemoveJsonProperties.class);
        }
    }
    @JsonIgnoreProperties({"hibernateLazyInitializer","{}"})
    private abstract  class RemoveJsonProperties{}
}
