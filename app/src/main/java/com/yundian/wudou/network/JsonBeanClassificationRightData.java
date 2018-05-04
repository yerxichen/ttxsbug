package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class JsonBeanClassificationRightData {

    /**
     * ret : 0
     * msg :
     * count : 2
     * data : [{"cateid":"12","catenumber":"001001001","name":"水果","subcategory":[{"cateid":"17","catenumber":"001001001001","name":"梨子","img":"noproduct.png"},{"cateid":"18","catenumber":"001001001002","name":"瓜类","img":"noproduct.png"},{"cateid":"19","catenumber":"001001001003","name":"猕猴桃","img":"noproduct.png"},{"cateid":"20","catenumber":"001001001004","name":"甘蔗","img":"noproduct.png"}]},{"cateid":"13","catenumber":"001001002","name":"蔬菜","subcategory":[{"cateid":"21","catenumber":"001001002001","name":"根茎类","img":"noproduct.png"},{"cateid":"22","catenumber":"001001002002","name":"叶菜类","img":"noproduct.png"},{"cateid":"23","catenumber":"001001002003","name":"瓜果类","img":"noproduct.png"},{"cateid":"24","catenumber":"001001002004","name":"香菇类","img":"noproduct.png"},{"cateid":"25","catenumber":"001001002005","name":"调味类","img":"noproduct.png"}]}]
     */

    private String ret;
    private String msg;
    private String count;
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
        /**
         * cateid : 12
         * catenumber : 001001001
         * name : 水果
         * subcategory : [{"cateid":"17","catenumber":"001001001001","name":"梨子","img":"noproduct.png"},{"cateid":"18","catenumber":"001001001002","name":"瓜类","img":"noproduct.png"},{"cateid":"19","catenumber":"001001001003","name":"猕猴桃","img":"noproduct.png"},{"cateid":"20","catenumber":"001001001004","name":"甘蔗","img":"noproduct.png"}]
         */

        private String cateid;
        private String catenumber;
        private String name;
        private List<SubcategoryBean> subcategory;

        public String getCateid() {
            return cateid;
        }

        public void setCateid(String cateid) {
            this.cateid = cateid;
        }

        public String getCatenumber() {
            return catenumber;
        }

        public void setCatenumber(String catenumber) {
            this.catenumber = catenumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SubcategoryBean> getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(List<SubcategoryBean> subcategory) {
            this.subcategory = subcategory;
        }

        public static class SubcategoryBean {
            /**
             * cateid : 17
             * catenumber : 001001001001
             * name : 梨子
             * img : noproduct.png
             */

            private String cateid;
            private String catenumber;
            private String name;
            private String img;

            public String getCateid() {
                return cateid;
            }

            public void setCateid(String cateid) {
                this.cateid = cateid;
            }

            public String getCatenumber() {
                return catenumber;
            }

            public void setCatenumber(String catenumber) {
                this.catenumber = catenumber;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
