package com.yundian.wudou.data;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class EvaluateResult {
    /**
     * access_token : 303C79365C24416D516817484285C44E94D8D505C1728E33
     * oid : 353
     * data : [{"pid":"1","star1":"2","star2":"3","star3":"1","message":"很好吃","media_ids":"13,56"},{"pid":"5","star1":"2","star2":"3","star3":"1","message":"很好吃","media_ids":"13,56"}]
     */

    private String access_token;
    private String oid;
    private List<DataBean> data;

    public EvaluateResult(String access_token, String oid, List<DataBean> data) {
        this.access_token = access_token;
        this.oid = oid;
        this.data = data;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pid : 1
         * star1 : 2
         * star2 : 3
         * star3 : 1
         * message : 很好吃
         * media_ids : 13,56
         */

        private String pid;
        private String star1;
        private String star2;
        private String star3;
        private String message;
        private String media_ids;

        public DataBean(String pid, String star1, String star2, String star3, String message, String media_ids) {
            this.pid = pid;
            this.star1 = star1;
            this.star2 = star2;
            this.star3 = star3;
            this.message = message;
            this.media_ids = media_ids;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getStar1() {
            return star1;
        }

        public void setStar1(String star1) {
            this.star1 = star1;
        }

        public String getStar2() {
            return star2;
        }

        public void setStar2(String star2) {
            this.star2 = star2;
        }

        public String getStar3() {
            return star3;
        }

        public void setStar3(String star3) {
            this.star3 = star3;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMedia_ids() {
            return media_ids;
        }

        public void setMedia_ids(String media_ids) {
            this.media_ids = media_ids;
        }
    }
}
