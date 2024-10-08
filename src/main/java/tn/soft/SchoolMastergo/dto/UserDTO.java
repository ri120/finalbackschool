package tn.soft.SchoolMastergo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tn.soft.SchoolMastergo.entites.Parent;
import tn.soft.SchoolMastergo.security.auth.RegisterRequest;
import tn.soft.SchoolMastergo.security.models.User;
@Getter()
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String adress;
	private String phone;
	private String description;
	 public static User toEntity(UserDTO request)
	    {
	        return User.builder()
	                .id(request.getId())
	                .firstName(request.getFirstName())
	                .lastName(request.getLastName())
	                .email(request.getEmail())
	                .adress(request.getAdress())
	                .phone(request.getPhone())
	                .build();
	    }
	 public static UserDTO fromEntity(User request)
	    {
	        return UserDTO.builder()
	                .id(request.getId())
	                .firstName(request.getFirstName())
	                .lastName(request.getLastName())
	                .email(request.getEmail())
	                .adress(request.getAdress())
	                .phone(request.getPhone())
	                .build();
	    }
	 


}
