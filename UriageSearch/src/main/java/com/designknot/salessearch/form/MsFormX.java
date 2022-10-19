package com.designknot.salessearch.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MsFormX {

    //店舗コード
    @NotEmpty(message="店舗が選択されていません。")
    private String ms_cd;
    //店舗名
    private String ms_name;
    
    private String flg;

}
