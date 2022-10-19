package com.designknot.salessearch.datasource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.designknot.salessearch.entity.MsMst;
import com.designknot.salessearch.repository.MsRepository;

@Component
public class MsMstDatasourceImpl implements MsMstDatasource {

    @Autowired
    MsRepository msrepo;

    //店舗一覧
    @Override
    public List<MsMst> findMs() {
        List<MsMst> msMsts = msrepo.findMs();
        return msMsts;
    }

    //店舗新規追加
    @Override
    public MsMst msAdd(MsMst msmst) {
        MsMst save = msrepo.msAdd(msmst);
        return save;
    }

    //店舗更新
    @Override
    public MsMst msUpd(MsMst msmst) {
        MsMst upd = msrepo.msUpd(msmst);
        return upd;
    }

    //削除フラグ更新
    @Override
    public boolean updByCd(String mscd) {
         boolean upd = msrepo.updByCd(mscd);
        return upd;
    }

}
