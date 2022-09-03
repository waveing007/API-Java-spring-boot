package Baiwa.TestAPI.framework.security.vo;

import lombok.Data;

@Data
public class JwtResponse {
	private final String jwttoken;
	private final String username;
}
