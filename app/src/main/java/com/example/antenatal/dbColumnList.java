package com.example.antenatal;

import android.provider.BaseColumns;

import java.util.List;

/**
 * Created by sherif146 on 03/01/2018.
 */

public class dbColumnList {
    public static List <myModels.tipsModel> dailyTips, weeklyTips, monthlyTips,
            nutritionTips;
    public static String address = "https://antenantal.000webhostapp.com/antenatalrest.php";
    public static List <myModels.trimesterModel> trimeseterOne,
            trimeseterTwo, trimeseterThree, momHealth, babyHealth, breastFeeding;

    public static int TabPostion;
    public static String TabText;

    public static final String tabs[] = {"1st Day", "2nd Day", "3rd Day", "4th Day", "5th Day", "6th Day", "7th Day", "8th Day", "9th Day", "10th Day",
            "11th Day", "12th Day", "13th Day", "14th Day", "15th Day", "16th Day", "17th Day", "18th Day", "19th Day", "20th Day",
            "21st Day", "22nd Day", "23rd Day", "24th Day", "25th Day", "26th Day", "27th Day", "28th Day", "29th Day", "30th Day",
            "31st Day", "32nd Day", "33rd Day", "34th Day", "35th Day", "36th Day", "37th Day", "38th Day", "39th Day", "40th Day",
            "41st Day", "42nd Day", "43rd Day", "44th Day", "45th Day", "46th Day", "47th Day", "48th Day", "49th Day", "50th Day",
            "51st Day", "52nd Day", "53rd Day", "54th Day", "55th Day", "56th Day", "57th Day", "58th Day", "59th Day", "60th Day", "61st Day",
            "62nd Day", "63rd Day", "64th Day", "65th Day", "66th Day", "67th Day", "68th Day", "69th Day", "70th Day", "71st Day", "72nd Day",
            "73rd Day", "74th Day", "75th Day", "76th Day", "77th Day", "78th Day", "79th Day", "80th Day", "81st Day", "82nd Day", "83rd Day",
            "84th Day", "85th Day", "86th Day", "87th Day", "88th Day", "89th Day", "90th Day", "91st Day", "92nd Day", "93rd Day", "94th Day",
            "95th Day", "96th Day", "97th Day", "98th Day", "99th Day", "100th Day", "101st Day", "102nd Day", "103rd Day", "104th Day", "105th Day",
            "106th Day", "107th Day", "108th Day", "109th Day", "110th Day", "111th Day", "112th Day", "113th Day", "114th Day", "115th Day", "116th Day",
            "117th Day", "118th Day", "119th Day", "120th Day", "121st Day", "122nd Day", "123rd Day", "124th Day", "125th Day", "126th Day", "127th Day",
            "128th Day", "129th Day", "130th Day", "131st Day", "132nd Day", "133rd Day", "134th Day", "135th Day", "136th Day", "137th Day", "138th Day",
            "139th Day", "140th Day", "141st Day", "142nd Day", "143rd Day", "144th Day", "145th Day", "146th Day", "147th Day", "148th Day", "149th Day",
            "150th Day", "151st Day", "152nd Day", "153rd Day", "154th Day", "155th Day", "156th Day", "157th Day", "158th Day", "159th Day", "160th Day",
            "161st Day", "162nd Day", "163rd Day", "164th Day", "165th Day", "166th Day", "167th Day", "168th Day", "169th Day", "170th Day", "171st Day",
            "172nd Day", "173rd Day", "174th Day", "175th Day", "176th Day", "177th Day", "178th Day", "179th Day", "180th Day", "181st Day", "182nd Day",
            "183rd Day", "184th Day", "185th Day", "186th Day", "187th Day", "188th Day", "189th Day", "190th Day", "191st Day", "192nd Day", "193rd Day",
            "194th Day", "195th Day", "196th Day", "197th Day", "198th Day", "199th Day", "200th Day", "201st Day", "202nd Day", "203rd Day", "204th Day",
            "205th Day", "206th Day", "207th Day", "208th Day", "209th Day", "210th Day", "211th Day", "212th Day", "213th Day", "214th Day", "215th Day",
            "216th Day", "217th Day", "218th Day", "219th Day", "220th Day", "221st Day", "222nd Day", "223rd Day", "224th Day", "225th Day", "226th Day",
            "227th Day", "228th Day", "229th Day", "230th Day", "231st Day", "232nd Day", "233rd Day", "234th Day", "235th Day", "236th Day", "237th Day",
            "238th Day", "239th Day", "240th Day", "241st Day", "242nd Day", "243rd Day", "244th Day", "245th Day", "246th Day", "247th Day", "248th Day",
            "249th Day", "250th Day", "251st Day", "252nd Day", "253rd Day", "254th Day", "255th Day", "256th Day", "257th Day", "258th Day", "259th Day",
            "260th Day", "261st Day", "262nd Day", "263rd Day", "264th Day", "265th Day", "266th Day", "267th Day", "268th Day", "269th Day", "270th Day",
            "271st Day", "272nd Day", "273rd Day", "274th Day", "275th Day", "276th Day", "277th Day", "278th Day", "279th Day", "280th Day"
    };
    public static final String weekTabs[] = {"1st Week", "2nd Week", "3rd Week", "4th Week", "5th Week", "6th Week",
            "7th Week", "8th Week", "9th Week", "10th Week", "11th Week", "12th Week", "13th Week", "14th Week",
            "15th Week", "16th Week", "17th Week", "18th Week", "19th Week", "20th Week", "21st Week", "22nd Week",
            "23rd Week", "24th Week", "25th Week", "26th Week", "27th Week", "28th Week", "29th Week", "30th Week",
            "31st Week", "32nd Week", "33rd Week", "34th Week", "35th Week", "36th Week", "37th Week", "38th Week", "39th Week", "40th Week"};

