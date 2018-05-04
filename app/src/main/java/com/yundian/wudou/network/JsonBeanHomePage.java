package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by cookie on 2016/9/12.
 */
public class JsonBeanHomePage {

    /**
     * ret : 0
     * msg :
     * adverts1 : {"ret":"0","msg":"","count":"3","data":[{"img":"20161102180821821.jpg","url":"40","urltype":"2"},{"img":"20161102180839839.jpg","url":"2","urltype":"1"},{"img":"20161102180855855.jpg","url":"1","urltype":"2"}]}
     * adverts2 : {"ret":"0","msg":"","count":"5","data":[{"img":"2018.png","url":"7","urltype":"1"},{"img":"2017.png","url":"6","urltype":"2"},{"img":"2016.png","url":"5","urltype":"1"},{"img":"2015.png","url":"4","urltype":"2"},{"img":"201609241011481148.png","url":"1","urltype":"1"}]}
     * adverts3 : {"ret":"0","msg":"","count":"2","data":[{"img":"20161102181008108.jpg","url":"6","urltype":"2"},{"img":"201611021810321032.jpg","url":"5","urltype":"1"}]}
     * adverts4 : {"ret":"0","msg":"","count":"8","data":[{"img":"2024.png","url":"13","urltype":"1"},{"img":"2022.png","url":"11","urltype":"1"},{"img":"2023.png","url":"12","urltype":"1"},{"img":"2021.png","url":"10","urltype":"1"},{"img":"2025.png","url":"14","urltype":"1"},{"img":"201612070926212621.png","url":"10","urltype":"1"},{"img":"201612070926412641.png","url":"1","urltype":"1"},{"img":"201612070926592659.png","url":"6","urltype":"1"}]}
     * products_lower : {"ret":"0","msg":"","count":"9","countdown":"586400","data":[{"pid":"221","name":"马铃薯","shopprice":"3.00","img":"201612291025292529.png"},{"pid":"215","name":"奥朵客厅灯长方形led吸顶灯","shopprice":"367.00","img":"201609301825342534.jpg"},{"pid":"214","name":"阿里智能LED吸顶灯方形客厅灯","shopprice":"54.00","img":"201609301824292429.jpg"},{"pid":"213","name":"欧普照明led吸顶灯","shopprice":"453.00","img":"201609301823262326.jpg"},{"pid":"203","name":"圆型智能4USB多功能插座","shopprice":"26.00","img":"201609301549564956.jpg"},{"pid":"200","name":"方便粉丝粉条面非螺蛳","shopprice":"6.00","img":"20160930154704474.jpg"},{"pid":"199","name":"白家陈记 四川酸辣粉88克/杯*12杯","shopprice":"8.00","img":"20160930154605465.jpg"},{"pid":"197","name":"金鹏新鲜虾皮500gx2袋","shopprice":"58.00","img":"201609301543264326.jpg"},{"pid":"195","name":"绿帝干贝淡干 瑶柱大","shopprice":"89.00","img":"201609301542284228.jpg"}]}
     * stores_nearby : {"ret":"0","msg":"","count":"1","data":[{"storeid":"19","name":"物兜配菜","img":"201612271741464146.jpg","distance":"0米","monthlysales":"0","startvalue":"10.00","startfee":"5.00","isown":"1"}]}
     * products_best : {"ret":"0","msg":"","count":"9","data":[{"pid":"217","name":"木瓜","shopprice":"20.00","img":"2016122718010919.jpg"},{"pid":"206","name":"陶瓷田园仿古电话机家用","shopprice":"356.00","img":"201609301554565456.jpg"},{"pid":"205","name":"蜂花护发素1L柔顺营养护发素","shopprice":"18.00","img":"201609301554115411.png"},{"pid":"201","name":"四川重庆正宗红薯粉条手工粗","shopprice":"21.00","img":"201609301547574757.jpg"},{"pid":"198","name":"力士720ml沐浴露 精油香氛沐浴乳","shopprice":"28.90","img":"201609301544574457.png"},{"pid":"189","name":"塞翁福黄花菜150g","shopprice":"34.00","img":"201609301536393639.jpg"},{"pid":"188","name":"进口Cetaphil丝塔芙洁面乳洗面奶","shopprice":"80.00","img":"201609301534233423.png"},{"pid":"187","name":"日本进口资生堂洗颜专科洗面奶","shopprice":"48.00","img":"201609301532143214.png"},{"pid":"182","name":"完美芦荟胶3只组合正品祛痘淡痘印","shopprice":"89.00","img":"201609301527112711.png"}]}
     * news : {"ret":"0","msg":"","isshow":"0","data":[{"title":"\u201c衣暖冬潮\u201d季正式启动 创意活动温暖整个冬季","newsid":"6"},{"title":"双十二活动红包购物券领取攻略","newsid":"5"}]}
     */

