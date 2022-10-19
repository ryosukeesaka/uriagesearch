package com.designknot.salessearch.form;

import lombok.Data;

@Data
public class SearchForm {

    //店舗コード
    public String ms_cd;
    //店舗名
    public String ms_name;
    //商品コード
    public String item_cd;
    //商品名
    public String item_name;
    //売上日
    public String uriage_date;
    //売上金
    public String uriage;
    //売上個数
    public String item_count;
    //遷移先の分岐
    public String flg;

}
