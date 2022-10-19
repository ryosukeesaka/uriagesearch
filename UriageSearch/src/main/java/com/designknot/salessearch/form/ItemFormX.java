package com.designknot.salessearch.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ItemFormX {
    
    //商品コード
    @Size(min=4,max=4,message="商品コードは4桁で入力してください。")
    @Pattern(regexp = "[0-9]*",message="商品コードは整数のみです。")
    private String item_cd;
    
    //商品名
    @NotEmpty(message="商品名が入力されていません。")
    @Size(min=1,max=20,message="商品名は20文字以内です。")
    private String item_name;
    
    private String del_flg;

}
