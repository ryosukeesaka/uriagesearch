package com.designknot.salessearch.datasource;

import java.util.List;

import com.designknot.salessearch.entity.ItemMst;
import com.designknot.salessearch.entity.Uriage;

public interface UriageDatasource {

	void uriageEntry(String ms_cd, String item_cd, String uriage, String uriage_date);

	List<Uriage> findDate();

	List<Uriage> findAll(String uriage_date,String ms_name,String item_name);

	List<ItemMst> itemAll();
}
