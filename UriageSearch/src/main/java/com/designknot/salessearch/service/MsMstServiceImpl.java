package com.designknot.salessearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designknot.salessearch.datasource.MsMstDatasource;
import com.designknot.salessearch.entity.MsMst;

@Service("MsMstService")
public class MsMstServiceImpl implements MsMstService{

    @Autowired
    MsMstDatasource msdata;

    //店舗一覧
    @Override
    public List<MsMst> findMs(){
        List<MsMst> msMsts = msdata.findMs();
        return msMsts;
    }

    //店舗追加
    @Override
    public MsMst msAdd(MsMst msmst) {
        MsMst add = msdata.msAdd(msmst);
        return add;
    }

    //店舗更新
    @Override
    public MsMst msUpd(MsMst msmst){
        MsMst upd = msdata.msUpd(msmst);
        return upd;
    }

    //削除フラグ更新
    @Override
    public boolean updByCd(String mscd) {
        boolean upd = msdata.updByCd(mscd);
        return upd;
    }

}
