package com.designknot.salessearch.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.designknot.salessearch.entity.ItemMst;

@Mapper
public interface ItemRepository {

    /** 商品マスタ全件取得 */
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
    @Results(id = "Mst", value = {
            @Result(column = "item_cd", property = "item_cd"),
            @Result(column = "item_name", property = "item_name"),
            @Result(column = "del_flag", property = "del_flag")})
    List<ItemMst> findItem();

    //商品追加
    @Select("INSERT INTO "
            + "ITEM_MST "
            + "values"
            + "(#{item_cd},#{item_name},#{del_flg})"
            )

    ItemMst itemAdd(ItemMst itemmst);

    //商品更新
    @Select("UPDATE "
            + "ITEM_MST "
            + "SET "
            + "item_name = #{item_name} "
            + "WHERE "
            + "item_cd = #{item_cd}"
            )
    ItemMst itemUpd(ItemMst itemmst);

    //商品削除
    @Update("UPDATE "
            + "ITEM_MST "
            + "SET "
            + "del_flg = '1' "
            + "WHERE "
            + "item_cd = #{itemcd}")

    boolean updByCd(String itemcd);

}
