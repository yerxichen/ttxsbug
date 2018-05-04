package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class JsonBeanMoreComment {
    /**
     * ret : 0
     * msg :
     * data : [{"username":"njsqah","time":"11.03","percentage":"3","message":"很好吃","imgs":[{"img":""}]},{"username":"njsqah","time":"11.02","percentage":"3","message":"CNN","imgs":[{"img":""}]}]
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
         * username : njsqah
         * time : 11.03
         * percentage : 3
         * message : 很好吃
         * imgs : [{"img":""}]
         */

        private String username;
        private String time;
        private String percentage;
        private String message;
        private List<ImgsBean> imgs;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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
             * img :
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
