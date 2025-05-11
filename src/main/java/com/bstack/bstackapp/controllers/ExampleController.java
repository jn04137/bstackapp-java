package com.bstack.bstackapp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bstack.bstackapp.models.Post;
import com.bstack.bstackapp.repositories.UserRepository;
import com.bstack.bstackapp.models.UserAccount;

@RestController
@RequestMapping("/example")
public class ExampleController {

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

}
