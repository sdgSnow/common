package com.sdg.common;

import java.util.List;

public class TestBean {


    /**
     * status : 200
     * message : 操作成功
     * data : {"currentSales":[{"ranking":"3","allconvarsation":12,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"ranking":6,"allconvarsation":68,"countBillNum":8,"performanceAll":8500,"dealRate":"12%","oneConversationPrice":125,"oneBillPrice":1062.5},{"ranking":3,"allconvarsation":207,"countBillNum":25,"performanceAll":21100,"dealRate":"12%","oneConversationPrice":101.93,"oneBillPrice":844}],"totalSales":[{"city":"武汉市","allconvarsation":4,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"黄石市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"十堰市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"宜昌市","allconvarsation":1,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"襄阳市","allconvarsation":1,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"鄂州市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"荆门市","allconvarsation":1,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"孝感市","allconvarsation":1,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"荆州市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"黄冈市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"咸宁市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"随州市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"恩施土家族苗族自治州","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"西安市","allconvarsation":3,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"铜川市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"宝鸡市","allconvarsation":1,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"咸阳市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"渭南市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"延安市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"汉中市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"榆林市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"安康市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"商洛市","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0},{"city":"西咸新区","allconvarsation":0,"countBillNum":0,"performanceAll":0,"dealRate":"0%","oneConversationPrice":0,"oneBillPrice":0}]}
     */

    private int status;
    private String message;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CurrentSalesBean> currentSales;
        private List<TotalSalesBean> totalSales;

        public List<CurrentSalesBean> getCurrentSales() {
            return currentSales;
        }

        public void setCurrentSales(List<CurrentSalesBean> currentSales) {
            this.currentSales = currentSales;
        }

        public List<TotalSalesBean> getTotalSales() {
            return totalSales;
        }

        public void setTotalSales(List<TotalSalesBean> totalSales) {
            this.totalSales = totalSales;
        }

        public static class CurrentSalesBean {
            /**
             * ranking : 3
             * allconvarsation : 12
             * countBillNum : 0
             * performanceAll : 0
             * dealRate : 0%
             * oneConversationPrice : 0
             * oneBillPrice : 0
             */

            private String ranking;
            private float allconvarsation;
            private float countBillNum;
            private float performanceAll;
            private String dealRate;
            private float oneConversationPrice;
            private float oneBillPrice;

            public String getRanking() {
                return ranking;
            }

            public void setRanking(String ranking) {
                this.ranking = ranking;
            }

            public float getAllconvarsation() {
                return allconvarsation;
            }

            public void setAllconvarsation(float allconvarsation) {
                this.allconvarsation = allconvarsation;
            }

            public float getCountBillNum() {
                return countBillNum;
            }

            public void setCountBillNum(float countBillNum) {
                this.countBillNum = countBillNum;
            }

            public float getPerformanceAll() {
                return performanceAll;
            }

            public void setPerformanceAll(float performanceAll) {
                this.performanceAll = performanceAll;
            }

            public String getDealRate() {
                return dealRate;
            }

            public void setDealRate(String dealRate) {
                this.dealRate = dealRate;
            }

            public float getOneConversationPrice() {
                return oneConversationPrice;
            }

            public void setOneConversationPrice(float oneConversationPrice) {
                this.oneConversationPrice = oneConversationPrice;
            }

            public float getOneBillPrice() {
                return oneBillPrice;
            }

            public void setOneBillPrice(float oneBillPrice) {
                this.oneBillPrice = oneBillPrice;
            }
        }

        public static class TotalSalesBean {
            /**
             * city : 武汉市
             * allconvarsation : 4
             * countBillNum : 0
             * performanceAll : 0
             * dealRate : 0%
             * oneConversationPrice : 0
             * oneBillPrice : 0
             */

            private String city;
            private float allconvarsation;
            private float countBillNum;
            private float performanceAll;
            private String dealRate;
            private float oneConversationPrice;
            private float oneBillPrice;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public float getAllconvarsation() {
                return allconvarsation;
            }

            public void setAllconvarsation(float allconvarsation) {
                this.allconvarsation = allconvarsation;
            }

            public float getCountBillNum() {
                return countBillNum;
            }

            public void setCountBillNum(float countBillNum) {
                this.countBillNum = countBillNum;
            }

            public float getPerformanceAll() {
                return performanceAll;
            }

            public void setPerformanceAll(float performanceAll) {
                this.performanceAll = performanceAll;
            }

            public String getDealRate() {
                return dealRate;
            }

            public void setDealRate(String dealRate) {
                this.dealRate = dealRate;
            }

            public float getOneConversationPrice() {
                return oneConversationPrice;
            }

            public void setOneConversationPrice(float oneConversationPrice) {
                this.oneConversationPrice = oneConversationPrice;
            }

            public float getOneBillPrice() {
                return oneBillPrice;
            }

            public void setOneBillPrice(float oneBillPrice) {
                this.oneBillPrice = oneBillPrice;
            }
        }
    }
}
