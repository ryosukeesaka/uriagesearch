package com.designknot.salessearch.datasource.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.designknot.salessearch.datasource.MsListDatasource;
import com.designknot.salessearch.entity.MsMst;
import com.designknot.salessearch.repository.MsListRepository;

@Component
public class MsListDatasourceImpl implements MsListDatasource {

	@Autowired
	private MsListRepository msListRepository;

	// 店舗一覧を表示する
	@Override
	public List<MsMst> findAll(){
		List<MsMst> msMst= msListRepository.findAll();
		return msMst;
	}

	// 店舗を追加登録する
	@Override
	public void saveMs(MsMst msmst) {
		msListRepository.insertMs(msmst);
	}

	// 店舗名称を更新する
	@Override
	public void updateMs(String ms_cd,String ms_name) {
		msListRepository.updateMs(ms_cd,ms_name);
	}

	// 店コードから店名を探す
	@Override
	public MsMst findByMscd(String ms_cd) {
		MsMst msmst = msListRepository.findByMscd(ms_cd);
		return msmst;
	}

	// 店舗を削除する
	@Override
	public void deleteMs(String ms_cd) {
		msListRepository.deleteMs(ms_cd);
	}

	// 削除店舗一覧を表示する
	@Override
	public List<MsMst> findMsDel(){
		List<MsMst> findMsDel= msListRepository.findMsDel();
		return findMsDel;
	}

	// 店舗を復活させる
	@Override
	public void rbnMs(String ms_cd) {
		msListRepository.rbnMs(ms_cd);
	}


}
