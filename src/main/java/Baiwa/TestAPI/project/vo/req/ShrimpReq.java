package Baiwa.TestAPI.project.vo.req;

import java.util.Date;

import lombok.Data;

@Data
public class ShrimpReq {

	private Long listShrimp;
	private String numberShrimp;
	private Date dateStart;
	private Date dateEnd;
	private String typeShrimp;
	private double unitShrimp;
	private double priceShrimp;
	private String typeShrimpFeed;
	private double unitShrimpFeed;
	private double priceShrimpFeed;
	private double wage;
	private double otherPrice;
	private String discription;
	private Date createDate;
	private String createBy;
	private Date updateDate;
	private String updateBy;
}
