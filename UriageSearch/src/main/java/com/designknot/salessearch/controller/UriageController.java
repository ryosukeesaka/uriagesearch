package com.designknot.salessearch.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.designknot.salessearch.entity.MsMst;
import com.designknot.salessearch.entity.UriageMst;
import com.designknot.salessearch.form.UriageForm;
import com.designknot.salessearch.repository.ItemRepository;
import com.designknot.salessearch.repository.MsRepository;
import com.designknot.salessearch.repository.UriageRepository;
import com.designknot.salessearch.service.ItemMstService;
import com.designknot.salessearch.service.MsMstService;
import com.designknot.salessearch.service.SearchService;
import com.designknot.salessearch.service.UriageMstService;

@Controller
public class UriageController {

    @Autowired
    UriageMstService service;
    @Autowired
    ItemMstService itemservice;
    @Autowired
    MsMstService msservice;
    @Autowired
    SearchService sservice;
    @Autowired
    UriageRepository repo;
    @Autowired
    MsRepository msrepo;
    @Autowired
    ItemRepository itemrepo;

    //売上登録
    @RequestMapping(value="/uriage",method=RequestMethod.GET)
    public  ModelAndView additem(ModelAndView mav, @ModelAttribute UriageForm uriageForm) {

        //商品一覧
        List<UriageMst> itemmsts = new ArrayList<UriageMst>();
        itemmsts = repo.findAll();
        mav.setViewName("uriage");
        mav.addObject("itemmst", itemmsts);

        //店舗一覧
        List<MsMst> msmsts = new ArrayList<MsMst>();
        msmsts = msservice.findMs();
        mav.setViewName("uriage");
        mav.addObject("msmst",msmsts);

        return mav;
    }

    // 売上登録POST
    @RequestMapping(value="/uriage", method=RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView add(
            @ModelAttribute @Validated UriageForm uriageForm,
            BindingResult result,
            ModelAndView mav,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<UriageMst> itemmsts = new ArrayList<UriageMst>();
            String itemcd = uriageForm.getItem_cd();
            itemmsts = repo.findAll();
            mav.addObject("itemmst", itemmsts);
            mav.addObject("itemcd", itemcd);

            List<MsMst> msmsts = new ArrayList<MsMst>();
            String mscd =  uriageForm.getMs_cd();
            msmsts = msrepo.findMs();
            mav.addObject("mscd",mscd);
            mav.addObject("msmst",msmsts);

            mav.addObject("uriage_date", result.getFieldValue("uriage_date"));
            mav.addObject("uriage", uriageForm.getUriage());

            return mav;

        }

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = df1.format(uriageForm.getUriage_date());
        String removed = strDate.replace("/", "");
        service.postSave(uriageForm.getMs_cd(),uriageForm.getItem_cd(),uriageForm.getUriage(),removed);
        String msg = "登録しました。";
        redirectAttributes.addFlashAttribute("msg", msg);
        mav.setViewName("uriage");
        return new ModelAndView("redirect:/uriage");

    }
}
