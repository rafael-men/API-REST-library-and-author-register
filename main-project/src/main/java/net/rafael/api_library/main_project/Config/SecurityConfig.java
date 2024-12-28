package net.rafael.api_library.main_project.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true,securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable).formLogin(configurer -> {
            configurer.loginPage("/login").permitAll();
                })
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login").permitAll();
<<<<<<< HEAD
                        authorize.requestMatchers("/author/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.POST,"/users/**").permitAll();
                    authorize.anyRequest().authenticated();
                }).oauth2Login(Customizer.withDefaults()).build();
=======
                    authorize.requestMatchers(HttpMethod.POST,"/users/**").permitAll();
                    authorize.anyRequest().authenticated();
                }).build();
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

<<<<<<< HEAD
    @Bean  //correção do erro Unauthorized
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.builder()
                .username("Administrador")
                .password(encoder.encode("admin1234"))
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.builder()
                .username("User")
                .password(encoder.encode("1234"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, user2);
    }

=======
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.builder().username("Admnistrador").password(encoder.encode("admin1234")).roles("ADMIN").build();
        UserDetails user2 = User.builder().username("User").password(encoder.encode("1234")).roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a
}
