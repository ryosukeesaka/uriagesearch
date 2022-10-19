package com.designknot.salessearch.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designknot.salessearch.datasource.UriageDatasource;
import com.designknot.salessearch.entity.ItemMst;
import com.designknot.salessearch.entity.Uriage;
import com.designknot.salessearch.service.UriageService;

@Service
public class UriageServiceImpl implements UriageService {

	@Autowired
private UriageDatasource uriageDatasource;

	// 売上年月を抽出する
	@Override
	public 	List<Uriage> findDate(){
		List<Uriage> uriageDate = uriageDatasource.findDate();
		return uriageDate;

	}

	@Override
	public 	List<ItemMst> itemAll(){
		List<ItemMst> itemList = uriageDatasource.itemAll();
		return itemList;

	}
	// 検索結果を抽出する
	@Override
	public List<Uriage> findAll(String uriage_date,String ms_name,String item_name){
		List<Uriage> uriage = uriageDatasource.findAll(uriage_date+"%",ms_name,item_name);
		return uriage;
	}

	// 売上を登録する
	@Override
	public void uriageEntry(String ms_cd, String item_cd, String uriage,LocalDate uriage_date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String uriage_dateFormat = uriage_date.format(formatter);
		uriageDatasource.uriageEntry(ms_cd, item_cd, uriage, uriage_dateFormat);
	}


}
