package com.designknot.salessearch.datasource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.designknot.salessearch.entity.MsUriageItem;
import com.designknot.salessearch.entity.SearchMst;
import com.designknot.salessearch.repository.ItemRepository;
import com.designknot.salessearch.repository.MsRepository;
import com.designknot.salessearch.repository.MsUriageRepository;
import com.designknot.salessearch.repository.SearchRepository;

@Component
public class SearchDatasourceImpl implements SearchDatasource{
    @Autowired
    SearchRepository repo;
    @Autowired
    ItemRepository itemrepo;
    @Autowired
    MsRepository msrepo;
    @Autowired
    MsUriageRepository uriagerepo;

    //売上検索
    public List<SearchMst> searchUriage( String uriage_date,String item_cd,String ms_cd){
        List<SearchMst> search = repo.searchUriage(uriage_date,item_cd,ms_cd);
        return search;
    }

    //売上検索（店一覧から遷移）
    public List<SearchMst> search(String ms_cd){
        List<SearchMst> search = repo.search(ms_cd);
        return search;
    }

    //年月日リスト
    @Override
    public List<MsUriageItem> searchDate() {
        MsUriageItem u = new MsUriageItem();
        u.setUriage_date("全期間");
        List<MsUriageItem> datelist = new ArrayList<MsUriageItem>();
        datelist.add(u);
        List<MsUriageItem> temp = uriagerepo.searchDate();
        datelist.addAll(temp);
        return datelist;
    }

}
