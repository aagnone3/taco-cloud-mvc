package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
        // order matters when adding ant-based grants. put least-specific rules first
        .authorizeRequests()
            //requests to these paths require authorization
            .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER')")
            // requests to these paths are permitted for all users
            .antMatchers("/", "/**")
                .access("permitAll")
        .and()
            .formLogin()
                .loginPage("/login")
                // .loginProcessingUrl("/authenticate")
                // .usernameParameter("user")
                // .passwordParameter("pwd")
                // .defaultSuccessUrl("/design")
        // set up security filter to intercept POST request to /logout
        .and()
            .logout()
                .logoutSuccessUrl("/")
        ;
    }

}