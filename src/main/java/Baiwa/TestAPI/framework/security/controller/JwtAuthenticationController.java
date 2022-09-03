package Baiwa.TestAPI.framework.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Baiwa.TestAPI.constant.ResponseConstant.RESPONSE_MESSAGE;
import Baiwa.TestAPI.constant.ResponseConstant.RESPONSE_STATUS;
import Baiwa.TestAPI.framework.model.ResponseData;
import Baiwa.TestAPI.framework.security.authorization.JwtTokenUtil;
import Baiwa.TestAPI.framework.security.service.JwtUserDetailsService;
import Baiwa.TestAPI.framework.security.vo.JwtRequest;
import Baiwa.TestAPI.framework.security.vo.JwtResponse;
import Baiwa.TestAPI.project.user.service.FwUserService;
import Baiwa.TestAPI.project.user.vo.req.FwUserReq;



@RestController
@CrossOrigin
@RequestMapping(value = "token/")
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	FwUserService fwUsersService;

	@PostMapping("authenticate")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		JwtResponse res = userDetailsService.setResponse(token, authenticationRequest.getUsername());
		return ResponseEntity.ok(res);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@PostMapping("register")
	@ResponseBody
	public ResponseEntity<?> saveUser(@RequestBody FwUserReq req) throws Exception {
		ResponseData<?> response = new ResponseData<>();
		try {
			fwUsersService.save(req);
			response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return ResponseEntity.ok(response);
	}
}
