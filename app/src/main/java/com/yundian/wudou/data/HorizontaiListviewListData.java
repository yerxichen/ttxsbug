package com.yundian.wudou.data;

import java.util.List;

/**
 * Created by taozipc on 2017/11/25.
 */

public class HorizontaiListviewListData {


    /**
     * ret : 0
     * msg :
     * stores : {"ret":"0","msg":"","count":"6","data":[{"storeid":"21","name":"康居人品牌专卖店","img":"20170706161706176.png","distance":"192公里","monthlysales":"281","startvalue":"0.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"1","msg":"暂无产品数据","count":"0","subdata":[]}},{"storeid":"7","name":"蔬心水果","img":"201707041352495249.png","distance":"193公里","monthlysales":"2505","startvalue":"0.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"1","msg":"暂无产品数据","count":"0","subdata":[]}},{"storeid":"31","name":"果缤纷（新城店）","img":"20170814090958958.png","distance":"195公里","monthlysales":"3474","startvalue":"30.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"0","msg":"","count":"3","subdata":[{"pid":"719","name":"秋月梨(约六两一个)肉质细腻水份充足","shopprice":"7.80","img":"20170816141600160.jpg","ishot":"1"},{"pid":"768","name":"好巴食串烧素肉牛汁味165g","shopprice":"11.50","img":"201708170315571557.png","ishot":"1"},{"pid":"772","name":"麻辣猪肉脯（蜀道香）","shopprice":"9.80","img":"201708170324182418.png","ishot":"1"}]}},{"storeid":"27","name":"蔬心便利","img":"20170704135208528.png","distance":"197公里","monthlysales":"3964","startvalue":"8.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"0","msg":"","count":"6","subdata":[{"pid":"658","name":"臀尖肉 500g","shopprice":"12.20","img":"201711190021452145.png","ishot":"1"},{"pid":"659","name":"精选五花肉 500g","shopprice":"16.90","img":"2017102200020222.png","ishot":"1"},{"pid":"663","name":"猪前腿肉 500g","shopprice":"12.80","img":"201708170532423242.png","ishot":"1"},{"pid":"665","name":"新鲜鹅肉 约2000g/半只","shopprice":"47.50","img":"201709121419361936.png","ishot":"1"},{"pid":"802","name":"精选瘦肉约500g","shopprice":"16.50","img":"20170917000852852.png","ishot":"1"},{"pid":"817","name":"红烧牛肉面超爽桶 143g","shopprice":"5.50","img":"201708281854345434.png","ishot":"1"}]}},{"storeid":"30","name":"蔬心粮油调味","img":"20170704140538538.png","distance":"196公里","monthlysales":"3198","startvalue":"8.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"1","msg":"暂无产品数据","count":"0","subdata":[]}},{"storeid":"14","name":"蔬心菜品","img":"201707041353395339.png","distance":"198公里","monthlysales":"7726","startvalue":"8.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"0","msg":"","count":"3","subdata":[{"pid":"916","name":"蟹肉棒（海欣）200g","shopprice":"5.20","img":"201711120033353335.png","ishot":"1"},{"pid":"938","name":"鱼肉花200g","shopprice":"5.50","img":"2017101916060969.png","ishot":"1"},{"pid":"942","name":"闽南肉板（安井）200g","shopprice":"5.50","img":"201710191625152515.png","ishot":"1"}]}}]}
     */

    private String ret;
    private String msg;
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

    public StoresBean getStores() {
        return stores;
    }

    public void setStores(StoresBean stores) {
        this.stores = stores;
    }

