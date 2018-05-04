package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/16.
 */
public class AdapterAddressSelectListData {

    private String said;
    private String name;
    private String phone;
    private String locate;
    private String details;

    public AdapterAddressSelectListData(String said, String name, String phone, String locate, String details) {
        this.said = said;
        this.name = name;
        this.phone = phone;
        this.locate = locate;
        this.details = details;
    }

    public String getSaid() {
        return said;
    }

    public void setSaid(String said) {
        this.said = said;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
