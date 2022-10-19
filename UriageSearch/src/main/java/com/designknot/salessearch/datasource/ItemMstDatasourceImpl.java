package com.designknot.salessearch.datasource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.designknot.salessearch.entity.ItemMst;
import com.designknot.salessearch.repository.ItemRepository;

@Component
public class ItemMstDatasourceImpl implements ItemMstDatasource{

    @Autowired
    ItemRepository itemrepo;

    //商品リスト
    @Override
    public List<ItemMst> findItem() {
        ItemMst i = new ItemMst();
        i.setItem_cd("全商品");
        i.setItem_name("全商品");
        List<ItemMst> itemlist = new ArrayList<ItemMst>();
        itemlist.add(i);
        List<ItemMst> temp = itemrepo.findItem();
        itemlist.addAll(temp);
        return itemlist;
    }

    //商品追加
    @Override
    public ItemMst itemAdd(ItemMst itemmst) {
        ItemMst add= itemrepo.itemAdd(itemmst);
        return add;
    }

    //商品更新
    @Override
    public ItemMst itemUpd(ItemMst itemmst) {
        ItemMst upd = itemrepo.itemUpd(itemmst);
        return upd;
    }

    //商品削除
    @Override
    public boolean updByCd(String itemcd) {
        boolean del = itemrepo.updByCd(itemcd);
        return del;
    }

}
