package com.hjelp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class TalleresApplication {

	public static void main(String[] args) {

		SpringApplication.run(TalleresApplication.class, args);
	}



	/**
	 * Se crea el Bean que encriptará y desencriptará las contraseñas
	 *
	 * @return Devuelve un objeto de tipo BCryptPasswordEncoder que implementa PasswordEncoder y utiliza la potente
	 * función hash BCrypt.
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Se crea un bean que se encargará de la resolución de mensajes en diferentes locales.
	 *
	 * @return Devuelve un ReloadableResourceBundleMessageSource, la implementación más común de MessageSource.
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
				= new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * Esto permite escribir nombres de mensajes personalizados en los archivos properties de mensajes.
	 *
	 * @return LocalValidatorFactoryBean, con el messageSource registrado.
	 */
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
