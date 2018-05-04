package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by cookie on 2016/9/13.
 */
public class JsonBeanStoreDetailsData {


    /**
     * ret : 0
     * msg :
     * storesinfo : {"storeid":"3","name":"零食派","img":"3023.png","startvalue":"50","startfee":"5","monthlysales":"2600","productscount":"30","hasfavorite":"0","hascoupons":"1"}
     * storescategories : {"ret":"0","msg":"","count":"101","data":[{"cateid":"12","catenumber":"001001001","name":"水果"},{"cateid":"13","catenumber":"001001002","name":"蔬菜"},{"cateid":"14","catenumber":"001002001","name":"鱼"},{"cateid":"15","catenumber":"001002002","name":"奶"},{"cateid":"16","catenumber":"001002003","name":"禽类"},{"cateid":"17","catenumber":"001001001001","name":"梨子"},{"cateid":"18","catenumber":"001001001002","name":"瓜类"},{"cateid":"19","catenumber":"001001001003","name":"猕猴桃"},{"cateid":"20","catenumber":"001001001004","name":"甘蔗"},{"cateid":"21","catenumber":"001001002001","name":"根茎类"},{"cateid":"22","catenumber":"001001002002","name":"叶菜类"},{"cateid":"23","catenumber":"001001002003","name":"瓜果类"},{"cateid":"24","catenumber":"001001002004","name":"香菇类"},{"cateid":"25","catenumber":"001001002005","name":"调味类"},{"cateid":"26","catenumber":"001002001001","name":"大鱼"},{"cateid":"27","catenumber":"001002001001","name":"小鱼"},{"cateid":"28","catenumber":"001002002001","name":"牛奶"},{"cateid":"29","catenumber":"001002002002","name":"羊奶"},{"cateid":"30","catenumber":"001002002003","name":"豆浆/豆奶"},{"cateid":"31","catenumber":"001002002004","name":"酸奶"},{"cateid":"32","catenumber":"001002003001","name":"鸡"},{"cateid":"33","catenumber":"001002003002","name":"鸭"},{"cateid":"34","catenumber":"001002003003","name":"鹅"},{"cateid":"35","catenumber":"001002003004","name":"鸽子"},{"cateid":"36","catenumber":"001003036","name":"低温速食"},{"cateid":"37","catenumber":"001003037","name":"常温速食"},{"cateid":"38","catenumber":"001003036038","name":"火锅丸类"},{"cateid":"39","catenumber":"001003036039","name":"水饺馄饨"},{"cateid":"40","catenumber":"001003037040","name":"方便面/粉"},{"cateid":"41","catenumber":"001003037041","name":"常温火腿"},{"cateid":"42","catenumber":"001003037042","name":"罐头"},{"cateid":"43","catenumber":"001004043","name":"休闲零食"},{"cateid":"44","catenumber":"001004044","name":"饼干/糕点"},{"cateid":"45","catenumber":"001005045","name":"酒"},{"cateid":"46","catenumber":"001005046","name":"饮料"},{"cateid":"47","catenumber":"001005047","name":"冲调类"},{"cateid":"48","catenumber":"001006048","name":"米面杂粮"},{"cateid":"49","catenumber":"001006049","name":"油"},{"cateid":"50","catenumber":"001006050","name":"调味品"},{"cateid":"51","catenumber":"001006051","name":"干货"},{"cateid":"52","catenumber":"001007052","name":"面部护理"},{"cateid":"53","catenumber":"001007053","name":"身体护理"},{"cateid":"54","catenumber":"001007054","name":"洗发护发"},{"cateid":"55","catenumber":"001007055","name":"口腔洗浴"},{"cateid":"56","catenumber":"001008056","name":"家用电器"},{"cateid":"57","catenumber":"001008057","name":"厨具"},{"cateid":"58","catenumber":"001008058","name":"纸品湿巾"},{"cateid":"59","catenumber":"001009059","name":"鲜花"},{"cateid":"60","catenumber":"001009060","name":"绿植多肉"},{"cateid":"61","catenumber":"001009061","name":"蛋糕"},{"cateid":"62","catenumber":"001004043062","name":"膨化食品"},{"cateid":"63","catenumber":"001004043063","name":"果干蜜饯"},{"cateid":"64","catenumber":"001004043064","name":"肉干肉铺"},{"cateid":"65","catenumber":"001004044065","name":"面包"},{"cateid":"66","catenumber":"001004044066","name":"饼干/威化"},{"cateid":"67","catenumber":"001004044067","name":"传统糕点"},{"cateid":"68","catenumber":"001005045068","name":"啤酒"},{"cateid":"69","catenumber":"001005045069","name":"白酒"},{"cateid":"70","catenumber":"001005045070","name":"红酒"},{"cateid":"71","catenumber":"001005046071","name":"水"},{"cateid":"72","catenumber":"001005046072","name":"碳酸饮料"},{"cateid":"73","catenumber":"001005046073","name":"果汁"},{"cateid":"74","catenumber":"001005047074","name":"茶叶"},{"cateid":"75","catenumber":"001005047075","name":"咖啡"},{"cateid":"76","catenumber":"001005047076","name":"麦片谷物类"},{"cateid":"77","catenumber":"001006048077","name":"大米"},{"cateid":"78","catenumber":"001006048078","name":"面粉"},{"cateid":"79","catenumber":"001006048079","name":"挂面"},{"cateid":"80","catenumber":"001006048080","name":"杂粮"},{"cateid":"81","catenumber":"001006049081","name":"调和油"},{"cateid":"82","catenumber":"001006049082","name":"花生油"},{"cateid":"83","catenumber":"001006049083","name":"大豆油"},{"cateid":"84","catenumber":"001006050084","name":"盐/糖/味精"},{"cateid":"85","catenumber":"001006050085","name":"其它调味"},{"cateid":"86","catenumber":"001006050086","name":"酱油、醋"},{"cateid":"87","catenumber":"001006051087","name":"南北干货"},{"cateid":"88","catenumber":"001006051088","name":"水产干货"},{"cateid":"89","catenumber":"001006051089","name":"粉丝/粉条"},{"cateid":"90","catenumber":"001007052090","name":"洁面"},{"cateid":"91","catenumber":"001007053091","name":"沐浴露"},{"cateid":"92","catenumber":"001007054092","name":"洗发"},{"cateid":"93","catenumber":"001007054093","name":"护发"},{"cateid":"94","catenumber":"001007055094","name":"牙膏"},{"cateid":"95","catenumber":"001007055095","name":"牙刷"},{"cateid":"96","catenumber":"001008056096","name":"家居电器"},{"cateid":"97","catenumber":"001008056097","name":"灯泡"},{"cateid":"98","catenumber":"001008056098","name":"电池"},{"cateid":"99","catenumber":"001008057099","name":"餐具"},{"cateid":"100","catenumber":"001008057100","name":"锅具/道具刀具"},{"cateid":"101","catenumber":"001008057101","name":"水杯/水壶"},{"cateid":"102","catenumber":"001008058102","name":"卫生纸"},{"cateid":"103","catenumber":"001008058103","name":"湿巾"},{"cateid":"104","catenumber":"001008058104","name":"抽纸"},{"cateid":"105","catenumber":"001009059105","name":"礼品鲜花"},{"cateid":"106","catenumber":"001009059106","name":"家庭用花"},{"cateid":"107","catenumber":"001009059107","name":"永生花"},{"cateid":"108","catenumber":"001009060108","name":"绿植"},{"cateid":"109","catenumber":"001009060109","name":"多肉"},{"cateid":"110","catenumber":"001009061110","name":"奶油蛋糕"},{"cateid":"111","catenumber":"001009061111","name":"水果蛋糕"},{"cateid":"112","catenumber":"001009061112","name":"其它蛋糕"}]}
     */

