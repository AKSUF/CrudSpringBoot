package com.powerconsuption.com.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
	@Id
	 @Type(type = "uuid-char")
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
