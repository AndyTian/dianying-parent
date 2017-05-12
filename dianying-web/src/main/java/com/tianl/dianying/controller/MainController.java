package com.tianl.dianying.controller;

import com.alibaba.fastjson.JSON;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.common.page.PagerHelper;
import com.tianl.dianying.common.pojo.HttpResponse;
import com.tianl.dianying.entity.*;
import com.tianl.dianying.enumeration.GenderType;
import com.tianl.dianying.service.*;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Administrator on 2016/12/11.
 */
@Controller
public class MainController extends BaseController{


    @Value("#{configProperties['session.user.name']}")
    protected String sessionUser;

    @Value("#{configProperties['session.admin.name']}")
    protected String adminSessionUser;

    @Value("#{configProperties['user.name']}")
    private String name;

    @Value("#{configProperties['user.pass']}")
    private String pass;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private PingjiaService pingjiaService;

    @Autowired
    private OrdService ordService;

    @Autowired
    private PlayService playService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String login() {
        return "main";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, PageCondition condition) {
        if (condition.getPageNumber() == null){
            condition.setPageNumber(1);
        }
        if (condition.getPageSize() == null){
            condition.setPageSize(12);
        }
        List<Movie> list = movieService.findAll(condition);
        Pager<Movie> pager = new Pager<Movie>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        pager.setTotalCount(condition.getTotalSize());
        PagerHelper<Movie> pagerHelper = new PagerHelper<Movie>();
        pagerHelper.setPager(pager);
        pagerHelper.setBaseUrl("/");

        model.addAttribute("pagerHelper", pagerHelper);
        model.addAttribute("list", list);
        return "index";
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String adminLoginPage(HttpSession session, Model model, HttpServletRequest request) {
        Object member = (Object)session.getAttribute(adminSessionUser);
        if (member != null){
            return "main";
        }

        return "adminLogin";
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String adminLogin(HttpServletRequest request, String username, String password){
        System.out.println(username);
        if( !(name.equals(username) && pass.equals(password))){
            logger.warn("{}登录失败",username);
            return "adminLogin";
        }

        request.getSession().setAttribute(adminSessionUser, username);
        logger.info("{}登录成功",username);
        return "main";
    }

    @RequestMapping(value = "admin/logout")
    public String adminLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "adminLogin";
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session, Model model, HttpServletRequest request) {
        Object member = (Object)session.getAttribute(sessionUser);
        if (member != null){
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, String username, String password, Model model) {
        User user = userService.findByUsername(username);
        System.out.println(username);
        if(user != null){
            System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
            if(user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
                request.getSession().setAttribute("sessionUser", user);
                logger.info("{}登录成功",user.getUsername());
                return "redirect:/";

            } else {
                model.addAttribute("errorMsg", "密码错误");
                return "login";
            }
        } else {
            model.addAttribute("errorMsg", "用户不存在");
            return "login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }

    @RequestMapping(value = "/register")
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String register(Model model, String username, String password, String nickname, String name, GenderType gender, String phonenumber){
        User user = userService.findByUsername(username);
        if (user != null){
            model.addAttribute("errorMsg", "用户名已存在");
            return "register";
        }
        System.out.println(nickname);
        User entity = new User();
        entity.setUsername(username);
        entity.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        entity.setNickname(nickname);
        entity.setName(name);
        entity.setGender(gender);
        entity.setPhonenumber(phonenumber);
        entity.setCreateDate(new Date());
        entity.setModifyDate(new Date());
        long result = userService.insert(entity);
        if (result == 0){
            model.addAttribute("errorMsg", "注册失败");
            return "register";
        }
        return "login";
    }


    @RequestMapping(value = "/cinema/allList", method = RequestMethod.GET)
    @ResponseBody
    public List<Cinema> allList() {
        List<Cinema> list = cinemaService.findAllCinema();
        return list;
    }

    @RequestMapping(value = "/movie/single/{id}", method = RequestMethod.GET)
    public String single(@PathVariable final Long id, Model model){
        Movie movie = movieService.findById(id);
        List<Cinema> cinemaList = cinemaService.findAllCinema();
        List<Pingjia> pingjiaList = pingjiaService.findByMovieId(id);
        System.out.println(JSON.toJSONString(pingjiaList));
        model.addAttribute("movie", movie);
        model.addAttribute("cinemaList",cinemaList);
        model.addAttribute("pingjiaList", pingjiaList);
        return "/single";
    }

    @RequestMapping(value = "/ord/add/{id}", method = RequestMethod.GET)
    public String add(@PathVariable long id, HttpServletRequest request){
        Play play = playService.findById(id);
        int seatNum = Integer.valueOf(play.getSeatNum());
        if(seatNum <= 0){
            return "error";
        }else {
            play.setSeatNum(String.valueOf(--seatNum));
            play.setModifyDate(new Date());
            boolean b = playService.update(play);
            if(!b){
                return "error";
            }
            Ord order = new Ord();
            User user = (User) request.getSession().getAttribute(sessionUser);
            order.setUserId(user.getId());
            order.setPayState(1);
            order.setPlayId(play.getId());
            order.setCreateDate(new Date());
            order.setModifyDate(new Date());
            long result = ordService.insert(order);
            if (result == 0){
                return "error";
            }

        }
        return "order";
    }

    @RequestMapping(value = "/ord/list", method = RequestMethod.GET)
    public String ordAdd(HttpServletRequest request, Model model){
        User user = (User)request.getSession().getAttribute(sessionUser);
        List<Ord> list = ordService.findByUserId(user.getId());
        model.addAttribute("list", list);
        return "order";
    }

    @RequestMapping(value = "/pingjia/add/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse add(@PathVariable Long id, String content, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(sessionUser);
        try {
            if (id == null) {
                return getFailResponse("电影id不能为空");
            }
            if (StringUtils.isEmpty(content)) {
                return getFailResponse("评价内容不能为空");
            }
            Pingjia pingjia = new Pingjia();
            pingjia.setMovieId(id);
            pingjia.setUserId(user.getId());
            pingjia.setContent(content);
            pingjia.setModifyDate(new Date());
            pingjia.setCreateDate(new Date());
            long result = pingjiaService.insert(pingjia);
            if (result != 1){
                return getFailResponse("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getFailResponse("系统繁忙");
        }
        return  getSuccessResponse("添加成功");
    }

    @RequestMapping(value = "/play/list/{movieId}/{cinemaId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Play> list(@PathVariable long movieId, @PathVariable long cinemaId){
        List<Play> list = new ArrayList<Play>();
        try {
            list = playService.findByCondition(movieId, cinemaId);
            System.out.println(JSON.toJSONString(list));
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
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

    @RequestMapping(value = "/test",  method = RequestMethod.GET)
    @ResponseBody
    public Play test(){
        Play play = playService.findById(1L);
        Pingjia pingjia = pingjiaService.findById(2L);
        System.out.println(play.getMovieId());
        System.out.println(pingjia.getUserId());
        return play;
    }

}
