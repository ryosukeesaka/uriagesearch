package com.designknot.salessearch.service;

import java.util.List;

import com.designknot.salessearch.entity.MsUriageItem;
import com.designknot.salessearch.entity.SearchMst;

public interface SearchService {

    //売上検索
    public List<SearchMst> searchUriage(String uriage_date,String item_cd,String ms_cd);

    //売上検索（店一覧から遷移）
    public List<SearchMst> search(String ms_cd);

    //年月日リスト
    public List<MsUriageItem> searchDate();

}
