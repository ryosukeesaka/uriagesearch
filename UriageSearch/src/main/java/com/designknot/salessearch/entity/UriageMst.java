package com.designknot.salessearch.entity;

import java.util.Date;

import lombok.Data;

@Data
public class UriageMst {

    //店舗コード
    public String ms_cd;
    //商品コード
    public String item_cd;
    //売上日
    public Date uriage_date;
    //商品名
    public String item_name;
    //店舗名
    public String ms_name;
    //売上金額
    public String uriage;
    /** 削除フラグ */
    private String del_flg;

}
