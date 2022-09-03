package Baiwa.TestAPI.project.user.repository.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import Baiwa.TestAPI.framework.model.DataTableResponse;
import Baiwa.TestAPI.framework.model.DatatableRequest;
import Baiwa.TestAPI.framework.utils.CommonJdbcTemplate;
import Baiwa.TestAPI.framework.utils.DatatableUtils;
import Baiwa.TestAPI.project.user.model.FwUser;

@Repository
public class FwUserDao {
	  @Autowired
	    private CommonJdbcTemplate commonJdbcTemplate;
	    
	    public DataTableResponse<FwUser> paginate(DatatableRequest req) {
	        DataTableResponse<FwUser> dataTable = new DataTableResponse<FwUser>();
	        
	        StringBuilder stringBuilder = new StringBuilder();
			String select = "*";
			String from = " fw_user";
			
			stringBuilder.append("select " + select + " from " + from);
	        
			String sqlCount = DatatableUtils.customQueryWithPageCount(from.toString(), req.getFilter());
			String sqlData = DatatableUtils.customQueryWithPage(stringBuilder.toString(), req.getPage(), req.getLength(),
					req.getFilter(), null, null, req.getSort());
			
	        List<Object> params = new ArrayList<>();
	        Integer count = commonJdbcTemplate.executeQueryForObject(sqlCount, params.toArray(), Integer.class);
	        List<FwUser> data = commonJdbcTemplate.executeQuery(sqlData, params.toArray(),
	                BeanPropertyRowMapper.newInstance(FwUser.class));
	        dataTable.setData(data);
	        dataTable.setRecordsTotal(count);
	        return dataTable;
	    }
}
