package com.designknot.salessearch.MsMstForm;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UriageEntryForm {

	@NotNull(message="年月日の形式が違います")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private LocalDate Uriage_date;

	@NotEmpty(message="店舗を選んでください")
	private String ms_cd;

	@NotEmpty(message="商品を選んでください")
	private String item_cd;

	@Range(min=1 ,max=10000000,message="1円以上、1千万円以下の数値を入力してください")
	private String uriage;
}
