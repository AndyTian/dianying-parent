package com.tianl.dianying.service;

import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/2.
 */
public interface PictureService {
    Map uploadPicture(HttpServletRequest request, MultipartFile uploadFile);
}
