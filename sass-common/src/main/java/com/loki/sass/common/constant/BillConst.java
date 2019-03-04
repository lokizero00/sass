package com.loki.sass.common.constant;

/**
 * created by lokizero00 on 2019-02-18
 */
public class BillConst extends SysConst{
    public static final String RECHARGE_AMOUNT="RECHARGE_AMOUNT";


    /**
     * 订单支付状态
     * 0:未支付；1.已支付；2.待支付3.无需支付
     */
    public enum OrderPayStatus {
        WAITING_PAY(0,"未支付"),
        PAY_OFF(1,"已支付"),
        OWE_FEE(2,"待支付"),
        FREE_MONEY(3,"无需支付");

        private int key;

        private String text;

        private OrderPayStatus(int key,String text) {
            this.key=key;
            this.text=text;
        }

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }
    }

    /**
     * 支付流水状态
     */
    public enum PayJournalState {
        FAIL(0,"失败"),
        SUCCESS(1,"成功");

        private int key;

        private String text;

        private PayJournalState(int key,String text) {
            this.key=key;
            this.text=text;
        }

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }
    }

    /**
     * 订单账单状态
     * 0:待支付；1.已支付；2.待支付
     */
    public enum OrderBillState {
        WAIT_PAY(0,"待支付"),
        PAY_OFF(1,"已支付");

        private int key;

        private String text;

        private OrderBillState(int key,String text) {
            this.key=key;
            this.text=text;
        }

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }
    }


    public enum PayType {
        ALI_PAY(1,"支付宝"),
        WEIXIN_PAY(2,"微信");

        private int key;

        private String text;

        private PayType(int key,String text) {
            this.key=key;
            this.text=text;
        }

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }
    }


    /**
     * 交易状态
     */
    public enum JournalState {
        TOBECONFIRMED("00","待确认"),
        SUCCESSFULTRADE("01","交易成功"),
        TRANSACTIONFAILURE("02","交易失败");

        private String key;

        private String text;

        private JournalState(String key,String text) {
            this.key=key;
            this.text=text;
        }

        public String getKey() {
            return key;
        }

        public String getText() {
            return text;
        }
    }

    /**
     * 对账状态
     */
    public enum JournalCheckState {
        NOTRECONCILED("00","未对账"),
        RECONCILIATION("01","对帐中"),
        RECONCILIATIONSUCCESS("02","已对账成功"),
        CHECKFAILURE("03","对帐失败");

        private String key;

        private String text;

        private JournalCheckState(String key,String text) {
            this.key=key;
            this.text=text;
        }

        public String getKey() {
            return key;
        }

        public String getText() {
            return text;
        }
    }

    /**
     * 用途
     */
    public enum JournalType {
        RECHARGE("01","充值");

        private String key;

        private String text;

        private JournalType(String key,String text) {
            this.key=key;
            this.text=text;
        }

        public String getKey() {
            return key;
        }

        public String getText() {
            return text;
        }
    }

    public enum DepositState {
        NOTRETURNED(1,"未退还"),
        RETURN(0,"退还"),
        FROZEN(-1,"冻结");

        private int key;

        private String text;

        private DepositState(int key,String text) {
            this.key=key;
            this.text=text;
        }

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }
    }

    /**
     * ProductUserOrderOrder 的equip_sort类型
     */
    public enum ProductUserOrderEquipSort{
        BATTERY(1,"电池"),
        VEHICLE(2,"车子");

        private int index;
        private String name;

        ProductUserOrderEquipSort(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }
    }

    public enum BillOrder {
        RECHARGE(1,"充值");

        private int key;

        private String text;

        private BillOrder(int key,String text) {
            this.key=key;
            this.text=text;
        }

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }
    }
}
