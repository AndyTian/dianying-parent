package com.tianl.dianying.controller;

import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.common.page.PagerHelper;
import com.tianl.dianying.entity.Ord;
import com.tianl.dianying.entity.Play;
import com.tianl.dianying.entity.User;
import com.tianl.dianying.service.OrdService;
import com.tianl.dianying.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
@Controller
@RequestMapping(value = "admin/ord")
public class OrdController extends BaseController{

    @Autowired
    private OrdService ordService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, PageCondition condition){
        List<Ord> list = ordService.findAll(condition);
        Pager<Ord> pager = new Pager<Ord>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        pager.setTotalCount(condition.getTotalSize());
        PagerHelper<Ord> pagerHelper = new PagerHelper<Ord>();
        pagerHelper.setPager(pager);
        pagerHelper.setBaseUrl("/admin/ord/list");

        model.addAttribute("pagerHelper", pagerHelper);
        model.addAttribute("list", list);
        return "ord/list";
    }


}
