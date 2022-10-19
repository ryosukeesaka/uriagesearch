package com.designknot.salessearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.designknot.salessearch.MsMstForm.ItemListForm;
import com.designknot.salessearch.MsMstForm.ItemMstForm;
import com.designknot.salessearch.entity.ItemMst;
import com.designknot.salessearch.service.ItemListService;

@Controller
public class ItemListController {

	@Autowired
	private ItemListService itemListService;

	// 商品一覧を表示する
	@RequestMapping("/itemlist")
	public ModelAndView itemlist(ModelAndView mav) {
		ItemListForm itemListForm = new ItemListForm();
		mav.addObject("itemListForm",itemListForm);
		List<ItemMst> itemList = itemListService.findAll();
		mav.addObject("itemList",itemList);
		mav.setViewName("itemlist");
		return mav;
	}

	// 商品追加ページを表示する
	@RequestMapping(value = "/itempost",method = RequestMethod.GET)
	public ModelAndView itempost(ModelAndView mav,@RequestParam("back") String back) {
		ItemMstForm itemMstForm = new ItemMstForm();
		mav.addObject("itemMstForm",itemMstForm);
		mav.addObject("back",back);
		mav.setViewName("itempost");
		return mav;
	}

	// 追加商品を登録する
	@RequestMapping(value = "/itempost",params = "add", method = RequestMethod.POST)
	public ModelAndView itempost_add(ModelAndView mav,@ModelAttribute @Validated ItemMstForm itemMstForm, BindingResult result) {
		if(result.hasErrors()) {
			mav.addObject("back",itemMstForm.getBack());
			return mav;
		} try {
			itemListService.saveItem(itemMstForm.getItem_cd(),itemMstForm.getItem_name());
		} catch (DuplicateKeyException e) {
			mav.addObject("err_item","既に登録されています");
			mav.addObject("back",itemMstForm.getBack());
			return mav;
		}
		mav.addObject("add_item","登録しました");
		mav.addObject("item_cd",itemMstForm.getItem_cd());
		mav.addObject("item_name",itemMstForm.getItem_name());
		mav.addObject("back",itemMstForm.getBack());
		return mav;
	}

	// 商品追加ページ、戻るボタンの分岐
	@RequestMapping(value = "/itempost", params="itemback",method = RequestMethod.POST)
	public ModelAndView itemback(ModelAndView mav,@ModelAttribute ItemMstForm itemMstForm) {
		if (itemMstForm.getBack().equals("1")) {
			return new ModelAndView("redirect:/index");
		} else if (itemMstForm.getBack().equals("2")){
			return new ModelAndView("redirect:/itemlist");
		}
		return mav;
	}

	// 商品コードを選んで更新ページに移動する
	@RequestMapping(value = "/itemlist", params = "upd", method = RequestMethod.POST)
	public ModelAndView itemupd(ModelAndView mav,RedirectAttributes redirectAttributes,@ModelAttribute @Validated ItemListForm itemListForm, BindingResult result) {
		if(result.hasErrors()) {
			mav.addObject("itemListForm",itemListForm);
			List<ItemMst> itemList = itemListService.findAll();
			mav.addObject("itemList",itemList);
			return mav;
		}
		String itemcd = itemListForm.getItem_cd();
		redirectAttributes.addAttribute("itemcd",itemcd);
		return new ModelAndView("redirect:/itemedit");
	}

	// 商品更新ページを表示する※選択した商品コードを商品名を表示
	@RequestMapping(value = "/itemedit", method = RequestMethod.GET)
	public ModelAndView itemedit(ModelAndView mav,@RequestParam("itemcd") String item_cd) {
		ItemMstForm itemMstForm = new ItemMstForm();
		ItemMst itemmst = itemListService.findByItemcd(item_cd);
		mav.addObject("itemMstForm",itemMstForm);
		mav.addObject("itemcd",item_cd);
		mav.addObject("itemname", itemmst.getItem_name());
		mav.setViewName("itemedit");
		return mav;
	}

	// 商品名称を更新する
	@RequestMapping(value = "/itemedit", params = "upd2", method = RequestMethod.POST)
	public ModelAndView itemedit_ptost(ModelAndView mav,@ModelAttribute @Validated ItemMstForm itemMstForm, BindingResult result) {
		if(result.hasErrors()) {
			mav.addObject("itemcd",itemMstForm.getItem_cd());
			return mav;
		}
		itemListService.updateItem(itemMstForm.getItem_cd(),itemMstForm.getItem_name());
		mav.addObject("upd_item","更新しました");
		mav.addObject("itemcd",itemMstForm.getItem_cd());
		mav.addObject("itemname",itemMstForm.getItem_name());
		return mav;
	}

	// 商品を削除する
	@RequestMapping(value = "/itemlist", params = "del", method = RequestMethod.POST)
	public ModelAndView itemdel(ModelAndView mav,@ModelAttribute @Validated ItemListForm itemListForm,BindingResult result) {
		if (result.hasErrors()) {
			mav.addObject("itemListForm",itemListForm);
			List<ItemMst> itemList = itemListService.findAll();
			mav.addObject("itemList",itemList);
			return mav;
		}
		itemListService.deleteItem(itemListForm.getItem_cd());
		mav.addObject("itemListForm",itemListForm);
		List<ItemMst> itemList =itemListService.findAll();
		mav.addObject("itemList",itemList);
		mav.addObject("del_item","削除しました");
		return mav;
	}

	// 商品復活画面を表示する
	@RequestMapping("/itemrbn")
	public ModelAndView msDelList(ModelAndView mav) {
		ItemListForm itemListForm = new ItemListForm();
		mav.addObject("itemListForm", itemListForm);
		List<ItemMst> itemDel = itemListService.findItemDel();
		mav.addObject("itemDel",itemDel);
		mav.setViewName("itemrbn");
		return mav;
	}

	// 商品を復活させる
	@RequestMapping(value = "/itemrbn", params = "rbn", method = RequestMethod.POST)
	public ModelAndView rbnMs(ModelAndView mav, @ModelAttribute @Validated ItemListForm itemListForm,
			BindingResult result) {
		if(!result.hasErrors()) {
			itemListService.rbnItem(itemListForm.getItem_cd());
			mav.addObject("rbn_item","復活させました");
		}
		List<ItemMst> itemDel = itemListService.findItemDel();
		mav.addObject("itemDel",itemDel);
		return mav;
	}

}
