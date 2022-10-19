package com.designknot.salessearch.datasource.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.designknot.salessearch.datasource.ItemListDatasource;
import com.designknot.salessearch.entity.ItemMst;
import com.designknot.salessearch.repository.ItemListRepository;

@Component
public class ItemListDatasourceImpl implements ItemListDatasource{

	@Autowired
	private ItemListRepository itemListRepository;

	// 商品一覧を表示する
	@Override
	public List<ItemMst> findAll(){
		List<ItemMst> itemList = itemListRepository.findAll();
		return itemList;
	}

	// 商品を追加登録する
	@Override
	public void saveItem(ItemMst itemMst) {
		itemListRepository.insertItem(itemMst);
	}

	//商品コードから商品名を抽出する
	public ItemMst findByItemcd(String item_cd) {
		ItemMst itemmst = itemListRepository.findByItemcd(item_cd);
		return itemmst;
	}

	// 商品名称を更新する
	@Override
	public void updateItem(String item_cd, String item_name) {
		itemListRepository.updateItem(item_cd, item_name);
	}

	// 商品を削除する
	@Override
	public void deleteItem(String item_cd) {
		itemListRepository.deleteItem(item_cd);
	}


	// 削除商品一覧を表示する
	@Override
	public List<ItemMst> findItemDel(){
		List<ItemMst> findItemDel= itemListRepository.findItemDel();
		return findItemDel;
	}

	// 商品を復活させる
	@Override
	public void rbnItem(String item_cd) {
		itemListRepository.rbnItem(item_cd);
	}
}
