package com.designknot.salessearch.datasource;

import java.util.List;

import com.designknot.salessearch.entity.MsUriageItem;
import com.designknot.salessearch.entity.SearchMst;

public interface SearchDatasource {

    //売上検索
    List<SearchMst> searchUriage(String uriage_date ,String item_cd,String ms_cd);

    //売上検索（店一覧から遷移）
    List<SearchMst> search(String ms_cd);

    //年月日リスト
    public List<MsUriageItem> searchDate();

}
