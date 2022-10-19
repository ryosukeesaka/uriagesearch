package com.designknot.salessearch.service;

import java.util.List;

import com.designknot.salessearch.entity.MsMst;

public interface MsListService {

	void saveMs(String ms_cd,String ms_name);

	void updateMs(String ms_cd,String ms_name);

	void deleteMs(String ms_cd);

	MsMst findByMscd(String ms_cd);

	List<MsMst> findAll();

	List<MsMst> findMsDel();

	void rbnMs(String ms_cd);
}
