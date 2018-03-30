package fr.gwombat.springembeddedtomcat.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by guillaume.
 *
 * @since 07/02/2018
 */
@Configuration
@ComponentScan("fr.gwombat.springembeddedtomcat.controllers")
public class WebConfig implements WebMvcConfigurer {
}
