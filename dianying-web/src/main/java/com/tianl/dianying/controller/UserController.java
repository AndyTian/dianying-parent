package com.tianl.dianying.controller;

import com.alibaba.fastjson.JSON;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.common.page.PagerHelper;
import com.tianl.dianying.entity.User;
import com.tianl.dianying.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public String list(Model model, PageCondition condition){
        List<User> list = userService.findAll(condition);
        Pager<User> pager = new Pager<User>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        pager.setTotalCount(condition.getTotalSize());
        PagerHelper<User> pagerHelper = new PagerHelper<User>();
        pagerHelper.setPager(pager);
        pagerHelper.setBaseUrl("/admin/user/list");
        model.addAttribute("pagerHelper", pagerHelper);
        model.addAttribute("list", list);

        System.out.println(JSON.toJSONString(list));

        return "/user/list";
    }


}
