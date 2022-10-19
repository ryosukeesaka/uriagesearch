package com.designknot.salessearch.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.designknot.salessearch.entity.SearchMst;

@Mapper
public interface SearchRepository {

    @Select("select "
            + "concat(substring(t1.uriage_date,1,4)  ,'/', + substring(t1.uriage_date,5,2) ,'/', + substring(t1.uriage_date,7,2)) as uriage_date "
            + ",t1.ms_cd "
            + ",t1.item_cd "
            + ",t2.item_name "
            + ",t3.ms_name "
            + " ,count(t2.item_name) as item_count "
            + ",sum(t1.uriage) as uriage "
            + "from "
            + "item_mst t2 "
            + "left outer join "
            + "ms_uriage_item t1 "
            + "on "
            + "(t2.item_cd = t1.item_cd) "
            + "left outer join "
            + "ms_mst t3 "
            + "on "
            + "(t1.ms_cd = t3.ms_cd) "
            + " where "
            + " t1.ms_cd = #{ms_cd} "
            + "group by "
            + "concat(substring(t1.uriage_date,1,4)  ,'/', + substring(t1.uriage_date,5,2) ,'/', + substring(t1.uriage_date,7,2)) "
            + ",t1.ms_cd "
            + ",t1.item_cd "
            + ",t2.item_name "
            + ",t3.ms_name "
            + "order by "
            + " concat(substring(t1.uriage_date,1,4)  ,'/', + substring(t1.uriage_date,5,2) ,'/', + substring(t1.uriage_date,7,2)) desc "
            + " ,t1.item_cd asc"
            )
    @Results(id = "Search", value = {
            @Result(column = "ms_cd", property = "ms_cd"),
            @Result(column = "item_cd", property = "item_cd"),
            @Result(column = "item_name", property = "item_name"),
            @Result(column = "ms_name", property = "ms_name"),
            @Result(column = "uriage", property = "uriage"),
            @Result(column = "item_count", property = "item_count"),
            @Result(column = "uriage_date", property = "uriage_date"),
            })
      List<SearchMst> search(String ms_cd);


    //売上検索
    @Select(" <script> "
            +"select "
            + " concat(substring(t1.uriage_date,1,4)  ,'/', + substring(t1.uriage_date,5,2) ,'/', + substring(t1.uriage_date,7,2)) as uriage_date"
            + " ,t1.item_cd "
            + ",t2.item_name "
            + " ,count(t2.item_name) as item_count "
            + ",sum(t1.uriage) as uriage "
            + "from "
            + "item_mst t2 "
            + "left outer join "
            + "ms_uriage_item t1 "
            + "on "
            + "(t2.item_cd = t1.item_cd) "
            + "left outer join "
            + "ms_mst t3 "
            + "on "
            + "(t1.ms_cd = t3.ms_cd) "
            + " where "
            + " t2.del_flg = '0' "
            + " and t1.ms_cd = #{ms_cd} "
            + " <if test=\"item_cd != null\"> "
            + "and t1.item_cd = #{item_cd} " //nullだった場合if文処理は実行されない　売り上げ機関における全商品検索に
            + " </if> "
            + " <if test=\"uriage_date != null\"> "
            + " and t1.uriage_date like #{uriage_date} "
            + " </if>"
            + "group by "
            + " concat(substring(t1.uriage_date,1,4)  ,'/', + substring(t1.uriage_date,5,2) ,'/', + substring(t1.uriage_date,7,2)) "
            + " ,t1.item_cd "
            + ",t2.item_name "
            + "order by "
            + " concat(substring(t1.uriage_date,1,4)  ,'/', + substring(t1.uriage_date,5,2) ,'/', + substring(t1.uriage_date,7,2)) desc "
            + " ,t1.item_cd asc"
            + " </script> "
            )
    @Results(id = "SearchMst", value = {
            @Result(column = "ms_cd", property = "ms_cd"),
            @Result(column = "item_cd", property = "item_cd"),
            @Result(column = "item_name", property = "item_name"),
            @Result(column = "ms_name", property = "ms_name"),
            @Result(column = "uriage", property = "uriage"),
            @Result(column = "item_count", property = "item_count"),
            @Result(column = "uriage_date", property = "uriage_date"),
            })
      List<SearchMst> searchUriage( String uriage_date,String item_cd,String ms_cd);

    //年月日リスト
    @Select("select "
            + "concat(substring(t1.uriage_date,1,4)  ,'/', + substring(t1.uriage_date,5,2)) as uriage_date "
            + "from "
            + "ms_uriage_item t1 "
            + "group by "
            + "concat(substring(t1.uriage_date,1,4)  ,'/', + substring(t1.uriage_date,5,2)) "
            + "order by "
            + "concat(substring(t1.uriage_date,1,4)  ,'/', + substring(t1.uriage_date,5,2)) "
            )
    @Results(id = "Date", value = {
            @Result(column = "uriage_date", property = "uriage_date"),
            })
      List<SearchMst> searchDate();

    /** 店マスタ全件取得 */
    @Select("SELECT"
            + " ms_cd "
            + ",ms_name "
            + ",del_flg "
            + "FROM"
            + " MS_MST "
            + "WHERE "
            + " ms_cd = #{ms_cd} "
            + " and del_flg = '0' "
            + " order by "
            + "  ms_mst.ms_cd "
            + " asc ")
    @Results(id = "MsMst", value = {
            @Result(column = "ms_cd", property = "ms_cd"),
            @Result(column = "ms_name", property = "ms_name"),
            @Result(column = "del_flag", property = "del_flag")})
    String findMst(String ms_cd);

}
