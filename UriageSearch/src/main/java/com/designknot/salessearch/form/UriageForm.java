package com.designknot.salessearch.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UriageForm {

    //店舗コード
    @NotEmpty(message="店舗が選択されていません。")
    public String ms_cd;
 
    //店舗名
    public String ms_name;
    @NotEmpty(message="商品が選択されていません。")
    //商品コード
    public String item_cd;
    
    //商品名
    public String item_name;
    //売上日
    @NotNull(message="年月日の形式が違います。")
    @DateTimeFormat(pattern="yyyy/MM/dd")
    public Date Uriage_date;
    //売上金
    @Range(min=1,max=10000000,message="1円以上,1千万円以下で登録してください。")
    @Pattern(regexp = "[0-9]*",message="数値で入力してください。")
    public String uriage;
}
