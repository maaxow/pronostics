package fr.maaxow.pronostics.rest;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.ws.rs.core.Application;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Component;

@Component
public class RestApplication extends Application {

	/**
	 * Default package for OF rest services.
	 */
	private final static String DEFAULT_REST_SERVICE_PACKAGE = "pronostics.rest";

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();

		// Register all rest services from the default package
		final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
				false);
		provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
		final Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents(DEFAULT_REST_SERVICE_PACKAGE);
		for (BeanDefinition bean : beanDefinitions) {
			try {
				System.out.println(bean.getBeanClassName() + " : " + bean.isAutowireCandidate());
				classes.add(Class.forName(bean.getBeanClassName()));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return classes;
	}
}
