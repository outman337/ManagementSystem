package com.backend.management.config;

import com.backend.management.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final JwtFilter jwtFilter;

    public SecurityConfig(DataSource dataSource, JwtFilter jwtFilter) {
        this.dataSource = dataSource;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/register/*").
                permitAll().antMatchers(HttpMethod.POST, "/authenticate/*").permitAll().
                antMatchers("/orders").hasAnyAuthority("ROLE_TENANT", "ROLE_MANAGER")
                .antMatchers("/orders/*").permitAll()
                .antMatchers("/announcements").hasAnyAuthority("ROLE_TENANT", "ROLE_MANAGER")
                .antMatchers("/announcement/*").hasAuthority("ROLE_MANAGER")
                .antMatchers("/announcement").hasAuthority("ROLE_MANAGER")
                .antMatchers("/post").hasAnyAuthority("ROLE_TENANT", "ROLE_MANAGER")
                .antMatchers("/post/*").hasAnyAuthority("ROLE_TENANT", "ROLE_MANAGER")
                .antMatchers("/posts").hasAnyAuthority("ROLE_TENANT", "ROLE_MANAGER")
                .antMatchers("/moveIn").hasAuthority("ROLE_MANAGER")
                .antMatchers("/moveInAndAssignNewOwner").hasAuthority("ROLE_MANAGER")
                .antMatchers("/moveOut").hasAuthority("ROLE_MANAGER")
                .antMatchers("/moveOutAndAssignNewOwner").hasAuthority("ROLE_MANAGER")
                .antMatchers("/getFlatmates").hasAuthority("ROLE_MANAGER")
                .antMatchers("/findApartmentsWithVacancy").hasAuthority("ROLE_MANAGER")
                .antMatchers("/getTenantsWithoutApartments").hasAuthority("ROLE_MANAGER")
                .antMatchers("/getTenantsWithApartments").hasAuthority("ROLE_MANAGER")
                .antMatchers("/getApartmentInfoByUsername").hasAuthority("ROLE_MANAGER")
                .antMatchers("/getApartmentInfoByApartmentNumber").hasAuthority("ROLE_MANAGER")
                .antMatchers("/getAllApartments").hasAuthority("ROLE_MANAGER")
                .antMatchers("/getAllTenants").hasAuthority("ROLE_MANAGER")
                .antMatchers("/reservation").hasAnyAuthority("ROLE_TENANT","ROLE_MANAGER")
                .antMatchers("/reservation/*").hasAnyAuthority("ROLE_TENANT", "ROLE_MANAGER")
                .antMatchers("/reservations").hasAnyAuthority("ROLE_TENANT", "ROLE_MANAGER")
                .anyRequest().authenticated().and().csrf().disable();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // 这个是spring security帮我们自动验证user数据
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, password, enabled FROM user WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authority WHERE username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 这里我们手动override成bean，这样这个验证管理器就可以被我们service使用
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
