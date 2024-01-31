package Com.First.CrudApp.Util.Cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreBeans {

    @Bean
    public CacheStore<String> tokenCache() {
        return new CacheStore<String>(30, TimeUnit.SECONDS);
    }

}