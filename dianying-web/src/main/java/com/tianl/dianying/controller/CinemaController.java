package com.tianl.dianying.controller;

import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.common.page.PagerHelper;
import com.tianl.dianying.common.pojo.HttpResponse;
import com.tianl.dianying.entity.Cinema;
import com.tianl.dianying.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
@Controller
@RequestMapping(value = "/admin/cinema")
public class CinemaController extends BaseController {

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, PageCondition condition) {
        List<Cinema> list = cinemaService.findAll(condition);
        Pager<Cinema> pager = new Pager<Cinema>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        pager.setTotalCount(condition.getTotalSize());
        PagerHelper<Cinema> pagerHelper = new PagerHelper<Cinema>();
        pagerHelper.setPager(pager);
        pagerHelper.setBaseUrl("/admin/cinema/list");

        model.addAttribute("pagerHelper", pagerHelper);
        model.addAttribute("list", list);

        return "/cinema/list";
    }



    @RequestMapping(value = "/showAdd", method = RequestMethod.GET)
    public String showAdd() {
        return "/cinema/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse add(String name, String pic, String summary) {

            try {
                if (StringUtils.isEmpty(name)) {
                    return getFailResponse("影院名不能为空");
                }
                if (StringUtils.isEmpty(pic)) {
                    return getFailResponse("图片链接不能为空");
                }
                if (StringUtils.isEmpty(summary)){
                    return getFailResponse("影院简介不能为空");
                }
                Cinema cinema = new Cinema();
                cinema.setName(name);
                cinema.setPic(pic);
                cinema.setSummary(summary);
                cinema.setModifyDate(new Date());
                cinema.setCreateDate(new Date());
                long result = cinemaService.insert(cinema);
                if (result != 1){
                    return getFailResponse("添加失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return getFailResponse("系统繁忙");
            }
                return  getSuccessResponse("添加成功");
    }

    @RequestMapping(value = "/showUpdate/{id}", method = RequestMethod.GET)
    public String showUpdate(@PathVariable final Long id, Model model){
        Cinema cinema = cinemaService.findById(id);
        model.addAttribute("cinema", cinema);
        return "cinema/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse update(@PathVariable final Long id, String name, String pic, String summary){
        try {
            if (StringUtils.isEmpty(name)) {
                return getFailResponse("影院名不能为空");
            }
            if (StringUtils.isEmpty(pic)) {
                return getFailResponse("图片链接不能为空");
            }
            if (StringUtils.isEmpty(summary)){
                return getFailResponse("影院简介不能为空");
            }
            Cinema cinema = cinemaService.findById(id);
            cinema.setName(name);
            cinema.setPic(pic);
            cinema.setSummary(summary);
            cinema.setModifyDate(new Date());
            boolean result = cinemaService.update(cinema);
            if (!result){
                return getFailResponse("更新失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return getFailResponse("系统繁忙");
        }
        return  getSuccessResponse("更新成功");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse delete(@PathVariable Long id)
    {
        try {
            boolean result = cinemaService.deleteById(id);
            if (!result){
                return getFailResponse("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return getFailResponse("系统繁忙");
        }
        return getSuccessResponse("删除成功");
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