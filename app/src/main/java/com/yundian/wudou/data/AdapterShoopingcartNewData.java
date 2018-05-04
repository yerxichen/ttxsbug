package com.yundian.wudou.data;

import java.util.List;

/**
 * Created by taozipc on 2017/12/10.
 */

public class AdapterShoopingcartNewData {


    /**
     * storeId : 21
     * storename : 瓜  类
     * products : {"data":[{"productId":"780","productName":"南瓜1000g","productPrice":"3.50","productUrl":"20171105000514514.PNG","productCount":"10","productWeight":"37","startValue":"8.00","sendPrice":"0.00"},{"productId":"780","productName":"南瓜1000g","productPrice":"3.50","productUrl":"20171105000514514.PNG","productCount":"10","productWeight":"37","startValue":"8.00","sendPrice":"0.00"},{"productId":"780","productName":"南瓜1000g","productPrice":"3.50","productUrl":"20171105000514514.PNG","productCount":"10","productWeight":"37","startValue":"8.00","sendPrice":"0.00"}]}
     */

    private String storeId;
    private String storename;
    private String startValue;
    private String sendPrice;
    private ProductsBean products;

    @Override
    public String toString() {
        return "AdapterShoopingcartNewData{" +
                "storeId='" + storeId + '\'' +
                ", storename='" + storename + '\'' +
                ", products=" + products +
                '}';
    }
    public String getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(String sendPrice) {
        this.sendPrice = sendPrice;
    }
    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }

    public static class ProductsBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * productId : 780
             * productName : 南瓜1000g
             * productPrice : 3.50
             * productUrl : 20171105000514514.PNG
             * productCount : 10
             * productWeight : 37
             * startValue : 8.00
             * sendPrice : 0.00
             */
            private String productId;
            private String productName;
            private float productPrice;
            private String productUrl;
            private int productCount;
            private String productWeight;



            @Override
            public String toString() {
                return "DataBean{" +
                        "productId='" + productId + '\'' +
                        ", productName='" + productName + '\'' +
                        ", productPrice='" + productPrice + '\'' +
                        ", productUrl='" + productUrl + '\'' +
                        ", productCount=" + productCount +
                        ", productWeight='" + productWeight + '\'' +

                         +
                        '}';
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public float getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(float productPrice) {
                this.productPrice = productPrice;
            }

            public String getProductUrl() {
                return productUrl;
            }

            public void setProductUrl(String productUrl) {
                this.productUrl = productUrl;
            }

            public int getProductCount() {
                return productCount;
            }

            public void setProductCount(int productCount) {
                this.productCount = productCount;
            }

            public String getProductWeight() {
                return productWeight;
            }

            public void setProductWeight(String productWeight) {
                this.productWeight = productWeight;
            }




        }
    }
}
