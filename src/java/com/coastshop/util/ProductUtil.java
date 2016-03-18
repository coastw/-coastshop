/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.util;

/**
 *
 * @author Coast
 */
public class ProductUtil {

    //122102052
    //1341F02021702002
    public static final String NEWREG = "^[1]{1}[0-9]{1}[1-8]{1}[1-9]{1}[a-zA-Z]{1}[0-9]{6}$";
    public static final String SNREGEX = "^[1]{1}[0-9]{1}[1-8]{1}[0-9]{1}[0-9]{5}$|^[1]{1}[0-9]{1}[1-8]{1}[a-zA-Z]{1}[0-9]{7}$|^[1]{1}[0-9]{1}[1-8]{1}[1-9]{1}[a-zA-Z]{1}[0-9]{6}$";
    public static final String COLORREGEX = "^[0-9]{3}$";
    public static final String SIZEREGEX = "^[0][1-5]$";
    public static final String SNOREGEX = "(^[1]{1}[0-9]{1}[1-8]{1}[0-9]{1}[0-9]{5}|^[1]{1}[0-9]{1}[1-8]{1}[a-zA-Z]{1}[0-9]{7}|^[1]{1}[0-9]{1}[1-8]{1}[1-9]{1}[a-zA-Z]{1}[0-9]{6})[0-9]{3}[0][1-5]$";

    //15101AE020012
    //15101AE020012
    //15103AF020104
    //15102AE020007
    //15206AK020636
    public static final String NEWREG2015 = "^[1]{1}[5-9]{1}[1-8]{1}[0-9]{2}[A-Z]{2}[0-9]{6}$";     //edited 2015.08.21 13.22
    public static final String SNREGEX2015 = "^[1]{1}[5-9]{1}[1-8]{1}[0-9]{2}[A-Z]{2}[0-9]{6}$";
    public static final String SNOREGEX2015 = "[1]{1}[5-9]{1}[1-8]{1}[0-9]{2}[A-Z]{2}[0-9]{6}[0-9]{3}[0][1-5]$";        //edited 2015.08.21 13.43
//    public static final String SNREGEX = "^[1]{1}[0-9]{1}[1-8]{1}[0-9a-zA-Z]{0,2}[0-9]{6,7}$";
//    public static final String COLORREGEX = "^[0-9]{3}";
//    public static final String SIZEREGEX = "^[0][1-5]$";
//    public static final String SNOREGEX = "^[1]{1}[0-9]{1}[1-8]{1}[0-9a-zA-Z]{0,2}[0-9]{6,7}[0-9]{3}[0][1-5]$";
    
    //15206AK020636 012 02
    //15206AK02063601202

    public static ProductInfo getProductInfo(String sn) {
        ProductInfo pi = new ProductInfo();
        if (isValidate(sn)) {
            pi.setSn(sn);
            pi.setYear(getYear(sn));
            pi.setSeason(getSeason(sn));
            pi.setType(getType(sn));
            pi.setFirstType(getFirstType(sn));
            pi.setSecondType(getSecondType(sn));
            pi.setThirdType(getThirdType(sn));
            pi.setFitSeason(getFitSeason(sn));
        } else {
            pi.setSn(sn);
        }
        return pi;
    }

    public static ProductInfo getProductInfo(String sn, String color, String size) {
        ProductInfo pi = new ProductInfo();
        if (isValidate(sn)) {
            pi.setSn(sn);
            pi.setColor(color);
            pi.setSize(size);
            pi.setYear(getYear(sn));
            pi.setSeason(getSeason(sn));
            pi.setType(getType(sn));
            pi.setFirstType(getFirstType(sn));
            pi.setSecondType(getSecondType(sn));
            pi.setThirdType(getThirdType(sn));
            pi.setFitSeason(getFitSeason(sn));
            pi.setColorType(getColorType(color));
            pi.setLocalSize(getLocalSize(sn, size));
            pi.setWorldSize(getWorldSize(sn, size));
        } else {
            pi.setSn(sn);
            pi.setColor(color);
            pi.setSize(size);
            if (isValidateColor(color)) {
                pi.setColorType(getColorType(color));
            }
            if (isValidateSize(size)) {
                pi.setLocalSize(getLocalSize(sn, size));
                pi.setWorldSize(getWorldSize(sn, size));
            }
        }
        return pi;
    }

