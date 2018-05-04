package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by cookie on 2016/9/12.
 */
public class JsonBeanStoreData {
    /**
     * ret : 0
     * msg :
     * storesate : {"ret":"0","msg":"","count":"2","data":[{"code":"","name":"全部商家"},{"code":"01","name":"优惠商家"}]}
     * storeclasses : {"ret":"0","msg":"","count":"7","data":[{"code":"","name":"全部分类"},{"code":"01","name":"果蔬生鲜"},{"code":"02",
     * "name":"粮油调味"},{"code":"03","name":"吃货天地"},{"code":"04","name":"家具建材"},{"code":"05","name":"超市便利"},{"code":"06","name":"鲜花糕点"}]}
     * storeorder : {"ret":"0","msg":"","count":"4","data":[{"code":"","name":"智能排序"},{"code":"01","name":"好评优先"},{"code":"02",
     * "name":"离我最近"},{"code":"03","name":"人均最低"}]}
     * stores : {"ret":"0","msg":"","count":"12","data":[{"storeid":"13","name":"暗夜森林","img":"3033.png","distance":"500米",
     * "monthlysales":"0","startvalue":"80","startfee":"5","isown":"1"},{"storeid":"12","name":"爱吃宝私房披萨","img":"3032.png",
     * "distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5","isown":"0"},{"storeid":"11","name":"台港鸡排","img":"3031.png",
     * "distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5","isown":"1"},{"storeid":"10","name":"金中蛋糕","img":"3030.png",
     * "distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5","isown":"0"},{"storeid":"9","name":"晶码西餐","img":"3029.png",
     * "distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5","isown":"1"},{"storeid":"8","name":"评价超市","img":"3028.png",
     * "distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5","isown":"0"},{"storeid":"7","name":"零食工坊","img":"3027.png",
     * "distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5","isown":"1"},{"storeid":"6","name":"芙蓉兴盛便利超市","img":"3026
     * .png","distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5","isown":"0"},{"storeid":"5","name":"力士德国啤酒",
     * "img":"3025.png","distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5","isown":"1"},{"storeid":"4","name":"世纪华联",
     * "img":"3024.png","distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5","isown":"0"},{"storeid":"3","name":"零食派",
     * "img":"3023.png","distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5","isown":"1"},{"storeid":"2","name":"好又多超市",
     * "img":"3022.png","distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5","isown":"0"}]}
     */

    private String ret;
    private String msg;
    /**
     * ret : 0
     * msg :
     * count : 2
     * data : [{"code":"","name":"全部商家"},{"code":"01","name":"优惠商家"}]
     */

    private StoresateBean storesate;
    /**
     * ret : 0
     * msg :
     * count : 7
     * data : [{"code":"","name":"全部分类"},{"code":"01","name":"果蔬生鲜"},{"code":"02","name":"粮油调味"},{"code":"03","name":"吃货天地"},
     * {"code":"04","name":"家具建材"},{"code":"05","name":"超市便利"},{"code":"06","name":"鲜花糕点"}]
     */

    private StoreclassesBean storeclasses;
    /**
     * ret : 0
     * msg :
     * count : 4
     * data : [{"code":"","name":"智能排序"},{"code":"01","name":"好评优先"},{"code":"02","name":"离我最近"},{"code":"03","name":"人均最低"}]
     */

    private StoreorderBean storeorder;
    /**
     * ret : 0
     * msg :
     * count : 12
     * data : [{"storeid":"13","name":"暗夜森林","img":"3033.png","distance":"500米","monthlysales":"0","startvalue":"80","startfee":"5",
     * "isown":"1"},{"storeid":"12","name":"爱吃宝私房披萨","img":"3032.png","distance":"500米","monthlysales":"0","startvalue":"80",
     * "startfee":"5","isown":"0"},{"storeid":"11","name":"台港鸡排","img":"3031.png","distance":"500米","monthlysales":"0","startvalue":"80",
     * "startfee":"5","isown":"1"},{"storeid":"10","name":"金中蛋糕","img":"3030.png","distance":"500米","monthlysales":"0","startvalue":"80",
     * "startfee":"5","isown":"0"},{"storeid":"9","name":"晶码西餐","img":"3029.png","distance":"500米","monthlysales":"0","startvalue":"80",
     * "startfee":"5","isown":"1"},{"storeid":"8","name":"评价超市","img":"3028.png","distance":"500米","monthlysales":"0","startvalue":"80",
     * "startfee":"5","isown":"0"},{"storeid":"7","name":"零食工坊","img":"3027.png","distance":"500米","monthlysales":"0","startvalue":"80",
     * "startfee":"5","isown":"1"},{"storeid":"6","name":"芙蓉兴盛便利超市","img":"3026.png","distance":"500米","monthlysales":"0",
     * "startvalue":"80","startfee":"5","isown":"0"},{"storeid":"5","name":"力士德国啤酒","img":"3025.png","distance":"500米",
     * "monthlysales":"0","startvalue":"80","startfee":"5","isown":"1"},{"storeid":"4","name":"世纪华联","img":"3024.png","distance":"500米",
     * "monthlysales":"0","startvalue":"80","startfee":"5","isown":"0"},{"storeid":"3","name":"零食派","img":"3023.png","distance":"500米",
     * "monthlysales":"0","startvalue":"80","startfee":"5","isown":"1"},{"storeid":"2","name":"好又多超市","img":"3022.png","distance":"500米",
     * "monthlysales":"0","startvalue":"80","startfee":"5","isown":"0"}]
     */

    private StoresBean stores;

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

    public StoresateBean getStoresate() {
        return storesate;
    }

    public void setStoresate(StoresateBean storesate) {
        this.storesate = storesate;
    }

    public StoreclassesBean getStoreclasses() {
        return storeclasses;
    }

    public void setStoreclasses(StoreclassesBean storeclasses) {
        this.storeclasses = storeclasses;
    }

    public StoreorderBean getStoreorder() {
        return storeorder;
    }

    public void setStoreorder(StoreorderBean storeorder) {
        this.storeorder = storeorder;
    }

    public StoresBean getStores() {
        return stores;
    }

    public void setStores(StoresBean stores) {
        this.stores = stores;
    }

    public static class StoresateBean {
        private String ret;
        private String msg;
        private String count;
        /**
         * code :
         * name : 全部商家
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

    public static class StoreclassesBean {
        private String ret;
        private String msg;
        private String count;
        /**
         * code :
         * name : 全部分类
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

    public static class StoreorderBean {
        private String ret;
        private String msg;
        private String count;
        /**
         * code :
         * name : 智能排序
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

    public static class StoresBean {
        private String ret;
        private String msg;
        private String count;
        /**
         * storeid : 13
         * name : 暗夜森林
         * img : 3033.png
         * distance : 500米
         * monthlysales : 0
         * startvalue : 80
         * startfee : 5
         * isown : 1
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
}
