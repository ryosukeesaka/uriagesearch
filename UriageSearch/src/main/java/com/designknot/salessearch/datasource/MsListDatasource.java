package com.designknot.salessearch.datasource;

import java.util.List;

import com.designknot.salessearch.entity.MsMst;

public interface MsListDatasource {

	List<MsMst> findAll();

	void saveMs(MsMst msmst);

	void updateMs(String ms_cd,String ms_name);

	void deleteMs(String ms_cd);

	MsMst findByMscd(String ms_cd);

	List<MsMst> findMsDel();

	void rbnMs(String ms_cd);

}
