package com.designknot.salessearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//全フィールドに初期価値を引数とするコンストラクタを作成する
@AllArgsConstructor
@NoArgsConstructor
public class MsMst {

    /** 店コード */
    private String ms_cd;

    /** 店名称 */
    private String ms_name;

    /** 削除フラグ */
    private String del_flg;

}