    public static class StoresBean {
        /**
         * ret : 0
         * msg :
         * count : 6
         * data : [{"storeid":"21","name":"康居人品牌专卖店","img":"20170706161706176.png","distance":"192公里","monthlysales":"281","startvalue":"0.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"1","msg":"暂无产品数据","count":"0","subdata":[]}},{"storeid":"7","name":"蔬心水果","img":"201707041352495249.png","distance":"193公里","monthlysales":"2505","startvalue":"0.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"1","msg":"暂无产品数据","count":"0","subdata":[]}},{"storeid":"31","name":"果缤纷（新城店）","img":"20170814090958958.png","distance":"195公里","monthlysales":"3474","startvalue":"30.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"0","msg":"","count":"3","subdata":[{"pid":"719","name":"秋月梨(约六两一个)肉质细腻水份充足","shopprice":"7.80","img":"20170816141600160.jpg","ishot":"1"},{"pid":"768","name":"好巴食串烧素肉牛汁味165g","shopprice":"11.50","img":"201708170315571557.png","ishot":"1"},{"pid":"772","name":"麻辣猪肉脯（蜀道香）","shopprice":"9.80","img":"201708170324182418.png","ishot":"1"}]}},{"storeid":"27","name":"蔬心便利","img":"20170704135208528.png","distance":"197公里","monthlysales":"3964","startvalue":"8.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"0","msg":"","count":"6","subdata":[{"pid":"658","name":"臀尖肉 500g","shopprice":"12.20","img":"201711190021452145.png","ishot":"1"},{"pid":"659","name":"精选五花肉 500g","shopprice":"16.90","img":"2017102200020222.png","ishot":"1"},{"pid":"663","name":"猪前腿肉 500g","shopprice":"12.80","img":"201708170532423242.png","ishot":"1"},{"pid":"665","name":"新鲜鹅肉 约2000g/半只","shopprice":"47.50","img":"201709121419361936.png","ishot":"1"},{"pid":"802","name":"精选瘦肉约500g","shopprice":"16.50","img":"20170917000852852.png","ishot":"1"},{"pid":"817","name":"红烧牛肉面超爽桶 143g","shopprice":"5.50","img":"201708281854345434.png","ishot":"1"}]}},{"storeid":"30","name":"蔬心粮油调味","img":"20170704140538538.png","distance":"196公里","monthlysales":"3198","startvalue":"8.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"1","msg":"暂无产品数据","count":"0","subdata":[]}},{"storeid":"14","name":"蔬心菜品","img":"201707041353395339.png","distance":"198公里","monthlysales":"7726","startvalue":"8.00","startfee":"0.00","star":"4","isdiscount":"1","discounttitle":"满12减1元,满30减5元,国庆满28减5元,满100减25元 ","stores_products":{"ret":"0","msg":"","count":"3","subdata":[{"pid":"916","name":"蟹肉棒（海欣）200g","shopprice":"5.20","img":"201711120033353335.png","ishot":"1"},{"pid":"938","name":"鱼肉花200g","shopprice":"5.50","img":"2017101916060969.png","ishot":"1"},{"pid":"942","name":"闽南肉板（安井）200g","shopprice":"5.50","img":"201710191625152515.png","ishot":"1"}]}}]
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
             * storeid : 21
             * name : 康居人品牌专卖店
             * img : 20170706161706176.png
             * distance : 192公里
             * monthlysales : 281
             * startvalue : 0.00
             * startfee : 0.00
             * star : 4
             * isdiscount : 1
             * isopen: 0
             * storemodel: 自营
             * isDistributioning: 0
             * isDistributioningMsg:2小时送达
             * discounttitle : 满12减1元,满30减5元,国庆满28减5元,满100减25元
             * stores_products : {"ret":"1","msg":"暂无产品数据","count":"0","subdata":[]}
             */
            private String isopen;
            private String storemodel;
            private String isDistributioning;
            private String isDistributioningMsg;
            private String storeid;
            private String name;
            private String img;
            private String distance;
            private String monthlysales;
            private String startvalue;
            private String startfee;
            private String star;
            private String isdiscount;
            private String discounttitle;
            private StoresProductsBean stores_products;

            public String getIsopen() {
                return isopen;
            }

            public void setIsopen(String isopen) {
                this.isopen = isopen;
            }

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

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getIsdiscount() {
                return isdiscount;
            }

            public void setIsdiscount(String isdiscount) {
                this.isdiscount = isdiscount;
            }

            public String getDiscounttitle() {
                return discounttitle;
            }

            public void setDiscounttitle(String discounttitle) {
                this.discounttitle = discounttitle;
            }

            public StoresProductsBean getStores_products() {
                return stores_products;
            }

            public void setStores_products(StoresProductsBean stores_products) {
                this.stores_products = stores_products;
            }

            public static class StoresProductsBean {
                /**
                 * ret : 1
                 * msg : 暂无产品数据
                 * count : 0
                 * subdata : []
                 */

                private String ret;
                private String msg;
                private String count;
                private List<SubdataBean> subdata;

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

                public List<SubdataBean> getSubdata() {
                    return subdata;
                }

                public void setSubdata(List<SubdataBean> subdata) {
                    this.subdata = subdata;
                }


                public static class SubdataBean {
                    String pid;
                    String name;
                    String shopprice;
                    String img;
                    String ishot;

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

                    public String getIshot() {
                        return ishot;
                    }

                    public void setIshot(String ishot) {
                        this.ishot = ishot;
                    }
                }
            }
        }
    }
}
