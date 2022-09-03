package Baiwa.TestAPI.project.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "shrimp_table")
public class ShrimpModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2604481543472597698L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "list_shrimp")
	private Long listShrimp;
	@Column(name = "number_shrimp")
	private String numberShrimp;
	@Column(name = "date_start")
	private Date dateStart;
	@Column(name = "date_end")
	private Date dateEnd;
	@Column(name = "type_shrimp")
	private String typeShrimp;
	@Column(name = "unit_shrimp")
	private double unitShrimp;
	@Column(name = "price_shrimp")
	private double priceShrimp;
	@Column(name = "type_shrimp_feed")
	private String typeShrimpFeed;
	@Column(name = "unit_shrimp_feed")
	private double unitShrimpFeed;
	@Column(name = "price_shrimp_feed")
	private double priceShrimpFeed;
	@Column(name = "wage")
	private double wage;
	@Column(name = "other_price")
	private double otherPrice;
	@Column(name = "discription")
	private String discription;
	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "update_date")
	private Date updateDate;
	@Column(name = "update_by")
	private String updateBy;
	@Column(name = "file_name")
	private String fileName;

}
