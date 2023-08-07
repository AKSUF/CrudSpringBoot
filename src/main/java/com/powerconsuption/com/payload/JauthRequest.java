package com.powerconsuption.com.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JauthRequest {
	private String login_id;
	private String password;
}
