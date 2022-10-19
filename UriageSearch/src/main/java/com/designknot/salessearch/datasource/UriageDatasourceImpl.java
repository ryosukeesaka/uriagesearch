package com.designknot.salessearch.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.designknot.salessearch.repository.UriageRepository;

@Component
public class UriageDatasourceImpl implements UriageMstDatasource{

    @Autowired
    UriageRepository uriagerepo;

    //売上登録
    @Override
    public boolean postSave(String ms_cd,String item_cd,String uriage,String uriage_date) {
        boolean save = uriagerepo.postSave(ms_cd,item_cd, uriage,uriage_date);
        return save;
    }


}
