package Baiwa.TestAPI.project.repo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Baiwa.TestAPI.framework.utils.CommonJdbcTemplate;
import Baiwa.TestAPI.project.model.ShrimpModel;


@Repository
public class ShrimpDao {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<ShrimpModel> getFileCheck(String month,String year) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("select * from treasury_upload_ztp_detail aa where 1 = 1 ");
		if(StringUtils.isNotEmpty(month)) {
			sqlBuilder.append(" and month(post_date) = '"+month+"'");
		}
		if(StringUtils.isNotEmpty(year)) {
			sqlBuilder.append(" and year(post_date) = '"+year+"'");
		}
		List<ShrimpModel> dataRes = commonJdbcTemplate.executeQuery(sqlBuilder.toString(),params.toArray(),
				BeanPropertyRowMapper.newInstance(ShrimpModel.class));

		return dataRes;
	}
}
