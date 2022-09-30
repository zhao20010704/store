package com.zzt.store.util;

/**
 * Json格式的数据进行响应
 */

public class JsonResult<E> {
    /**
     * 状态码
     */
    private Integer state;
    /**
     * 描述信息
     */
    private String massage;
    /**
     * 数据
     */
    private E data;

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable throwable) {
        this.massage= throwable.getMessage();
    }
    public JsonResult(Integer state,  E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
