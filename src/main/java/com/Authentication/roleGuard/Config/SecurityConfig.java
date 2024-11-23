package com.Authentication.roleGuard.Config;

import com.Authentication.roleGuard.Models.RoleEnum;
import com.Authentication.roleGuard.Services.UserDetailService;
import com.Authentication.roleGuard.Services.UserDetailServiceImpl;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final authenticationSuccessHandler successHandler;

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private jwtFilter filter;

    public SecurityConfig(authenticationSuccessHandler successHandler) {
        this.successHandler=successHandler;
    }

   /* @Autowired
    private UrlTamperCheckFiler urlTamperCheckFiler;
*/

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
return httpSecurity.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req->req.requestMatchers("/auth/login","/auth/register").permitAll()
                .requestMatchers("/auth/registerAdmin").hasAuthority("ADMIN")
                .anyRequest().authenticated())
        .userDetailsService(userDetailService)
        .formLogin(form->form.loginPage("/login").successHandler(new authenticationSuccessHandler()).permitAll())
        .sessionManagement(sessoin->sessoin.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/login?invalid"))
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
        .logout(logout->logout.logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"))
        .build();


    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
    return config.getAuthenticationManager();

    }
  /*  @Bean
    public UrlTamperCheckFiler urlTamperCheckFiler() throws Exception{
        return new UrlTamperCheckFiler();
    }*/

    /*@Bean
    public FilterRegistrationBean<UrlTamperCheckFiler> urlTamperCheckFilerFilterRegistrationBean() {
        FilterRegistrationBean<UrlTamperCheckFiler> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UrlTamperCheckFiler());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(13);
        return registrationBean;
    }*/

   /* @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider();

    }*/
    /*@Bean
    protected AuthenticationManagerBuilder managerBuilder(DaoAuthenticationProvider daoAuthenticationProvider)throws Exception{
       AuthenticationManagerBuilder managerBuilder1 = new AuthenticationManagerBuilder(daoAuthenticationProvider);
        return managerBuilder1;
    }*/

@Bean
    authenticationSuccessHandler successHandler(){
    return new authenticationSuccessHandler();
}




}
