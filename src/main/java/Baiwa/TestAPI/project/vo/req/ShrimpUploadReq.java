package Baiwa.TestAPI.project.vo.req;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class ShrimpUploadReq {

	private MultipartFile fileUpload;
	private String fileName;
	
}
