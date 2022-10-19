package com.designknot.salessearch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designknot.salessearch.datasource.MsListDatasource;
import com.designknot.salessearch.entity.MsMst;
import com.designknot.salessearch.service.MsListService;

@Service
public class MsListServiceImpl implements MsListService {
	@Autowired
	private MsListDatasource msListDatasource;

	// 店舗一覧を表示する
	@Override
	public List<MsMst> findAll(){
		List<MsMst> msList = msListDatasource.findAll();
		return msList;
	}

	// 追加店舗を登録する
	@Override
	public void saveMs(String ms_cd,String ms_name) {
		MsMst msmst = new MsMst(ms_cd,ms_name,"0");
		msListDatasource.saveMs(msmst);
	}

	// 店コードから店名を探す
	@Override
	public MsMst findByMscd(String ms_cd) {
		MsMst msmst = msListDatasource.findByMscd(ms_cd);
		return msmst;
	}

	// 店舗名称を更新する
	@Override
	public void updateMs(String ms_cd, String ms_name) {
		msListDatasource.updateMs(ms_cd, ms_name);
	}

	// 店舗を削除する
	@Override
	public void deleteMs(String ms_cd) {
		msListDatasource.deleteMs(ms_cd);
	}

	// 削除店舗一覧を表示する
	@Override
	public List<MsMst> findMsDel(){
		List<MsMst> findMsDel= msListDatasource.findMsDel();
		return findMsDel;
	}

	// 店舗を復活させる
	@Override
	public void rbnMs(String ms_cd) {
		msListDatasource.rbnMs(ms_cd);
	}

}
