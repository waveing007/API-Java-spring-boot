package Baiwa.TestAPI.framework.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Baiwa.TestAPI.framework.security.vo.UserDetailResponse;
import Baiwa.TestAPI.project.user.model.FwUser;
import Baiwa.TestAPI.project.user.repository.jpa.FwUserRepo;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserDataService {

	@Autowired
	private FwUserRepo fwUserRepoLogin;

//	@Autowired
//	private RoleService roleService;

	public UserDetailResponse setResponse(String username) {
		UserDetailResponse res = new UserDetailResponse(username);
		try {
			FwUser user = fwUserRepoLogin.findByUsername(username);
//			GetRoleRes role = roleService.getRoleByRoleCode(user.getRoleCode());
//			res.setRole(role);
		} catch (Exception e) {
			log.error("Login::JwtUserDetailsService::setResponse", e.getMessage());
		}
		return res;
	}
}
