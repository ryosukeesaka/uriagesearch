package com.designknot.salessearch.service;

public interface UriageMstService {

    //売上登録
    boolean postSave(String ms_cd,String item_cd,String uriage,String uriage_date);
}
