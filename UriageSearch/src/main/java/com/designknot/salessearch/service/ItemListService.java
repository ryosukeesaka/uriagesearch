package com.designknot.salessearch.service;

import java.util.List;

import com.designknot.salessearch.entity.ItemMst;

public interface ItemListService {

	List<ItemMst> findAll();

	void saveItem(String item_cd, String item_name);

	void updateItem(String item_cd, String item_name);

	ItemMst findByItemcd(String item_cd);

	void deleteItem(String item_cd);

	List<ItemMst> findItemDel();

	void rbnItem(String item_cd);
}
