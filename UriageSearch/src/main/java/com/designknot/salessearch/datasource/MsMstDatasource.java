package com.designknot.salessearch.datasource;

import java.util.List;

import com.designknot.salessearch.entity.MsMst;

public interface MsMstDatasource {

    //店舗一覧
    List<MsMst> findMs();

    //店舗新規追加
    public MsMst msAdd(MsMst msMst);

    //店舗更新
    public MsMst msUpd(MsMst msMst);

    //削除フラグ更新
    public boolean updByCd(String mscd);

}
