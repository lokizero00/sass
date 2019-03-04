package com.loki.sass.common.util;

import com.loki.sass.common.constant.BillConst;

import java.util.Date;
import java.util.Random;

/**
 * created by lokizero00 on 2019-02-16
 */
public class OrderNoGenerator {

    /**
     * 生成订单号
     * @param orderType
     * @return
     */
    public static String getPayOrderNo(Integer orderType) {

        String orderNo = null;
        Date currentTime = new Date();
        String timeInfo = DateTool.dateToStr(currentTime,DateTool.DATE_TIME_SORT_SSS);
        String randomNum = getFixLenthString(3);

        if(orderType == BillConst.BillOrder.RECHARGE.getKey()) {
            orderNo = "R" + timeInfo + randomNum;
        }
        return orderNo;
    }
//
//    /**
//     * 生成缴纳押金、充值,购买套餐订单号
//     * @param orderType
//     * @return
//     */
//    public static String getOrderNo(Integer orderType) {
//
//        String orderNo = null;
//        Date currentTime = new Date();
//        String timeInfo = DateTool.dateToStr(currentTime,DateTool.DATE_TIME_SORT_SSS);
//        String randomNum = getFixLenthString(3);
//
//        if(orderType == OrderConst.UserOrderType.BATTERY_RENT.getKey()){
//            orderNo="BR"+timeInfo+randomNum;
//            return orderNo;
//        } else if(orderType == OrderConst.UserOrderType.BATTERY_CHANGE.getKey()) {
//            orderNo = "BC" + timeInfo + randomNum;
//            return orderNo;
//        }else if(orderType == OrderConst.UserOrderType.RETURN_DEPOSIT.getKey()) {
//            orderNo = "RD" + timeInfo + randomNum;
//            return orderNo;
//        }else if(orderType == OrderConst.UserOrderType.VEHICLE_RENEW.getKey()){
//            orderNo = "VRR" + timeInfo + randomNum;
//            return orderNo;
//        }else if(orderType == OrderConst.UserOrderType.VEHICLE_RENT.getKey()){
//            orderNo = "VR" + timeInfo + randomNum;
//            return orderNo;
//        }
//        return orderNo;
//    }
//
//    /**
//     * 生成站点招聘付款，冻结，解冻，扣除订单号
//     * @param type
//     * @return
//     */
//    public static String getSiteAcctJournalPayNo(String type){
//        String orderNo=null;
//        Date currentTime=new Date();
//        String timeInfo = DateTool.dateToStr(currentTime,DateTool.DATE_TIME_SORT_SSS);
//        String randomNum = getFixLenthString(3);
//
//        if(type.equals(RecruitConst.ThirdPartyIdJournalType.RECHARGE.getKey())){
//            orderNo = "RE" + timeInfo + randomNum;
//            return orderNo;
//        }else if(type.equals(RecruitConst.ThirdPartyIdJournalType.WITHDRAW.getKey())){
//            orderNo = "WI" + timeInfo + randomNum;
//            return orderNo;
//        }else if(type.equals(RecruitConst.ThirdPartyIdJournalType.FREEZE.getKey())){
//            orderNo = "FR" + timeInfo + randomNum;
//            return orderNo;
//        }else if(type.equals(RecruitConst.ThirdPartyIdJournalType.UNFREEZE.getKey())){
//            orderNo = "UNFR" + timeInfo + randomNum;
//            return orderNo;
//        }else if(type.equals(RecruitConst.ThirdPartyIdJournalType.DEDUCT.getKey())){
//            orderNo = "DE" + timeInfo + randomNum;
//            return orderNo;
//        }
//        return orderNo;
//    }
//
//    //部分退款生成唯一out_request_no
//    public static String getOuRequestNo() {
//        String orderNo = null;
//        Date currentTime = new Date();
//        String timeInfo = DateTool.dateToStr(currentTime,DateTool.DATE_TIME_YMD_NOSYMBOL);
//        String randomNum = getFixLenthString(3);
//        orderNo="BR"+timeInfo+randomNum;
//        return orderNo;
//    }
//
//
    /**
     * 返回长度为【strLength】的随机数，在前面补0
     * @param strLength
     */
    private static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }

    /**
     * 返回长度 length 由字母数字组成的随机数
     * @param length
     * @return
     */
    public static String getRandomStr(int length)
    {
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();//随机用以下三个随机生成器
        Random randdata=new Random();
        int data=0;
        for(int i=0;i<length;i++)
        {
            int index=rand.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch(index)
            {
                case 0:
                    data=randdata.nextInt(10);//仅仅会生成0~9
                    sb.append(data);
                    break;
                case 1:
                    data=randdata.nextInt(26)+65;//保证只会产生65~90之间的整数
                    sb.append((char)data);
                    break;
                case 2:
                    data=randdata.nextInt(26)+97;//保证只会产生97~122之间的整数
                    sb.append((char)data);
                    break;
            }
        }
        String result=sb.toString();
        return result;
    }
}
