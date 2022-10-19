package com.designknot.salessearch.MsMstForm;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UriageSearchForm {

	@NotEmpty(message="売上日を選択してください")
	private String Uriage_date;

	@NotEmpty(message="店名を選択してください")
	private String ms_name;

	@NotEmpty(message="商品を選択してください")
	private String item_name;

	private String back;
}
