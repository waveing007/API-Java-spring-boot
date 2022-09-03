package Baiwa.TestAPI.framework.security.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Baiwa.TestAPI.framework.security.vo.JwtResponse;
import Baiwa.TestAPI.project.user.model.FwUser;
import Baiwa.TestAPI.project.user.repository.jpa.FwUserRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private FwUserRepo fwUserRepo;

//	@Autowired
//	private RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		FwUser user = fwUserRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public void addNewUser() {

	}

	public JwtResponse setResponse(String token, String username) {
		JwtResponse res = new JwtResponse(token, username);
//		try {
//			FwUsers user = fwUserRepoLogin.findByUsername(username);
//			GetRoleRes role = roleService.getRoleByRoleCode(user.getRoleCode());
//			res.setRole(role);
//		} catch (Exception e) {
//			log.error("Login::JwtUserDetailsService::setResponse", e.getMessage());
//		}
		return res;
	}

}
