package com.example.ipLab.StoreDataBase.Configurations;

import com.example.ipLab.StoreDataBase.Configurations.jwt.JwtFilter;
import com.example.ipLab.StoreDataBase.Controllers.UserController;
import com.example.ipLab.StoreDataBase.MVC.UserLoginMVCController;
import com.example.ipLab.StoreDataBase.Model.UserRole;
import com.example.ipLab.StoreDataBase.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
    private final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
    private static final String LOGIN_URL = "/login";
    public static final String SPA_URL_MASK = "/{path:[^\\.]*}";
    private UserService userService;
    private JwtFilter jwtFilter;

    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
        this.jwtFilter = new JwtFilter(userService);
        createAdminOnStartup();
        createTestUsersOnStartup();
    }

    private void createAdminOnStartup() {
        final String admin = "admin";
        if (userService.getUserByLogin(admin) == null) {
            log.info("Admin user successfully created");
            userService.addUser(admin, admin, admin, UserRole.ADMIN, 2L);
        }
    }

    private void createTestUsersOnStartup() {
        final String[] userNames = {"user1", "user2", "user3"};
        final Long[] userId = {19052L, 19053L, 	20652L};
        int cnt = 0;
        for (String user : userNames) {
            if (userService.getUserByLogin(user) == null) {
                userService.addUser(user, user, user, UserRole.USER, userId[cnt]);
            }
            cnt++;
        }
    }

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests((a) -> a
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/customer/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/store/addStore/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/customer/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/store/addToStore/**").hasRole("ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers(HttpMethod.POST, UserController.URL_LOGIN).permitAll()
                        .requestMatchers(HttpMethod.GET, "/img/**").permitAll())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .anonymous().and().authorizeHttpRequests((a) ->
                        a.requestMatchers(LOGIN_URL, UserLoginMVCController.SIGNUP_URL, "/h2-console/**")
                                .permitAll().requestMatchers("/users").hasRole("ADMIN").anyRequest().authenticated())
                .formLogin()
                .loginPage(LOGIN_URL).permitAll()
                .defaultSuccessUrl("/", true)
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/login")
                .and()
                .userDetailsService(userService);
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/templates/**", "/webjars/**", "/styles/**");
    }
}
