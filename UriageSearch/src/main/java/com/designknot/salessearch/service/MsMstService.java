package com.designknot.salessearch.service;

import java.util.List;

import com.designknot.salessearch.entity.MsMst;

public interface MsMstService {

    //店舗一覧
    public List<MsMst> findMs();

    //店舗新規追加
    public MsMst msAdd(MsMst msmst);

    //店舗更新
    public MsMst msUpd(MsMst msmst);

    //削除フラグ更新
    public boolean updByCd(String mscd);

}