    private String ret;
    private String msg;
    /**
     * storeid : 3
     * name : 零食派
     * img : 3023.png
     * startvalue : 50
     * startfee : 5
     * monthlysales : 2600
     * productscount : 30
     * hasfavorite : 0
     * hascoupons : 1
     */

    private StoresinfoBean storesinfo;
    /**
     * ret : 0
     * msg :
     * count : 101
     * data : [{"cateid":"12","catenumber":"001001001","name":"水果"},{"cateid":"13","catenumber":"001001002","name":"蔬菜"},{"cateid":"14","catenumber":"001002001","name":"鱼"},{"cateid":"15","catenumber":"001002002","name":"奶"},{"cateid":"16","catenumber":"001002003","name":"禽类"},{"cateid":"17","catenumber":"001001001001","name":"梨子"},{"cateid":"18","catenumber":"001001001002","name":"瓜类"},{"cateid":"19","catenumber":"001001001003","name":"猕猴桃"},{"cateid":"20","catenumber":"001001001004","name":"甘蔗"},{"cateid":"21","catenumber":"001001002001","name":"根茎类"},{"cateid":"22","catenumber":"001001002002","name":"叶菜类"},{"cateid":"23","catenumber":"001001002003","name":"瓜果类"},{"cateid":"24","catenumber":"001001002004","name":"香菇类"},{"cateid":"25","catenumber":"001001002005","name":"调味类"},{"cateid":"26","catenumber":"001002001001","name":"大鱼"},{"cateid":"27","catenumber":"001002001001","name":"小鱼"},{"cateid":"28","catenumber":"001002002001","name":"牛奶"},{"cateid":"29","catenumber":"001002002002","name":"羊奶"},{"cateid":"30","catenumber":"001002002003","name":"豆浆/豆奶"},{"cateid":"31","catenumber":"001002002004","name":"酸奶"},{"cateid":"32","catenumber":"001002003001","name":"鸡"},{"cateid":"33","catenumber":"001002003002","name":"鸭"},{"cateid":"34","catenumber":"001002003003","name":"鹅"},{"cateid":"35","catenumber":"001002003004","name":"鸽子"},{"cateid":"36","catenumber":"001003036","name":"低温速食"},{"cateid":"37","catenumber":"001003037","name":"常温速食"},{"cateid":"38","catenumber":"001003036038","name":"火锅丸类"},{"cateid":"39","catenumber":"001003036039","name":"水饺馄饨"},{"cateid":"40","catenumber":"001003037040","name":"方便面/粉"},{"cateid":"41","catenumber":"001003037041","name":"常温火腿"},{"cateid":"42","catenumber":"001003037042","name":"罐头"},{"cateid":"43","catenumber":"001004043","name":"休闲零食"},{"cateid":"44","catenumber":"001004044","name":"饼干/糕点"},{"cateid":"45","catenumber":"001005045","name":"酒"},{"cateid":"46","catenumber":"001005046","name":"饮料"},{"cateid":"47","catenumber":"001005047","name":"冲调类"},{"cateid":"48","catenumber":"001006048","name":"米面杂粮"},{"cateid":"49","catenumber":"001006049","name":"油"},{"cateid":"50","catenumber":"001006050","name":"调味品"},{"cateid":"51","catenumber":"001006051","name":"干货"},{"cateid":"52","catenumber":"001007052","name":"面部护理"},{"cateid":"53","catenumber":"001007053","name":"身体护理"},{"cateid":"54","catenumber":"001007054","name":"洗发护发"},{"cateid":"55","catenumber":"001007055","name":"口腔洗浴"},{"cateid":"56","catenumber":"001008056","name":"家用电器"},{"cateid":"57","catenumber":"001008057","name":"厨具"},{"cateid":"58","catenumber":"001008058","name":"纸品湿巾"},{"cateid":"59","catenumber":"001009059","name":"鲜花"},{"cateid":"60","catenumber":"001009060","name":"绿植多肉"},{"cateid":"61","catenumber":"001009061","name":"蛋糕"},{"cateid":"62","catenumber":"001004043062","name":"膨化食品"},{"cateid":"63","catenumber":"001004043063","name":"果干蜜饯"},{"cateid":"64","catenumber":"001004043064","name":"肉干肉铺"},{"cateid":"65","catenumber":"001004044065","name":"面包"},{"cateid":"66","catenumber":"001004044066","name":"饼干/威化"},{"cateid":"67","catenumber":"001004044067","name":"传统糕点"},{"cateid":"68","catenumber":"001005045068","name":"啤酒"},{"cateid":"69","catenumber":"001005045069","name":"白酒"},{"cateid":"70","catenumber":"001005045070","name":"红酒"},{"cateid":"71","catenumber":"001005046071","name":"水"},{"cateid":"72","catenumber":"001005046072","name":"碳酸饮料"},{"cateid":"73","catenumber":"001005046073","name":"果汁"},{"cateid":"74","catenumber":"001005047074","name":"茶叶"},{"cateid":"75","catenumber":"001005047075","name":"咖啡"},{"cateid":"76","catenumber":"001005047076","name":"麦片谷物类"},{"cateid":"77","catenumber":"001006048077","name":"大米"},{"cateid":"78","catenumber":"001006048078","name":"面粉"},{"cateid":"79","catenumber":"001006048079","name":"挂面"},{"cateid":"80","catenumber":"001006048080","name":"杂粮"},{"cateid":"81","catenumber":"001006049081","name":"调和油"},{"cateid":"82","catenumber":"001006049082","name":"花生油"},{"cateid":"83","catenumber":"001006049083","name":"大豆油"},{"cateid":"84","catenumber":"001006050084","name":"盐/糖/味精"},{"cateid":"85","catenumber":"001006050085","name":"其它调味"},{"cateid":"86","catenumber":"001006050086","name":"酱油、醋"},{"cateid":"87","catenumber":"001006051087","name":"南北干货"},{"cateid":"88","catenumber":"001006051088","name":"水产干货"},{"cateid":"89","catenumber":"001006051089","name":"粉丝/粉条"},{"cateid":"90","catenumber":"001007052090","name":"洁面"},{"cateid":"91","catenumber":"001007053091","name":"沐浴露"},{"cateid":"92","catenumber":"001007054092","name":"洗发"},{"cateid":"93","catenumber":"001007054093","name":"护发"},{"cateid":"94","catenumber":"001007055094","name":"牙膏"},{"cateid":"95","catenumber":"001007055095","name":"牙刷"},{"cateid":"96","catenumber":"001008056096","name":"家居电器"},{"cateid":"97","catenumber":"001008056097","name":"灯泡"},{"cateid":"98","catenumber":"001008056098","name":"电池"},{"cateid":"99","catenumber":"001008057099","name":"餐具"},{"cateid":"100","catenumber":"001008057100","name":"锅具/道具刀具"},{"cateid":"101","catenumber":"001008057101","name":"水杯/水壶"},{"cateid":"102","catenumber":"001008058102","name":"卫生纸"},{"cateid":"103","catenumber":"001008058103","name":"湿巾"},{"cateid":"104","catenumber":"001008058104","name":"抽纸"},{"cateid":"105","catenumber":"001009059105","name":"礼品鲜花"},{"cateid":"106","catenumber":"001009059106","name":"家庭用花"},{"cateid":"107","catenumber":"001009059107","name":"永生花"},{"cateid":"108","catenumber":"001009060108","name":"绿植"},{"cateid":"109","catenumber":"001009060109","name":"多肉"},{"cateid":"110","catenumber":"001009061110","name":"奶油蛋糕"},{"cateid":"111","catenumber":"001009061111","name":"水果蛋糕"},{"cateid":"112","catenumber":"001009061112","name":"其它蛋糕"}]
     */

