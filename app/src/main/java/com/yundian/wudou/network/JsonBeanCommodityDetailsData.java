package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by cookie on 2016/9/13.
 */
public class JsonBeanCommodityDetailsData {
    /**
     * ret : 0
     * msg :
     * productsinfo : {"pid":"1","name":"恒大冰泉","shopprice":"2.00","monthlysales":"520","images":{"ret":"0","msg":"","count":"2",
     * "data":[{"img":"9011.png"},{"img":"9012.png"}]}}
     * storesinfo : {"ret":"0","msg":"","storeid":"1","name":"羽呈鲜农品屋","img":"3021.png","startvalue":"80","startfee":"5","starcount":"1",
     * "isopen":"0"}
     */

    private String ret;
    private String msg;
    /**
     * pid : 1
     * name : 恒大冰泉
     * shopprice : 2.00
     * monthlysales : 520
     * images : {"ret":"0","msg":"","count":"2","data":[{"img":"9011.png"},{"img":"9012.png"}]}
     */

    private ProductsinfoBean productsinfo;
    /**
     * ret : 0
     * msg :
     * storeid : 1
     * name : 羽呈鲜农品屋
     * img : 3021.png
     * startvalue : 80
     * startfee : 5
     * starcount : 1
     * isopen : 0
     */

    private StoresinfoBean storesinfo;

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

    public ProductsinfoBean getProductsinfo() {
        return productsinfo;
    }

    public void setProductsinfo(ProductsinfoBean productsinfo) {
        this.productsinfo = productsinfo;
    }

    public StoresinfoBean getStoresinfo() {
        return storesinfo;
    }

    public void setStoresinfo(StoresinfoBean storesinfo) {
        this.storesinfo = storesinfo;
    }

    public static class ProductsinfoBean {
        private String pid;
        private String name;
        private String shopprice;
        private String monthlysales;


        private String pimg;

        public String getPimg() {
            return pimg;
        }

        public void setPimg(String pimg) {
            this.pimg = pimg;
        }

        /**
         * ret : 0
         * msg :
         * count : 2
         * data : [{"img":"9011.png"},{"img":"9012.png"}]
         */

        private ImagesBean images;

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

        public String getMonthlysales() {
            return monthlysales;
        }

        public void setMonthlysales(String monthlysales) {
            this.monthlysales = monthlysales;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public static class ImagesBean {
            private String ret;
            private String msg;
            private String count;
            /**
             * img : 9011.png
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

    public static class StoresinfoBean {
        private String ret;
        private String msg;
        private String storeid;
        private String name;
        private String img;
        private String startvalue;
        private String startfee;
        private String starcount;

        private String isopen;
        private String storemodel;
        private String isDistributioning;
        private String isDistributioningMsg;

        public String getStoremodel() {
            return storemodel;
        }

        public void setStoremodel(String storemodel) {
            this.storemodel = storemodel;
        }

        public String getIsDistributioning() {
            return isDistributioning;
        }

        public void setIsDistributioning(String isDistributioning) {
            this.isDistributioning = isDistributioning;
        }

        public String getIsDistributioningMsg() {
            return isDistributioningMsg;
        }

        public void setIsDistributioningMsg(String isDistributioningMsg) {
            this.isDistributioningMsg = isDistributioningMsg;
        }

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

        public String getStarcount() {
            return starcount;
        }

        public void setStarcount(String starcount) {
            this.starcount = starcount;
        }

        public String getIsopen() {
            return isopen;
        }

        public void setIsopen(String isopen) {
            this.isopen = isopen;
        }
    }
}