    private String ret;
    private String msg;
    private Adverts1Bean adverts1;
    private Adverts2Bean adverts2;
    public Adverts3Bean adverts3;
    private Adverts4Bean adverts4;
    private ProductsLowerBean products_lower;
    private StoresNearbyBean stores_nearby;
    private ProductsBestBean products_best;
    private Adverts_stores_nearby adverts_stores_nearby;

    public Adverts_stores_nearby getAdverts_stores_nearby() {
        return adverts_stores_nearby;
    }

    public void setAdverts_stores_nearby(Adverts_stores_nearby adverts_stores_nearby) {
        this.adverts_stores_nearby = adverts_stores_nearby;
    }

    public ProductsCompetitiveBean getProducts_competitive() {
        return products_competitive;
    }

    public void setProducts_competitive(ProductsCompetitiveBean products_competitive) {
        this.products_competitive = products_competitive;
    }

    private ProductsCompetitiveBean products_competitive;
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

    public Adverts1Bean getAdverts1() {
        return adverts1;
    }

    public void setAdverts1(Adverts1Bean adverts1) {
        this.adverts1 = adverts1;
    }

    public Adverts2Bean getAdverts2() {
        return adverts2;
    }

    public void setAdverts2(Adverts2Bean adverts2) {
        this.adverts2 = adverts2;
    }

    public Adverts3Bean getAdverts3() {
        return adverts3;
    }

    public void setAdverts3(Adverts3Bean adverts3) {
        this.adverts3 = adverts3;
    }

    public Adverts4Bean getAdverts4() {
        return adverts4;
    }

    public void setAdverts4(Adverts4Bean adverts4) {
        this.adverts4 = adverts4;
    }

    public ProductsLowerBean getProducts_lower() {
        return products_lower;
    }

    public void setProducts_lower(ProductsLowerBean products_lower) {
        this.products_lower = products_lower;
    }

    public StoresNearbyBean getStores_nearby() {
        return stores_nearby;
    }

    public void setStores_nearby(StoresNearbyBean stores_nearby) {
        this.stores_nearby = stores_nearby;
    }

    public ProductsBestBean getProducts_best() {
        return products_best;
    }

    public void setProducts_best(ProductsBestBean products_best) {
        this.products_best = products_best;
    }

    public NewsBean getNews() {
        return news;
    }

    public void setNews(NewsBean news) {
        this.news = news;
    }

    public static class Adverts1Bean {
        /**
         * ret : 0
         * msg :
         * count : 3
         * data : [{"img":"20161102180821821.jpg","url":"40","urltype":"2"},{"img":"20161102180839839.jpg","url":"2","urltype":"1"},{"img":"20161102180855855.jpg","url":"1","urltype":"2"}]
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
             * img : 20161102180821821.jpg
             * url : 40
             * urltype : 2
             */

            private String img;
            private String url;
            private String urltype;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrltype() {
                return urltype;
            }

