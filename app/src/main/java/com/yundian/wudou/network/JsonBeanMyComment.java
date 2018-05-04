package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class JsonBeanMyComment {

    /**
     * ret : 0
     * msg :
     * data : [{"pname":"福临门 葵花籽原香食用调和油","pid":"163","time":"16.12.24 10:37","percentage":"5","message":"5","imgs":[{"img":"20161224103722372209.png"}]},{"pname":"西湖牌龙井茶 2016新茶 茶叶绿茶","pid":"157","time":"16.12.24 10:37","percentage":"4","message":"4","imgs":[{"img":"2016122410370737709.png"}]},{"pname":"闽乐手撕面包2斤","pid":"124","time":"16.12.24 10:37","percentage":"3","message":"3","imgs":[{"img":"20161224103614361409.png"}]},{"pname":"恒蜂源旗舰店 精品鸭 恒沃3足年老鸭","pid":"86","time":"16.12.24 10:37","percentage":"2","message":"2","imgs":[{"img":"2016122410360336309.png"},{"img":"20161224103550355009.png"}]},{"pname":"红心猕猴桃 30粒","pid":"95","time":"16.12.24 10:37","percentage":"1","message":"1","imgs":[{"img":"20161224103542354209.png"},{"img":"20161224103530353009.png"}]},{"pname":"阿里智能LED吸顶灯方形客厅灯","pid":"214","time":"16.12.22 17:11","percentage":"1","message":"测试","imgs":[{"img":"2016122217110511509.png"}]},{"pname":"欧普照明led吸顶灯","pid":"213","time":"16.12.22 16:46","percentage":"3","message":"测试评价内容","imgs":[{"img":"20161222164541454109.png"}]},{"pname":"红心猕猴桃 30粒","pid":"95","time":"16.12.22 16:26","percentage":"4","message":"这个很好吃","imgs":[{"img":"20161222162529252909.png"}]},{"pname":"港荣蒸三明治蛋糕","pid":"30","time":"16.12.09 10:28","percentage":"0","message":"","imgs":[]},{"pname":"三惠蜜方鲜蛋糕486g整箱","pid":"29","time":"16.12.09 10:28","percentage":"0","message":"","imgs":[]},{"pname":"好丽友 Q蒂多层蛋糕","pid":"28","time":"16.12.09 10:28","percentage":"1","message":"","imgs":[]},{"pname":"港荣蒸三明治蛋糕","pid":"30","time":"16.12.09 10:18","percentage":"0","message":"很不醋意哦","imgs":[]},{"pname":"三惠蜜方鲜蛋糕486g整箱","pid":"29","time":"16.12.09 10:18","percentage":"4","message":"很不醋意哦","imgs":[]},{"pname":"四川重庆正宗红薯粉条手工粗","pid":"201","time":"16.12.08 14:43","percentage":"3","message":"不多","imgs":[]},{"pname":"阿里智能LED吸顶灯方形客厅灯","pid":"214","time":"16.12.08 14:34","percentage":"3","message":"grr","imgs":[]},{"pname":"奥朵客厅灯长方形led吸顶灯","pid":"215","time":"16.12.07 16:45","percentage":"0","message":"","imgs":[{"img":"20161207164342434209.png"}]},{"pname":"阿里智能LED吸顶灯方形客厅灯","pid":"214","time":"16.12.07 16:03","percentage":"0","message":"","imgs":[]},{"pname":"阿里智能LED吸顶灯方形客厅灯","pid":"214","time":"16.12.07 16:03","percentage":"3","message":"很好","imgs":[]},{"pname":"","pid":"0","time":"16.10.27 18:48","percentage":"2","message":"我的评价是空的","imgs":[]},{"pname":"","pid":"0","time":"16.10.26 11:44","percentage":"1","message":"鸡蛋很新鲜啊","imgs":[]}]
     */

    private String ret;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pname : 福临门 葵花籽原香食用调和油
         * pid : 163
         * time : 16.12.24 10:37
         * percentage : 5
         * message : 5
         * imgs : [{"img":"20161224103722372209.png"}]
         */

        private String pname;
        private String pid;
        private String time;
        private String percentage;
        private String message;
        private List<ImgsBean> imgs;

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class ImgsBean {
            /**
             * img : 20161224103722372209.png
             */

            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
