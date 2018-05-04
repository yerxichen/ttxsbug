package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class JsonBeanConvenienceServices {

    /**
     * ret : 0
     * msg :
     * region : {"ret":"0","msg":"","count":"10","data":[{"code":"","name":"区域"},{"code":"001001001","name":"南通市"},{"code":"001001001001","name":"崇川区"},{"code":"001001001002","name":"港闸区"},{"code":"001001001003","name":"通州区"},{"code":"001001001004","name":"海安县"},{"code":"001001001005","name":"如东县"},{"code":"001001001006","name":"如皋"},{"code":"001001001007","name":"海门"},{"code":"001001001008","name":"启东"}]}
     * sate : {"ret":"0","msg":"","count":"3","data":[{"code":"","name":"不限"},{"code":"0101","name":"个人"},{"code":"0102","name":"企业"}]}
     * categories : {"ret":"0","msg":"","count":"9","data":[{"code":"","name":"类别"},{"code":"1","name":"家政服务"},{"code":"2","name":"装修建材"},{"code":"3","name":"汽车服务"},{"code":"4","name":"婚庆摄影"},{"code":"5","name":"旅游度假"},{"code":"6","name":"休闲娱乐"},{"code":"7","name":"教育培训"},{"code":"8","name":"其他服务"}]}
     * news : {"ret":"0","msg":"","count":"15","data":[{"newsid":"15","name":"徐卫婚庆总公司 性价比较高 优惠多多","img":"20161019150032032.jpg","time":"15:54:16","region":"崇川区"},{"newsid":"14","name":"一诺今生助您幸福","img":"201610191459205920.jpg","time":"15:54:16","region":"通州区"},{"newsid":"13","name":"名媛婚纱摄影\u2014\u2014婚礼策划","img":"20161019145801581.jpg","time":"15:54:16","region":"港闸区"},{"newsid":"12","name":"今世缘婚车租赁有限公司","img":"noproduct.png","time":"15:54:16","region":"南通市"},{"newsid":"11","name":"南通金诚开锁,汽车钥匙,指纹锁等","img":"201610191337403740.jpg","time":"15:54:16","region":"南通市"},{"newsid":"10","name":"110联动\u2014\u2014南通本地人,24小时服务","img":"20161019133701371.jpg","time":"15:54:16","region":"南通市"},{"newsid":"9","name":"专业管道疏通,化粪池清理","img":"201610191329412941.jpg","time":"15:54:16","region":"启东"},{"newsid":"8","name":"高压清洗管道,清理化粪池,专车抽粪","img":"201610191327342734.jpg","time":"15:54:16","region":"海门"},{"newsid":"7","name":"专车疏通市政管道 工厂小区雨污管道","img":"201610191326212621.jpg","time":"15:54:16","region":"如皋"},{"newsid":"6","name":"南通专业下水道疏通 马桶疏通","img":"201610191325272527.jpg","time":"15:54:16","region":"海安县"},{"newsid":"5","name":"专业通下水道、马桶、钻孔。免上门费、清理化粪池","img":"201610191324462446.jpg","time":"15:54:16","region":"通州区"},{"newsid":"4","name":"专业保洁 【十年保洁经验】服务全南通","img":"201610191322342234.jpg","time":"15:54:16","region":"南通市"},{"newsid":"3","name":"专业承接家庭、公司、商场等室内空气质量检测","img":"201610191317421742.jpg","time":"15:54:16","region":"海安县"},{"newsid":"2","name":"品质生活,就选通诚","img":"20161019131609169.jpg","time":"15:54:16","region":"如东县"},{"newsid":"1","name":"居民搬家、企业单位搬运、小型搬家,放心的搬家公司","img":"20161019130519519.jpg","time":"15:54:16","region":"港闸区"}]}
     */

    private String ret;
    private String msg;
    private RegionBean region;
    private SateBean sate;
    private CategoriesBean categories;
    private NewsBean news;

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

    public RegionBean getRegion() {
        return region;
    }

    public void setRegion(RegionBean region) {
        this.region = region;
    }

    public SateBean getSate() {
        return sate;
    }

    public void setSate(SateBean sate) {
        this.sate = sate;
    }

    public CategoriesBean getCategories() {
        return categories;
    }

    public void setCategories(CategoriesBean categories) {
        this.categories = categories;
    }

    public NewsBean getNews() {
        return news;
    }

    public void setNews(NewsBean news) {
        this.news = news;
    }

    public static class RegionBean {
        /**
         * ret : 0
         * msg :
         * count : 10
         * data : [{"code":"","name":"区域"},{"code":"001001001","name":"南通市"},{"code":"001001001001","name":"崇川区"},{"code":"001001001002","name":"港闸区"},{"code":"001001001003","name":"通州区"},{"code":"001001001004","name":"海安县"},{"code":"001001001005","name":"如东县"},{"code":"001001001006","name":"如皋"},{"code":"001001001007","name":"海门"},{"code":"001001001008","name":"启东"}]
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
             * code :
             * name : 区域
             */

            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class SateBean {
        /**
         * ret : 0
         * msg :
         * count : 3
         * data : [{"code":"","name":"不限"},{"code":"0101","name":"个人"},{"code":"0102","name":"企业"}]
         */

        private String ret;
        private String msg;
        private String count;
        private List<DataBeanX> data;

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

        public List<DataBeanX> getData() {
            return data;
        }

        public void setData(List<DataBeanX> data) {
            this.data = data;
        }

        public static class DataBeanX {
            /**
             * code :
             * name : 不限
             */

            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class CategoriesBean {
        /**
         * ret : 0
         * msg :
         * count : 9
         * data : [{"code":"","name":"类别"},{"code":"1","name":"家政服务"},{"code":"2","name":"装修建材"},{"code":"3","name":"汽车服务"},{"code":"4","name":"婚庆摄影"},{"code":"5","name":"旅游度假"},{"code":"6","name":"休闲娱乐"},{"code":"7","name":"教育培训"},{"code":"8","name":"其他服务"}]
         */

        private String ret;
        private String msg;
        private String count;
        private List<DataBeanXX> data;

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

        public List<DataBeanXX> getData() {
            return data;
        }

        public void setData(List<DataBeanXX> data) {
            this.data = data;
        }

        public static class DataBeanXX {
            /**
             * code :
             * name : 类别
             */

            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class NewsBean {
        /**
         * ret : 0
         * msg :
         * count : 15
         * data : [{"newsid":"15","name":"徐卫婚庆总公司 性价比较高 优惠多多","img":"20161019150032032.jpg","time":"15:54:16","region":"崇川区"},{"newsid":"14","name":"一诺今生助您幸福","img":"201610191459205920.jpg","time":"15:54:16","region":"通州区"},{"newsid":"13","name":"名媛婚纱摄影\u2014\u2014婚礼策划","img":"20161019145801581.jpg","time":"15:54:16","region":"港闸区"},{"newsid":"12","name":"今世缘婚车租赁有限公司","img":"noproduct.png","time":"15:54:16","region":"南通市"},{"newsid":"11","name":"南通金诚开锁,汽车钥匙,指纹锁等","img":"201610191337403740.jpg","time":"15:54:16","region":"南通市"},{"newsid":"10","name":"110联动\u2014\u2014南通本地人,24小时服务","img":"20161019133701371.jpg","time":"15:54:16","region":"南通市"},{"newsid":"9","name":"专业管道疏通,化粪池清理","img":"201610191329412941.jpg","time":"15:54:16","region":"启东"},{"newsid":"8","name":"高压清洗管道,清理化粪池,专车抽粪","img":"201610191327342734.jpg","time":"15:54:16","region":"海门"},{"newsid":"7","name":"专车疏通市政管道 工厂小区雨污管道","img":"201610191326212621.jpg","time":"15:54:16","region":"如皋"},{"newsid":"6","name":"南通专业下水道疏通 马桶疏通","img":"201610191325272527.jpg","time":"15:54:16","region":"海安县"},{"newsid":"5","name":"专业通下水道、马桶、钻孔。免上门费、清理化粪池","img":"201610191324462446.jpg","time":"15:54:16","region":"通州区"},{"newsid":"4","name":"专业保洁 【十年保洁经验】服务全南通","img":"201610191322342234.jpg","time":"15:54:16","region":"南通市"},{"newsid":"3","name":"专业承接家庭、公司、商场等室内空气质量检测","img":"201610191317421742.jpg","time":"15:54:16","region":"海安县"},{"newsid":"2","name":"品质生活,就选通诚","img":"20161019131609169.jpg","time":"15:54:16","region":"如东县"},{"newsid":"1","name":"居民搬家、企业单位搬运、小型搬家,放心的搬家公司","img":"20161019130519519.jpg","time":"15:54:16","region":"港闸区"}]
         */

        private String ret;
        private String msg;
        private String count;
        private List<DataBeanXXX> data;

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

        public List<DataBeanXXX> getData() {
            return data;
        }

        public void setData(List<DataBeanXXX> data) {
            this.data = data;
        }

        public static class DataBeanXXX {
            /**
             * newsid : 15
             * name : 徐卫婚庆总公司 性价比较高 优惠多多
             * img : 20161019150032032.jpg
             * time : 15:54:16
             * region : 崇川区
             */

            private String newsid;
            private String name;
            private String img;
            private String time;
            private String region;

            public String getNewsid() {
                return newsid;
            }

            public void setNewsid(String newsid) {
                this.newsid = newsid;
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

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }
        }
    }
}
