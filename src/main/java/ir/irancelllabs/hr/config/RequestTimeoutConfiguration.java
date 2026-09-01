package ir.irancelllabs.hr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class RequestTimeoutConfiguration implements WebMvcConfigurer {

    private final Duration requestTimeout;

    public RequestTimeoutConfiguration(
            @Value("${app.request.timeout}") Duration requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(requestTimeout.toMillis());
    }
}
