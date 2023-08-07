package com.powerconsuption.com.payload;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	    private Long id;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String password;
}
