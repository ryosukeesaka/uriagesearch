package com.designknot.salessearch.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.designknot.salessearch.entity.MsMst;

@Mapper
public interface MsListRepository {
	// 店舗一覧を抽出する
	@Select("select ms_cd, ms_name, del_flg from MS_MST where del_flg = '0'")
	@Results(id="MsMst", value = {
		@Result(column = "ms_cd", property="ms_cd"),
		@Result(column = "ms_name", property="ms_name"),
		@Result(column = "del_flg", property="del_flg"),
	})
	List<MsMst> findAll();

	// 追加店舗を登録する
	@Insert("insert into MS_MST values(#{ms_cd}, #{ms_name}, #{del_flg})")
	@Results(id="save", value = {
		@Result(column = "ms_cd", property="ms_cd"),
		@Result(column = "ms_name", property="ms_name"),
		@Result(column = "del_flg", property="del_flg"),
	})
	void insertMs(MsMst msmst);


	// 店コードから店名を探す
	@Select("select ms_cd, ms_name, del_flg from MS_MST where ms_cd = #{ms_cd}")
	@Results(id="findms", value = {
		@Result(column = "ms_cd", property="ms_cd"),
		@Result(column = "ms_name", property="ms_name"),
		@Result(column = "del_flg", property="del_flg"),
	})
	MsMst findByMscd(String ms_cd);

	// 店舗名称を更新する
	@Update("update MS_MST set ms_name = #{ms_name} where del_flg = '0' and ms_cd = #{ms_cd}")
	@Results(id="upd", value = {
		@Result(column = "ms_cd", property="ms_cd"),
		@Result(column = "ms_name", property="ms_name"),
		@Result(column = "del_flg", property="del_flg"),
	})
	void updateMs(String ms_cd,String ms_name);

	// 店舗を削除する
	@Update("update MS_MST set del_flg = '1' where del_flg = '0' and ms_cd = #{ms_cd}")
	@Results(id="del", value = {
		@Result(column = "ms_cd", property="ms_cd"),
		@Result(column = "ms_name", property="ms_name"),
		@Result(column = "del_flg", property="del_flg"),
	})
	void deleteMs(String ms_cd);

	// 削除済み店舗一覧を抽出する
	@Select("select ms_cd, ms_name, del_flg from MS_MST where del_flg = '1'")
	@Results(id="MsDel", value = {
		@Result(column = "ms_cd", property="ms_cd"),
		@Result(column = "ms_name", property="ms_name"),
		@Result(column = "del_flg", property="del_flg"),
	})
	List<MsMst> findMsDel();

	// 店舗を復活させる
	@Update("update MS_MST set del_flg = '0' where del_flg = '1' and ms_cd = #{ms_cd}")
	@Results(id="rbn", value = {
		@Result(column = "ms_cd", property="ms_cd"),
		@Result(column = "ms_name", property="ms_name"),
		@Result(column = "del_flg", property="del_flg"),
	})
	void rbnMs(String ms_cd);
}
