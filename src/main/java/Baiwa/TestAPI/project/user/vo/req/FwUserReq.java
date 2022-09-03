package Baiwa.TestAPI.project.user.vo.req;

import java.util.Date;
import lombok.Data;

@Data
public class FwUserReq {
	
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}
