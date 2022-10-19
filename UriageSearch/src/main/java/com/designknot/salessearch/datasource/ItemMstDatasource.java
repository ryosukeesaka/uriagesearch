package com.designknot.salessearch.datasource;

import java.util.List;

import com.designknot.salessearch.entity.ItemMst;

public interface ItemMstDatasource {

    //商品一覧
    List<ItemMst> findItem();

    //商品追加
    public ItemMst itemAdd(ItemMst itemmst);

    //商品更新
    public ItemMst itemUpd(ItemMst itemmst);

    //商品削除
    public boolean updByCd(String itemcd);

}
