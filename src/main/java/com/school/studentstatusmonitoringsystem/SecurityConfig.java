package com.school.studentstatusmonitoringsystem;

import com.school.studentstatusmonitoringsystem.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }
    // 🔐 SECURITY RULES
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // 🌍 PUBLIC
                        // =========================
                        .requestMatchers("/auth/**").permitAll()

                        // =========================
                        // 👑 ADMIN ONLY
                        // =========================
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/parents/**").hasRole("ADMIN")

                        // =========================
                        // 👨‍🏫 TEACHER WRITE ACCESS
                        // =========================
                        .requestMatchers("/attendance").hasRole("TEACHER")          // POST
                        .requestMatchers("/exam-results").hasRole("TEACHER")        // POST
                        .requestMatchers("/behavior").hasRole("TEACHER")            // POST

                        // =========================
                        // 👨‍👩‍👧 PARENT VIEW (SAFE)
                        // =========================
                        .requestMatchers("/attendance/parent/**").hasRole("PARENT")
                        .requestMatchers("/exam-results/parent/**").hasRole("PARENT")
                        .requestMatchers("/behavior/parent/**").hasRole("PARENT")

                        // =========================
                        // 📚 SHARED READ (TEACHER + ADMIN)
                        // =========================
                        .requestMatchers("/students/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers("/courses/**").hasAnyRole("ADMIN", "TEACHER")

                        // =========================
                        // 🔒 EVERYTHING ELSE
                        // =========================
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {});

        return http.build();
    }

    // 🔐 LOAD USER FROM DATABASE
    @Bean
    public UserDetailsService userDetailsService() {

        return username -> {

            com.school.studentstatusmonitoringsystem.model.User dbUser =
                    userService.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return User.builder()
                    .username(dbUser.getUsername())
                    .password(dbUser.getPassword())
                    .roles(dbUser.getRole().name()) // ADMIN / TEACHER / PARENT
                    .build();
        };
    }


    // 🔐 REQUIRED FOR LOGIN SYSTEM
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}