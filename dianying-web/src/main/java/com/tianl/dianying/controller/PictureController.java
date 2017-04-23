package com.tianl.dianying.controller;

import com.alibaba.fastjson.JSON;
import com.tianl.dianying.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/31.
 */
@Controller
public class PictureController extends BaseController{

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/pic/upload",  method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, MultipartFile uploadFile){
        Map result = pictureService.uploadPicture(request, uploadFile);
        //为了保证功能的兼容性，需要把Result转换成json格式的字符串。
        String json = JSON.toJSONString(result);
        return json;
    }

    @RequestMapping(value = "/testt",  method = RequestMethod.GET)
    public String testt(){
        return "/movie/testt";
    }
}