    private StorescategoriesBean storescategories;

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

    public StoresinfoBean getStoresinfo() {
        return storesinfo;
    }

    public void setStoresinfo(StoresinfoBean storesinfo) {
        this.storesinfo = storesinfo;
    }

    public StorescategoriesBean getStorescategories() {
        return storescategories;
    }

    public void setStorescategories(StorescategoriesBean storescategories) {
        this.storescategories = storescategories;
    }

    public static class StoresinfoBean {
        private String storeid;
        private String name;
        private String img;
        private String startvalue;
        private String startfee;
        private String monthlysales;
        private String productscount;
        private String hasfavorite;
        private String hascoupons;

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

        public String getMonthlysales() {
            return monthlysales;
        }

        public void setMonthlysales(String monthlysales) {
            this.monthlysales = monthlysales;
        }

        public String getProductscount() {
            return productscount;
        }

        public void setProductscount(String productscount) {
            this.productscount = productscount;
        }

        public String getHasfavorite() {
            return hasfavorite;
        }

        public void setHasfavorite(String hasfavorite) {
            this.hasfavorite = hasfavorite;
        }

        public String getHascoupons() {
            return hascoupons;
        }

        public void setHascoupons(String hascoupons) {
            this.hascoupons = hascoupons;
        }
    }

    public static class StorescategoriesBean {
        private String ret;
        private String msg;
        private String count;
        /**
         * cateid : 12
         * catenumber : 001001001
         * name : 水果
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
            private String cateid;
            private String catenumber;
            private String name;

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
        }
    }
}
