package com.westbarn.security.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.westbarn.security.payload.request.LoginRequest;
import com.westbarn.security.payload.request.SignupRequest;
import com.westbarn.security.payload.response.JwtResponse;
import com.westbarn.security.payload.response.MessageResponse;
import com.westbarn.model.Customer;
import com.westbarn.model.Message;
import com.westbarn.security.jwt.JwtUtils;
import com.westbarn.security.model.ERole;
import com.westbarn.security.model.Role;
import com.westbarn.security.model.User;
import com.westbarn.security.repository.RoleRepository;
import com.westbarn.security.repository.UserRepository;
import com.westbarn.security.services.UserDetailsImpl;
import com.westbarn.service.CustomerService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private CustomerService serv;

	@PostMapping(value = "/signin",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		if(signUpRequest.getUsername()==null || signUpRequest.getUsername().equals(""))
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username cannot be empty!"));
		
		if(signUpRequest.getEmail()==null || signUpRequest.getEmail().equals(""))
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email cannot be empty!"));
		
		if(signUpRequest.getPassword()==null || signUpRequest.getPassword().equals(""))
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Password cannot be empty!"));
		
		if(!signUpRequest.getEmail().matches("^(.+)@(\\S+)$"))
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Please Enter a valid email address!"));
		
		if(signUpRequest.getUsername().length() < 4)
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username needs to have at least 4 characters!"));
		
		if(signUpRequest.getPassword().length() < 8)
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Password needs to have at least 8 characters!"));
		
		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(
					ERole.USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
			
			//if role is user, create a customer and add it to database using CustomerService
			Customer c = new Customer(signUpRequest.getUsername(),signUpRequest.getEmail());
			serv.add(c);
			
		} else {
			strRoles.forEach(role -> {
				switch (role.toLowerCase()) {
				case "admin":
					Role adminRole = roleRepository.findByName(
							ERole.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "moderator":
					Role modRole = roleRepository.findByName(
							ERole.MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;

				default:
					Role userRole = roleRepository.findByName(
							ERole.USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
					
					//if role is user, create a customer and add it to database using CustomerService
					Customer c = new Customer(signUpRequest.getUsername(),signUpRequest.getEmail());
					serv.add(c);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		// mail service to send plain text mail to user's email account about successful
		// registration
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@GetMapping("/find/{id}")
	public User find(@PathVariable long id) {
		User u = new User();
		Optional<User> op = userRepository.findById(id);
		if(op.isPresent())
			u = op.get();
		else
			System.out.println("User not found");
		return u;
	}
	
	@GetMapping("/delete/{id}")
	public Message delete(@PathVariable long id) {
		userRepository.deleteById(id);
		return serv.delete((int)id);
	}
	
	@GetMapping("/findByUsername/{username}")
	public User findByUsername(@PathVariable String username) {
		User u = new User();
		Optional<User> op = userRepository.findByUsername(username);
		if(op.isPresent())
			u = op.get();
		else
			System.out.println("User not found");
		return u;
	}
	
	@GetMapping("/changePwd/{username}+{new_pwd}")
	public Message changePwd(@PathVariable String username,@PathVariable String new_pwd) {
		Message m = new Message();
		try {
			User old_u = findByUsername(username);
			delete((long)old_u.getId());
			registerUser(new SignupRequest(old_u.getUsername(), old_u.getEmail(),new_pwd));
		} catch(Exception e) {
			m.setS("Error: Failed to change password.");
			return m;
		}
		m.setS("Sccess: password changed.");
		return m;

	}
	
}