    public static boolean isValidate(String sn) {
        //String regex = "^[1]{1}[0-9]{1}[1-8]{1}[0-9]{1}[0-1]{1}[0-9]{1}[0-9]{3}$";
        return (sn.matches(SNREGEX) || sn.matches(SNREGEX2015));
    }

    public static boolean isValidateColor(String color) {
        return color.matches(COLORREGEX);
    }

    public static boolean isValidateSize(String size) {
        return size.matches(SIZEREGEX);
    }

    public static String getYear(String sn) {
        String year;
        String ys = sn.substring(0, 2);
        if (ys.equals("10")) {
            year = "2010";
        } else if (ys.equals("11")) {
            year = "2011";
        } else if (ys.equals("12")) {
            year = "2012";
        } else if (ys.equals("13")) {
            year = "2013";
        } else if (ys.equals("14")) {
            year = "2014";
        } else if (ys.equals("15")) {
            year = "2015";
        } else if (ys.equals("16")) {
            year = "2016";
        } else if (ys.equals("17")) {
            year = "2017";
        } else if (ys.equals("18")) {
            year = "2018";
        } else if (ys.equals("19")) {
            year = "2009";
        } else {
            year = "#";
        }
        return year;
    }

    public static String getSeason(String sn) {
        String season;
        String ss = sn.substring(2, 3);
        if (ss.equals("1") || ss.equals("5")) {
            season = "春";
        } else if (ss.equals("2") || ss.equals("6")) {
            season = "夏";
        } else if (ss.equals("3") || ss.equals("7")) {
            season = "秋";
        } else if (ss.equals("4") || ss.equals("8")) {
            season = "冬";
        } else {
            season = "#";
        }
        return season;
    }

