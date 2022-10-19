package com.designknot.salessearch.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.designknot.salessearch.entity.MsMst;

@Mapper
public interface MsRepository {

    @Select("SELECT"
            + " ms_cd "
            + ",ms_name "
            + ",del_flg "
            + "FROM"
            + " MS_MST "
            + " where "
            + " del_flg = '0' "
            + " order by "
            + "  ms_mst.ms_cd "
            + " asc ")
    @Results(id = "MsMst", value = {
            @Result(column = "ms_cd", property = "ms_cd"),
            @Result(column = "ms_name", property = "ms_name"),
            @Result(column = "del_flag", property = "del_flag")})
    List<MsMst> findMs();

    /** 店舗追加 */
    @Select("INSERT INTO "
            + "MS_MST "
            + "values"
            + "(#{ms_cd},#{ms_name},#{del_flg})"
            )
    MsMst msAdd(MsMst msmst);

    /** 店舗更新 */
    @Select("UPDATE "
            + "MS_MST "
            + "SET "
            + "ms_name = #{ms_name} "
            + "WHERE "
            + "ms_cd = #{ms_cd}"
            )
    MsMst msUpd(MsMst msmst);

    /** 削除フラグ更新 */
    @Update("UPDATE "
            + "MS_MST "
            + "SET "
            + "del_flg = '1' "
            + "WHERE "
            + "ms_cd = #{mscd}")

    boolean updByCd(String mscd);

}
