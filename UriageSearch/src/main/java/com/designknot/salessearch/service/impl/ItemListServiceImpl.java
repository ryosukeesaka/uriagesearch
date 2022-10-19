package com.designknot.salessearch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designknot.salessearch.datasource.ItemListDatasource;
import com.designknot.salessearch.entity.ItemMst;
import com.designknot.salessearch.service.ItemListService;

@Service
public class ItemListServiceImpl implements ItemListService {

	@Autowired
	private ItemListDatasource itemListDatasource;

	// 商品一覧を表示する
	@Override
	public List<ItemMst> findAll(){
		List<ItemMst> itemList = itemListDatasource.findAll();
		return itemList;
	}

	// 追加商品を登録する
	@Override
	public void saveItem(String item_cd,String item_name) {
		ItemMst itemMst = new ItemMst(item_cd,item_name,"0");
		itemListDatasource.saveItem(itemMst);
	}

	// 商品コードから商品名を探す
	@Override
	public ItemMst findByItemcd(String item_cd) {
		ItemMst itemmst = itemListDatasource.findByItemcd(item_cd);
		return itemmst;
	}



	// 商品名称を更新する
	@Override
	public void updateItem(String item_cd, String item_name) {
		itemListDatasource.updateItem(item_cd,item_name);
	}

	// 商品を削除する
	@Override
	public void deleteItem(String item_cd) {
		itemListDatasource.deleteItem(item_cd);
	}

	// 削除商品一覧を表示する
	@Override
	public List<ItemMst> findItemDel(){
		List<ItemMst> findItemDel= itemListDatasource.findItemDel();
		return findItemDel;
	}

	// 商品を復活させる
	@Override
	public void rbnItem(String item_cd) {
		itemListDatasource.rbnItem(item_cd);
	}
}