    public static String getType(String sn) {
        String type = "#";
        if (sn.matches(NEWREG2015)) {//after 2015
            String Ts2015 = sn.substring(7, 10);
            if (Ts2015.equals("020")) {
                type = "梭织连衣裙";
            } else if (Ts2015.equals("021")) {
                type = "针织连衣裙";
            } else if (Ts2015.equals("030")) {
                type = "针织上衣";
            } else if (Ts2015.equals("031")) {
                type = "T恤";
            } else if (Ts2015.equals("040")) {
                type = "真丝上衣";
            } else if (Ts2015.equals("050")) {
                type = "梭织上衣";
            } else if (Ts2015.equals("060")) {
                type = "衬衫";
            } else if (Ts2015.equals("070")) {
                type = "薄外套";
            } else if (Ts2015.equals("071")) {
                type = "厚外套";
            } else if (Ts2015.equals("080")) {
                type = "梭织裤";
            } else if (Ts2015.equals("081")) {
                type = "针织裤";
            } else if (Ts2015.equals("090")) {
                type = "梭织裙";
            } else if (Ts2015.equals("091")) {
                type = "针织裙";
            } else if (Ts2015.equals("100")) {
                type = "毛织衫";
            } else if (Ts2015.equals("101")) {
                type = "毛织外套";
            } else if (Ts2015.equals("102")) {
                type = "毛织连衣裙";
            } else if (Ts2015.equals("110")) {
                type = "风衣";
            } else if (Ts2015.equals("120") || Ts2015.equals("121") || Ts2015.equals("122")) {
                type = "大衣";
            } else if (Ts2015.equals("150")) {
                type = "羽绒服";
            } else if (Ts2015.equals("160")) {
                type = "皮衣";
            } else if (Ts2015.equals("161")) {
                type = "皮草";
            } else if (Ts2015.equals("170")) {
                type = "牛仔裤";
            } else if (Ts2015.equals("180")) {
                type = "连体裤";
            } else if (Ts2015.equals("190")) {
                type = "马夹";
            } else if (Ts2015.equals("18")) {
                type = "休闲裤";
            } else {
                type = "#";
            }
            return type;
        } else if (sn.matches(NEWREG)) {// after 2014
            String newTs = sn.substring(5, 8);
            if (newTs.equals("020")) {
                type = "梭织连衣裙";
            } else if (newTs.equals("021")) {
                type = "针织连衣裙";
            } else if (newTs.equals("030")) {
                type = "针织上衣";
            } else if (newTs.equals("031")) {
                type = "T恤";
            } else if (newTs.equals("040")) {
                type = "真丝上衣";
            } else if (newTs.equals("050")) {
                type = "梭织上衣";
            } else if (newTs.equals("060")) {
                type = "衬衫";
            } else if (newTs.equals("070")) {
                type = "薄外套";
            } else if (newTs.equals("071")) {
                type = "厚外套";
            } else if (newTs.equals("080")) {
                type = "梭织裤";
            } else if (newTs.equals("081")) {
                type = "针织裤";
            } else if (newTs.equals("090")) {
                type = "梭织裙";
            } else if (newTs.equals("091")) {
                type = "针织裙";
            } else if (newTs.equals("100")) {
                type = "毛织衫";
            } else if (newTs.equals("101")) {
                type = "毛织外套";
            } else if (newTs.equals("102")) {
                type = "毛织连衣裙";
            } else if (newTs.equals("110")) {
                type = "风衣";
            } else if (newTs.equals("120") || newTs.equals("121") || newTs.equals("122")) {
                type = "大衣";
            } else if (newTs.equals("150")) {
                type = "羽绒服";
            } else if (newTs.equals("160")) {
                type = "皮衣";
            } else if (newTs.equals("161")) {
                type = "皮草";
            } else if (newTs.equals("170")) {
                type = "牛仔裤";
            } else if (newTs.equals("180")) {
                type = "连体裤";
            } else if (newTs.equals("190")) {
                type = "马夹";
            } else if (newTs.equals("18")) {
                type = "休闲裤";
            } else {
                type = "#";
            }
            return type;
        } else { //0ld
            String ts = null;
            if (sn.length() == 9) {
                ts = sn.substring(4, 6);
            } else {
                ts = sn.substring(5, 7);
            }
            if (ts.equals("02")) {
                type = "连衣裙";
            } else if (ts.equals("03")) {
                type = "梭织上衣";
            } else if (ts.equals("04")) {
                type = "上衣";
            } else if (ts.equals("05")) {
                type = "针织上衣";
            } else if (ts.equals("06")) {
                type = "衬衫";
            } else if (ts.equals("07")) {
                type = "外套";
            } else if (ts.equals("08")) {
                type = "裤子";
            } else if (ts.equals("09")) {
                type = "裙子";
            } else if (ts.equals("10")) {
                type = "毛衫";
            } else if (ts.equals("11")) {
                type = "风衣";
            } else if (ts.equals("12")) {
                type = "长大衣";
            } else if (ts.equals("13")) {
                type = "短大衣";
            } else if (ts.equals("14")) {
                type = "棉衣";
            } else if (ts.equals("15")) {
                type = "羽绒服";
            } else if (ts.equals("16")) {
                type = "皮衣";
            } else if (ts.equals("17")) {
                type = "牛仔裤";
            } else if (ts.equals("18")) {
                type = "休闲裤";
            } else {
                type = "#";
            }
            return type;
        }

    }

    public static String getFirstType(String sn) {
        return "女装";
    }

