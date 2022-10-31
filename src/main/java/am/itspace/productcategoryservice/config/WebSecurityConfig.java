package am.itspace.productcategoryservice.config;

import am.itspace.productcategoryservice.model.Role;
import am.itspace.productcategoryservice.security.CurrentUserDetailServiceImpl;
import am.itspace.productcategoryservice.security.JWTAuthenticationTokenFilter;
import am.itspace.productcategoryservice.security.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CurrentUserDetailServiceImpl userDetailsService;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/categories").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/categories").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/categories").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/products").hasAuthority(Role.USER.name())
                .antMatchers(HttpMethod.PUT, "/products").hasAuthority(Role.USER.name())
                .antMatchers(HttpMethod.DELETE, "/products").hasAuthority(Role.USER.name())
                .anyRequest().permitAll();

        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public JWTAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JWTAuthenticationTokenFilter();
    }
}