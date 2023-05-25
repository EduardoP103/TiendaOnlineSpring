package com.tiendaOnline.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Asignamos nuestras rutas publicas
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/assets/**","/","principal","/home","/inicio","/logeo","login","/rest/**").permitAll()
		// Asignamos las rutas que queremos PROTEGER
		.antMatchers("/", "/index").hasAnyRole("USER")
		.antMatchers("/listar/**").hasAnyRole("USER")
		.antMatchers("/listarUsuario/**").hasAnyRole("USER")
		.antMatchers("/form/**").hasAnyRole("ADMIN")
		.antMatchers("/formUsuario/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		PasswordEncoder encoder = passwordEncoder();
		// Poder crear usuarios y encriptar contraseñas
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		
		// Creamos usuarios y asignamos roles
		builder.inMemoryAuthentication()
		.withUser(users.username("sagender").password("tobio@").roles("ADMIN", "USER"))
		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
		.withUser(users.username("anthony").password("12345").roles("USER"));
	}
}
