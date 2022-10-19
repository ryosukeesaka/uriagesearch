package com.designknot.salessearch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.designknot.salessearch.entity.ItemMst;
import com.designknot.salessearch.entity.MsMst;
import com.designknot.salessearch.entity.MsUriageItem;
import com.designknot.salessearch.entity.SearchMst;
import com.designknot.salessearch.form.MsFormX;
import com.designknot.salessearch.form.SearchForm;
import com.designknot.salessearch.service.ItemMstService;
import com.designknot.salessearch.service.MsMstService;
import com.designknot.salessearch.service.SearchService;

@Controller
public class SearchController {

    @Autowired
    private SearchService sservice;
    @Autowired
    private MsMstService msservice;
    @Autowired
    private ItemMstService itemservice;

    //売上検索 店舗一覧から遷移
    @RequestMapping(value="/search/{ms_cd}", method=RequestMethod.GET)
    public ModelAndView search(@ModelAttribute MsFormX form, @ModelAttribute SearchForm searchForm,ModelAndView mav,@RequestParam("flg") String flg,@PathVariable String ms_cd) {

        //年月日リスト
        List<MsUriageItem> uriage = sservice.searchDate();
        String uriagedate = searchForm.getUriage_date();
        mav.addObject("uriagedate", uriagedate);
        mav.addObject("uriage", uriage);
       // mav.setViewName("search");

        //店リスト
        List<MsMst> msmst = new ArrayList<MsMst>();
        msmst = msservice.findMs();
        //String temp = srepo.findMst(ms_cd);
       // mav.addObject("msname", temp);
        mav.addObject("data", ms_cd);
        mav.addObject("msmst", msmst);
       // mav.setViewName("search");

        //商品リスト
        List<ItemMst> itemmst = itemservice.findItem();
        mav.addObject("itemmst", itemmst);
       // mav.setViewName("search");

        /*try {
            List<SearchMst> mslist = new ArrayList<SearchMst>();
            mslist = sservice.search(ms_cd);
            String msname = mslist.get(0).getMs_name(); //get0でとろうとするからえらーでる
           // String flg = searchForm.getFlg();
            mav.addObject("flg", flg);
            mav.setViewName("search");
            //mav.addObject("flg", flg);
            mav.addObject("datalist", mslist);
            mav.addObject("msname", msname);


        }catch(IndexOutOfBoundsException e) {

            //String flg = searchForm.getFlg();
            mav.addObject("flg", flg);
            mav.setViewName("search");
            return mav;

        }
        mav.setViewName("search");*/

        List<SearchMst> mslist = new ArrayList<SearchMst>();
        mslist = sservice.search(ms_cd);
        mav.addObject("datalist", mslist);
        mav.addObject("flg", flg);
        mav.setViewName("search");


        return mav;
    }

    @RequestMapping(value="/search", method=RequestMethod.GET)
    public ModelAndView get(@ModelAttribute MsFormX form, @ModelAttribute SearchForm searchForm,ModelAndView mav) {

        //年月日リスト
        List<MsUriageItem> uriage = new ArrayList<MsUriageItem>();
        uriage = sservice.searchDate();
        String uriagedate = searchForm.getUriage_date();
        mav.addObject("uriagedate", uriagedate);
        mav.addObject("uriage", uriage);
        //mav.setViewName("search");

        //店リスト
        List<MsMst> msmst = new ArrayList<MsMst>();
        String data = searchForm.getMs_cd();
        msmst = msservice.findMs();
        mav.addObject("msmst", msmst);
        mav.addObject("data", data);
        //mav.setViewName("search");

        //商品リスト
        List<ItemMst> itemmst = new ArrayList<ItemMst>();
        itemmst = itemservice.findItem();
        mav.addObject("itemmst", itemmst);
        mav.setViewName("search");

        String flg = searchForm.getFlg();
        mav.addObject("flg", flg);


        return mav;

    }

    //遷移先の分岐
   @RequestMapping(value="/search",params = "return", method=RequestMethod.POST)
    public ModelAndView rtn(
            @ModelAttribute SearchForm searchForm,
            ModelAndView mav
            ) {
        String flg = searchForm.getFlg();
        if(flg.equals("1")) {
            return new ModelAndView("redirect:/");
            }else if(flg.equals("2")) {
                return new ModelAndView("redirect:/mslist");
            }
        return mav;
    }


    @RequestMapping(value="/search", params="search",method=RequestMethod.POST)
    public ModelAndView post(
            ModelAndView mav,
            @ModelAttribute SearchForm searchForm) {

        //年月日リスト
        List<MsUriageItem> uriage = new ArrayList<MsUriageItem>();
        String uriagedate = searchForm.getUriage_date();
        uriage = sservice.searchDate();
        mav.addObject("uriagedate", uriagedate);
        mav.addObject("uriage", uriage);
        mav.setViewName("search");

        //店リスト
        List<MsMst> msmst = new ArrayList<MsMst>();
        String data = searchForm.getMs_cd();//formに選択値を固定
        msmst = msservice.findMs();
        mav.addObject("data", data);
        mav.addObject("msmst", msmst);
        mav.setViewName("search");

        //商品リスト
        List<ItemMst> itemmst = new ArrayList<ItemMst>();
        String item = searchForm.getItem_cd();
        itemmst = itemservice.findItem();
        //String remove = item.replace("全商品", "");
        mav.addObject("item", item);
        mav.addObject("itemmst", itemmst);
        mav.setViewName("search");

        //検索メソッド
        List<SearchMst> datalist = new ArrayList<SearchMst>();

        String date = searchForm.getUriage_date();
        String removed = date.replace("/", "");
        String itemcd = searchForm.getItem_cd();
        String mscd = searchForm.getMs_cd();
        String msname = searchForm.getMs_name();

        if(removed.equals("全期間")) {
            removed = null;

        }else {
            removed = removed + "%";
        }
        if(itemcd.equals("全商品")) {
            itemcd = null;
        }

        datalist = sservice.searchUriage(removed, itemcd, mscd);
        if (datalist.isEmpty()){
            mav.setViewName("search");
            String msg = "該当する条件はありません";
            mav.addObject("msgbox", msg);
        }
        String flg = searchForm.getFlg();
        mav.addObject("flg",flg);
        mav.addObject("msall",msmst);
            mav.addObject("itemall",itemmst);
            mav.addObject("datalist", datalist);
            mav.addObject("msname", msname);
            mav.setViewName("search");

            return mav;

        }

    }
