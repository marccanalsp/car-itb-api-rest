package cat.itb.m09_marccanals_apirest_persistent.security;

import cat.itb.m09_marccanals_apirest_persistent.model.service.mUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigWebSecurity extends WebSecurityConfigurerAdapter {
    private  final AuthenticationEntryPoint entryPoint;
    private  final mUserDetailsService mUserDetailsService;
    private  final PasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mUserDetailsService).passwordEncoder(encoder);
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors() //amb aquesta línia evitem la configuració custom del cors en un fitxer a part
                .and()
                .httpBasic()
                .authenticationEntryPoint(entryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/me/**").hasRole("USER") //per fer proves del forbidden
                .antMatchers(HttpMethod.GET, "/users/**", "/cars/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/users/**", "/cars/").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/cars/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/cars/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/cars/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated();
        // .and()
        // .csrf().disable();
    }

}
