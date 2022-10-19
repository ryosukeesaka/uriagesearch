package com.designknot.salessearch.MsMstForm;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MsMstForm {

	@Size(min = 2, max = 2, message= "店コードは数値2桁で入力してください")
	@Pattern(regexp = "[0-9]*",message = "店コードは数値で入力してください")
	private String ms_cd;
	@Size(min = 1, max = 10, message= "店舗名称は10文字以内で入力してください")
	private String ms_name;

	private String back;
}


