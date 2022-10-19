package com.designknot.salessearch.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.designknot.salessearch.entity.ItemMst;

@Mapper
public interface ItemListRepository {

	// 商品一覧を抽出する
	@Select("select item_cd, item_name, del_flg from ITEM_MST where del_flg = '0'")
	@Results(id = "ItemMst", value = {
		@Result(column = "item_cd", property = "item_cd"),
		@Result(column = "item_name", property = "item_name"),
		@Result(column = "del_flg", property = "del_flg"),
	})
	List<ItemMst> findAll();

	// 追加商品を登録する
	@Insert("insert into ITEM_MST values(#{item_cd},#{item_name},#{del_flg})")
	@Results(id = "save", value = {
		@Result(column = "item_cd", property="item_cd"),
		@Result(column = "item_name", property="item_name"),
		@Result(column = "del_flg", property = "del_flg"),
	})
	void insertItem(ItemMst itemMst);

	// 商品コードから商品名称を探す
	@Select("select item_name from ITEM_MST where item_cd = #{item_cd}")
	@Results(id = "finditem", value = {
		@Result(column = "item_name", property = "item_name"),
	})
	ItemMst findByItemcd(String item_cd);


	// 商品名称を更新する
	@Update("update ITEM_MST set item_name = #{item_name} where del_flg = '0' and item_cd = #{item_cd}")
	@Results(id = "upd", value = {
		@Result(column = "item_cd", property = "item_cd"),
		@Result(column = "item_name", property = "item_name"),
		@Result(column = "del_flg", property = "del_flg"),
	})
	void updateItem(String item_cd, String item_name);

	// 商品を削除する
	@Update("update ITEM_MST set del_flg = '1' where item_cd = #{item_cd} and del_flg = '0'")
	@Results(id = "del" ,value = {
		@Result(column = "item_cd", property = "item_cd"),
		@Result(column = "item_name", property = "item_name"),
		@Result(column = "del_flg", property = "del_flg"),
		})
	void deleteItem(String item_cd);

	// 削除済み商品一覧を抽出する
	@Select("select item_cd, item_name, del_flg from ITEM_MST where del_flg = '1'")
	@Results(id="itemDel", value = {
		@Result(column = "item_cd", property="item_cd"),
		@Result(column = "item_name", property="item_name"),
		@Result(column = "del_flg", property="del_flg"),
	})
	List<ItemMst> findItemDel();

	// 商品を復活させる
	@Update("update ITEM_MST set del_flg = '0' where del_flg = '1' and item_cd = #{item_cd}")
	@Results(id="itemRbn", value = {
		@Result(column = "item_cd", property="item_cd"),
		@Result(column = "item_name", property="item_name"),
		@Result(column = "del_flg", property="del_flg"),
	})
	void rbnItem(String item_cd);
}
