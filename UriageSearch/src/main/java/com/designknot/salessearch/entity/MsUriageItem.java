package com.designknot.salessearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsUriageItem {

    //店コード
    private String ms_cd;

    //店名称
    private String item_cd;

    //売上金額
    private String uriage;

    //売上日
    private String uriage_date;

}
