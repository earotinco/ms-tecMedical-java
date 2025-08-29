package com.cib.tecMedical.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.cib.tecMedical.service.CustomUserDetailsService;

@Configuration
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true) //ale
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .cors(Customizer.withDefaults()) //ale
	        .csrf(csrf -> csrf.disable()) // forma moderna
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/auth/**", "/api/productos/**",  "/api/categorias/**",
	            		 "/api/ventas/**").permitAll()
	            .requestMatchers("/api/clientes/**").hasAnyRole("Administrador", "Vendedor")
	            .anyRequest().authenticated()
	        )
	        .httpBasic(Customizer.withDefaults());

	    return http.build();
    }
	
	
    @Bean
    public PasswordEncoder passwordEncoder() {
    	System.out.println(" Bean PasswordEncoder registrado");
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
	 AuthenticationManager authManager(HttpSecurity http) throws Exception {
	    AuthenticationManagerBuilder authBuilder = 
	        http.getSharedObject(AuthenticationManagerBuilder.class);

	    authBuilder.userDetailsService(userDetailsService)
	               .passwordEncoder(passwordEncoder());

	    return authBuilder.build();
	}
    
    
   

    @Bean
    public CorsFilter corsFilter() { //ale
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200"); 
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}