    public static final String monthTabs[] = {"1st Month", "2nd Month", "3rd Month",
            "4th Month", "5th Month", "6th Month", "7th Month", "8th Month",
            "9th Month", "10th Month"};
    public static final String trimesterTabs[] = {"1st Trimester", "2nd Trimester", "3rd Trimester"};
    public static final String healthTabs[] = {"Mom's Health", "Baby's Health", "Breastfeeding"};

    public static class pregnantInformation implements BaseColumns{
        public static final String TABLE_NAME = "pregnantInformation";
        public static final String COLUMN_STATRDATE= "pregStartDate";
        public static final String COLUMN_BABYDESCRIPTION = "babyGenderDescription";
        public static final String COLUMN_ENDDATE = "pregEndDate";
        public static final String COLUMN_NOOFBABY = "noOfBaby";
        public static final String COLUMN_HID = "hid";
    }

    public static class contactInfo implements BaseColumns{
        public static final String TABLE_NAME = "contactInfo";
        public static final String COLUMN_CONTACTNAME= "contactName";
        public static final String COLUMN_RELATIONSHIP = "relationship";
        public static final String COLUMN_PHONE= "phone";
        public static final String COLUMN_EMAIL= "email";
        public static final String COLUMN_CONTACTADDRESS = "officeAddress";
        public static final String COLUMN_CONTACTID = "contactId";
    }

    public static class abuadNotice implements BaseColumns{
        public static final String TABLE_NAME = "abuadnotice";
        public static final String COLUMN_ID = "noticeid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_NOTICEDATE = "noticedate";
        public static final String COLUMN_STATUS = "delStatus";
    }

    public static class hospitalDocInfo implements BaseColumns{
        public static final String TABLE_NAME = "hospitalDocInfo";
        public static final String COLUMN_DOCID = "docid";
        public static final String COLUMN_DOCNAME= "docname";
        public static final String COLUMN_DOCTYPE = "doctype";
        public static final String COLUMN_GENDER= "gender";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE= "phone";
        public static final String COLUMN_DATEREG= "dateReg";
        public static final String COLUMN_CONTACTADDRESS = "contactAddress";
    }
    public static class antenantalUser implements BaseColumns{
        public static final String TABLE_NAME = "antenantalUser";
        public static final String COLUMN_HID = "hid";
        public static final String COLUMN_FULLNAME = "patientName";
        public static final String COLUMN_PHONE = "patientPhone";
        public static final String COLUMN_EMAIL = "patientEmail";
        public static final String COLUMN_CONTACTADD = "contactAddress";
        public static final String COLUMN_OFFICEADD = "officeAddress";
        public static final String COLUMN_ILLNESSDESCRIPTION= "illnessDescription";
        public static final String COLUMN_STATE = "patientState";
        public static final String COLUMN_LGOV= "patientLocalGovt";
        public static final String COLUMN_CREATEDBY = "createdBy";
        public static final String COLUMN_DATEREG = "dateReg";
    }


    public static class userSchedule implements BaseColumns{
        public static final String TABLE_NAME = "userSchedule";
        public static final String COLUMN_HID = "hid";
        public static final String COLUMN_SCHEDULEDATE = "dateSchedule";
        public static final String COLUMN_SCHEDULETIME = "timeSchedule";
        public static final String COLUMN_SCHEDULEDOCTOR = "docSchedule";
        public static final String COLUMN_SCHEDULEOUTCOME = "outcome";
        public static final String COLUMN_SCHEDULESTATUS = "status";
        public static final String COLUMN_DATEREG = "recordDate";
        public static final String COLUMN_PURPOSE = "purpose";
    }

    public static class userProfilePics implements BaseColumns{
        public static final String TABLE_NAME = "userProfilePics";
        public static final String COLUMN_REGNO = "regNo";
        public static final String COLUMN_PROFILEPICS = "profilepics";
    }

}
