package com.tianl.dianying.common.pojo;

/**
 * Created by Administrator on 2017/1/11.
 */
public class HttpResponse {

    /**
     * 成功
     */
    public static final String CODE_OK = "200";

    /**
     * 服务器内部错误
     */
    public static final String CODE_INTERNAL_SERVER_ERROR = "500";

    /**
     * 成功
     */
    public static final String STATUS_SUCCESS = "success";

    /**
     * 失败，用于状态码500-599
     */
    public static final String STATUS_FAIL = "fail";

    private String code;
    private String status;
    private String message;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

