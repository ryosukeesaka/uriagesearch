package com.designknot.salessearch.MsMstForm;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MsListForm {

	@NotEmpty(message="店コードを選んでください")
	private String ms_cd;

	private String ms_name;

	private String back;
}
