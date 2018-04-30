package com.in28minutes.rest.webservices.restfullwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userService;
	
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers(){
		return userService.findAll();
		
	}
	
	@GetMapping("users/{id}")
	public User retriveUser(@PathVariable int id) {
		User user = userService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-"+id);
		}
		return user;
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		//Store the newly saved user in a new local object
		User savedUser = userService.save(user);
		//in order to return the URI of new user, it is broken in into 3 parts
		//fromCurrentRequest() returns the /users part, as in the mapping
		//.path("/{id}").buildAndExpand(savedUser.getId()) appends to /users the id of newly saved user
		//.toUri() converts into URI
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
