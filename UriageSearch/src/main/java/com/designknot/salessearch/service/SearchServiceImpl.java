package com.designknot.salessearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designknot.salessearch.datasource.SearchDatasource;
import com.designknot.salessearch.entity.MsUriageItem;
import com.designknot.salessearch.entity.SearchMst;



@Service("SearchService")
public class SearchServiceImpl implements SearchService{

    @Autowired
    SearchDatasource data;

    //売上検索
    @Override
    public List<SearchMst> searchUriage( String uriage_date,String item_cd,String ms_cd){
        List<SearchMst> uriage = data.searchUriage(uriage_date,item_cd,ms_cd);
        return uriage;
    }

    //売上検索（店一覧から遷移）
    @Override
    public List<SearchMst> search(String ms_cd){
        List<SearchMst> search = data.search(ms_cd);
        return search;
    }

    //年月日リスト
    @Override
    public List<MsUriageItem> searchDate(){
        List<MsUriageItem> date = data.searchDate();
        return date;
    }

}
