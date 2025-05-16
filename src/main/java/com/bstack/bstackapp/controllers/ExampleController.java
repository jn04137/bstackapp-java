package com.bstack.bstackapp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import com.bstack.bstackapp.models.Post;
import com.bstack.bstackapp.repositories.UserRepository;
import com.bstack.bstackapp.models.UserAccount;

@RestController
@RequestMapping("/public")
public class ExampleController {

	private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/hello")
	public Map<String, Object> sayHello() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("something", "some value here");
		return data;
	}

	@GetMapping("/testObject")
	public Post testPost() {
		return new Post("Some title", "This is the content");
	}

	@PostMapping("/createUser")
	public void createUser(@RequestBody UserAccount user) {
		userRepo.save(user);
	}

	@GetMapping("/getAllUsers")
	public List<UserAccount> getAllUsers() {
		List<UserAccount> userList = new ArrayList<>();
		Iterator<UserAccount> userIterator = userRepo.findAll().iterator();

		while(userIterator.hasNext()) {
			UserAccount user = userIterator.next();
			user.setPassword("");
			userList.add(user);
		}

		return userList;
	}

	@GetMapping("/getCurrentUser")
	public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {

		//Cookie[] cookies = request.getCookies();
        //if(cookies != null) {
		//	for(Cookie cookie : cookies) {
		//		logger.info("This is the cookie: {}", cookie.toString());
		//	}
		//}
		return ResponseEntity.ok(String.format("These are the cookies: %s", userDetails.getUsername()));
	}

}
