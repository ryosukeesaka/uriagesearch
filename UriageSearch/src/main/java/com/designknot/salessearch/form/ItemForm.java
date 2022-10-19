package com.designknot.salessearch.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ItemForm {

    //商品コード
    @NotEmpty(message="商品が選択されていません。")
    private String item_cd;
    //商品名
    private String item_name;

    private String del_flg;

}
