package com.designknot.salessearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designknot.salessearch.datasource.UriageMstDatasource;

@Service("UriageMstService")
public class UriageServiceImpl implements UriageMstService{

    @Autowired
    UriageMstDatasource data;

    //売上登録
    public boolean postSave(String ms_cd,String item_cd,String uriage,String uriage_date) {
        boolean save = data.postSave(ms_cd,item_cd, uriage,uriage_date);
        return save;
    }

}