    public static String getSecondType(String sn) {
//        女装上装	03,06,07,10,11,12,13,14,15,16 (04?)
//        女装裤子	08,18
//        裙装		02,09
//        连体衣
//        唐装
        String secondType;

        //todo
        if (sn.matches(NEWREG2015)) {// after 2015
            String newTs;
            newTs = sn.substring(7, 10);
            if (newTs.equals("190") || newTs.equals("110") || newTs.equals("070") || newTs.equals("160") || newTs.equals("120") || newTs.equals("071")
                    || newTs.equals("150") || newTs.equals("161") || newTs.equals("040") || newTs.equals("050")
                    || newTs.equals("060") || newTs.equals("030") || newTs.equals("031") || newTs.equals("100") || newTs.equals("101") || newTs.equals("102")) {
                secondType = "女装上装";
            } else if (newTs.equals("080") || newTs.equals("081") || newTs.equals("170") || newTs.equals("180")) {
                secondType = "女装裤子";
            } else if (newTs.equals("090") || newTs.equals("091") || newTs.equals("020") || newTs.equals("021")) {
                secondType = "裙装";
            } else {
                secondType = "#";
            }
            return secondType;
        } else if (sn.matches(NEWREG)) {// after 2014
            String newTs;
            newTs = sn.substring(5, 8);
            if (newTs.equals("190") || newTs.equals("110") || newTs.equals("070") || newTs.equals("160") || newTs.equals("120") || newTs.equals("071")
                    || newTs.equals("150") || newTs.equals("161") || newTs.equals("040") || newTs.equals("050")
                    || newTs.equals("060") || newTs.equals("030") || newTs.equals("031") || newTs.equals("100") || newTs.equals("101") || newTs.equals("102")) {
                secondType = "女装上装";
            } else if (newTs.equals("080") || newTs.equals("081") || newTs.equals("170") || newTs.equals("180")) {
                secondType = "女装裤子";
            } else if (newTs.equals("090") || newTs.equals("091") || newTs.equals("020") || newTs.equals("021")) {
                secondType = "裙装";
            } else {
                secondType = "#";
            }
            return secondType;
        } else {    //0ld
            String ts;
            if (sn.length() == 9) {
                ts = sn.substring(4, 6);
            } else {
                ts = sn.substring(5, 7);
            }
            if (ts.equals("03") || ts.equals("05") || ts.equals("06") || ts.equals("07") || ts.equals("10") || ts.equals("11") || ts.equals("12") || ts.equals("13") || ts.equals("14") || ts.equals("15") || ts.equals("16") || ts.equals("04")) {
                secondType = "女装上装";
            } else if (ts.equals("08") || ts.equals("17") || ts.equals("18")) {
                secondType = "女装裤子";
            } else if (ts.equals("02") || ts.equals("09")) {
                secondType = "裙装";
            } else {
                secondType = "#";
            }
            return secondType;
        }

    }

