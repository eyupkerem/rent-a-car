package com.example.rent_a_car.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final  JwtAuthFilter jwtAuthFilter;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(AbstractHttpConfigurer:: disable)
                .authorizeHttpRequests( x->
                  x.requestMatchers("/api/user/login").permitAll()
                          //USER
                          .requestMatchers(HttpMethod.GET, "/api/user").hasAuthority("ROLE_ADMIN")
                          .requestMatchers(HttpMethod.GET, "/api/user/*").hasAuthority("ROLE_ADMIN")
                          .requestMatchers(HttpMethod.POST, "/api/user").permitAll()
                          .requestMatchers(HttpMethod.PUT, "/api/user/*").hasAuthority("ROLE_ADMIN")
                          .requestMatchers(HttpMethod.DELETE, "/api/user/*").hasAuthority("ROLE_ADMIN")

                          // Gear
                          .requestMatchers(HttpMethod.GET, "/api/gear").permitAll()
                          .requestMatchers(HttpMethod.GET, "/api/gear/*").permitAll()
                          .requestMatchers("/api/gear/**").hasAuthority("ROLE_ADMIN")

                           //Fuel
                          .requestMatchers(HttpMethod.GET, "/api/fuel").permitAll()
                          .requestMatchers(HttpMethod.GET, "/api/fuel/*").permitAll()
                          .requestMatchers("/api/fuel/**").hasAuthority("ROLE_ADMIN")

                           // Category
                          .requestMatchers(HttpMethod.GET, "/api/category").permitAll()
                          .requestMatchers(HttpMethod.GET, "/api/category/*").permitAll()
                          .requestMatchers("/api/category/**").hasAuthority("ROLE_ADMIN")

                          // Brand
                          .requestMatchers(HttpMethod.GET, "/api/brand").permitAll()
                          .requestMatchers(HttpMethod.GET, "/api/brand/*").permitAll()
                          .requestMatchers("/api/brand/**").hasAuthority("ROLE_ADMIN")

                          // Reservation
                          .requestMatchers(HttpMethod.GET, "/api/reservation").hasAuthority("ROLE_ADMIN")
                          .requestMatchers(HttpMethod.GET, "/api/reservation/*").hasAuthority("ROLE_ADMIN")
                          .requestMatchers(HttpMethod.POST, "/api/reservation/user/*/car/*").authenticated()

                          .requestMatchers(HttpMethod.GET, "/api/car/**").authenticated()
                          .requestMatchers(HttpMethod.GET, "/api/car").authenticated()
                          .requestMatchers(HttpMethod.GET, "/api/car/**").authenticated()
                          .requestMatchers(HttpMethod.POST, "/api/car/**").authenticated()
                          .requestMatchers(HttpMethod.PATCH, "/api/car/**").authenticated()



                          // Swagger UI ve API dokümantasyonlarına izin ver
                          .requestMatchers("/swagger-ui/index.html#/").permitAll()
                          .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

//                        x.anyRequest().permitAll()

                )
                .sessionManagement( x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

}
