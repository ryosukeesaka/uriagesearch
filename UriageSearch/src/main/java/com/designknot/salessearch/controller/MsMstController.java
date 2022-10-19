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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.designknot.salessearch.entity.MsMst;
import com.designknot.salessearch.form.MsForm;
import com.designknot.salessearch.form.MsFormX;
import com.designknot.salessearch.service.MsMstService;

@Controller
public class MsMstController {

    @Autowired
    MsMstService service;

    //店舗一覧
    @RequestMapping("/mslist")
    public ModelAndView index(ModelAndView mav) {

        List<MsMst> msMsts = new ArrayList<MsMst>();
        msMsts = service.findMs();
        //formはautowired不可
        mav.addObject("msform", new MsFormX());
        mav.addObject("msmst", msMsts);
        mav.setViewName("mslist");

        return mav;
    }

    //店舗削除
    @RequestMapping(value="/mslist", params="delete",method=RequestMethod.POST)
    public  ModelAndView del(
            ModelAndView mav,
            @ModelAttribute @Validated MsFormX msForm,
            BindingResult result,
            RedirectAttributes redirect
            ) {

        if (result.hasErrors()) {
            List<MsMst> msmsts = new ArrayList<MsMst>();
            mav.addObject("msform", new MsFormX());
            msmsts = service.findMs();
            mav.addObject("msmst", msmsts);
            mav.addObject("empty", "店舗が選択されていません");
            mav.setViewName("mslist");

            return mav;

        }

        String data = msForm.getMs_cd();
        String[] split = data.split(",", 0);
        String mscd = split[0];
        service.updByCd(mscd);
        String delete = "削除しました。";
        redirect.addFlashAttribute("delete", delete);

        return new ModelAndView("redirect:/mslist");
    }

    //店舗追加GET
    @RequestMapping(value="/msadd",method=RequestMethod.GET)
    public ModelAndView addget(
            ModelAndView mav,
            @RequestParam("flg") String flg) {

        MsForm msForm = new MsForm();

        mav.addObject("flg", flg);
        mav.addObject("msForm", msForm);
        mav.setViewName("msadd");
        return mav;
    }

    //遷移先の分岐
    @RequestMapping(value="/msadd",params = "rtn", method=RequestMethod.POST)
    public ModelAndView rtn(
            @ModelAttribute MsForm msForm,
            ModelAndView mav
            ) {
        String flg = msForm.getFlg();
        if(flg.equals("1")) {
            return new ModelAndView("redirect:/");
        }else if(flg.equals("2")) {
            return new ModelAndView("redirect:/mslist");
      }
        return mav;
    }

    //店舗追加POST
    @RequestMapping(value="/msadd", params="add",method=RequestMethod.POST)
    public ModelAndView add(
            @ModelAttribute @Validated MsForm msForm,
            BindingResult result,
            ModelAndView mav,
            RedirectAttributes redirectAttributes
            ) {
        mav.setViewName("msadd");
        if (result.hasErrors()) {
            mav.setViewName("msadd");
            return mav;
        }

        try {
            MsMst msmst = new MsMst(msForm.getMs_cd(),msForm.getMs_name(),"0");
            service.msAdd(msmst);
            mav.addObject("msForm", new MsForm());
            String msg = "登録しました。";
            String msname = msForm.getMs_name();
            String mscd = msForm.getMs_cd();
            mav.addObject("msg",msg);
            mav.addObject("msname",msname);
            mav.addObject("mscd",mscd);
            redirectAttributes.addFlashAttribute("msg",msg);
        }catch(DuplicateKeyException e) {
            mav.addObject("duplicate","すでに登録済です。");
            String msname = msForm.getMs_name();
            String mscd = msForm.getMs_cd();
            mav.addObject("msname",msname);
            mav.addObject("mscd",mscd);
            mav.setViewName("msadd");
            return mav;

        }

        return mav;
    }



    //店舗更新
    @RequestMapping(value="/mslist",params = "update", method=RequestMethod.POST)
    public ModelAndView upd(
            ModelAndView mav,
            @ModelAttribute @Validated MsFormX msForm,
            BindingResult result,
            RedirectAttributes redirectAttributes
            ) {

        if (result.hasErrors()) {
            List<MsMst> msmsts = new ArrayList<MsMst>();
            mav.addObject("msform", new MsFormX());
            msmsts = service.findMs();
            mav.addObject("msmst", msmsts);
            mav.addObject("empty", "店舗が選択されていません");
            mav.setViewName("mslist");
            return mav;
        }

        String msg = msForm.getMs_cd();
        String[] split = msg.split(",", 0);
        redirectAttributes.addFlashAttribute("mscd", split[0]);
        redirectAttributes.addFlashAttribute("msname",split[1]);
        return new ModelAndView("redirect:/msedit");
    }

    @RequestMapping(value="/msedit", method=RequestMethod.GET)
    public ModelAndView upd(
            ModelAndView mav,
            @ModelAttribute MsForm msForm
            ) {
        mav.setViewName("msedit");
        return mav;
    }

    @RequestMapping(value="/msedit", method=RequestMethod.POST)
    public ModelAndView post(
            @Validated MsForm msForm,
            BindingResult result,
            ModelAndView mav
            ) {

        if (result.hasErrors()) {
            mav.setViewName("msedit");
            return mav;
        }

        //店舗名をformで更新
        MsMst msMst = new MsMst(msForm.getMs_cd(),msForm.getMs_name(), "0");
        service.msUpd(msMst);
        String msname = msForm.getMs_name();
        String mscd = msForm.getMs_cd();
        String msg = "更新しました。";
        mav.addObject("msname",msname);
        mav.addObject("mscd",mscd);
        mav.addObject("msg",msg);
        mav.setViewName("msedit");
        return mav;
    }

}
