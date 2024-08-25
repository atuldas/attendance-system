package org.learn.newattendancesystem.config;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public ErrorViewResolver errorViewResolver() {
        return (request, status, model) -> {
            if (status.is4xxClientError()) {
                model.put("errorMessage", "An error occurred: " + status.value());
                return new ModelAndView("accessDenied", model);
            }
            return null;
        };
    }
}
