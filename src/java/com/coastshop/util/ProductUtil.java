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
        switch (ys) {
            case "10":
                year = "2010";
                break;
            case "11":
                year = "2011";
                break;
            case "12":
                year = "2012";
                break;
            case "13":
                year = "2013";
                break;
            case "14":
                year = "2014";
                break;
            case "15":
                year = "2015";
                break;
            case "16":
                year = "2016";
                break;
            case "17":
                year = "2017";
                break;
            case "18":
                year = "2018";
                break;
            case "19":
                year = "2009";
                break;
            default:
                year = "#"+ys;
                break;
        }
        return year;
    }

    public static String getSeason(String sn) {
        String season;
        String ss = sn.substring(2, 3);
        switch (ss) {
            case "1":
            case "5":
                season = "春";
                break;
            case "2":
            case "6":
                season = "夏";
                break;
            case "3":
            case "7":
                season = "秋";
                break;
            case "4":
            case "8":
                season = "冬";
                break;
            default:
                season = "#"+ss;
                break;
        }
        return season;
    }

    public static String getType(String sn) {
        String type;
        if (sn.matches(NAMEREG2015)) {//after 2015
            String Ts2015 = sn.substring(7, 10);
            switch (Ts2015) {
                case "010":
                    type = "套装";
                    break;
                case "020":
                    type = "梭织连衣裙";
                    break;
                case "021":
                    type = "针织连衣裙";
                    break;
                case "030":
                    type = "针织上衣";
                    break;
                case "031":
                    type = "T恤";
                    break;
                case "040":
                    type = "真丝上衣";
                    break;
                case "050":
                    type = "梭织上衣";
                    break;
                case "060":
                    type = "衬衫";
                    break;
                case "070":
                    type = "薄外套";
                    break;
                case "071":
                    type = "厚外套";
                    break;
                case "080":
                    type = "梭织裤";
                    break;
                case "081":
                    type = "针织裤";
                    break;
                case "090":
                    type = "梭织裙";
                    break;
                case "091":
                    type = "针织裙";
                    break;
                case "100":
                    type = "毛织衫";
                    break;
                case "101":
                    type = "毛织外套";
                    break;
                case "102":
                    type = "毛织连衣裙";
                    break;
                case "110":
                    type = "风衣";
                    break;
                case "120":
                case "121":
                case "122":
                    type = "大衣";
                    break;
                case "150":
                    type = "羽绒服";
                    break;
                case "160":
                    type = "皮衣";
                    break;
                case "161":
                    type = "皮草";
                    break;
                case "170":
                    type = "牛仔裤";
                    break;
                case "180":
                    type = "连体裤";
                    break;
                case "190":
                    type = "马夹";
                    break;
                case "890":
                    type = "内衣";
                    break;
                default:
                    type = "#"+Ts2015;
                    break;
            }
            return type;
        } else if (sn.matches(NAMEREG)) {// after 134
            String newTs = sn.substring(5, 8);
            switch (newTs) {
                case "010":
                    type = "套装";
                    break;
                case "020":
                    type = "梭织连衣裙";
                    break;
                case "021":
                    type = "针织连衣裙";
                    break;
                case "030":
                    type = "针织上衣";
                    break;
                case "031":
                    type = "T恤";
                    break;
                case "040":
                    type = "真丝上衣";
                    break;
                case "050":
                    type = "梭织上衣";
                    break;
                case "060":
                    type = "衬衫";
                    break;
                case "070":
                    type = "薄外套";
                    break;
                case "071":
                    type = "厚外套";
                    break;
                case "080":
                    type = "梭织裤";
                    break;
                case "081":
                    type = "针织裤";
                    break;
                case "090":
                    type = "梭织裙";
                    break;
                case "091":
                    type = "针织裙";
                    break;
                case "100":
                    type = "毛织衫";
                    break;
                case "101":
                    type = "毛织外套";
                    break;
                case "102":
                    type = "毛织连衣裙";
                    break;
                case "110":
                    type = "风衣";
                    break;
                case "120":
                case "121":
                case "122":
                    type = "大衣";
                    break;
                case "150":
                    type = "羽绒服";
                    break;
                case "160":
                    type = "皮衣";
                    break;
                case "161":
                    type = "皮草";
                    break;
                case "170":
                    type = "牛仔裤";
                    break;
                case "180":
                    type = "连体裤";
                    break;
                case "190":
                    type = "马夹";
                    break;
                case "890":
                    type = "内衣";
                    break;
                default:
                    type = "#"+newTs;
                    break;
            }
            return type;
        } else { //0ld
            String ts;
            if (sn.length() == 9) {
                ts = sn.substring(4, 6);
            } else {
                ts = sn.substring(5, 7);
            }
            switch (ts) {
                case "02":
                    type = "连衣裙";
                    break;
                case "03":
                    type = "梭织上衣";
                    break;
                case "04":
                    type = "上衣";
                    break;
                case "05":
                    type = "针织上衣";
                    break;
                case "06":
                    type = "衬衫";
                    break;
                case "07":
                    type = "外套";
                    break;
                case "08":
                    type = "裤子";
                    break;
                case "09":
                    type = "裙子";
                    break;
                case "10":
                    type = "毛衫";
                    break;
                case "11":
                    type = "风衣";
                    break;
                case "12":
                    type = "长大衣";
                    break;
                case "13":
                    type = "短大衣";
                    break;
                case "14":
                    type = "棉衣";
                    break;
                case "15":
                    type = "羽绒服";
                    break;
                case "16":
                    type = "皮衣";
                    break;
                case "17":
                    type = "牛仔裤";
                    break;
                case "18":
                    type = "休闲裤";
                    break;
                default:
                    type = "#"+ts;
                    break;
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
            switch (newTs) {
                case "030":
                case "031":
                case "040":
                case "050":
                case "060":
                case "070":
                case "071":
                case "100":
                case "101":
                case "110":
                case "120":
                case "121":
                case "122":
                case "150":
                case "160":
                case "161":
                case "190":
                case "010":
                case "890":
                    secondType = "女装上装";
                    break;
                case "080":
                case "081":
                case "170":
                case "180":
                    secondType = "女装裤子";
                    break;
                case "090":
                case "091":
                case "020":
                case "021":
                case "102":
                    secondType = "裙装";
                    break;
                default:
                    secondType = "#" + newTs;
                    break;
            }
            return secondType;
        } else if (sn.matches(NAMEREG)) {// after 134
            String newTs;
            newTs = sn.substring(5, 8);
            switch (newTs) {
                case "030":
                case "031":
                case "040":
                case "050":
                case "060":
                case "070":
                case "071":
                case "100":
                case "101":
                case "110":
                case "120":
                case "121":
                case "122":
                case "150":
                case "160":
                case "161":
                case "190":
                case "010":
                case "890":
                    secondType = "女装上装";
                    break;
                case "080":
                case "081":
                case "170":
                case "180":
                    secondType = "女装裤子";
                    break;
                case "090":
                case "091":
                case "020":
                case "021":
                case "102":
                    secondType = "裙装";
                    break;
                default:
                    secondType = "#" + newTs;
                    break;
            }
            return secondType;
        } else {    //0ld
            String ts;
            if (sn.length() == 9) {
                ts = sn.substring(4, 6);
            } else {
                ts = sn.substring(5, 7);
            }
            switch (ts) {
                case "03":
                case "05":
                case "06":
                case "07":
                case "10":
                case "11":
                case "12":
                case "13":
                case "14":
                case "15":
                case "16":
                case "04":
                    secondType = "女装上装";
                    break;
                case "08":
                case "17":
                case "18":
                    secondType = "女装裤子";
                    break;
                case "02":
                case "09":
                    secondType = "裙装";
                    break;
                default:
                    secondType = "#"+ts;
                    break;
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
            switch (ts) {
                case "081":
                    thirdType = "打底裤";
                    break;
                case "170":
                    thirdType = "牛仔裤";
                    break;
                case "080":
                case "180":
                    thirdType = "休闲裤";
                    break;
                case "090":
                case "091":
                    thirdType = "半身裙";
                    break;
                case "020":
                case "021":
                case "102":
                    thirdType = "连衣裙";
                    break;
                case "031":
                    thirdType = "T恤";
                    break;
                case "040":
                case "060":
                    thirdType = "衬衣";
                    break;
                case "120":
                case "121":
                case "122":
                    thirdType = "大衣";
                    break;
                case "110":
                    thirdType = "风衣";
                    break;
                case "190":
                    thirdType = "马夹";
                    break;
                case "030":
                case "100":
                    thirdType = "毛衣/针织衫";
                    break;
                case "160":
                    thirdType = "皮衣";
                    break;
                case "070":
                case "071":
                case "101":
                case "010":
                    thirdType = "外套";
                    break;
                case "050":
                    thirdType = "雪纺衫";
                    break;
                case "150":
                    thirdType = "羽绒服";
                    break;
                case "161":
                    thirdType = "中款皮草";
                    break;
                case "890":
                    thirdType = "小吊带/背心";
                    break;
                default:
                    thirdType = "#" + ts;
                    break;
            }
        } else if (sn.matches(NAMEREG)) {//after 134
            ts = sn.substring(5, 8);
            switch (ts) {
                case "081":
                    thirdType = "打底裤";
                    break;
                case "170":
                    thirdType = "牛仔裤";
                    break;
                case "080":
                case "180":
                    thirdType = "休闲裤";
                    break;
                case "090":
                case "091":
                    thirdType = "半身裙";
                    break;
                case "020":
                case "021":
                case "102":
                    thirdType = "连衣裙";
                    break;
                case "031":
                    thirdType = "T恤";
                    break;
                case "040":
                case "060":
                    thirdType = "衬衣";
                    break;
                case "120":
                case "121":
                case "122":
                    thirdType = "大衣";
                    break;
                case "110":
                    thirdType = "风衣";
                    break;
                case "190":
                    thirdType = "马夹";
                    break;
                case "030":
                case "100":
                    thirdType = "毛衣/针织衫";
                    break;
                case "160":
                    thirdType = "皮衣";
                    break;
                case "070":
                case "071":
                case "101":
                case "010":
                    thirdType = "外套";
                    break;
                case "050":
                    thirdType = "雪纺衫";
                    break;
                case "150":
                    thirdType = "羽绒服";
                    break;
                case "161":
                    thirdType = "中款皮草";
                    break;
                case "890":
                    thirdType = "小吊带/背心";
                    break;
                default:
                    thirdType = "#" + ts;
                    break;
            }
        } else {//0ld
            //判断位数
            if (sn.length() == 9) {
                ts = sn.substring(4, 6);
            } else {
                ts = sn.substring(5, 7);
            }
            switch (ts) {
                case "06":
                    thirdType = "衬衣";
                    break;
                case "12":
                case "13":
                    thirdType = "大衣";
                    break;
                case "11":
                    thirdType = "风衣";
                    break;
                case "03":
                case "05":
                    thirdType = "毛衣/针织衫";
                    break;
                case "04":
                    thirdType = "雪纺衫";
                    break;
                case "14":
                    thirdType = "棉衣/棉服";
                    break;
                case "16":
                    thirdType = "皮衣";
                    break;
                case "07":
                    thirdType = "外套";
                    break;
                case "10":
                    thirdType = "卫衣/绒衫";
                    break;
                case "15":
                    thirdType = "羽绒服";
                    break;
                case "08":
                case "17":
                case "18":
                    thirdType = "休闲裤";
                    break;
                case "09":
                    thirdType = "半身裙";
                    break;
                case "02":
                    thirdType = "连衣裙";
                    break;
                default:
                    thirdType = "#";
                    break;
            }
        }
        return thirdType;
    }

    public static String getFitSeason(String sn) {
        String fitSeason;
        String ss = sn.substring(2, 3);
        switch (ss) {
            case "1":
            case "5":
            case "2":
            case "6":
                fitSeason = "春/夏FP01";
                break;
            case "3":
            case "7":
            case "4":
            case "8":
                fitSeason = "秋/冬FP02";
                break;
            default:
                fitSeason = "无FP00";
                break;
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
        switch (color) {
            case "010":
                originColor = "珍珠白";
                break;
            case "011":
                originColor = "漂白";
                break;
            case "012":
                originColor = "本白";
                break;
            case "014":
                originColor = "米白";
                break;
            case "020":
                originColor = "黑色";
                break;
            case "029":
                originColor = "沙色";
                break;
            case "030":
                originColor = "米色";
                break;
            case "031":
                originColor = "棕米色";
                break;
            case "033":
                originColor = "卡其色";
                break;
            case "035":
                originColor = "米黄";
                break;
            case "040":
                originColor = "橡皮红";
                break;
            case "041":
                originColor = "大红";
                break;
            case "042":
                originColor = "枣红";
                break;
            case "043":
                originColor = "粉红";
                break;
            case "044":
                originColor = "紫红";
                break;
            case "045":
                originColor = "桔红";
                break;
            case "046":
                originColor = "梅红";
                break;
            case "047":
                originColor = "铁锈红";
                break;
            case "048":
                originColor = "肉粉色";
                break;
            case "049":
                originColor = "桃红";
                break;
            case "050":
                originColor = "浅粉桔";
                break;
            case "051":
                originColor = "果绿";
                break;
            case "052":
                originColor = "军绿";
                break;
            case "053":
                originColor = "蓝绿";
                break;
            case "054":
                originColor = "墨绿";
                break;
            case "055":
                originColor = "亮绿";
                break;
            case "056":
                originColor = "灰绿";
                break;
            case "058":
                originColor = "芥末绿";
                break;
            case "060":
                originColor = "浅蓝";
                break;
            case "061":
                originColor = "蓝色";
                break;
            case "062":
                originColor = "宝蓝";
                break;
            case "063":
                originColor = "深蓝";
                break;
            case "064":
                originColor = "牛仔蓝";
                break;
            case "071":
                originColor = "浅灰";
                break;
            case "072":
                originColor = "中灰";
                break;
            case "073":
                originColor = "深灰";
                break;
            case "074":
                originColor = "花灰";
                break;
            case "081":
                originColor = "紫色";
                break;
            case "084":
                originColor = "灰紫";
                break;
            case "091":
                originColor = "咖啡";
                break;
            case "092":
                originColor = "棕色";
                break;
            case "093":
                originColor = "红咖";
                break;
            case "101":
                originColor = "鹅黄";
                break;
            case "102":
                originColor = "桔黄";
                break;
            case "103":
                originColor = "浅黄";
                break;
            case "104":
                originColor = "黄色";
                break;
            case "105":
                originColor = "深黄";
                break;
            case "111":
                originColor = "藏青色";
                break;
            case "112":
                originColor = "金色";
                break;
            case "115":
                originColor = "裸色";
                break;
            case "116":
                originColor = "粉桔";
                break;
            case "117":
                originColor = "水绿";
                break;
            case "118":
                originColor = "绿色";
                break;
            default:
                originColor = color;
                break;
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
        switch (color) {
            case "010":
            case "011":
            case "012":
            case "014":
            case "030":
                colorType = "白色";
                break;
            case "043":
            case "046":
            case "048":
            case "049":
                colorType = "粉色";
                break;
            case "020":
                colorType = "黑色";
                break;
            case "040":
            case "041":
            case "042":
            case "044":
            case "047":
                colorType = "红色";
                break;
            case "033":
            case "035":
            case "101":
            case "103":
            case "104":
            case "105":
            case "112":
            case "115":
                colorType = "黄色";
                break;
            case "071":
            case "072":
            case "073":
            case "074":
                colorType = "灰色";
                break;
            case "045":
            case "050":
            case "102":
            case "116":
                colorType = "桔色";
                break;
            case "060":
            case "061":
            case "062":
            case "063":
            case "064":
            case "111":
                colorType = "蓝色";
                break;
            case "051":
            case "052":
            case "053":
            case "054":
            case "055":
            case "056":
            case "058":
            case "117":
            case "118":
                colorType = "绿色";
                break;
            case "081":
            case "084":
                colorType = "紫色";
                break;
            case "029":
            case "031":
            case "091":
            case "092":
            case "093":
                colorType = "棕色";
                break;
            default:
                colorType = "#" + color;
                break;
        }
        return colorType;
    }

    //done
    //080 081 170 180
    public static String getLocalSize(String size) {
        String localSize;
        switch (size) {
            case "01":
                localSize = "S";
                break;
            case "02":
                localSize = "M";
                break;
            case "03":
                localSize = "L";
                break;
            case "04":
                localSize = "XL";
                break;
            case "05":
                localSize = "XXL";
                break;
            default:
                localSize = "#";
                break;
        }
        return localSize;
    }

    public static String getLocalSize(String sn, String size) {
        String localSize;
        if (sn.matches(NAMEREG2015)) {// after 2015
            String ts;
            ts = sn.substring(7, 10);
            if (ts.equals("080") || ts.equals("081") || ts.equals("170") || ts.equals("180")) {  //kuzi
                switch (size) {
                    case "01":
                        localSize = "S";
                        break;
                    case "02":
                        localSize = "M";
                        break;
                    case "03":
                        localSize = "L";
                        break;
                    case "04":
                        localSize = "XL";
                        break;
                    case "05":
                        localSize = "XXL";
                        break;
                    default:
                        localSize = "#";
                        break;
                }
            } else //shangyi
            {
                switch (size) {
                    case "01":
                        localSize = "S";
                        break;
                    case "02":
                        localSize = "M";
                        break;
                    case "03":
                        localSize = "L";
                        break;
                    case "04":
                        localSize = "XL";
                        break;
                    case "05":
                        localSize = "XXL";
                        break;
                    default:
                        localSize = "#";
                        break;
                }
            }

        } else if (sn.matches(NAMEREG)) {// after 2014
            String ts;
            ts = sn.substring(5, 8);
            if (ts.equals("080") || ts.equals("081") || ts.equals("170") || ts.equals("180")) {  //kuzi
                switch (size) {
                    case "01":
                        localSize = "S";
                        break;
                    case "02":
                        localSize = "M";
                        break;
                    case "03":
                        localSize = "L";
                        break;
                    case "04":
                        localSize = "XL";
                        break;
                    case "05":
                        localSize = "XXL";
                        break;
                    default:
                        localSize = "#";
                        break;
                }
            } else //shangyi
            {
                switch (size) {
                    case "01":
                        localSize = "S";
                        break;
                    case "02":
                        localSize = "M";
                        break;
                    case "03":
                        localSize = "L";
                        break;
                    case "04":
                        localSize = "XL";
                        break;
                    case "05":
                        localSize = "XXL";
                        break;
                    default:
                        localSize = "#";
                        break;
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
                switch (size) {
                    case "01":
                        localSize = "S";
                        break;
                    case "02":
                        localSize = "M";
                        break;
                    case "03":
                        localSize = "L";
                        break;
                    case "04":
                        localSize = "XL";
                        break;
                    case "05":
                        localSize = "XXL";
                        break;
                    default:
                        localSize = "#";
                        break;
                }
            } else //shangyi
            {
                switch (size) {
                    case "01":
                        localSize = "S";
                        break;
                    case "02":
                        localSize = "M";
                        break;
                    case "03":
                        localSize = "L";
                        break;
                    case "04":
                        localSize = "XL";
                        break;
                    case "05":
                        localSize = "XXL";
                        break;
                    default:
                        localSize = "#";
                        break;
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
                switch (size) {
                    case "01":
                        worldSize = "155/62A";
                        break;
                    case "02":
                        worldSize = "160/66A";
                        break;
                    case "03":
                        worldSize = "165/70A";
                        break;
                    case "04":
                        worldSize = "170/74A";
                        break;
                    case "05":
                        worldSize = "175/78A";
                        break;
                    default:
                        worldSize = "#" + typeCode;
                        break;
                }
            } else { //shangyi
                switch (size) {
                    case "01":
                        worldSize = "155/80A";
                        break;
                    case "02":
                        worldSize = "160/84A";
                        break;
                    case "03":
                        worldSize = "165/88A";
                        break;
                    case "04":
                        worldSize = "170/92A";
                        break;
                    case "05":
                        worldSize = "175/96A";
                        break;
                    default:
                        worldSize = "#";
                        break;
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
                    switch (size) {
                        case "01":
                            worldSize = "155/60A";
                            break;
                        case "02":
                            worldSize = "160/64A";
                            break;
                        case "03":
                            worldSize = "165/68A";
                            break;
                        case "04":
                            worldSize = "170/72A";
                            break;
                        case "05":
                            worldSize = "175/76A";
                            break;
                        default:
                            worldSize = "#" + typeCode;
                            break;
                    }
                } else {
                    switch (size) {
                        case "01":
                            worldSize = "155/62A";
                            break;
                        case "02":
                            worldSize = "160/66A";
                            break;
                        case "03":
                            worldSize = "165/70A";
                            break;
                        case "04":
                            worldSize = "170/74A";
                            break;
                        case "05":
                            worldSize = "175/78A";
                            break;
                        default:
                            worldSize = "#" + typeCode;
                            break;
                    }
                }
            } else {//shangyi
                if(typeCode.equals("100")){         //针织衫
                    if(i>132 && i<143){
                        switch (size) {
                            case "01":
                                worldSize = "75cm";
                                break;
                            case "02":
                                worldSize = "80cm";
                                break;
                            case "03":
                                worldSize = "85cm";
                                break;
                            case "04":
                                worldSize = "90cm";
                                break;
                            case "05":
                                worldSize = "95cm";
                                break;
                            default:
                                worldSize = "#" + typeCode;
                                break;
                        }
                    }
                    else if (i<133) {
                        switch (size) {
                            case "01":
                                worldSize = "76cm";
                                break;
                            case "02":
                                worldSize = "80cm";
                                break;
                            case "03":
                                worldSize = "84cm";
                                break;
                            case "04":
                                worldSize = "88cm";
                                break;
                            case "05":
                                worldSize = "92cm";
                                break;
                            default:
                                worldSize = "#" + typeCode;
                                break;
                        }
                    }else{
                        switch (size) {
                            case "01":
                                worldSize = "155/80A";
                                break;
                            case "02":
                                worldSize = "160/84A";
                                break;
                            case "03":
                                worldSize = "165/88A";
                                break;
                            case "04":
                                worldSize = "170/92A";
                                break;
                            case "05":
                                worldSize = "175/96A";
                                break;
                            default:
                                worldSize = "#" +typeCode;
                                break;
                        }
                    }
                }else{
                    switch (size) {
                        case "01":
                            worldSize = "155/80A";
                            break;
                        case "02":
                            worldSize = "160/84A";
                            break;
                        case "03":
                            worldSize = "165/88A";
                            break;
                        case "04":
                            worldSize = "170/92A";
                            break;                   
                        case "05":
                            worldSize = "175/96A";
                            break;
                        default:
                            worldSize = "#" + typeCode;
                            break;
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
                switch (size) {
                    case "01":
                        worldSize = "155/60A";
                        break;
                    case "02":
                        worldSize = "160/64A";
                        break;
                    case "03":
                        worldSize = "165/68A";
                        break;
                    case "04":
                        worldSize = "170/72A";
                        break;
                    case "05":
                        worldSize = "175/76A";
                        break;
                    default:
                        worldSize = "#" + typeCode;
                        break;
                }
            } else //shangyi
            {
                if (typeCode.equals("10")) { //毛衫
                    switch (size) {
                        case "01":
                            worldSize = "76cm";
                            break;
                        case "02":
                            worldSize = "80cm";
                            break;
                        case "03":
                            worldSize = "84cm";
                            break;
                        case "04":
                            worldSize = "88cm";
                            break;
                        case "05":
                            worldSize = "92A";
                            break;
                        default:
                            worldSize = "#" + typeCode;
                            break;
                    }
                }else{
                    switch (size) {
                        case "01":
                            worldSize = "155/80A";
                            break;
                        case "02":
                            worldSize = "160/84A";
                            break;
                        case "03":
                            worldSize = "165/88A";
                            break;
                        case "04":
                            worldSize = "170/92A";
                            break;
                        case "05":
                            worldSize = "175/96A";
                            break;
                        default:
                            worldSize = "#" + typeCode;
                            break;
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
