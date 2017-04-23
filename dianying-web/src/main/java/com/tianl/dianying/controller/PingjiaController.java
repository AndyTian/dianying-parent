package com.tianl.dianying.controller;

import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.common.page.PagerHelper;
import com.tianl.dianying.common.pojo.HttpResponse;
import com.tianl.dianying.entity.Cinema;
import com.tianl.dianying.entity.Pingjia;
import com.tianl.dianying.entity.User;
import com.tianl.dianying.service.PingjiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
@Controller
@RequestMapping(value = "/admin/pingjia")
public class PingjiaController {

    @Autowired
    private PingjiaService pingjiaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, PageCondition condition){
        List<Pingjia> list = pingjiaService.findAll(condition);
        Pager<Pingjia> pager = new Pager<Pingjia>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        pager.setTotalCount(condition.getTotalSize());
        PagerHelper<Pingjia> pagerHelper = new PagerHelper<Pingjia>();
        pagerHelper.setPager(pager);
        pagerHelper.setBaseUrl("/admin/pingjia/list");

        model.addAttribute("pagerHelper", pagerHelper);
        model.addAttribute("list", list);
        return "pingjia/list";
    }


    private HttpResponse getSuccessResponse(final String msg) {
        final HttpResponse httpResponse = new HttpResponse();
        httpResponse.setCode(HttpResponse.CODE_OK);
        httpResponse.setStatus(HttpResponse.STATUS_SUCCESS);
        httpResponse.setMessage(msg);
        return httpResponse;
    }

    private HttpResponse getFailResponse(final String msg) {
        final HttpResponse httpResponse = new HttpResponse();
        httpResponse.setCode(HttpResponse.CODE_INTERNAL_SERVER_ERROR);
        httpResponse.setStatus(HttpResponse.STATUS_FAIL);
        httpResponse.setMessage(msg);
        return httpResponse;
    }
}
