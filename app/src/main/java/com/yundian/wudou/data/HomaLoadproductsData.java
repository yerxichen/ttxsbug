package com.yundian.wudou.data;

import java.util.List;

/**
 * Created by taozipc on 2017/11/28.
 */

public class HomaLoadproductsData {

    /**
     * ret : 0
     * msg :
     * count : 6
     * data : [{"pid":"989","name":"思念大馅馄饨（猪肉荠菜）500g","shopprice":"14.50","img":"201711251619471947.png"},{"pid":"985","name":"蟹排（安井）200g","shopprice":"5.20","img":"201711251620102010.png"},{"pid":"956","name":"羊排 约500g","shopprice":"29.80","img":"201711251620332033.png"},{"pid":"685","name":"海天老抽王1.9L","shopprice":"17.50","img":"20171125162100210.png"},{"pid":"658","name":"臀尖肉 500g","shopprice":"13.50","img":"201711251621232123.png"},{"pid":"641","name":"小青菜350g","shopprice":"1.50","img":"201711251621452145.png"}]
     * data2 : [{"pid":"989","name":"思念大馅馄饨（猪肉荠菜）500g","shopprice":"14.50","img":"201711251619471947.png"},{"pid":"985","name":"蟹排（安井）200g","shopprice":"5.20","img":"201711251620102010.png"},{"pid":"956","name":"羊排 约500g","shopprice":"29.80","img":"201711251620332033.png"},{"pid":"685","name":"海天老抽王1.9L","shopprice":"17.50","img":"20171125162100210.png"},{"pid":"658","name":"臀尖肉 500g","shopprice":"13.50","img":"201711251621232123.png"},{"pid":"641","name":"小青菜350g","shopprice":"1.50","img":"201711251621452145.png"}]
     * data3 : [{"pid":"989","name":"思念大馅馄饨（猪肉荠菜）500g","shopprice":"14.50","img":"201711251619471947.png"},{"pid":"985","name":"蟹排（安井）200g","shopprice":"5.20","img":"201711251620102010.png"},{"pid":"956","name":"羊排 约500g","shopprice":"29.80","img":"201711251620332033.png"},{"pid":"685","name":"海天老抽王1.9L","shopprice":"17.50","img":"20171125162100210.png"},{"pid":"658","name":"臀尖肉 500g","shopprice":"13.50","img":"201711251621232123.png"},{"pid":"641","name":"小青菜350g","shopprice":"1.50","img":"201711251621452145.png"}]
     */

    private String ret;
    private String msg;
    private String count;
    private List<DataBean> data;
    private List<Data2Bean> data2;
    private List<Data3Bean> data3;

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

    public List<Data2Bean> getData2() {
        return data2;
    }

    public void setData2(List<Data2Bean> data2) {
        this.data2 = data2;
    }

    public List<Data3Bean> getData3() {
        return data3;
    }

    public void setData3(List<Data3Bean> data3) {
        this.data3 = data3;
    }

    public static class DataBean {
        /**
         * pid : 989
         * name : 思念大馅馄饨（猪肉荠菜）500g
         * shopprice : 14.50
         * img : 201711251619471947.png
         */

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

    public static class Data2Bean {
        /**
         * pid : 989
         * name : 思念大馅馄饨（猪肉荠菜）500g
         * shopprice : 14.50
         * img : 201711251619471947.png
         */

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

    public static class Data3Bean {
        /**
         * pid : 989
         * name : 思念大馅馄饨（猪肉荠菜）500g
         * shopprice : 14.50
         * img : 201711251619471947.png
         */

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
