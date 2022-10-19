package com.designknot.salessearch.datasource;

public interface UriageMstDatasource {

    //売上登録
    boolean postSave(String ms_cd,String item_cd,String uriage,String uriage_date);

}
