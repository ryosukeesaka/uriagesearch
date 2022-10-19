package com.designknot.salessearch.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MsForm {

    //店コード
    @Size(min=2,max=2,message="店コードは2桁で入力してください。")
    @Pattern(regexp = "[0-9]*",message="店コードは整数のみです。")
    private String ms_cd;

    //店舗名
    @NotEmpty(message="店舗名が入力されていません。")
    @Size(min=1,max=10,message="10文字以内で入力してください。")
    private String ms_name;

    private String flg;

}
