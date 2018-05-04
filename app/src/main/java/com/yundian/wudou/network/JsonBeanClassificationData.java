package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by cookie on 2016/9/12.
 */
public class JsonBeanClassificationData {

    /**
     * ret : 0
     * msg :
     * count : 10
     * data : [{"cateid":"2","catenumber":"001001","name":"果蔬生鲜","subcategory":{"ret":"0","msg":"","count":"2","data":[{"cateid":"12",
     * "catenumber":"001001001","name":"水果","subcategory":{"ret":"0","msg":"","count":"4","data":[{"cateid":"17",
     * "catenumber":"001001001001","name":"梨子"},{"cateid":"18","catenumber":"001001001002","name":"瓜类"},{"cateid":"19",
     * "catenumber":"001001001003","name":"猕猴桃"},{"cateid":"20","catenumber":"001001001004","name":"甘蔗"}]}},{"cateid":"13",
     * "catenumber":"001001002","name":"蔬菜","subcategory":{"ret":"0","msg":"","count":"5","data":[{"cateid":"21",
     * "catenumber":"001001002001","name":"根茎类"},{"cateid":"22","catenumber":"001001002002","name":"叶菜类"},{"cateid":"23",
     * "catenumber":"001001002003","name":"瓜果类"},{"cateid":"24","catenumber":"001001002004","name":"香菇类"},{"cateid":"25",
     * "catenumber":"001001002005","name":"调味类"}]}}]}},{"cateid":"3","catenumber":"001002","name":"肉禽蛋奶","subcategory":{"ret":"0",
     * "msg":"","count":"3","data":[{"cateid":"14","catenumber":"001002001","name":"鱼","subcategory":{"ret":"0","msg":"","count":"2",
     * "data":[{"cateid":"26","catenumber":"001002001001","name":"大鱼"},{"cateid":"27","catenumber":"001002001001","name":"小鱼"}]}},
     * {"cateid":"15","catenumber":"001002002","name":"奶","subcategory":{"ret":"0","msg":"","count":"4","data":[{"cateid":"28",
     * "catenumber":"001002002001","name":"牛奶"},{"cateid":"29","catenumber":"001002002002","name":"羊奶"},{"cateid":"30",
     * "catenumber":"001002002003","name":"豆奶"},{"cateid":"31","catenumber":"001002002004","name":"酸奶"}]}},{"cateid":"16",
     * "catenumber":"001002003","name":"禽类","subcategory":{"ret":"0","msg":"","count":"4","data":[{"cateid":"32",
     * "catenumber":"001002003001","name":"鸡"},{"cateid":"33","catenumber":"001002003002","name":"鸭"},{"cateid":"34",
     * "catenumber":"001002003003","name":"鹅"},{"cateid":"35","catenumber":"001002003004","name":"鸽子"}]}}]}},{"cateid":"4",
     * "catenumber":"001003","name":"冷热速食","subcategory":{"ret":"1","msg":"暂无子类别数据","count":"0","data":[]}},{"cateid":"5",
     * "catenumber":"001004","name":"休闲食品","subcategory":{"ret":"1","msg":"暂无子类别数据","count":"0","data":[]}},{"cateid":"6",
     * "catenumber":"001005","name":"酒水饮料","subcategory":{"ret":"1","msg":"暂无子类别数据","count":"0","data":[]}},{"cateid":"7",
     * "catenumber":"001006","name":"粮油调味","subcategory":{"ret":"1","msg":"暂无子类别数据","count":"0","data":[]}},{"cateid":"8",
     * "catenumber":"001007","name":"清洁日化","subcategory":{"ret":"1","msg":"暂无子类别数据","count":"0","data":[]}},{"cateid":"9",
     * "catenumber":"001008","name":"家居用品","subcategory":{"ret":"1","msg":"暂无子类别数据","count":"0","data":[]}},{"cateid":"10",
     * "catenumber":"001009","name":"鲜花蛋糕","subcategory":{"ret":"1","msg":"暂无子类别数据","count":"0","data":[]}},{"cateid":"11",
     * "catenumber":"001010","name":"便民服务","subcategory":{"ret":"1","msg":"暂无子类别数据","count":"0","data":[]}}]
     */

