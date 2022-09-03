package Baiwa.TestAPI.project.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Baiwa.TestAPI.framework.utils.ExcelUtils;
import Baiwa.TestAPI.project.model.ShrimpModel;
import Baiwa.TestAPI.project.repo.dao.ShrimpDao;
import Baiwa.TestAPI.project.repo.jpa.ShrimpRepository;
import Baiwa.TestAPI.project.vo.req.ShrimpReq;
import Baiwa.TestAPI.project.vo.req.ShrimpUploadReq;

@Service
public class ShrimpService {

	@Autowired
	private ShrimpRepository shrimpRepository;
	@Autowired
	private ShrimpDao shrimpDao;

	public ShrimpModel saveShrimp(ShrimpReq req) {
		ShrimpModel res = new ShrimpModel();
		res.setListShrimp(req.getListShrimp());
		res.setNumberShrimp(req.getNumberShrimp());
		res.setDateStart(req.getDateStart());
		res.setDateEnd(req.getDateEnd());
		res.setTypeShrimp(req.getTypeShrimp());
		res.setUnitShrimp(req.getUnitShrimp());
		res.setPriceShrimp(req.getPriceShrimp());
		res.setTypeShrimpFeed(req.getTypeShrimpFeed());
		res.setUnitShrimpFeed(req.getUnitShrimpFeed());
		res.setPriceShrimpFeed(res.getUnitShrimpFeed());
		res.setWage(req.getWage());
		res.setOtherPrice(req.getOtherPrice());
		res.setDiscription(req.getDiscription());
		res.setCreateBy(req.getCreateBy());
		res.setCreateDate(new Date());

		return shrimpRepository.save(res);
	}

	public List<ShrimpModel> getAll() {
		return shrimpRepository.findAll();
	}

	public List<ShrimpModel> importExcel(ShrimpUploadReq req) {
//		OtherDepositHdr header = new OtherDepositHdr();
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		List<String> dataSend = new ArrayList<String>();
		List<String> monthSend = new ArrayList<String>();
		List<ShrimpModel> entities = null;
//		boolean status;
		List<List<String>> allLine = null;
		try {
			allLine = ExcelUtils.readExcel(req.getFileUpload());
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		if (allLine.size() == 0) {

		} else {
			entities = new ArrayList<ShrimpModel>();
//			if(StringUtils.isNotBlank(String.valueOf(header.getId()))) {
			MultipartFile filePart = req.getFileUpload();
			try {
				@SuppressWarnings("resource")
				XSSFWorkbook wb = new XSSFWorkbook(filePart.getInputStream());
				XSSFSheet sheet = wb.getSheetAt(0);
				ShrimpModel vo = null;
				for (Row row : sheet) {
					if (row.getRowNum() >= 0) {
						if (StringUtils.isBlank(String.valueOf(ExcelUtils.getCellValueAsString(row.getCell(0))))
								|| StringUtils.isBlank(ExcelUtils.getCellValueAsString(row.getCell(1)))) {
							continue;
						}
						vo = new ShrimpModel();
						vo.setListShrimp(Long.parseLong((ExcelUtils.getCellValueAsString(row.getCell(0)))));
						vo.setNumberShrimp(ExcelUtils.getCellValueAsString(row.getCell(1)));
						vo.setDateStart(format1.parse(ExcelUtils.getCellValueAsString(row.getCell(2))));
						vo.setDateEnd((format1.parse(ExcelUtils.getCellValueAsString(row.getCell(3)))));
						vo.setTypeShrimp(ExcelUtils.getCellValueAsString(row.getCell(4)));
						vo.setUnitShrimp(Integer.parseInt(ExcelUtils.getCellValueAsString(row.getCell(5))));
						vo.setPriceShrimp(Integer.parseInt(ExcelUtils.getCellValueAsString(row.getCell(6))));
						vo.setTypeShrimpFeed(ExcelUtils.getCellValueAsString(row.getCell(7)));
						vo.setUnitShrimpFeed(Integer.parseInt(ExcelUtils.getCellValueAsString(row.getCell(8))));
						vo.setPriceShrimpFeed(Integer.parseInt(ExcelUtils.getCellValueAsString(row.getCell(9))));
						vo.setWage(Integer.parseInt(ExcelUtils.getCellValueAsString(row.getCell(10))));
						vo.setOtherPrice(Integer.parseInt(ExcelUtils.getCellValueAsString(row.getCell(11))));
						vo.setDiscription(ExcelUtils.getCellValueAsString(row.getCell(12)));
						vo.setCreateDate(new Date());
						vo.setFileName(req.getFileName());
						entities.add(vo);

						if (entities != null) {
							shrimpRepository.saveAll(entities);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}
		return entities;
	}

	public void checkUpdateOrSaveData(List<ShrimpModel> data) {
		boolean isAdd = false;
		boolean isNew = false;
		int countNew = 0;
		DateFormat yy = new SimpleDateFormat("yyyy");
		String year = yy.format(data.get(0).getDateStart());
		DateFormat mm = new SimpleDateFormat("MM");
		String month = mm.format(data.get(0).getDateStart());
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		List<ShrimpModel> check = shrimpDao.getFileCheck(month, year);
		List<ShrimpModel> listdatasave = new ArrayList<ShrimpModel>();
		for (int i = 0; i < data.size(); i++) {
			isAdd = true;
			for (int j = 0; j < check.size(); j++) {
				if (data.get(i).getNumberShrimp().equals(check.get(j).getNumberShrimp())
						&& format.format(data.get(i).getDateStart()).equals(format.format(check.get(j).getDateStart()))
						&& data.get(i).getListShrimp().equals(check.get(j).getListShrimp())
						&& data.get(i).getTypeShrimp().equals(check.get(j).getTypeShrimp())) {
					ShrimpModel dataSave = check.get(j);
								
								
						}

					}
//					listdatasave.add(dataSave);
					isAdd = false;
					break;
				}
			
//			if (isAdd) {
//				listdatasave.add(data.get(i));
//				isNew = true;
//				countNew++;
//
//			}
//		
//		fundIncomeService.countNewData(yyyymm.format(data.get(0).getPostDate()), data.get(0).getRef1(), "treasury",
//				countNew);
//		TreasuryBankdePositRepository.saveAll(listdatasave);
	}

}
