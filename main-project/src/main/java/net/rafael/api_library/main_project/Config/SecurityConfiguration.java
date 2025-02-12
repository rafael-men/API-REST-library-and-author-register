package net.rafael.api_library.main_project.Config;

import net.rafael.api_library.main_project.Security.CustomUserDetails;
import net.rafael.api_library.main_project.Security.LoginSocialSuccessHandler;
import net.rafael.api_library.main_project.Services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true,jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http, LoginSocialSuccessHandler successHandler) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin( configurer -> {
                    configurer.loginPage("/login").permitAll();
                })
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login").permitAll();
                    authorize.requestMatchers(HttpMethod.POST,"/users/**").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> {
                    oauth2
                            .loginPage("/login").successHandler(successHandler);
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    // Usuários em Memória

    //@Bean
    public UserDetailsService UserDetailsService(UserService service) {
        //UserDetails user1 = User.builder()
                //.username("User")
                //.password(encoder.encode("123"))
                //.roles("USER")
                //.build();
        //UserDetails user2 = User.builder()
                //.username("Admin")
                //.password(encoder.encode("admin123"))
               //.roles("ADMIN")
                //.build();
        //return new InMemoryUserDetailsManager(user1,user2);

        return new CustomUserDetails(service);
    }
}
