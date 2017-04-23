package com.tianl.dianying.controller;

import com.alibaba.fastjson.JSON;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.common.page.PagerHelper;
import com.tianl.dianying.common.pojo.HttpResponse;
import com.tianl.dianying.entity.Cinema;
import com.tianl.dianying.entity.Movie;
import com.tianl.dianying.entity.Pingjia;
import com.tianl.dianying.service.CinemaService;
import com.tianl.dianying.service.MovieService;
import com.tianl.dianying.service.PingjiaService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
@Controller
@RequestMapping(value = "/admin/movie")
public class MovieController extends BaseController{

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private PingjiaService pingjiaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, PageCondition condition){
        List<Movie> list = movieService.findAll(condition);
        Pager<Movie> pager = new Pager<Movie>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        pager.setTotalCount(condition.getTotalSize());
        PagerHelper<Movie> pagerHelper = new PagerHelper<Movie>();
        pagerHelper.setPager(pager);
        pagerHelper.setBaseUrl("/admin/movie/list");

        model.addAttribute("pagerHelper", pagerHelper);
        model.addAttribute("list", list);

        return "/movie/list";
    }

    @RequestMapping(value = "/showAdd", method = RequestMethod.GET)
    public String showAdd(){
        return "/movie/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse add(String name, String pic, String director, String stars, String showtime){
        try {
            if(StringUtils.isEmpty(name)){
                return getFailResponse("电影名不能为空");
            }
            if(StringUtils.isEmpty(pic)){
                return getFailResponse("图片链接不能为空");
            }
            if(StringUtils.isEmpty(director)){
                return getFailResponse("导演不能为空");
            }
            if(StringUtils.isEmpty(stars)){
                return getFailResponse("主演不能为空");
            }
            if(StringUtils.isEmpty(showtime)){
                return getFailResponse("上映日期不能为空");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Movie movie = new Movie();
            movie.setName(name);
            movie.setPic(pic);
            movie.setDirector(director);
            movie.setStars(stars);
            movie.setShowtime(sdf.parse(showtime));
            movie.setCreateDate(new Date());
            movie.setModifyDate(new Date());
            long result = movieService.insert(movie);
            if (result != 1) {
                return getFailResponse("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return getFailResponse("系统繁忙");
        }
        return getSuccessResponse("添加成功");
    }

    @RequestMapping(value = "/showUpdate/{id}", method = RequestMethod.GET)
    public String showUpdate(@PathVariable final Long id, Model model){
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        return "/movie/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse update(@PathVariable final Long id, String name, String pic, String director, String stars, String showtime){
        try {
            if (StringUtils.isEmpty(name)) {
                return getFailResponse("电影名不能为空");
            }
            if (StringUtils.isEmpty(pic)) {
                return getFailResponse("图片链接不能为空");
            }
            if (StringUtils.isEmpty(director)) {
                return getFailResponse("导演不能为空");
            }
            if (StringUtils.isEmpty(stars)) {
                return getFailResponse("主演不能为空");
            }
            if (StringUtils.isEmpty(showtime)) {
                return getFailResponse("上映日期不能为空");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Movie movie = movieService.findById(id);
            movie.setName(name);
            movie.setPic(pic);
            movie.setDirector(director);
            movie.setStars(stars);
            movie.setShowtime(sdf.parse(showtime));
            movie.setModifyDate(new Date());
            boolean result = movieService.update(movie);
            if (!result){
                return getSuccessResponse("修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return getFailResponse("系统繁忙");
        }
        return getSuccessResponse("修改成功");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse delete(@PathVariable Long id)
    {
        try {
            boolean result = movieService.deleteById(id);
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
