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
    //133E1 100016 020 02
    //1341F 020217 020 02
    public static final String NAMEREG = "^[1]{1}[0-9]{1}[1-8]{1}[0-9a-zA-Z]{2}[0-9]{6}$";  //133 134 only sn
    //public static final String NEWREG = "^[1]{1}[0-9]{1}[1-8]{1}[1-9]{1}[a-zA-Z]{1}[0-9]{6}$";  //从134往后 ，不包括133
    public static final String SNREGEX = "^[1]{1}[0-9]{1}[1-8]{1}[0-9]{1}[0-9]{5}$|^[1]{1}[0-9]{1}[1-8]{1}[0-9a-zA-Z]{2}[0-9]{6}$"; //only sn
    //public static final String SNREGEX = "^[1]{1}[0-9]{1}[1-8]{1}[0-9]{1}[0-9]{5}$|^[1]{1}[0-9]{1}[1-8]{1}[a-zA-Z]{1}[0-9]{7}$|^[1]{1}[0-9]{1}[1-8]{1}[1-9]{1}[a-zA-Z]{1}[0-9]{6}$";
    public static final String COLORREGEX = "^[0-9]{3}$";
    public static final String SIZEREGEX = "^[0][1-5]$";
    public static final String SNOREGEX = "(^[1]{1}[0-9]{1}[1-8]{1}[0-9]{1}[0-9]{5}|^[1]{1}[0-9]{1}[1-8]{1}[0-9a-zA-Z]{2}[0-9]{6})[0-9]{3}[0][1-5]$"; //sn color size

    //15101AE020012
    //15101AE020012
    //15103AF020104
    //15102AE020007
    //15206AK020636
    public static final String NAMEREG2015 = "^[1]{1}[5-9]{1}[1-8]{1}[0-9]{2}[A-Z]{2}[0-9]{6}$";     //edited 2015.08.21 13.22
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
            pi.setLocalSize(getLocalSize(size));
            pi.setWorldSize(getWorldSize(sn, size));
            pi.setOriginColor(getOriginColor(color));
        } else {
            pi.setSn(sn);
            pi.setColor(color);
            pi.setSize(size);
            if (isValidateColor(color)) {
                pi.setColorType(getColorType(color));
                pi.setOriginColor(getOriginColor(color));
            }
            if (isValidateSize(size)) {
                pi.setLocalSize(getLocalSize(size));
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
        if (sn.matches(NAMEREG2015)) {//after 2015
            String Ts2015 = sn.substring(7, 10);
            if (Ts2015.equals("010")) {
                type = "套装";
            } else if (Ts2015.equals("020")) {
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
            } else if (Ts2015.equals("890")) {
                type = "内衣";
            } else {
                type = "#";
            }
            return type;
        } else if (sn.matches(NAMEREG)) {// after 134
            String newTs = sn.substring(5, 8);
            if (newTs.equals("010")) {
                type = "套装";
            } else if (newTs.equals("020")) {
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
            } else if (newTs.equals("890")) {
                type = "内衣";
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
        if (sn.matches(NAMEREG2015)) {// after 2015
            String newTs;
            newTs = sn.substring(7, 10);
            if (newTs.equals("030") || newTs.equals("031") || newTs.equals("040") || newTs.equals("050") || newTs.equals("060")
                    || newTs.equals("070") || newTs.equals("071") || newTs.equals("100") || newTs.equals("101") || newTs.equals("110")
                    || newTs.equals("120") || newTs.equals("121") || newTs.equals("122") || newTs.equals("150") || newTs.equals("160")
                    || newTs.equals("161") || newTs.equals("190") || newTs.equals("010") || newTs.equals("890")) {
                secondType = "女装上装";
            } else if (newTs.equals("080") || newTs.equals("081") || newTs.equals("170") || newTs.equals("180")) {
                secondType = "女装裤子";
            } else if (newTs.equals("090") || newTs.equals("091") || newTs.equals("020") || newTs.equals("021") || newTs.equals("102")) {
                secondType = "裙装";
            } else {
                secondType = "#" + newTs;
            }
            return secondType;
        } else if (sn.matches(NAMEREG)) {// after 134
            String newTs;
            newTs = sn.substring(5, 8);
            if (newTs.equals("030") || newTs.equals("031") || newTs.equals("040") || newTs.equals("050") || newTs.equals("060")
                    || newTs.equals("070") || newTs.equals("071") || newTs.equals("100") || newTs.equals("101") || newTs.equals("110")
                    || newTs.equals("120") || newTs.equals("121") || newTs.equals("122") || newTs.equals("150") || newTs.equals("160")
                    || newTs.equals("161") || newTs.equals("190") || newTs.equals("010") || newTs.equals("890")) {
                secondType = "女装上装";
            } else if (newTs.equals("080") || newTs.equals("081") || newTs.equals("170") || newTs.equals("180")) {
                secondType = "女装裤子";
            } else if (newTs.equals("090") || newTs.equals("091") || newTs.equals("020") || newTs.equals("021") || newTs.equals("102")) {
                secondType = "裙装";
            } else {
                secondType = "#" + newTs;
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
        if (sn.matches(NAMEREG2015)) {//after 2015 15203AC020206
            ts = sn.substring(7, 10);
            if (ts.equals("081")) {
                thirdType = "打底裤";
            } else if (ts.equals("170")) {
                thirdType = "牛仔裤";
            } else if (ts.equals("080") || ts.equals("180")) {
                thirdType = "休闲裤";
            } else if (ts.equals("090") || ts.equals("091")) {
                thirdType = "半身裙";
            } else if (ts.equals("020") || ts.equals("021") || ts.equals("102")) {
                thirdType = "连衣裙";
            } else if (ts.equals("031")) {
                thirdType = "T恤";
            } else if (ts.equals("040") || ts.equals("060")) {
                thirdType = "衬衣";
            } else if (ts.equals("120") || ts.equals("121") || ts.equals("122")) {
                thirdType = "大衣";
            } else if (ts.equals("110")) {
                thirdType = "风衣";
            } else if (ts.equals("190")) {
                thirdType = "马夹";
            } else if (ts.equals("030") || ts.equals("100")) {
                thirdType = "毛衣/针织衫";
            } else if (ts.equals("160")) {
                thirdType = "皮衣";
            } else if (ts.equals("070") || ts.equals("071") || ts.equals("101") || ts.equals("010")) {
                thirdType = "外套";
            } else if (ts.equals("050")) {
                thirdType = "雪纺衫";
            } else if (ts.equals("150")) {
                thirdType = "羽绒服";
            } else if (ts.equals("161")) {
                thirdType = "中款皮草";
            } else if (ts.equals("890")) {
                thirdType = "小吊带/背心";
            } else {
                thirdType = "#" + ts;
            }
        } else if (sn.matches(NAMEREG)) {//after 134
            ts = sn.substring(5, 8);
            if (ts.equals("081")) {
                thirdType = "打底裤";
            } else if (ts.equals("170")) {
                thirdType = "牛仔裤";
            } else if (ts.equals("080") || ts.equals("180")) {
                thirdType = "休闲裤";
            } else if (ts.equals("090") || ts.equals("091")) {
                thirdType = "半身裙";
            } else if (ts.equals("020") || ts.equals("021") || ts.equals("102")) {
                thirdType = "连衣裙";
            } else if (ts.equals("031")) {
                thirdType = "T恤";
            } else if (ts.equals("040") || ts.equals("060")) {
                thirdType = "衬衣";
            } else if (ts.equals("120") || ts.equals("121") || ts.equals("122")) {
                thirdType = "大衣";
            } else if (ts.equals("110")) {
                thirdType = "风衣";
            } else if (ts.equals("190")) {
                thirdType = "马夹";
            } else if (ts.equals("030") || ts.equals("100")) {
                thirdType = "毛衣/针织衫";
            } else if (ts.equals("160")) {
                thirdType = "皮衣";
            } else if (ts.equals("070") || ts.equals("071") || ts.equals("101") || ts.equals("010")) {
                thirdType = "外套";
            } else if (ts.equals("050")) {
                thirdType = "雪纺衫";
            } else if (ts.equals("150")) {
                thirdType = "羽绒服";
            } else if (ts.equals("161")) {
                thirdType = "中款皮草";
            } else if (ts.equals("890")) {
                thirdType = "小吊带/背心";
            } else {
                thirdType = "#" + ts;
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

//    		022	亮色	043	粉红	054	墨绿	071	浅灰	101	鹅黄
//		023	亚光色	044	紫红	055	亮绿	072	中灰	102	桔黄
//		029	沙色	045	桔红	056	灰绿	073	深灰	103	浅黄
//010	珍珠色	030	米色	046	梅红	060	浅蓝	074	花灰	104	黄色
//011	漂白	031	棕米色	047	铁锈红	061	蓝色	081	紫色	105	105
//012	本白	032	沙白	048	西瓜红	062	孔雀蓝	082	紫罗兰	111	深黄
//013	本色	035	米黄	049	桃红	063	深蓝	083	深紫	112	金色
//014	米白	040	红色	051	果绿	064	牛仔蓝	091	咖啡	113	银色
//020	黑色	041	大红	052	军绿	065	宝蓝	092	棕色	115	裸色
//021	亚黑	042	枣红	053	蓝绿	070	灰色	093	红咖	116	裸粉
    public static String getOriginColor(String color) {
        String originColor;
        if (color.equals("010")) {
            originColor = "珍珠白";
        } else if (color.equals("011")) {
            originColor = "漂白";
        } else if (color.equals("012")) {
            originColor = "本白";
        } else if (color.equals("014")) {
            originColor = "米白";
        } else if (color.equals("020")) {
            originColor = "黑色";
        } else if (color.equals("029")) {
            originColor = "沙色";
        } else if (color.equals("030")) {
            originColor = "米色";
        } else if (color.equals("031")) {
            originColor = "棕米色";
        } else if (color.equals("033")) {
            originColor = "卡其色";
        } else if (color.equals("035")) {
            originColor = "米黄";
        } else if (color.equals("040")) {
            originColor = "橡皮红";
        } else if (color.equals("041")) {
            originColor = "大红";
        } else if (color.equals("042")) {
            originColor = "枣红";
        } else if (color.equals("043")) {
            originColor = "粉红";
        } else if (color.equals("044")) {
            originColor = "紫红";
        } else if (color.equals("045")) {
            originColor = "桔红";
        } else if (color.equals("046")) {
            originColor = "梅红";
        } else if (color.equals("047")) {
            originColor = "铁锈红";
        } else if (color.equals("048")) {
            originColor = "肉粉色";
        } else if (color.equals("049")) {
            originColor = "桃红";
        } else if (color.equals("050")) {
            originColor = "浅粉桔";
        } else if (color.equals("051")) {
            originColor = "果绿";
        } else if (color.equals("052")) {
            originColor = "军绿";
        } else if (color.equals("053")) {
            originColor = "蓝绿";
        } else if (color.equals("054")) {
            originColor = "墨绿";
        } else if (color.equals("055")) {
            originColor = "亮绿";
        } else if (color.equals("056")) {
            originColor = "灰绿";
        } else if (color.equals("058")) {
            originColor = "芥末绿";
        } else if (color.equals("060")) {
            originColor = "浅蓝";
        } else if (color.equals("061")) {
            originColor = "蓝色";
        } else if (color.equals("062")) {
            originColor = "宝蓝";
        } else if (color.equals("063")) {
            originColor = "深蓝";
        } else if (color.equals("064")) {
            originColor = "牛仔蓝";
        } else if (color.equals("071")) {
            originColor = "浅灰";
        } else if (color.equals("072")) {
            originColor = "中灰";
        } else if (color.equals("073")) {
            originColor = "深灰";
        } else if (color.equals("074")) {
            originColor = "花灰";
        } else if (color.equals("081")) {
            originColor = "紫色";
        } else if (color.equals("084")) {
            originColor = "灰紫";
        } else if (color.equals("091")) {
            originColor = "咖啡";
        } else if (color.equals("092")) {
            originColor = "棕色";
        } else if (color.equals("093")) {
            originColor = "红咖";
        } else if (color.equals("101")) {
            originColor = "鹅黄";
        } else if (color.equals("102")) {
            originColor = "桔黄";
        } else if (color.equals("103")) {
            originColor = "浅黄";
        } else if (color.equals("104")) {
            originColor = "黄色";
        } else if (color.equals("105")) {
            originColor = "深黄";
        } else if (color.equals("111")) {
            originColor = "藏青色";
        } else if (color.equals("112")) {
            originColor = "金色";
        } else if (color.equals("115")) {
            originColor = "裸色";
        } else if (color.equals("116")) {
            originColor = "粉桔";
        } else if (color.equals("117")) {
            originColor = "水绿";
        } else if (color.equals("118")) {
            originColor = "绿色";
        } else {
            originColor = color;
        }
        return originColor;
    }
    //
//112	金色	黄色系
//093	红咖	棕色系
//092	棕色	棕色系
//063	深蓝	蓝色系

    //add true color
//072 中灰
//073 深灰
//056 灰绿 绿色
//115 裸色 黄色系
//091 咖啡 棕色系
//047 铁锈红 红色系
//048 肉粉色 粉色系
    //true color
//010	珍珠白	白色系
//012	本白	白色系
//014	米白	白色系
//020	黑色	黑色系
//030	米色	白色系
//031	棕米色	棕色系
//033	卡其色	黄色系
//041	大红	红色系
//042	枣红	红色系
//043	粉红	粉色系
//045	桔红	桔色系
//046	梅红	粉色系
//049	桃红	粉色系
//050	浅粉桔	桔色系
//051	果绿	绿色系
//052	军绿	绿色系
//053	蓝绿	绿色系
//054	墨绿	绿色系
//055	亮绿	绿色系
//058	芥末绿	绿色系
//060	浅蓝	蓝色系
//061	蓝色	蓝色系
//062	宝蓝	蓝色系
//064	牛仔蓝	蓝色系
//071	浅灰	灰色系
//074	花灰	灰色系
//081	紫色	紫色系
//084	灰紫	紫色系
//101	鹅黄	黄色系
//102	桔黄	桔色系
//103	浅黄	黄色系
//104	黄色	黄色系
//105	深黄	黄色系
//111	藏青色	蓝色系
//116	粉桔	桔色系
//116	粉桔	粉色系
//117	水绿	绿色系
//118	绿色	绿色系
    //old color
//    		022	亮色	043	粉红	054	墨绿	071	浅灰	101	鹅黄
//		023	亚光色	044	紫红	055	亮绿	072	中灰	102	桔黄
//		029	沙色	045	桔红	056	灰绿	073	深灰	103	浅黄
//010	珍珠色	030	米色	046	梅红	060	浅蓝	074	花灰	104	黄色
//011	漂白	031	棕米色	047	铁锈红	061	蓝色	081	紫色	105	105
//012	本白	032	沙白	048	西瓜红	062	孔雀蓝	082	紫罗兰	111	深黄
//013	本色	035	米黄	049	桃红	063	深蓝	083	深紫	112	金色
//014	米白	040	红色	051	果绿	064	牛仔蓝	091	咖啡	113	银色
//020	黑色	041	大红	052	军绿	065	宝蓝	092	棕色	115	裸色
//021	亚黑	042	枣红	053	蓝绿	070	灰色	093	红咖	116	裸粉
    //TODO
    public static String getColorType(String color) {
        String colorType;
        if (color.equals("010") || color.equals("011") || color.equals("012")
                || color.equals("014") || color.equals("030")) {
            colorType = "白色";
        } else if (color.equals("043") || color.equals("046") || color.equals("048")
                || color.equals("049") || color.equals("116")) {
            colorType = "粉色";
        } else if (color.equals("020")) {
            colorType = "黑色";
        } else if (color.equals("040") || color.equals("041") || color.equals("042")
                || color.equals("044") || color.equals("047")) {
            colorType = "红色";
        } else if (color.equals("033") || color.equals("035") || color.equals("101")
                || color.equals("103") || color.equals("104") || color.equals("105")
                || color.equals("112") || color.equals("115")) {
            colorType = "黄色";
        } else if (color.equals("071") || color.equals("072") || color.equals("073")
                || color.equals("074")) {
            colorType = "灰色";
        } else if (color.equals("045") || color.equals("050") || color.equals("102")
                || color.equals("116")) {
            colorType = "桔色";
        } else if (color.equals("060") || color.equals("061") || color.equals("062")
                || color.equals("063") || color.equals("064") || color.equals("111")) {
            colorType = "蓝色";
        } else if (color.equals("051") || color.equals("052") || color.equals("053")
                || color.equals("054") || color.equals("055") || color.equals("056")
                || color.equals("058") || color.equals("117") || color.equals("118")) {
            colorType = "绿色";
        } else if (color.equals("081") || color.equals("084")) {
            colorType = "紫色";
        } else if (color.equals("029") || color.equals("031") || color.equals("091")
                || color.equals("092") || color.equals("093")) {
            colorType = "棕色";
        } else {
            colorType = "#" + color;
        }
        return colorType;
    }

    //done
    //080 081 170 180
    public static String getLocalSize(String size) {
        String localSize;
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
        return localSize;
    }

    public static String getLocalSize(String sn, String size) {
        String localSize;
        if (sn.matches(NAMEREG2015)) {// after 2015
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
            } else //shangyi
            {
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

        } else if (sn.matches(NAMEREG)) {// after 2014
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
            } else //shangyi
            {
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
            } else //shangyi
            {
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

        if (sn.matches(NAMEREG2015)) {// after 2015
            String typeCode;
            typeCode = sn.substring(7, 10);
            if (typeCode.equals("081") || typeCode.equals("170") || typeCode.equals("080")
                    || typeCode.equals("180") || typeCode.equals("090") || typeCode.equals("091")) {  //kuzi
                if (size.equals("01")) {
                    worldSize = "155/62A";
                } else if (size.equals("02")) {
                    worldSize = "160/66A";
                } else if (size.equals("03")) {
                    worldSize = "165/70A";
                } else if (size.equals("04")) {
                    worldSize = "170/74A";
                } else if (size.equals("05")) {
                    worldSize = "175/78A";
                } else {
                    worldSize = "#" + typeCode;
                }
            } else {//shangyi
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
        } else if (sn.matches(NAMEREG)) {// after 2014 2013 133Exxx  1341Exxx
            String typeCode;
            typeCode = sn.substring(5, 8);
            String headString = sn.substring(0, 3);
            int i = Integer.valueOf(headString);
            if (typeCode.equals("081") || typeCode.equals("170") || typeCode.equals("080")
                    || typeCode.equals("180") || typeCode.equals("090") || typeCode.equals("091")) {  //kuzi
                if (i < 133) {
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
                        worldSize = "#" + typeCode;
                    }
                } else {
                   if (size.equals("01")) {
                        worldSize = "155/62A";
                    } else if (size.equals("02")) {
                        worldSize = "160/66A";
                    } else if (size.equals("03")) {
                        worldSize = "165/70A";
                    } else if (size.equals("04")) {
                        worldSize = "170/74A";
                    } else if (size.equals("05")) {
                        worldSize = "175/78A";
                    } else {
                        worldSize = "#" + typeCode;
                    }
                }
            } else {//shangyi
                if(typeCode.equals("100")){         //针织衫
                    if(i>132 && i<143){
                        if (size.equals("01")) {
                            worldSize = "75cm";
                        } else if (size.equals("02")) {
                            worldSize = "80cm";
                        } else if (size.equals("03")) {
                            worldSize = "85cm";
                        } else if (size.equals("04")) {
                            worldSize = "90cm";
                        } else if (size.equals("05")) {
                            worldSize = "95cm";
                        } else {
                            worldSize = "#" + typeCode;
                        }
                    }
                    else if (i<133) {
                        if (size.equals("01")) {
                            worldSize = "76cm";
                        } else if (size.equals("02")) {
                            worldSize = "80cm";
                        } else if (size.equals("03")) {
                            worldSize = "84cm";
                        } else if (size.equals("04")) {
                            worldSize = "88cm";
                        } else if (size.equals("05")) {
                            worldSize = "92cm";
                        } else {
                            worldSize = "#" + typeCode;
                        }
                    }else{
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
                            worldSize = "#" +typeCode;
                        }
                    }
                }else{
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
                        worldSize = "#" + typeCode;
                    }                   
                }
            }
        } else {    //0ld
            String typeCode;
            if (sn.length() == 9) {
                typeCode = sn.substring(4, 6);
            } else {
                typeCode = sn.substring(5, 7);
            }
            if (typeCode.equals("08") || typeCode.equals("09") || typeCode.equals("18")) {  //kuzi
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
                    worldSize = "#" + typeCode;
                }
            } else //shangyi
            {
                if (typeCode.equals("10")) {//毛衫
                    if (size.equals("01")) {
                        worldSize = "76cm";
                    } else if (size.equals("02")) {
                        worldSize = "80cm";
                    } else if (size.equals("03")) {
                        worldSize = "84cm";
                    } else if (size.equals("04")) {
                        worldSize = "88cmA";
                    } else if (size.equals("05")) {
                        worldSize = "92A";
                    } else {
                        worldSize = "#" + typeCode;
                    }
                }else{
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
                        worldSize = "#" + typeCode;
                    }
                }
            }
        }
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
