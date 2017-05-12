package com.tianl.dianying.controller;

import com.alibaba.fastjson.JSON;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.common.page.PagerHelper;
import com.tianl.dianying.common.pojo.HttpResponse;
import com.tianl.dianying.entity.Cinema;
import com.tianl.dianying.entity.Movie;
import com.tianl.dianying.entity.Play;
import com.tianl.dianying.service.CinemaService;
import com.tianl.dianying.service.MovieService;
import com.tianl.dianying.service.PlayService;
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
 * Created by Administrator on 2017/2/8.
 */
@Controller
@RequestMapping(value = "/admin/play")
public class PlayController {

    @Autowired
    private PlayService playService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, PageCondition condition){
        List<Play> list = playService.findAll(condition);
        Pager<Play> pager = new Pager<Play>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        pager.setTotalCount(condition.getTotalSize());
        PagerHelper<Play> pagerHelper = new PagerHelper<Play>();
        pagerHelper.setPager(pager);
        pagerHelper.setBaseUrl("/admin/play/list");

        List<Movie> movieList = movieService.findAllMovie();
        List<Cinema> cinemaList = cinemaService.findAllCinema();
        model.addAttribute("movieList", movieList);
        model.addAttribute("cinemaList", cinemaList);
        model.addAttribute("pagerHelper", pagerHelper);
        model.addAttribute("list", list);
        return "play/list";
    }

    @RequestMapping(value = "/showAdd", method = RequestMethod.GET)
    public String showAdd(Model model) {
        List<Movie> movieList = movieService.findAllMovie();
        List<Cinema> cinemaList = cinemaService.findAllCinema();
        model.addAttribute("movieList", movieList);
        model.addAttribute("cinemaList", cinemaList);
        return "/play/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse add(String movieId, String cinemaId, String videoHall, String showtime, String seatNum) {
        try{
            if (StringUtils.isEmpty(movieId)){
                return getFailResponse("电影id为空");
            }
            if (StringUtils.isEmpty(cinemaId)){
                return getFailResponse("影院id为空");
            }
            if (StringUtils.isEmpty(videoHall)){
                return getFailResponse("放映厅为空");
            }
            if (StringUtils.isEmpty(showtime)){
                return getFailResponse("放映时间为空");
            }
            if (StringUtils.isEmpty(seatNum)){
                return getFailResponse("剩余座位数为空");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Play play = new Play();
            play.setCinemaId(Long.valueOf(cinemaId));
            play.setMovieId(Long.valueOf(movieId));
            play.setVideoHall(videoHall);
            play.setShowtime(sdf.parse(showtime));
            play.setSeatNum(seatNum);
            play.setCreateDate(new Date());
            play.setModifyDate(new Date());
            long result = playService.insert(play);

            if(result == 0){
                return getFailResponse("插入记录失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return getFailResponse("系统繁忙");
        }
        return getSuccessResponse("添加成功");
    }

    @RequestMapping(value = "/showUpdate/{id}", method = RequestMethod.GET)
    public String showUpdate(@PathVariable final Long id, Model model){
        List<Movie> movieList = movieService.findAllMovie();
        List<Cinema> cinemaList = cinemaService.findAllCinema();
        Play play = playService.findById(id);
        model.addAttribute("movieList", movieList);
        model.addAttribute("cinemaList", cinemaList);
        model.addAttribute("play",play);
        return "/play/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse update(@PathVariable final Long id, String movieId, String cinemaId, String videoHall, String showtime, String seatNum){
        try{
            if (StringUtils.isEmpty(movieId)){
                return getFailResponse("电影id为空");
            }
            if (StringUtils.isEmpty(cinemaId)){
                return getFailResponse("影院id为空");
            }
            if (StringUtils.isEmpty(videoHall)){
                return getFailResponse("放映厅为空");
            }
            if (StringUtils.isEmpty(showtime)){
                return getFailResponse("放映时间为空");
            }
            if (StringUtils.isEmpty(seatNum)){
                return getFailResponse("剩余座位数为空");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Play play = playService.findById(id);
            play.setCinemaId(Long.valueOf(cinemaId));
            play.setMovieId(Long.valueOf(movieId));
            play.setVideoHall(videoHall);
            play.setShowtime(sdf.parse(showtime));
            play.setSeatNum(seatNum);

            boolean result = playService.update(play);

            if(!result){
                return getFailResponse("更新失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            return getFailResponse("系统繁忙");
        }
        return getSuccessResponse("更新成功");
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse delete(@PathVariable Long id)
    {
        try {
            boolean result = playService.deleteById(id);
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
