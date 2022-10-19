package com.designknot.salessearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designknot.salessearch.datasource.ItemMstDatasource;
import com.designknot.salessearch.entity.ItemMst;

@Service("ItemMstService")
public class ItemMstServiceImpl implements ItemMstService{
    
    @Autowired
    ItemMstDatasource itemdata;
    
    //商品一覧
    @Override
    public List<ItemMst> findItem(){
        List<ItemMst> itemMsts = itemdata.findItem();
        return itemMsts;
    }
    
    //商品追加
    @Override
    public ItemMst itemAdd(ItemMst itemmst) {
        ItemMst add = itemdata.itemAdd(itemmst);
        return add;
    }
    
    //商品更新
    @Override
    public ItemMst itemUpd(ItemMst itemmst) {
        ItemMst upd = itemdata.itemUpd(itemmst);
        return upd;
    }
    
    //商品削除
    @Override
    public boolean updByCd(String itemcd) {
        boolean del = itemdata.updByCd(itemcd);
        return del;
    }
}
