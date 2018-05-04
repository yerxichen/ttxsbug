package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class JsonBeanEvaluateProduct {

    /**
     * ret : 0
     * msg :
     * oid : 364
     * storename : 物兜自营店铺
     * data : [{"pid":"215","name":"奥朵客厅灯长方形led吸顶灯","img":"201609301825342534.jpg","isreviews":"1","reviews":{"time":"12.07","percentage":"3","message":"","imgs":[{"img":"20161208144215421509.png"},{"img":"20161208143628362809.png"},{"img":"20161208141910191009.png"},{"img":"20161208141811181109.png"},{"img":"2016120814051751709.png"},{"img":"2016120814034634609.png"},{"img":"20161207164342434209.png"},{"img":"20161207161116111609.png"}]}}]
     */

    private String ret;
    private String msg;
    private String oid;
    private String storename;
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

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pid : 215
         * name : 奥朵客厅灯长方形led吸顶灯
         * img : 201609301825342534.jpg
         * isreviews : 1
         * reviews : {"time":"12.07","percentage":"3","message":"","imgs":[{"img":"20161208144215421509.png"},{"img":"20161208143628362809.png"},{"img":"20161208141910191009.png"},{"img":"20161208141811181109.png"},{"img":"2016120814051751709.png"},{"img":"2016120814034634609.png"},{"img":"20161207164342434209.png"},{"img":"20161207161116111609.png"}]}
         */

        private String pid;
        private String name;
        private String img;
        private String isreviews;
        private ReviewsBean reviews;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIsreviews() {
            return isreviews;
        }

        public void setIsreviews(String isreviews) {
            this.isreviews = isreviews;
        }

        public ReviewsBean getReviews() {
            return reviews;
        }

        public void setReviews(ReviewsBean reviews) {
            this.reviews = reviews;
        }

        public static class ReviewsBean {
            /**
             * time : 12.07
             * percentage : 3
             * message :
             * imgs : [{"img":"20161208144215421509.png"},{"img":"20161208143628362809.png"},{"img":"20161208141910191009.png"},{"img":"20161208141811181109.png"},{"img":"2016120814051751709.png"},{"img":"2016120814034634609.png"},{"img":"20161207164342434209.png"},{"img":"20161207161116111609.png"}]
             */

            private String time;
            private String percentage;
            private String message;
            private List<ImgsBean> imgs;

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
                 * img : 20161208144215421509.png
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
}
