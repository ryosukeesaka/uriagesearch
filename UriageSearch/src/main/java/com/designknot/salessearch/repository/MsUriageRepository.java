package com.designknot.salessearch.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.designknot.salessearch.entity.MsUriageItem;


@Mapper
public interface MsUriageRepository {

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
      List<MsUriageItem> searchDate();
}