            public void setUrltype(String urltype) {
                this.urltype = urltype;
            }
        }
    }

    public static class Adverts2Bean {
        /**
         * ret : 0
         * msg :
         * count : 5
         * data : [{"img":"2018.png","url":"7","urltype":"1"},{"img":"2017.png","url":"6","urltype":"2"},{"img":"2016.png","url":"5","urltype":"1"},{"img":"2015.png","url":"4","urltype":"2"},{"img":"201609241011481148.png","url":"1","urltype":"1"}]
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
             * img : 2018.png
             * url : 7
             * urltype : 1
             */

            private String img;
            private String url;
            private String urltype;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrltype() {
                return urltype;
            }

            public void setUrltype(String urltype) {
                this.urltype = urltype;
            }
        }
    }

    public static class Adverts3Bean {
        /**
         * ret : 0
         * msg :
         * count : 2
         * data : [{"img":"20161102181008108.jpg","url":"6","urltype":"2"},{"img":"201611021810321032.jpg","url":"5","urltype":"1"}]
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
             * img : 20161102181008108.jpg
             * url : 6
             * urltype : 2
             */

            private String img;
            private String url;
            private String urltype;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrltype() {
                return urltype;
            }

            public void setUrltype(String urltype) {
                this.urltype = urltype;
            }
        }
    }

    public static class Adverts4Bean {
        /**
         * ret : 0
         * msg :
         * count : 8
         * data : [{"img":"2024.png","url":"13","urltype":"1"},{"img":"2022.png","url":"11","urltype":"1"},{"img":"2023.png","url":"12","urltype":"1"},{"img":"2021.png","url":"10","urltype":"1"},{"img":"2025.png","url":"14","urltype":"1"},{"img":"201612070926212621.png","url":"10","urltype":"1"},{"img":"201612070926412641.png","url":"1","urltype":"1"},{"img":"201612070926592659.png","url":"6","urltype":"1"}]
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
             * img : 2024.png
             * url : 13
             * urltype : 1
             */

            private String img;
            private String url;
            private String urltype;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrltype() {
                return urltype;
            }

            public void setUrltype(String urltype) {
                this.urltype = urltype;
            }
        }
    }

    public static class ProductsLowerBean {
        /**
         * ret : 0
         * msg :
         * count : 9
         * countdown : 586400
         * data : [{"pid":"221","name":"马铃薯","shopprice":"3.00","img":"201612291025292529.png"},{"pid":"215","name":"奥朵客厅灯长方形led吸顶灯","shopprice":"367.00","img":"201609301825342534.jpg"},{"pid":"214","name":"阿里智能LED吸顶灯方形客厅灯","shopprice":"54.00","img":"201609301824292429.jpg"},{"pid":"213","name":"欧普照明led吸顶灯","shopprice":"453.00","img":"201609301823262326.jpg"},{"pid":"203","name":"圆型智能4USB多功能插座","shopprice":"26.00","img":"201609301549564956.jpg"},{"pid":"200","name":"方便粉丝粉条面非螺蛳","shopprice":"6.00","img":"20160930154704474.jpg"},{"pid":"199","name":"白家陈记 四川酸辣粉88克/杯*12杯","shopprice":"8.00","img":"20160930154605465.jpg"},{"pid":"197","name":"金鹏新鲜虾皮500gx2袋","shopprice":"58.00","img":"201609301543264326.jpg"},{"pid":"195","name":"绿帝干贝淡干 瑶柱大","shopprice":"89.00","img":"201609301542284228.jpg"}]
         */

        private String ret;
        private String msg;
        private String count;
        private String countdown;
        private List<DataBeanXXXX> data;

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

        public String getCountdown() {
            return countdown;
        }

        public void setCountdown(String countdown) {
            this.countdown = countdown;
        }

        public List<DataBeanXXXX> getData() {
            return data;
        }

        public void setData(List<DataBeanXXXX> data) {
            this.data = data;
        }

        public static class DataBeanXXXX {
            /**
             * pid : 221
             * name : 马铃薯
             * shopprice : 3.00
             * img : 201612291025292529.png
             */

            private String pid;
            private String name;
            private String shopprice;
            private String img;
            private String marketprice;

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

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

    public static class StoresNearbyBean {
        /**
         * ret : 0
         * msg :
         * count : 1
         * data : [{"storeid":"19","name":"物兜配菜","img":"201612271741464146.jpg","distance":"0米","monthlysales":"0","startvalue":"10.00","startfee":"5.00","isown":"1"}]
         */

        private String ret;
        private String msg;
        private String count;
        private List<DataBeanXXXXX> data;

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

        public List<DataBeanXXXXX> getData() {
            return data;
        }

        public void setData(List<DataBeanXXXXX> data) {
            this.data = data;
        }

        public static class DataBeanXXXXX {
            /**
             * storeid : 19
             * name : 物兜配菜
             * img : 201612271741464146.jpg
             * distance : 0米
             * monthlysales : 0
             * startvalue : 10.00
             * startfee : 5.00
             * isown : 1
             */

            private String storeid;
            private String name;
            private String img;
            private String distance;
            private String monthlysales;
            private String startvalue;
            private String startfee;
            private String isown;

            public String getStoreid() {
                return storeid;
            }

            public void setStoreid(String storeid) {
                this.storeid = storeid;
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

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getMonthlysales() {
                return monthlysales;
            }

            public void setMonthlysales(String monthlysales) {
                this.monthlysales = monthlysales;
            }

            public String getStartvalue() {
                return startvalue;
            }

            public void setStartvalue(String startvalue) {
                this.startvalue = startvalue;
            }

            public String getStartfee() {
                return startfee;
            }

            public void setStartfee(String startfee) {
                this.startfee = startfee;
            }

            public String getIsown() {
                return isown;
            }

            public void setIsown(String isown) {
                this.isown = isown;
            }
        }
    }

    public static class Adverts_stores_nearby
    {
        /*
        "ret": "0",
                "msg": "",
                "count": "1",
                "data": [
        {
            "img": "20171206175208528.png",
                "url": "27",
                "urltype": "1"
        }
        ]
        */

        private String ret;
        private String msg;
        private String count;
        private List<DataBeanImg> data;

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

        public List<DataBeanImg> getData() {
            return data;
        }

        public void setData(List<DataBeanImg> data) {
            this.data = data;
        }


        public static class DataBeanImg {

            /*
            "img":"12341234.png"
            "url":"27"
            "urltype":"1"
            */

            private String img;
            private String url;
            private String urltype;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrltype() {
                return urltype;
            }

            public void setUrltype(String urltype) {
                this.urltype = urltype;
            }
        }
    }




    public static class ProductsBestBean {
        /**
         * ret : 0
         * msg :
         * count : 9
         * data : [{"pid":"217","name":"木瓜","shopprice":"20.00","img":"2016122718010919.jpg"},{"pid":"206","name":"陶瓷田园仿古电话机家用","shopprice":"356.00","img":"201609301554565456.jpg"},{"pid":"205","name":"蜂花护发素1L柔顺营养护发素","shopprice":"18.00","img":"201609301554115411.png"},{"pid":"201","name":"四川重庆正宗红薯粉条手工粗","shopprice":"21.00","img":"201609301547574757.jpg"},{"pid":"198","name":"力士720ml沐浴露 精油香氛沐浴乳","shopprice":"28.90","img":"201609301544574457.png"},{"pid":"189","name":"塞翁福黄花菜150g","shopprice":"34.00","img":"201609301536393639.jpg"},{"pid":"188","name":"进口Cetaphil丝塔芙洁面乳洗面奶","shopprice":"80.00","img":"201609301534233423.png"},{"pid":"187","name":"日本进口资生堂洗颜专科洗面奶","shopprice":"48.00","img":"201609301532143214.png"},{"pid":"182","name":"完美芦荟胶3只组合正品祛痘淡痘印","shopprice":"89.00","img":"201609301527112711.png"}]
         */

        private String ret;
        private String msg;
        private String count;
        private List<DataBeanXXXXXX> data;

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

        public List<DataBeanXXXXXX> getData() {
            return data;
        }

        public void setData(List<DataBeanXXXXXX> data) {
            this.data = data;
        }

        public static class DataBeanXXXXXX {
            /**
             * pid : 217
             * name : 木瓜
             * shopprice : 20.00
             * img : 2016122718010919.jpg
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

    public static class ProductsCompetitiveBean {

        /**
         * ret : 0
         * msg :
         * count : 6
         * Adverts : [{"img":"20171206175208528.png","url":"27","urltype":"1"}]
         * Adverts2 : [{"img":"20171206175208528.png","url":"27","urltype":"1"}]
         * Adverts3 : [{"img":"20171206175208528.png","url":"27","urltype":"1"}]
         * data : [{"pid":"1007","name":"纯手工彩色水饺（猪肉白菜）400g","shopprice":"21.80","img":"201712061748274827.png"},{"pid":"932","name":"巴沙鱼片（冷冻）750-850g","shopprice":"17.60","img":"20171202234200420.png"},{"pid":"845","name":"脆皮椰香糕（12枚装）","shopprice":"14.50","img":"201712022342294229.png"},{"pid":"833","name":"花生米 约500g","shopprice":"7.50","img":"201712022342554255.png"},{"pid":"804","name":"茶干250g","shopprice":"2.60","img":"201712022343184318.png"},{"pid":"523","name":"绿豆面500g","shopprice":"3.30","img":"201712022343414341.png"}]
         * data2 : [{"pid":"1007","name":"纯手工彩色水饺（猪肉白菜）400g","shopprice":"21.80","img":"201712061748274827.png"},{"pid":"932","name":"巴沙鱼片（冷冻）750-850g","shopprice":"17.60","img":"20171202234200420.png"},{"pid":"845","name":"脆皮椰香糕（12枚装）","shopprice":"14.50","img":"201712022342294229.png"},{"pid":"833","name":"花生米 约500g","shopprice":"7.50","img":"201712022342554255.png"},{"pid":"804","name":"茶干250g","shopprice":"2.60","img":"201712022343184318.png"},{"pid":"523","name":"绿豆面500g","shopprice":"3.30","img":"201712022343414341.png"}]
         * data3 : [{"pid":"1007","name":"纯手工彩色水饺（猪肉白菜）400g","shopprice":"21.80","img":"201712061748274827.png"},{"pid":"932","name":"巴沙鱼片（冷冻）750-850g","shopprice":"17.60","img":"20171202234200420.png"},{"pid":"845","name":"脆皮椰香糕（12枚装）","shopprice":"14.50","img":"201712022342294229.png"},{"pid":"833","name":"花生米 约500g","shopprice":"7.50","img":"201712022342554255.png"},{"pid":"804","name":"茶干250g","shopprice":"2.60","img":"201712022343184318.png"},{"pid":"523","name":"绿豆面500g","shopprice":"3.30","img":"201712022343414341.png"}]
         */

        private String ret;
        private String msg;
        private String count;
        private List<AdvertsBean> Adverts;
        private List<Adverts2Bean> Adverts2;
        private List<Adverts3Bean> Adverts3;
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

        public List<AdvertsBean> getAdverts() {
            return Adverts;
        }

        public void setAdverts(List<AdvertsBean> Adverts) {
            this.Adverts = Adverts;
        }

        public List<Adverts2Bean> getAdverts2() {
            return Adverts2;
        }

        public void setAdverts2(List<Adverts2Bean> Adverts2) {
            this.Adverts2 = Adverts2;
        }

        public List<Adverts3Bean> getAdverts3() {
            return Adverts3;
        }

        public void setAdverts3(List<Adverts3Bean> Adverts3) {
            this.Adverts3 = Adverts3;
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

        public static class AdvertsBean {
            /**
             * img : 20171206175208528.png
             * url : 27
             * urltype : 1
             */

            private String img;
            private String url;
            private String urltype;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrltype() {
                return urltype;
            }

            public void setUrltype(String urltype) {
                this.urltype = urltype;
            }
        }

        public static class Adverts2Bean {
            /**
             * img : 20171206175208528.png
             * url : 27
             * urltype : 1
             */

            private String img;
            private String url;
            private String urltype;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrltype() {
                return urltype;
            }

            public void setUrltype(String urltype) {
                this.urltype = urltype;
            }
        }

        public static class Adverts3Bean {
            /**
             * img : 20171206175208528.png
             * url : 27
             * urltype : 1
             */

            private String img;
            private String url;
            private String urltype;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrltype() {
                return urltype;
            }

            public void setUrltype(String urltype) {
                this.urltype = urltype;
            }
        }

        public static class DataBean {
            /**
             * pid : 1007
             * name : 纯手工彩色水饺（猪肉白菜）400g
             * shopprice : 21.80
             * img : 201712061748274827.png
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
             * pid : 1007
             * name : 纯手工彩色水饺（猪肉白菜）400g
             * shopprice : 21.80
             * img : 201712061748274827.png
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
             * pid : 1007
             * name : 纯手工彩色水饺（猪肉白菜）400g
             * shopprice : 21.80
             * img : 201712061748274827.png
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

    public static class NewsBean {
        /**
         * ret : 0
         * msg :
         * isshow : 0
         * data : [{"title":"\u201c衣暖冬潮\u201d季正式启动 创意活动温暖整个冬季","newsid":"6"},{"title":"双十二活动红包购物券领取攻略","newsid":"5"}]
         */

        private String ret;
        private String msg;
        private String isshow;
        private List<DataBeanXXXXXXX> data;

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

        public String getIsshow() {
            return isshow;
        }

        public void setIsshow(String isshow) {
            this.isshow = isshow;
        }

        public List<DataBeanXXXXXXX> getData() {
            return data;
        }

        public void setData(List<DataBeanXXXXXXX> data) {
            this.data = data;
        }

        public static class DataBeanXXXXXXX {
            /**
             * title : “衣暖冬潮”季正式启动 创意活动温暖整个冬季
             * newsid : 6
             */

            private String title;
            private String newsid;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNewsid() {
                return newsid;
            }

            public void setNewsid(String newsid) {
                this.newsid = newsid;
            }
        }
    }
}
