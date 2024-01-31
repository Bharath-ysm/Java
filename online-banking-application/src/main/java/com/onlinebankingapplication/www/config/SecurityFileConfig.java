package com.onlinebankingapplication.www.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.onlinebankingapplication.filter.JwtAuthFilter;
import com.onlinebankingapplication.www.utility.Constants.UserRole;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityFileConfig 
{
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	//Authentication
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new CustomerUserDetailsService;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
	{
		http.csrf(csrf->csrf.disable()).cors(cors->cors.disable()).
		
		authorizeHttpRequests(auth->auth.requestMatchers("api/user/login","api/user/register/admin").permitAll().
				
				//this api's only accessible by admin
				
				requestMatchers("api/bank/add","api/bank/allBanks","api/bank/BankByUserId",
						"api/account/fetch/all","api/transaction/All Bank/ customer transaction")
				.hasAuthority(UserRole.ROLE_ADMIN.Value()).
				
				//this api's only accessible by Bank
				
				requestMatchers("api/account/fetch",
						"api/account/fetch/bank",
						"api/account/search",
						"api/account/update/status",
						"api/bank/BankById","api/bank/BankByUserId",
						"api/transaction/withdraw/transaction",
						"api/transaction/bank/userTransactionHistory",
						"api/transaction/BankCustomerTransaction/by Time Range",
						"api/user/fetch/AllBankManagers","api/user/fetch/Allbankusers"
						.hasAuthority(UserRole.ROLE_BANK.value())).
				
				
				//this api's only accesible by customer
				
				requestMatchers("api/user/Update/userstatus","api/transaction/bank/user Transaction History",
				"api/transaction/download/Statement","api/transaction/Transfer/Transaction","api/user/search/customers",
				"api/user/search/Id's,customers").hasAuthority(UserRole.ROLE_CUSTOMER.value())).
				
				//this api's only accesible by Bank and customer
				 
				requestMatchers("api/user/Update/userStatus","api/user/fetch/ByBankId",
						"api/account/fetch/user","api/bank/BankByUserId",
						"api/transaction/deposit/transaction","api/transaction/withdraw/transaction",
						"api/transaction/bank/userTransactionHistory",
						"api/transaction/bank/customer transaction",
						"api/transaction/bank customer transaction/by Time Range",
						"api/transaction/All customer transaction",
						"api/transaction/download/Statement")
						.hasAnyAuthority(UserRole.ROLE_BANK.value(),
								UserRole.ROLE_CUSTOMER.value()).
						
				//this api's only accesible by Bank and Admin
						
				requestMatchers("api/user/Login","api/user/fetch/All bank managers",
						"api/account/add","api/account/fetch/all",
						"api/bank/allBanks").
						hasAnyAuthority(UserRole.ROLE_BANK.value(),
						UserRole.ROLE_ADMIN.value()).
		
		//this api's accesible by Bank , Admin and Customer
		
			requestMatchers("api/user/Login")
			.hasAnyAuthority(UserRole.ROLE_ADMIN.value(),UserRole.ROLE_BANK.value(),
					UserRole.ROLE_CUSTOMER.value()).anyRequest().authenticated()).
			
			sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			http.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
			
			return http.build();
	}

			
			@Bean
			public PasswordEncoder passwordEncoder() 
			{
				return new BCryptPasswordEncoder();
			}

			@Bean
			public AuthenticationProvider authenticationProvider() {
				DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
				authenticationProvider.setUserDetailsService(userDetailsService());
				authenticationProvider.setPasswordEncoder(passwordEncoder());
				return authenticationProvider;
			}

			@Bean
			public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
				return config.getAuthenticationManager();
			}
	
}
