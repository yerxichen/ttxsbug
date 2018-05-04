package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by cookie on 2016/9/29.
 */
public class JsonBeanSelfStoreMoreData {
    /**
     * ret : 0
     * msg :
     * count : 9
     * data : [{"pid":"14","name":"有机小白菜","shopprice":"7.60","img":"20160928163606366.jpg"},{"pid":"13","name":"伊利牛奶","shopprice":"11
     * .00","img":"201609280043144314.jpg"},{"pid":"12","name":"风干牛肉","shopprice":"30.00","img":"5022.png"},{"pid":"11","name":"拉面丸子",
     * "shopprice":"25.00","img":"5021.png"},{"pid":"10","name":"原味牛肉棒","shopprice":"20.00","img":"5020.png"},{"pid":"9","name":"小麻花",
     * "shopprice":"7.00","img":"5019.png"},{"pid":"8","name":"康师傅牛肉面","shopprice":"5.00","img":"5018.png"},{"pid":"6","name":"雪花啤酒",
     * "shopprice":"2.50","img":"5016.png"},{"pid":"5","name":"养乐多酸乳","shopprice":"9.00","img":"5015.png"}]
     */

    private String ret;
    private String msg;
    private String count;
    /**
     * pid : 14
     * name : 有机小白菜
     * shopprice : 7.60
     * img : 20160928163606366.jpg
     */

    private List<DataBean> data;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String pid;
        private String name;
        private String shopprice;
        private String img;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShopprice() {
            return shopprice;
        }

        public void setShopprice(String shopprice) {
            this.shopprice = shopprice;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
