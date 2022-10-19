package com.designknot.salessearch.datasource;

import java.util.List;

import com.designknot.salessearch.entity.ItemMst;

public interface ItemListDatasource {

	List<ItemMst> findAll();

	void saveItem(ItemMst itemMst);

	ItemMst findByItemcd(String item_cd);

	void updateItem(String item_cd, String item_name);

	void deleteItem(String item_cd);

	List<ItemMst> findItemDel();

	void rbnItem(String item_cd);
}
