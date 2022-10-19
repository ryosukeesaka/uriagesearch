package com.designknot.salessearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMst {

    private String item_cd;

    private String item_name;

    private String del_flg;


}
