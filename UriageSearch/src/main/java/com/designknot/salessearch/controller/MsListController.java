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

import com.designknot.salessearch.MsMstForm.MsListForm;
import com.designknot.salessearch.MsMstForm.MsMstForm;
import com.designknot.salessearch.entity.MsMst;
import com.designknot.salessearch.service.MsListService;

@Controller
public class MsListController {

	@Autowired
	private MsListService msListService;

	// 店舗一覧を表示する
	@RequestMapping("/mslist")
	public ModelAndView mslist(ModelAndView mav) {
		MsListForm msListForm = new MsListForm();
		mav.addObject("msListForm", msListForm);
		List<MsMst> msList = msListService.findAll();
		mav.addObject("msList",msList);
		mav.setViewName("mslist");
		return mav;
	}

	// 店舗追加ページを表示する
	@RequestMapping(value = "/mspost", method = RequestMethod.GET)
	public ModelAndView mspost(ModelAndView mav,@RequestParam("back") String back) {
		MsMstForm msMstForm = new MsMstForm();
		mav.addObject("msMstForm",msMstForm);
		mav.addObject("back",back);
		mav.setViewName("mspost");
		return mav;
	}

	// 店舗追加ページ、戻るボタン
	@RequestMapping(value = "/mspost", params="msback",method = RequestMethod.POST)
	public ModelAndView msback(ModelAndView mav,@ModelAttribute MsMstForm msMstForm) {
		if (msMstForm.getBack().equals("1")) {
			return new ModelAndView("redirect:/index");
		} else if (msMstForm.getBack().equals("2")){
			return new ModelAndView("redirect:/mslist");
		}
		return mav;
	}

	// 追加店舗を登録する
	@RequestMapping(value = "/mspost", params = "add", method = RequestMethod.POST)
	public ModelAndView mspost_add(ModelAndView mav, @ModelAttribute @Validated MsMstForm msMstForm,
			BindingResult result) {
		if(result.hasErrors()) {
			mav.addObject("back",msMstForm.getBack());
			return mav;
		}
		try {
			msListService.saveMs(msMstForm.getMs_cd(), msMstForm.getMs_name());
		} catch (DuplicateKeyException e) {
			mav.addObject("err_ms","既に登録されています");
			mav.addObject("back",msMstForm.getBack());
			return mav;
		}
		mav.addObject("add_ms","登録しました");
		mav.addObject("ms_cd",msMstForm.getMs_cd());
		mav.addObject("ms_name",msMstForm.getMs_name());
		mav.addObject("back",msMstForm.getBack());
		return mav;
	}

	// 店コードを選んで更新ページに移動する
	@RequestMapping(value = "/mslist", params = "upd", method = RequestMethod.POST)
	public ModelAndView msupd(ModelAndView mav, RedirectAttributes redirectAttributes,
			@ModelAttribute @Validated MsListForm msListForm, BindingResult result) {
		if(result.hasErrors()) {
			mav.addObject("msListForm", msListForm);
			List<MsMst> msList = msListService.findAll();
			mav.addObject("msList",msList);
			return mav;
		}
		String mscd = msListForm.getMs_cd();
		redirectAttributes.addAttribute("mscd",mscd);
		return new ModelAndView("redirect:/msedit");
	}

	// 店舗更新ページを表示する※選択した商品コードを商品名を表示
	@RequestMapping(value = "/msedit", method = RequestMethod.GET)
	public ModelAndView msedit(ModelAndView mav,@RequestParam("mscd") String ms_cd) {
		MsMst msmst = msListService.findByMscd(ms_cd);
		MsMstForm msMstForm = new MsMstForm();
		mav.addObject("msMstForm",msMstForm);
		mav.addObject("mscd",msmst.getMs_cd());
		mav.addObject("msname",msmst.getMs_name());
		mav.setViewName("msedit");
		return mav;
	}

	// 店舗名称を更新する
	@RequestMapping(value = "/msedit", params = "upd2", method = RequestMethod.POST)
	public ModelAndView msedit_post(ModelAndView mav,@ModelAttribute @Validated MsMstForm msMstForm,
			BindingResult result) {
		mav.addObject("mscd",msMstForm.getMs_cd());
		mav.addObject("msname",msMstForm.getMs_name());
		if (result.hasErrors()) {
		} else {
			msListService.updateMs(msMstForm.getMs_cd(), msMstForm.getMs_name());
			mav.addObject("upd_ms","更新しました");
		}
		return mav;
	}

	// 店舖を削除する
	@RequestMapping(value = "/mslist", params = "del", method = RequestMethod.POST)
	public ModelAndView msdel(ModelAndView mav, @ModelAttribute @Validated MsListForm msListForm,
			BindingResult result) {
		if(!result.hasErrors()) {
			msListService.deleteMs(msListForm.getMs_cd());
			mav.addObject("del_ms","削除しました");
		}
		List<MsMst> msList = msListService.findAll();
		mav.addObject("msList",msList);
		return mav;
	}

	// 店舗復活画面を表示する
	@RequestMapping("/msrbn")
	public ModelAndView msDelList(ModelAndView mav) {
		MsListForm msListForm = new MsListForm();
		mav.addObject("msListForm", msListForm);
		List<MsMst> msDel = msListService.findMsDel();
		mav.addObject("msDel",msDel);
		mav.setViewName("msrbn");
		return mav;
	}

	// 店舖を復活させる
	@RequestMapping(value = "/msrbn", params = "rbn", method = RequestMethod.POST)
	public ModelAndView rbnMs(ModelAndView mav, @ModelAttribute @Validated MsListForm msListForm,
			BindingResult result) {
		if(!result.hasErrors()) {
			msListService.rbnMs(msListForm.getMs_cd());
			mav.addObject("rbn_ms","復活させました");
		}
		List<MsMst> msDel = msListService.findMsDel();
		mav.addObject("msDel",msDel);
		return mav;
	}
}