    /**
     * 三级品类：04上衣？ ----女装上装---- T恤 衬衣	--06-- 大衣	--12、13-- 短款皮草 风衣	--11-- 马夹 马甲
     * 毛衣/针织衫	--03-- 棉衣/棉服	--14-- 披肩 皮衣	--16-- 外套	--07-- 卫衣/绒衫	--10-- 西服 小吊带/背心
     * 雪纺衫 羽绒服	--15-- 中款皮草
     *
     * ----女装裤子---- 打底裤 短裤 牛仔裤 休闲裤	--08-- 长裤 ----裙装---- 半身裙	--09-- 吊带裙 礼服裙 连衣裙
     * --02--
     *
     * ----连体衣---- 连体衣
     *
     * ----唐装---- 男式唐装裤子 男式唐装上装 女式唐装裤子 女式唐装上装 旗袍 裙装
     *
     * @param sn
     * @return
     */
    public static String getThirdType(String sn) {
        String thirdType;
        String ts;
        if (sn.matches(NEWREG2015)) {//after 2015
            ts = sn.substring(7, 10);
            if (ts.equals("031")) {
                thirdType = "T恤";
            } else if (ts.equals("060")) {
                thirdType = "衬衣";
            } else if (ts.equals("120")) {
                thirdType = "大衣";
//            } else if (ts.equals("050") || ts.equals("030") || ts.equals("100") || ts.equals("101") || ts.equals("102")) {
//                thirdType = "短款皮草";
            } else if (ts.equals("110")) {
                thirdType = "风衣";
            } else if (ts.equals("190")) {
                thirdType = "马夹";
//            } else if (ts.equals("")) {
//                thirdType = "马甲";
            } else if (ts.equals("040") || ts.equals("050") || ts.equals("030") || ts.equals("100") || ts.equals("101") || ts.equals("102")) {
                thirdType = "毛衣/针织衫";
//            } else if (ts.equals("070") || ts.equals("071")) {
//                thirdType = "棉衣/棉服";
//            } else if (ts.equals("10")) {
//                thirdType = "披肩";
            } else if (ts.equals("160")) {
                thirdType = "皮衣";
            } else if (ts.equals("070") || ts.equals("071")) {
                thirdType = "外套";
//            } else if (ts.equals("090") || ts.equals("091")) {
//                thirdType = "卫衣/绒衫";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "西服";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "小吊带/背心";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "雪纺衫";
            } else if (ts.equals("150")) {
                thirdType = "羽绒服";
            } else if (ts.equals("161")) {
                thirdType = "中款皮草";
            } else if (ts.equals("081")) {
                thirdType = "打底裤";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "短裤";
            } else if (ts.equals("170")) {
                thirdType = "牛仔裤";
            } else if (ts.equals("080") || ts.equals("180")) {
                thirdType = "休闲裤";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "长裤";
            } else if (ts.equals("090") || ts.equals("091")) {
                thirdType = "半身裙";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "吊带裙";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "礼服裙";
            } else if (ts.equals("020") || ts.equals("021")) {
                thirdType = "连衣裙";
            } else {
                thirdType = "#";
            }
        } else if (sn.matches(NEWREG)) {//after 2014
            ts = sn.substring(5, 8);
            if (ts.equals("031")) {
                thirdType = "T恤";
            } else if (ts.equals("060")) {
                thirdType = "衬衣";
            } else if (ts.equals("120")) {
                thirdType = "大衣";
//            } else if (ts.equals("050") || ts.equals("030") || ts.equals("100") || ts.equals("101") || ts.equals("102")) {
//                thirdType = "短款皮草";
            } else if (ts.equals("110")) {
                thirdType = "风衣";
            } else if (ts.equals("190")) {
                thirdType = "马夹";
//            } else if (ts.equals("")) {
//                thirdType = "马甲";
            } else if (ts.equals("040") || ts.equals("050") || ts.equals("030") || ts.equals("100") || ts.equals("101") || ts.equals("102")) {
                thirdType = "毛衣/针织衫";
//            } else if (ts.equals("070") || ts.equals("071")) {
//                thirdType = "棉衣/棉服";
//            } else if (ts.equals("10")) {
//                thirdType = "披肩";
            } else if (ts.equals("160")) {
                thirdType = "皮衣";
            } else if (ts.equals("070") || ts.equals("071")) {
                thirdType = "外套";
//            } else if (ts.equals("090") || ts.equals("091")) {
//                thirdType = "卫衣/绒衫";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "西服";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "小吊带/背心";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "雪纺衫";
            } else if (ts.equals("150")) {
                thirdType = "羽绒服";
            } else if (ts.equals("161")) {
                thirdType = "中款皮草";
            } else if (ts.equals("081")) {
                thirdType = "打底裤";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "短裤";
            } else if (ts.equals("170")) {
                thirdType = "牛仔裤";
            } else if (ts.equals("080") || ts.equals("180")) {
                thirdType = "休闲裤";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "长裤";
            } else if (ts.equals("090") || ts.equals("091")) {
                thirdType = "半身裙";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "吊带裙";
//            } else if (ts.equals("020") || ts.equals("021")) {
//                thirdType = "礼服裙";
            } else if (ts.equals("020") || ts.equals("021")) {
                thirdType = "连衣裙";
            } else {
                thirdType = "#";
            }
        } else {//0ld
            //判断位数
            if (sn.length() == 9) {
                ts = sn.substring(4, 6);
            } else {
                ts = sn.substring(5, 7);
            }
            if (ts.equals("06")) {
                thirdType = "衬衣";
            } else if (ts.equals("12") || ts.equals("13")) {
                thirdType = "大衣";
            } else if (ts.equals("11")) {
                thirdType = "风衣";
            } else if (ts.equals("03") || ts.equals("05")) {
                thirdType = "毛衣/针织衫";
            } else if (ts.equals("04")) {
                thirdType = "雪纺衫";
            } else if (ts.equals("14")) {
                thirdType = "棉衣/棉服";
            } else if (ts.equals("16")) {
                thirdType = "皮衣";
            } else if (ts.equals("07")) {
                thirdType = "外套";
            } else if (ts.equals("10")) {
                thirdType = "卫衣/绒衫";
            } else if (ts.equals("15")) {
                thirdType = "羽绒服";
            } else if (ts.equals("08") || ts.equals("17") || ts.equals("18")) {
                thirdType = "休闲裤";
            } else if (ts.equals("09")) {
                thirdType = "半身裙";
            } else if (ts.equals("02")) {
                thirdType = "连衣裙";
            } else {
                thirdType = "#";
            }
        }
        return thirdType;
    }

