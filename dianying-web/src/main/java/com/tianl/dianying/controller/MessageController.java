package com.tianl.dianying.controller;

import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.common.page.PagerHelper;
import com.tianl.dianying.common.pojo.HttpResponse;
import com.tianl.dianying.entity.Message;
import com.tianl.dianying.entity.User;
import com.tianl.dianying.service.MessageService;
import com.tianl.dianying.service.UserService;
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
@RequestMapping(value = "/admin/message")
public class MessageController extends BaseController{

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, PageCondition condition){
        List<Message> list = messageService.findAll(condition);
        Pager<Message> pager = new Pager<Message>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        pager.setTotalCount(condition.getTotalSize());
        PagerHelper<Message> pagerHelper = new PagerHelper<Message>();
        pagerHelper.setPager(pager);
        pagerHelper.setBaseUrl("/admin/message/list");

        List<User> userList = userService.findALLUser();
        model.addAttribute("userList", userList);
        model.addAttribute("pagerHelper", pagerHelper);
        model.addAttribute("list", list);
        return "message/list";
    }

    @RequestMapping(value = "/showAdd", method = RequestMethod.GET)
    public String showAdd(Model model) {
        List<User> list = userService.findALLUser();
        model.addAttribute("userList", list);
        return "/message/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse add(String userId, String content) {
        try {
            if (StringUtils.isEmpty(userId)){
                return getFailResponse("用户id不许为空");
            }
            if (StringUtils.isEmpty(content)){
                return getFailResponse("消息内容不许为空");
            }

            Message message = new Message();
            message.setUserId(Long.valueOf(userId));
            message.setContent(content);
            message.setState(0);
            message.setCreateDate(new Date());
            message.setCreateDate(new Date());

            long result = messageService.insert(message);

            if(result == 0){
                return getFailResponse("插入记录失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return getFailResponse("系统繁忙");
        }
        return getSuccessResponse("插入数据成功");
    }

    @RequestMapping(value = "/showUpdate/{id}", method = RequestMethod.GET)
    public String showUpdate(@PathVariable final Long id, Model model){
        List<User> list = userService.findALLUser();
        Message message = messageService.findById(id);
        model.addAttribute("userList", list);
        model.addAttribute("message", message);
        return "/message/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse update(@PathVariable final Long id, String userId, String content){
        try{
            if (StringUtils.isEmpty(userId)){
                return getFailResponse("用户id为空");
            }
            if (StringUtils.isEmpty(content)){
                return getFailResponse("消息内容为空");
            }
            Message message = messageService.findById(id);
            message.setUserId(Long.valueOf(userId));
            message.setContent(content);
            message.setModifyDate(new Date());
            boolean result = messageService.update(message);
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
            boolean result = messageService.deleteById(id);
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
