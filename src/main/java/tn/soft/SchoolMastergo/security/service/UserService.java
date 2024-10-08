package tn.soft.SchoolMastergo.security.service;

import java.security.Principal;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import tn.soft.SchoolMastergo.security.models.ChangePasswordRequest;
import tn.soft.SchoolMastergo.security.models.User;
import tn.soft.SchoolMastergo.security.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {
	  private final PasswordEncoder passwordEncoder;
	    private final UserRepository repository;
	    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

	        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

	        // check if the current password is correct
	        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
	            throw new IllegalStateException("Wrong password");
	        }
	        // check if the two new passwords are the same
	        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
	            throw new IllegalStateException("Password are not the same");
	        }

	        // update the password
	        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

	        // save the new password
	        repository.save(user);
	    }
	    public User fetchUser(String email) {
	        return repository.existsByEmail(email)
	                ? repository.findByEmail(email).get()
	                : null;
	    }

	    public static String applicationUrl(HttpServletRequest request) {
	        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	    }
	    //gestion compte
		/*
		 * public List<UserDto> listvendeur(ModeleRole role){ List<User> users=
		 * repository.listusersByRole(role.getRole()); return users.stream().map(x ->
		 * UserDto.formUser(x)).collect(Collectors.toList());
		 * 
		 * }
		 */
	   /* public List<UserDto> listvendeur(ModeleRole role) {
	    	List<User> users= repository.listusersByRole(role.getRole());
			return UserDto.entityToDto(users);
		}*/
//	    public List<StagiaireDTO> entityToDto(List<Stagiaire> listStagier) {
//			return listStagier.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
//			}
	}

