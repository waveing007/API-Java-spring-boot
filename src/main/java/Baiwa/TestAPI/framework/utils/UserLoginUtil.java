package Baiwa.TestAPI.framework.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import Baiwa.TestAPI.framework.model.UserDetailModel;


public class UserLoginUtil {

//	@Autowired
//	public UserLoginUtil(EmployeeDao usersRepo) {
//		UserLoginUtil.employeeDao = usersRepo;
//	}

	public static UserDetailModel getCurrentUserBean() {
		UserDetailModel userBean = null;

		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = "";
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			}
			userBean = new UserDetailModel(username);
		} else {
			String username = "NO LOGIN";
			userBean = new UserDetailModel(username);
		}
		return userBean;
	}

	public static String getUsername() {

		return UserLoginUtil.getCurrentUserBean().getUsername();
	}

//	public static Employee getEmployee() { // can't user now.
//		if (StringUtils.isNotBlank(UserLoginUtil.getUsername())) {
//			System.out.println("___________________" + UserLoginUtil.getUsername() + "_____________________");
//			Employee fwusers = new Employee();
//			fwusers = employeeDao.getByUsername();
//			return fwusers;
//		} else {
//			return null;
//		}
////		return UserLoginUtil.getCurrentUserBean().getUsername();
//	}
}
