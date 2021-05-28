package com.dtw.oAuth2Server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dtw.oAuth2Server.entity.User;
import com.dtw.oAuth2Server.entity.UserDetailsImpl;
import com.dtw.oAuth2Server.repo.UserRepo;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optUser = userRepo.findByUsername(username);
		optUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));
		UserDetails userDetails = new UserDetailsImpl(optUser.get());
		
		new AccountStatusUserDetailsChecker().check(userDetails);
		return userDetails;
	}
}