package Baiwa.TestAPI.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Baiwa.TestAPI.constant.ResponseConstant.RESPONSE_MESSAGE;
import Baiwa.TestAPI.constant.ResponseConstant.RESPONSE_STATUS;
import Baiwa.TestAPI.framework.model.ResponseData;
import Baiwa.TestAPI.project.model.ShrimpModel;
import Baiwa.TestAPI.project.service.ShrimpService;
import Baiwa.TestAPI.project.vo.req.ShrimpReq;
import Baiwa.TestAPI.project.vo.req.ShrimpUploadReq;

@RestController
@RequestMapping("api/shrimp")
public class ShrimpController {

	@Autowired
	private ShrimpService shrimpService;

	@PostMapping("/save-shrimp")
	@ResponseBody
	public ResponseData<List<ShrimpModel>> save(@RequestBody ShrimpReq req) {
		ResponseData<List<ShrimpModel>> response = new ResponseData<List<ShrimpModel>>();
		try {
			shrimpService.saveShrimp(req);
			response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@GetMapping("/get-all-shrimp")
	public ResponseData<List<ShrimpModel>> getAllShrimp() {
		ResponseData<List<ShrimpModel>> response = new ResponseData<List<ShrimpModel>>();
		try {
			response.setData(shrimpService.getAll());
			response.setMessage(RESPONSE_MESSAGE.GET.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.GET.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/save-excel")
	private ResponseData<List<ShrimpModel>> importExcel(@ModelAttribute ShrimpUploadReq req) {
		ResponseData<List<ShrimpModel>> responseData = new ResponseData<List<ShrimpModel>>();
		try {
			responseData.setData(shrimpService.importExcel(req));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
			e.printStackTrace();
		}
		return responseData;
	}

}