    private String ret;
    private String msg;
    private String count;
    /**
     * cateid : 2
     * catenumber : 001001
     * name : 果蔬生鲜
     * subcategory : {"ret":"0","msg":"","count":"2","data":[{"cateid":"12","catenumber":"001001001","name":"水果",
     * "subcategory":{"ret":"0","msg":"","count":"4","data":[{"cateid":"17","catenumber":"001001001001","name":"梨子"},{"cateid":"18",
     * "catenumber":"001001001002","name":"瓜类"},{"cateid":"19","catenumber":"001001001003","name":"猕猴桃"},{"cateid":"20",
     * "catenumber":"001001001004","name":"甘蔗"}]}},{"cateid":"13","catenumber":"001001002","name":"蔬菜","subcategory":{"ret":"0","msg":"",
     * "count":"5","data":[{"cateid":"21","catenumber":"001001002001","name":"根茎类"},{"cateid":"22","catenumber":"001001002002",
     * "name":"叶菜类"},{"cateid":"23","catenumber":"001001002003","name":"瓜果类"},{"cateid":"24","catenumber":"001001002004","name":"香菇类"},
     * {"cateid":"25","catenumber":"001001002005","name":"调味类"}]}}]}
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
        /**
         * ret : 0
         * msg :
         * count : 2
         * data : [{"cateid":"12","catenumber":"001001001","name":"水果","subcategory":{"ret":"0","msg":"","count":"4",
         * "data":[{"cateid":"17","catenumber":"001001001001","name":"梨子"},{"cateid":"18","catenumber":"001001001002","name":"瓜类"},
         * {"cateid":"19","catenumber":"001001001003","name":"猕猴桃"},{"cateid":"20","catenumber":"001001001004","name":"甘蔗"}]}},
         * {"cateid":"13","catenumber":"001001002","name":"蔬菜","subcategory":{"ret":"0","msg":"","count":"5","data":[{"cateid":"21",
         * "catenumber":"001001002001","name":"根茎类"},{"cateid":"22","catenumber":"001001002002","name":"叶菜类"},{"cateid":"23",
         * "catenumber":"001001002003","name":"瓜果类"},{"cateid":"24","catenumber":"001001002004","name":"香菇类"},{"cateid":"25",
         * "catenumber":"001001002005","name":"调味类"}]}}]
         */

        private SubcategoryFatherBean subcategory;

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

        public SubcategoryFatherBean getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(SubcategoryFatherBean subcategory) {
            this.subcategory = subcategory;
        }

        public static class SubcategoryFatherBean {
            private String ret;
            private String msg;
            private String count;
            /**
             * cateid : 12
             * catenumber : 001001001
             * name : 水果
             * subcategory : {"ret":"0","msg":"","count":"4","data":[{"cateid":"17","catenumber":"001001001001","name":"梨子"},
             * {"cateid":"18","catenumber":"001001001002","name":"瓜类"},{"cateid":"19","catenumber":"001001001003","name":"猕猴桃"},
             * {"cateid":"20","catenumber":"001001001004","name":"甘蔗"}]}
             */

            private List<DataFatherBean> data;

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

            public List<DataFatherBean> getData() {
                return data;
            }

            public void setData(List<DataFatherBean> data) {
                this.data = data;
            }

            public static class DataFatherBean {
                private String cateid;
                private String catenumber;
                private String name;
                /**
                 * ret : 0
                 * msg :
                 * count : 4
                 * data : [{"cateid":"17","catenumber":"001001001001","name":"梨子"},{"cateid":"18","catenumber":"001001001002",
                 * "name":"瓜类"},{"cateid":"19","catenumber":"001001001003","name":"猕猴桃"},{"cateid":"20","catenumber":"001001001004",
                 * "name":"甘蔗"}]
                 */

                private SubcategoryChildBean subcategory;

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

                public SubcategoryChildBean getSubcategory() {
                    return subcategory;
                }

                public void setSubcategory(SubcategoryChildBean subcategory) {
                    this.subcategory = subcategory;
                }

                public static class SubcategoryChildBean {
                    private String ret;
                    private String msg;
                    private String count;
                    /**
                     * cateid : 17
                     * catenumber : 001001001001
                     * name : 梨子
                     */

                    private List<DataChildBean> data;

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

                    public List<DataChildBean> getData() {
                        return data;
                    }

                    public void setData(List<DataChildBean> data) {
                        this.data = data;
                    }

                    public static class DataChildBean {
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
        }
    }
}
