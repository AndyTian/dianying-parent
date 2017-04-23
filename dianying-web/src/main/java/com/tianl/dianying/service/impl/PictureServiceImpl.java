package com.tianl.dianying.service.impl;

import com.tianl.dianying.service.PictureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2017/1/2.
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Value("#{configProperties['GetFilePath']}")
    private String GetFilePath;

    @Value("#{configProperties['SaveFilePath']}")
    private String SaveFilePath;

    public Map uploadPicture(HttpServletRequest request, MultipartFile uploadFile) {
        Map resultMap = new HashMap();
        try {
            // 生成一个新的文件名
            // 取原始文件名
            String oldName = uploadFile.getOriginalFilename();
            // 生成新文件名
            // UUID.randomUUID();
            String newName = genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            // 图片上传
            String saveFilePath = request.getSession().getServletContext().getRealPath("/");
            String getFilePath = GetFilePath;
            File fileDir = new File(saveFilePath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            FileOutputStream out = new FileOutputStream(saveFilePath +"uploadPic/"+ newName);
            // 写入文件
            out.write(uploadFile.getBytes());
            out.flush();
            out.close();

            resultMap.put("error", 0);
            resultMap.put("url", getFilePath + newName);
            System.out.println(getFilePath + newName);
            return resultMap;

        } catch (Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
    }

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }
}