    public static String getFitSeason(String sn) {
        String fitSeason;
        String ss = sn.substring(2, 3);
        if (ss.equals("1") || ss.equals("5") || ss.equals("2") || ss.equals("6")) {
            fitSeason = "春/夏FP01";
        } else if (ss.equals("3") || ss.equals("7") || ss.equals("4") || ss.equals("8")) {
            fitSeason = "秋/冬FP02";
        } else {
            fitSeason = "无FP00";
        }
        return fitSeason;
    }

    //TODO
    public static String getColorType(String color) {
        String colorType;
        if (color.equals("010") || color.equals("011") || color.equals("012")
                || color.equals("013") || color.equals("014")) {
            colorType = "白色";
        } else if (color.equals("030") || color.equals("031") || color.equals("032") || color.equals("033") || color.equals("035")
                || color.equals("101") || color.equals("102") || color.equals("103") || color.equals("104")
                || color.equals("105") || color.equals("112")
                || color.equals("115") || color.equals("116")) {
            colorType = "黄色";
        } else if (color.equals("081") || color.equals("082") || color.equals("083")) {
            colorType = "紫色";
        } else if (color.equals("041") || color.equals("042") || color.equals("043")
                || color.equals("044") || color.equals("045") || color.equals("046")
                || color.equals("047") || color.equals("048") || color.equals("049") || color.equals("040")) {
            colorType = "红色";
        } else if (color.equals("020") || color.equals("021") || color.equals("022")
                || color.equals("023") || color.equals("111") || color.equals("029")) {
            colorType = "黑色";
        } else if (color.equals("050") || color.equals("051") || color.equals("052") || color.equals("053")
                || color.equals("054") || color.equals("055") || color.equals("056")) {
            colorType = "绿色";
        } else if (color.equals("070") || color.equals("071") || color.equals("072")
                || color.equals("073") || color.equals("074") || color.equals("113")) {
            colorType = "灰色";
        } else if (color.equals("060") || color.equals("061") || color.equals("062")
                || color.equals("063") || color.equals("064") || color.equals("065")) {
            colorType = "蓝色";
        } else if (color.equals("091") || color.equals("092") || color.equals("093")) {
            colorType = "棕色";
        } else {
            colorType = "#";
        }
        return colorType;
    }

