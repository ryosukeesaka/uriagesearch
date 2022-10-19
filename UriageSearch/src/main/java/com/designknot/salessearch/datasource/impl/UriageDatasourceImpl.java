package com.designknot.salessearch.datasource.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.designknot.salessearch.datasource.UriageDatasource;
import com.designknot.salessearch.entity.ItemMst;
import com.designknot.salessearch.entity.Uriage;
import com.designknot.salessearch.repository.UriageRepository;

@Component
public class UriageDatasourceImpl implements UriageDatasource{

	@Autowired
	private UriageRepository uriageRepository;

	// 売上年月を抽出する
	@Override
	public 	List<Uriage> findDate(){
		List<Uriage> uriageDate = uriageRepository.findDate();
		return uriageDate;
	}

	// 商品一覧を抽出する
	@Override
	public List<ItemMst> itemAll(){
		List<ItemMst> itemList = uriageRepository.itemAll();
		return itemList;
	}

	// 検索結果を抽出する
	@Override
	public List<Uriage> findAll(String uriage_date, String ms_name,String item_name){
		List<Uriage> uriage = uriageRepository.findAll(uriage_date,ms_name,item_name);
		return uriage;
	}


	// 売上を登録する
	@Override
	public void uriageEntry(String ms_cd, String item_cd, String uriage, String uriage_date) {
		uriageRepository.uriageEntry(ms_cd, item_cd, uriage, uriage_date);
	}


}
