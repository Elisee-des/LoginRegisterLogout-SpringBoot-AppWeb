package admin_user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import admin_user.dto.UserDto;
import admin_user.model.User;
import admin_user.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userrepository;
	
	@Override
	public User save(UserDto userdto) {
		User  user = new User(userdto.getEmail(), passwordEncoder.encode(userdto.getPassword()), userdto.getRole(), userdto.getFullname());
		return userrepository.save(user);
	}

}