    //done
    //080 081 170 180
    public static String getLocalSize(String sn, String size) {
        String localSize;
        if (sn.matches(NEWREG2015)) {// after 2015
            String ts;
            ts = sn.substring(7, 10);
            if (ts.equals("080") || ts.equals("081") || ts.equals("170") || ts.equals("180")) {  //kuzi
                if (size.equals("01")) {
                    localSize = "S";
                } else if (size.equals("02")) {
                    localSize = "M";
                } else if (size.equals("03")) {
                    localSize = "L";
                } else if (size.equals("04")) {
                    localSize = "XL";
                } else if (size.equals("05")) {
                    localSize = "XXL";
                } else {
                    localSize = "#";
                }
            } else { //shangyi
                if (size.equals("01")) {
                    localSize = "S";
                } else if (size.equals("02")) {
                    localSize = "M";
                } else if (size.equals("03")) {
                    localSize = "L";
                } else if (size.equals("04")) {
                    localSize = "XL";
                } else if (size.equals("05")) {
                    localSize = "XXL";
                } else {
                    localSize = "#";
                }
            }

        } else if (sn.matches(NEWREG)) {// after 2014
            String ts;
            ts = sn.substring(5, 8);
            if (ts.equals("080") || ts.equals("081") || ts.equals("170") || ts.equals("180")) {  //kuzi
                if (size.equals("01")) {
                    localSize = "S";
                } else if (size.equals("02")) {
                    localSize = "M";
                } else if (size.equals("03")) {
                    localSize = "L";
                } else if (size.equals("04")) {
                    localSize = "XL";
                } else if (size.equals("05")) {
                    localSize = "XXL";
                } else {
                    localSize = "#";
                }
            } else { //shangyi
                if (size.equals("01")) {
                    localSize = "S";
                } else if (size.equals("02")) {
                    localSize = "M";
                } else if (size.equals("03")) {
                    localSize = "L";
                } else if (size.equals("04")) {
                    localSize = "XL";
                } else if (size.equals("05")) {
                    localSize = "XXL";
                } else {
                    localSize = "#";
                }
            }
        } else {    //0ld
            String ts;
            if (sn.length() == 9) {
                ts = sn.substring(4, 6);
            } else {
                ts = sn.substring(5, 7);
            }
            //done...
            if (ts.equals("08") || ts.equals("09") || ts.equals("18")) {  //kuzi
                if (size.equals("01")) {
                    localSize = "S";
                } else if (size.equals("02")) {
                    localSize = "M";
                } else if (size.equals("03")) {
                    localSize = "L";
                } else if (size.equals("04")) {
                    localSize = "XL";
                } else if (size.equals("05")) {
                    localSize = "XXL";
                } else {
                    localSize = "#";
                }
            } else { //shangyi
                if (size.equals("01")) {
                    localSize = "S";
                } else if (size.equals("02")) {
                    localSize = "M";
                } else if (size.equals("03")) {
                    localSize = "L";
                } else if (size.equals("04")) {
                    localSize = "XL";
                } else if (size.equals("05")) {
                    localSize = "XXL";
                } else {
                    localSize = "#";
                }
            }
        }

        return localSize;
    }

