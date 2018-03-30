package fr.gwombat.springembeddedtomcat.initializer;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import fr.gwombat.springembeddedtomcat.config.MainConfig;
import fr.gwombat.springembeddedtomcat.config.WebConfig;

/**
 * Created by gWombat.
 *
 * @since 07/02/2018
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { MainConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}
