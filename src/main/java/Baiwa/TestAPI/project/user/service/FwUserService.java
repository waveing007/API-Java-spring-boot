package Baiwa.TestAPI.project.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Baiwa.TestAPI.framework.model.DataTableResponse;
import Baiwa.TestAPI.framework.model.DatatableRequest;
import Baiwa.TestAPI.project.user.model.FwUser;
import Baiwa.TestAPI.project.user.repository.dao.FwUserDao;
import Baiwa.TestAPI.project.user.repository.jpa.FwUserRepo;
import Baiwa.TestAPI.project.user.vo.req.FwUserReq;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FwUserService {
	@Autowired
	FwUserRepo fwUserRepo;
	
	@Autowired
	FwUserDao fwUserDao;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public void save(FwUserReq req) {
		FwUser data = new FwUser();
		
		data.setUsername(req.getUsername());
		data.setPassword(bcryptEncoder.encode(req.getPassword().trim()));
		data.setName(req.getName());
		data.setEmail(req.getEmail());
		data.setPhone(req.getPhone());
		data.setRole(req.getRole());
		data.setCreatedBy(req.getCreatedBy());
		data.setCreatedDate(new Date());
		data.setUpdatedBy(req.getUpdatedBy());
		data.setUpdatedDate(req.getUpdatedDate());
		
		fwUserRepo.save(data);
	}
	
	public void delete(Long id) {
		fwUserRepo.deleteById(id);
	}
	
	public List<FwUser> getDetail(){
		List<FwUser> data = (List<FwUser>) fwUserRepo.findAll();
		
		return data;
	}
	
	public DataTableResponse<FwUser> paginateList(DatatableRequest req) throws Exception{
		DataTableResponse<FwUser> paginateData = fwUserDao.paginate(req);
		DataTableResponse<FwUser> dataTable = new DataTableResponse<>();
		List<FwUser> data = paginateData.getData();
        dataTable.setRecordsTotal(paginateData.getRecordsTotal());
        dataTable.setDraw(paginateData.getDraw());
        dataTable.setData(data);
        dataTable.setPage(req.getPage());
		return paginateData;
	}
	
	}
