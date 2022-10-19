package com.designknot.salessearch.MsMstForm;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ItemMstForm {
	@Size(min = 4, max = 4, message="商品コードは数値4桁で入力してください")
	@Pattern(regexp = "[0-9]*",message = "商品コードは数値で入力してください")
	private String item_cd;

	@Size(min = 1, max = 20,message="商品名称は20文字以内で入力してください")
	private String item_name;

	private String back;
}
