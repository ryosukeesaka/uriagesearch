package com.designknot.salessearch.service;

import java.time.LocalDate;
import java.util.List;

import com.designknot.salessearch.entity.ItemMst;
import com.designknot.salessearch.entity.Uriage;

public interface UriageService {

	void uriageEntry(String ms_cd, String item_cd, String uriage, LocalDate uriage_date);


	List<Uriage> findDate();

	List<ItemMst> itemAll();

	List<Uriage> findAll(String uriage_date,String ms_name,String item_name);
}
