package com.designknot.salessearch.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.designknot.salessearch.entity.UriageMst;

@Mapper
public interface UriageRepository {

    /** 売上登録 */
    @Insert ("INSERT INTO "
            + "MS_URIAGE_ITEM "
            + "values "
            + "(#{ms_cd},#{item_cd},#{uriage},#{uriage_date}) "
            )

    boolean postSave(String ms_cd, String item_cd, String uriage, String uriage_date);

    /** 商品マスタ全件取得 */
    @Select("SELECT"
            + " item_cd "
            + ",item_name "
            + ",del_flg "
            + " from "
            + " item_mst "
            + " where "
            + " item_cd = #{item_cd} "
            + " and del_flg = '0 ' "
            + " union all "
            + " select "
            + " item_cd as cd"
            + ",item_name "
            + ",del_flg "
            + "FROM"
            + " ITEM_MST "
            + "WHERE "
            + " del_flg = '0' "
            //+ " order by "
            //+ "  item_mst.cd "
            //+ " asc "
            )
    @Results(id = "ItemMst", value = {
            @Result(column = "item_cd", property = "item_cd"),
            @Result(column = "item_name", property = "item_name"),
            @Result(column = "del_flag", property = "del_flag")})
    List<UriageMst> findItem(String itemcd);



    @Select("SELECT"
            + " item_cd "
            + ",item_name "
            + ",del_flg "
            + "FROM"
            + " ITEM_MST "
            + "WHERE "
            + " del_flg = '0' "
            + " order by "
            + "  item_mst.item_cd "
            + " asc ")
    @Results(id = "Item", value = {
            @Result(column = "item_cd", property = "item_cd"),
            @Result(column = "item_name", property = "item_name"),
            @Result(column = "del_flag", property = "del_flag")})
    List<UriageMst> findAll();



   /* 店マスタ全件取得
    @Select("SELECT"
            + " ms_cd "
            + ",ms_name "
            + "FROM"
            + " MS_MST "
            + " where "
            + " ms_cd = #{ms_cd} "
            + "union "
            + " select "
            + " ms_cd "
            + " ,ms_name "
            + ",del_flg "
           // + "WHERE "
           // + " del_flg = '0' "
            + " order by "
            + "  ms_mst.ms_cd "
            + " asc ")
    @Results(id = "UriageMst", value = {
            @Result(column = "ms_cd", property = "ms_cd"),
            @Result(column = "ms_name", property = "ms_name"),
            @Result(column = "del_flg", property = "del_flg")})
    List<UriageMst> findMs();*/





}
