package com.designknot.salessearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchMst {

    //店舗コード
    public String ms_cd;
    //店舗名
    public String ms_name;
    //商品コード
    public String item_cd;
    //商品名
    public String item_name;
    //売上日
    public  String uriage_date;
    //売上金額
    public String uriage;
    //売上個数
    public String item_count;

}
