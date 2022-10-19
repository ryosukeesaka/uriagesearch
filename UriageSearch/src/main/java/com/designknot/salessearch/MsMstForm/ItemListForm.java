package com.designknot.salessearch.MsMstForm;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ItemListForm {

	@NotEmpty(message="商品コードを選んでください")
	private String item_cd;

	private String item_name;
}
