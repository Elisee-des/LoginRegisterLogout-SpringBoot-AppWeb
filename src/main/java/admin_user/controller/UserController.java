package admin_user.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import admin_user.dto.UserDto;
import admin_user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/inscription")
	public String getRegisterPage(@ModelAttribute("user") UserDto userdto)
	{
		return "register";
	}
	
	@PostMapping("/inscription/action")
	public String saveUser(@ModelAttribute("user") UserDto userdto, Model model)
	{
		userservice.save(userdto);
		model.addAttribute("sucess", "Inscription effectué avec succès");
		return "register";
	}
	
	@GetMapping("/connexion")
	public String login()
	{
		return "login";
	}

	@GetMapping("/user-page")
	public String userPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "user";
	}
	
	@GetMapping("/admin-page")
	public String adminPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "admin";
	}

}