    public static String getWorldSize(String sn, String size) {
        String worldSize;
        ///

        if (sn.matches(NEWREG2015)) {// after 2015
            String ts;
            ts = sn.substring(7, 10);
            if (ts.equals("080") || ts.equals("081") || ts.equals("170") || ts.equals("180")) {  //kuzi
                if (size.equals("01")) {
                    worldSize = "155/60A";
                } else if (size.equals("02")) {
                    worldSize = "160/64A";
                } else if (size.equals("03")) {
                    worldSize = "165/68A";
                } else if (size.equals("04")) {
                    worldSize = "170/72A";
                } else if (size.equals("05")) {
                    worldSize = "175/76A";
                } else {
                    worldSize = "#";
                }
            } else { //shangyi
                if (size.equals("01")) {
                    worldSize = "155/80A";
                } else if (size.equals("02")) {
                    worldSize = "160/84A";
                } else if (size.equals("03")) {
                    worldSize = "165/88A";
                } else if (size.equals("04")) {
                    worldSize = "170/92A";
                } else if (size.equals("05")) {
                    worldSize = "175/96A";
                } else {
                    worldSize = "#";
                }
            }
        } else if (sn.matches(NEWREG)) {// after 2014
            String ts;
            ts = sn.substring(5, 8);
            if (ts.equals("080") || ts.equals("081") || ts.equals("170") || ts.equals("180")) {  //kuzi
                if (size.equals("01")) {
                    worldSize = "155/60A";
                } else if (size.equals("02")) {
                    worldSize = "160/64A";
                } else if (size.equals("03")) {
                    worldSize = "165/68A";
                } else if (size.equals("04")) {
                    worldSize = "170/72A";
                } else if (size.equals("05")) {
                    worldSize = "175/76A";
                } else {
                    worldSize = "#";
                }
            } else { //shangyi
                if (size.equals("01")) {
                    worldSize = "155/80A";
                } else if (size.equals("02")) {
                    worldSize = "160/84A";
                } else if (size.equals("03")) {
                    worldSize = "165/88A";
                } else if (size.equals("04")) {
                    worldSize = "170/92A";
                } else if (size.equals("05")) {
                    worldSize = "175/96A";
                } else {
                    worldSize = "#";
                }
            }
        } else {    //0ld
            String ts;
            if (sn.length() == 9) {
                ts = sn.substring(4, 6);
            } else {
                ts = sn.substring(5, 7);
            }
            //done...
            if (ts.equals("08") || ts.equals("09") || ts.equals("18")) {  //kuzi
                if (size.equals("01")) {
                    worldSize = "155/60A";
                } else if (size.equals("02")) {
                    worldSize = "160/64A";
                } else if (size.equals("03")) {
                    worldSize = "165/68A";
                } else if (size.equals("04")) {
                    worldSize = "170/72A";
                } else if (size.equals("05")) {
                    worldSize = "175/76A";
                } else {
                    worldSize = "#";
                }
            } else { //shangyi
                if (size.equals("01")) {
                    worldSize = "155/80A";
                } else if (size.equals("02")) {
                    worldSize = "160/84A";
                } else if (size.equals("03")) {
                    worldSize = "165/88A";
                } else if (size.equals("04")) {
                    worldSize = "170/92A";
                } else if (size.equals("05")) {
                    worldSize = "175/96A";
                } else {
                    worldSize = "#";
                }
            }
        }

        ///
        return worldSize;
    }

    public static boolean checkSNO(String sno) {
        int len = sno.length();
        if (len != 14 || len != 16 || len != 18) {
            return false;
        }
        String sn = null;
        String color = null;
        String size = null;
        if (len == 14) {
            //  133102051 0  2  0   0  2
            //  012345678 9  10 11  12 13
            sn = sno.substring(0, 9);
            color = sno.substring(9, 12);
            size = sno.substring(12, 14);
            /*
             System.out.println("sno=" + sno);
             System.out.println("sn=" + sn);
             System.out.println("color=" + color);
             System.out.println("size=" + size);
             */
        }
        if (len == 16) {
            sn = sno.substring(0, 11);
            color = sno.substring(11, 14);
            size = sno.substring(14, 16);
        }
        if (len == 18) {
            sn = sno.substring(0, 13);
            color = sno.substring(13, 16);
            size = sno.substring(16, 18);
        }

        return isValidate(sn) && isValidateColor(color) && isValidateSize(size);
    }
}
