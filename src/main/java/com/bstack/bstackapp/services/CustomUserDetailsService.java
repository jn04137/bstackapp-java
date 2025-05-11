package com.bstack.bstackapp.services;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bstack.bstackapp.models.UserAccount;
import com.bstack.bstackapp.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserAccount user = userRepo.findUserByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("Username couldn't be found"));

		return new User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority("User")));
	}
}
