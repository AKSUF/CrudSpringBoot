package com.powerconsuption.com.payload;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {
	  private UUID id;
	    private String first_name;
	    private String last_name;
	    private String street;
	    private String address;
	    private String city;
	    private String state;
	    private String email;
	    private String phone;
}
