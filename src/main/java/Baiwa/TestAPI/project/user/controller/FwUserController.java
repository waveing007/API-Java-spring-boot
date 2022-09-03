package Baiwa.TestAPI.project.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Baiwa.TestAPI.constant.ResponseConstant.RESPONSE_MESSAGE;
import Baiwa.TestAPI.constant.ResponseConstant.RESPONSE_STATUS;
import Baiwa.TestAPI.framework.model.ResponseData;
import Baiwa.TestAPI.project.user.service.FwUserService;
import Baiwa.TestAPI.project.user.vo.req.FwUserReq;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/user")
@Slf4j
public class FwUserController {
	@Autowired
	FwUserService fwUsersService;
	
	@PostMapping("/save")
	public ResponseData<?> save(@RequestBody FwUserReq req){
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
		return response;
	}
}
