package com.yundian.wudou.network;

import com.yundian.wudou.data.AdapterSearchResultRecycleViewData;

import java.util.List;

/**
 * Created by cookie on 2016/9/13.
 */
public class JsonBeanSearhResultData {
    /**
     * ret : 0
     * msg :
     * productscount : 60
     * storescount : 10
     * data : [{"storeid":"13","name":"暗夜森林","img":"3033.png","startvalue":"50","startfee":"5","stores_products":{"ret":"1",
     * "msg":"暂无产品数据","count":"0","subdata":[]}},{"storeid":"12","name":"爱吃宝私房披萨","img":"3032.png","startvalue":"50","startfee":"5",
     * "stores_products":{"ret":"1","msg":"暂无产品数据","count":"0","subdata":[]}},{"storeid":"11","name":"台港鸡排","img":"3031.png",
     * "startvalue":"50","startfee":"5","stores_products":{"ret":"1","msg":"暂无产品数据","count":"0","subdata":[]}},{"storeid":"10",
     * "name":"金中蛋糕","img":"3030.png","startvalue":"50","startfee":"5","stores_products":{"ret":"1","msg":"暂无产品数据","count":"0",
     * "subdata":[]}},{"storeid":"9","name":"晶码西餐","img":"3029.png","startvalue":"50","startfee":"5","stores_products":{"ret":"0",
     * "msg":"","count":"1","subdata":[{"pid":"12","name":"风干牛肉","shopprice":"30.00","img":"5022.png"}]}},{"storeid":"8","name":"评价超市",
     * "img":"3028.png","startvalue":"50","startfee":"5","stores_products":{"ret":"0","msg":"","count":"2","subdata":[{"pid":"10",
     * "name":"原味牛肉棒","shopprice":"20.00","img":"5020.png"},{"pid":"11","name":"拉面丸子","shopprice":"25.00","img":"5021.png"}]}},
     * {"storeid":"7","name":"零食工坊","img":"3027.png","startvalue":"50","startfee":"5","stores_products":{"ret":"1","msg":"暂无产品数据",
     * "count":"0","subdata":[]}},{"storeid":"6","name":"芙蓉兴盛便利超市","img":"3026.png","startvalue":"50","startfee":"5",
     * "stores_products":{"ret":"0","msg":"","count":"1","subdata":[{"pid":"9","name":"小麻花","shopprice":"7.00","img":"5019.png"}]}},
     * {"storeid":"5","name":"力士德国啤酒","img":"3025.png","startvalue":"50","startfee":"5","stores_products":{"ret":"0","msg":"",
     * "count":"1","subdata":[{"pid":"8","name":"康师傅牛肉面","shopprice":"5.00","img":"5018.png"}]}},{"storeid":"4","name":"世纪华联","img":"3024
     * .png","startvalue":"50","startfee":"5","stores_products":{"ret":"0","msg":"","count":"1","subdata":[{"pid":"7","name":"德芙巧克力",
     * "shopprice":"15.00","img":"5017.png"}]}},{"storeid":"3","name":"零食派","img":"3023.png","startvalue":"50","startfee":"5",
     * "stores_products":{"ret":"0","msg":"","count":"2","subdata":[{"pid":"3","name":"可口可乐","shopprice":"3.00","img":"5013.png"},
     * {"pid":"6","name":"雪花啤酒","shopprice":"2.50","img":"5016.png"}]}},{"storeid":"2","name":"好又多超市","img":"3022.png","startvalue":"50",
     * "startfee":"5","stores_products":{"ret":"0","msg":"","count":"2","subdata":[{"pid":"2","name":"红牛维生素","shopprice":"6.00",
     * "img":"5012.png"},{"pid":"5","name":"养乐多酸乳","shopprice":"9.00","img":"5015.png"}]}}]
     */

    private String ret;
    private String msg;
    private String productscount;
    private String storescount;
    /**
     * storeid : 13
     * name : 暗夜森林
     * img : 3033.png
     * startvalue : 50
     * startfee : 5
     * stores_products : {"ret":"1","msg":"暂无产品数据","count":"0","subdata":[]}
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

    public String getProductscount() {
        return productscount;
    }

    public void setProductscount(String productscount) {
        this.productscount = productscount;
    }

    public String getStorescount() {
        return storescount;
    }

    public void setStorescount(String storescount) {
        this.storescount = storescount;
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
        private String startvalue;
        private String startfee;
        /**
         * ret : 1
         * msg : 暂无产品数据
         * count : 0
         * subdata : []
         */

        private StoresProductsBean stores_products;

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

        public StoresProductsBean getStores_products() {
            return stores_products;
        }

        public void setStores_products(StoresProductsBean stores_products) {
            this.stores_products = stores_products;
        }

        public static class StoresProductsBean {
            private String ret;
            private String msg;
            private String count;
            private List<AdapterSearchResultRecycleViewData> subdata;

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

            public List<AdapterSearchResultRecycleViewData> getSubdata() {
                return subdata;
            }

            public void setSubdata(List<AdapterSearchResultRecycleViewData> subdata) {
                this.subdata = subdata;
            }
        }

    }
}
