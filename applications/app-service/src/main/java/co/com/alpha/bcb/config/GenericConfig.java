package co.com.alpha.bcb.config;

import co.com.alpha.bcb.serializer.JSONMapper;
import co.com.alpha.bcb.serializer.JSONMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericConfig {

    @Bean
    public JSONMapper jsonMapper(){
        return new JSONMapperImpl();
    }
}
