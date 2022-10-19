package com.designknot.salessearch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.designknot.salessearch.entity.ItemMst;
import com.designknot.salessearch.entity.UriageMst;
import com.designknot.salessearch.form.ItemForm;
import com.designknot.salessearch.form.ItemFormX;
import com.designknot.salessearch.repository.UriageRepository;
import com.designknot.salessearch.service.ItemMstService;

@Controller
public class ItemMstController {

    @Autowired
    ItemMstService service;
    @Autowired
    UriageRepository repo;

    //商品一覧
    @RequestMapping("/itemlist")
    public ModelAndView index(ModelAndView mav) {

        List<UriageMst> itemMsts = new ArrayList<UriageMst>();
        itemMsts = repo.findAll();
        mav.addObject("itemform", new ItemForm());
        mav.addObject("itemmst", itemMsts);
        mav.setViewName("itemlist");
        return mav;
    }

    //商品削除
    @RequestMapping(value="/itemlist",method=RequestMethod.POST)
    public ModelAndView del(
            @ModelAttribute @Validated ItemForm itemForm,
            BindingResult result,
            ModelAndView mav,
            RedirectAttributes redirect) {

        if(result.hasErrors()) {
            List<UriageMst> itemMsts = repo.findAll();
            //itemMsts = repo.findAll();
            mav.addObject("itemform", new ItemForm());
            mav.addObject("itemmst",itemMsts);
            mav.addObject("empty","商品が選択されていません。");
            mav.setViewName("itemlist");
            return mav;
        }
        String data = itemForm.getItem_cd();
        String[] split = data.split(",", 0);
        String itemcd = split[0];
        service.updByCd(itemcd);
        redirect.addFlashAttribute("del", "削除しました。");

        mav.addObject("itemForm", itemForm);
        mav.setViewName("itemlist");
        return new ModelAndView("redirect:/itemlist");
    }

    //商品追加
    @RequestMapping(value="/itemadd",method=RequestMethod.GET)
    public ModelAndView get(ModelAndView mav) {
        ItemFormX form = new ItemFormX();
        mav.addObject("itemFormX",form);
        mav.setViewName("itemadd");
        return mav;
    }

    //商品追加
    @RequestMapping(value="/itemadd",method=RequestMethod.POST)
    public ModelAndView post(
            @ModelAttribute @Validated ItemFormX itemFormX,
            BindingResult result,
            ModelAndView mav,
            RedirectAttributes redirectAttributes
            ) {

        if (result.hasErrors()){

           //mav.addObject("itemFormX",itemFormX);
            //mav.setViewName("itemadd"); 上記のGETメソッドが走る
            return mav;
        }
        try {
            ItemMst itemmst = new ItemMst(itemFormX.getItem_cd(),itemFormX.getItem_name(),"0");
            service.itemAdd(itemmst);
            String itemname = itemFormX.getItem_name();
            String itemcd = itemFormX.getItem_cd();
            mav.addObject("itemname",itemname);
            mav.addObject("itemcd",itemcd);
            mav.addObject("itemForm", new ItemFormX());
            mav.addObject("add","更新しました。");
        }catch(DuplicateKeyException e) {
            mav.addObject("duplicate","既に登録済です。");
            mav.setViewName("itemadd");
            return mav;
    }
    return mav;
    }

    //商品更新
    @RequestMapping(value="/itemlist",params = "update",method=RequestMethod.POST)
    public ModelAndView updata(
            @ModelAttribute @Validated ItemForm itemform,
            BindingResult result,
            ModelAndView mav,
            RedirectAttributes redirectAttributes
            ) {

       if (result.hasErrors()) {
            List<UriageMst> itemmst = new ArrayList<UriageMst>();
            mav.addObject("itemform", new ItemForm());
            itemmst = repo.findAll();
            mav.addObject("itemmst", itemmst);
            mav.addObject("empty", "商品が選択されていません。");
            mav.setViewName("itemlist");
            return mav;
        }

        String itemcd = itemform.getItem_cd();
        String temp = itemform.getItem_name();
        mav.addObject("temp", temp);

        String[] split = itemcd.split(",", 0);
        redirectAttributes.addFlashAttribute("itemcd", split[0]);

        redirectAttributes.addFlashAttribute("itemname",split[1]);

        return new ModelAndView("redirect:/itemedit");
    }

    @RequestMapping(value="/itemedit",method=RequestMethod.GET)
    public ModelAndView edit(
            ModelAndView mav,
            @ModelAttribute ItemFormX itemFormX) {

        mav.setViewName("itemedit");
        return mav;
    }

    @RequestMapping(value="/itemedit",method=RequestMethod.POST)
    public ModelAndView postx(
           @Validated ItemFormX itemFormX,
            BindingResult result,
            ModelAndView mav,
            RedirectAttributes redirectAttributes
            ) {
       if(result.hasErrors()) {

           //値を保持
           mav.addObject("itemcd", itemFormX.getItem_cd());
           mav.addObject("itemname", itemFormX.getItem_name());
           return mav;

        }

       ItemMst itemmst = new ItemMst(itemFormX.getItem_cd(),itemFormX.getItem_name(),"0");
        //String itemname = itemFormX.getItem_name();
        //String itemcd = itemFormX.getItem_cd();
        service.itemUpd(itemmst);
        mav.addObject("msg", "更新しました。");
        mav.addObject("itemname", itemFormX.getItem_name());
        mav.addObject("itemcd", itemFormX.getItem_cd());
        mav.setViewName("itemedit");
        return mav;
    }
}
