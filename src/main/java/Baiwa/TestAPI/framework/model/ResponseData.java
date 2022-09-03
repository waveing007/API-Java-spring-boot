package Baiwa.TestAPI.framework.model;


import Baiwa.TestAPI.constant.ResponseConstant.RESPONSE_STATUS;
import lombok.Data;

@Data
public class ResponseData<T> {
	private RESPONSE_STATUS status = RESPONSE_STATUS.FAILED;
	private String message;
	private T data;
}

