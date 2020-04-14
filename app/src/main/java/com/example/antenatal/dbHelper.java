package com.example.antenatal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sherif146 on 03/01/2018.
 */

public class dbHelper extends SQLiteOpenHelper {
    // Database Info
    // Database Info
    public static final String DATABASE_NAME = "ANTN.db";
    private static final int DATABASE_VERSION = 3;
    private Context mcontext;
    private SQLiteDatabase mdatabase;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mcontext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_ANTENANTALUSER = "CREATE TABLE IF NOT EXISTS " + dbColumnList.antenantalUser.TABLE_NAME +
                "(" +
                dbColumnList.antenantalUser._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define a primary key
                dbColumnList.antenantalUser.COLUMN_HID + " VARCHAR, " +
                dbColumnList.antenantalUser.COLUMN_CONTACTADD + " TEXT, " +
                dbColumnList.antenantalUser.COLUMN_CREATEDBY + " VARCHAR, " +
                dbColumnList.antenantalUser.COLUMN_DATEREG + " VARCHAR, " +
                dbColumnList.antenantalUser.COLUMN_EMAIL + " VARCHAR, " +
                dbColumnList.antenantalUser.COLUMN_ILLNESSDESCRIPTION + " TEXT, " +
                dbColumnList.antenantalUser.COLUMN_LGOV + " VARCHAR, " +
                dbColumnList.antenantalUser.COLUMN_OFFICEADD + " TEXT, " +
                dbColumnList.antenantalUser.COLUMN_PHONE + " VARCHAR, " +
                dbColumnList.antenantalUser.COLUMN_STATE + " VARCHAR, " +
                dbColumnList.antenantalUser.COLUMN_FULLNAME + " VARCHAR " +
                ")";

        String CREATE_TABLE_CONTACTINFO= "CREATE TABLE IF NOT EXISTS " + dbColumnList.contactInfo.TABLE_NAME +
                "(" +
                dbColumnList.contactInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define a primary key
                dbColumnList.contactInfo.COLUMN_CONTACTADDRESS + " TEXT, " +
                dbColumnList.contactInfo.COLUMN_CONTACTID + " VARCHAR, " +
                dbColumnList.contactInfo.COLUMN_CONTACTNAME + " VARCHAR, " +
                dbColumnList.contactInfo.COLUMN_RELATIONSHIP + " VARCHAR, " +
                dbColumnList.contactInfo.COLUMN_EMAIL + " VARCHAR, " +
                dbColumnList.contactInfo.COLUMN_PHONE + " VARCHAR " +
                ")";

        String CREATE_TABLE_ABUADNOTICE= "CREATE TABLE IF NOT EXISTS " + dbColumnList.abuadNotice.TABLE_NAME +
                "(" +
                dbColumnList.abuadNotice._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define a primary key
                dbColumnList.abuadNotice.COLUMN_ID + " VARCHAR, " +
                dbColumnList.abuadNotice.COLUMN_DESCRIPTION + " TEXT, " +
                dbColumnList.abuadNotice.COLUMN_TITLE + " TEXT, " +
                dbColumnList.abuadNotice.COLUMN_AUTHOR + " VARCHAR, " +
                dbColumnList.abuadNotice.COLUMN_STATUS + " VARCHAR, " +
                dbColumnList.abuadNotice.COLUMN_NOTICEDATE + " VARCHAR " +
                ")";
        String CREATE_TABLE_PREGNANTINFO= "CREATE TABLE IF NOT EXISTS " + dbColumnList.pregnantInformation.TABLE_NAME +
                "(" +
                dbColumnList.pregnantInformation._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define a primary key
                dbColumnList.pregnantInformation.COLUMN_BABYDESCRIPTION + " TEXT, " +
                dbColumnList.pregnantInformation.COLUMN_ENDDATE + " VARCHAR, " +
                dbColumnList.pregnantInformation.COLUMN_STATRDATE + " VARCHAR, " +
                dbColumnList.pregnantInformation.COLUMN_HID + " VARCHAR, " +
                dbColumnList.pregnantInformation.COLUMN_NOOFBABY + " VARCHAR " +
                ")";
        String CREATE_TABLE_HOSPITALDOC= "CREATE TABLE IF NOT EXISTS " + dbColumnList.hospitalDocInfo.TABLE_NAME +
                "(" +
                dbColumnList.hospitalDocInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define a primary key
                dbColumnList.hospitalDocInfo.COLUMN_DOCID + " VARCHAR, " +
                dbColumnList.hospitalDocInfo.COLUMN_DATEREG + " VARCHAR, " +
                dbColumnList.hospitalDocInfo.COLUMN_DOCNAME + " VARCHAR, " +
                dbColumnList.hospitalDocInfo.COLUMN_EMAIL + " VARCHAR, " +
                dbColumnList.hospitalDocInfo.COLUMN_PHONE + " VARCHAR, " +
                dbColumnList.hospitalDocInfo.COLUMN_GENDER + " VARCHAR, " +
                dbColumnList.hospitalDocInfo.COLUMN_DOCTYPE + " VARCHAR, " +
                dbColumnList.hospitalDocInfo.COLUMN_CONTACTADDRESS + " VARCHAR " +
                ")";

        String CREATE_TABLE_USERSCHEDULE= "CREATE TABLE IF NOT EXISTS " + dbColumnList.userSchedule.TABLE_NAME +
                "(" +
                dbColumnList.userSchedule._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define a primary key
                dbColumnList.userSchedule.COLUMN_HID + " VARCHAR, " +
                dbColumnList.userSchedule.COLUMN_SCHEDULEDATE + " VARCHAR, " +
                dbColumnList.userSchedule.COLUMN_SCHEDULEDOCTOR + " VARCHAR, " +
                dbColumnList.userSchedule.COLUMN_DOCTYPE + " VARCHAR, " +
                dbColumnList.userSchedule.COLUMN_SCHEDULEOUTCOME + " TEXT, " +
                dbColumnList.userSchedule.COLUMN_SCHEDULESTATUS + " VARCHAR, " +
                dbColumnList.userSchedule.COLUMN_SCHEDULETIME + " VARCHAR, " +
                dbColumnList.userSchedule.COLUMN_DATEREG + " VARCHAR, " +
                dbColumnList.userSchedule.COLUMN_PURPOSE + " TEXT " +
                ")";


        String CREATE_TABLE_PROFILEPICS= "CREATE TABLE IF NOT EXISTS " + dbColumnList.userProfilePics.TABLE_NAME +
                "(" +
                    dbColumnList.userProfilePics._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define a primary key
                    dbColumnList.userProfilePics.COLUMN_REGNO + " VARCHAR, " +
                    dbColumnList.userProfilePics.COLUMN_PROFILEPICS + " blob " +
                ")";

        sqLiteDatabase.execSQL(CREATE_TABLE_ANTENANTALUSER);
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTACTINFO);
        sqLiteDatabase.execSQL(CREATE_TABLE_ABUADNOTICE);
        sqLiteDatabase.execSQL(CREATE_TABLE_HOSPITALDOC);
        sqLiteDatabase.execSQL(CREATE_TABLE_PREGNANTINFO);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILEPICS);
        sqLiteDatabase.execSQL(CREATE_TABLE_USERSCHEDULE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbColumnList.contactInfo.TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbColumnList.antenantalUser.TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbColumnList.hospitalDocInfo.TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbColumnList.abuadNotice.TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbColumnList.pregnantInformation.TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbColumnList.userSchedule.TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbColumnList.userProfilePics.TABLE_NAME);
            //recreate the tables
            onCreate(sqLiteDatabase);
        }
    }

/*******************************************************************/
    /****** USER DETAILS *********************************************/

    //****************************CONTACT INFO*******************************************
    public Cursor verifyContactExist(String contactId){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM "+dbColumnList.contactInfo.TABLE_NAME + " WHERE "+dbColumnList.contactInfo.COLUMN_CONTACTID +"= '" + contactId +"' Limit 1";
        return database.rawQuery(sql, null);
    }
    public void SaveContactInformation(String contactId, String contactAddress,String contactName, String companyRelationship,
                           String contactEmail, String contactPhone){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbColumnList.contactInfo.COLUMN_CONTACTID, contactId);
        cv.put(dbColumnList.contactInfo.COLUMN_CONTACTADDRESS, contactAddress);
        cv.put(dbColumnList.contactInfo.COLUMN_CONTACTNAME, contactName);
        cv.put(dbColumnList.contactInfo.COLUMN_RELATIONSHIP, companyRelationship);
        cv.put(dbColumnList.contactInfo.COLUMN_EMAIL, contactEmail);
        cv.put(dbColumnList.contactInfo.COLUMN_PHONE, contactPhone);

        Cursor cursor = verifyContactExist(contactId);
        if(cursor.getCount() >= 1) {
            database.update(dbColumnList.contactInfo.TABLE_NAME, cv, dbColumnList.contactInfo.COLUMN_CONTACTID + "= ?", new String[]{contactId});
        }else{
            database.insert(dbColumnList.contactInfo.TABLE_NAME,null,cv);
        }
    }

    public Cursor getAllContact(){
        //    openDatabase();
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.contactInfo.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public Cursor getAContact(String contactId){
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.contactInfo.TABLE_NAME,
                null,
                dbColumnList.contactInfo.COLUMN_CONTACTID +" = ? Limit 1",
                new String[]{contactId},
                null,
                null,null);
    }

    //delete all schedule
    public void deleteContact(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(dbColumnList.contactInfo.TABLE_NAME,
                null,null);
    }

    //*******************USERS INFO****************************************************

    public Cursor verifyUserExist(String regno){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM "+dbColumnList.antenantalUser.TABLE_NAME + " WHERE "+dbColumnList.antenantalUser.COLUMN_HID +"= '" + regno +"' Limit 1";
        return database.rawQuery(sql, null);
    }
    public void saveUserInformation(String regno, String fullname,
                                         String contactAdd, String createdBy,String dateReg,
                                         String contactAddress, String email,String illnessdescription,
                                         String lgov, String state, String phone){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbColumnList.antenantalUser.COLUMN_HID, regno);
        cv.put(dbColumnList.antenantalUser.COLUMN_FULLNAME, fullname);
        cv.put(dbColumnList.antenantalUser.COLUMN_CONTACTADD, contactAdd);
        cv.put(dbColumnList.antenantalUser.COLUMN_CREATEDBY, createdBy);
        cv.put(dbColumnList.antenantalUser.COLUMN_DATEREG, dateReg);
        cv.put(dbColumnList.antenantalUser.COLUMN_EMAIL, email);
        cv.put(dbColumnList.antenantalUser.COLUMN_ILLNESSDESCRIPTION, illnessdescription);
        cv.put(dbColumnList.antenantalUser.COLUMN_LGOV, lgov);
        cv.put(dbColumnList.antenantalUser.COLUMN_OFFICEADD, contactAddress);
        cv.put(dbColumnList.antenantalUser.COLUMN_PHONE, phone);
        cv.put(dbColumnList.antenantalUser.COLUMN_STATE, state);

        Cursor cursor = verifyUserExist(regno);
        if(cursor.getCount() >= 1) {
            database.update(dbColumnList.antenantalUser.TABLE_NAME, cv, dbColumnList.antenantalUser.COLUMN_HID + "= ?", new String[]{regno});
        }else{
            database.insert(dbColumnList.antenantalUser.TABLE_NAME,null,cv);
        }
    }
    public Cursor getAUser(String regno){
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.antenantalUser.TABLE_NAME,
                null,
                dbColumnList.antenantalUser.COLUMN_HID +" = ? Limit 1",
                new String[]{regno},
                null,
                null,null);
    }


    //*********************************DOCTORS*************************************************

    public Cursor verifyDoctorExist(String staffid){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM "+dbColumnList.hospitalDocInfo.TABLE_NAME + " WHERE "+dbColumnList.hospitalDocInfo.COLUMN_DOCID +"= '" + staffid +"' Limit 1";
        return database.rawQuery(sql, null);
    }
    public void saveDoctorInformation(String docid, String fullname,
                                         String dateReg, String gender,String phone, String email,
                                        String doctype,String contactAdd){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbColumnList.hospitalDocInfo.COLUMN_DOCID, docid);
        cv.put(dbColumnList.hospitalDocInfo.COLUMN_DOCNAME, fullname);
        cv.put(dbColumnList.hospitalDocInfo.COLUMN_DATEREG, dateReg);
        cv.put(dbColumnList.hospitalDocInfo.COLUMN_PHONE, phone);
        cv.put(dbColumnList.hospitalDocInfo.COLUMN_GENDER, gender);
        cv.put(dbColumnList.hospitalDocInfo.COLUMN_EMAIL, email);
        cv.put(dbColumnList.hospitalDocInfo.COLUMN_DOCTYPE, doctype);
        cv.put(dbColumnList.hospitalDocInfo.COLUMN_CONTACTADDRESS, contactAdd);

        Cursor cursor = verifyDoctorExist(docid);
        if(cursor.getCount() >= 1) {
            database.update(dbColumnList.hospitalDocInfo.TABLE_NAME, cv, dbColumnList.hospitalDocInfo.COLUMN_DOCID + "= ?", new String[]{docid});
        }else{
            database.insert(dbColumnList.hospitalDocInfo.TABLE_NAME,null,cv);
        }
    }
    public Cursor getADoctorInfo(String staffid){
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.hospitalDocInfo.TABLE_NAME,
                null,
                dbColumnList.hospitalDocInfo.COLUMN_DOCID +" = ? Limit 1",
                new String[]{staffid},
                null,
                null,null);
    }

    public Cursor getAllDoctor(){
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.hospitalDocInfo.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    //delete all schedule
    public void deleteDoctors(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(dbColumnList.hospitalDocInfo.TABLE_NAME,
                null,null);
    }
    //*********************** PREGNANT INFORMATION ***********************************************************

    public Cursor verifyPregnantInfoExist(String regno){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM "+dbColumnList.pregnantInformation.TABLE_NAME + " WHERE "+dbColumnList.pregnantInformation.COLUMN_HID +"= '" + regno +"' Limit 1";
        return database.rawQuery(sql, null);
    }

    public void savePregnantInformation(String regno,String startDate, String endDate, String noofbaby,
                                          String babydescription){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbColumnList.pregnantInformation.COLUMN_HID, regno);
        cv.put(dbColumnList.pregnantInformation.COLUMN_STATRDATE, startDate);
        cv.put(dbColumnList.pregnantInformation.COLUMN_ENDDATE, endDate);
        cv.put(dbColumnList.pregnantInformation.COLUMN_BABYDESCRIPTION, babydescription);
        cv.put(dbColumnList.pregnantInformation.COLUMN_NOOFBABY, noofbaby);

        Cursor cursor = verifyPregnantInfoExist(regno);
        if(cursor.getCount() >= 1) {
            database.update(dbColumnList.pregnantInformation.TABLE_NAME, cv, dbColumnList.pregnantInformation.COLUMN_HID + "= ?", new String[]{regno});
        }else{
            database.insert(dbColumnList.pregnantInformation.TABLE_NAME,null,cv);
        }
    }
    public Cursor getAPregnantInfo(String regno){
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.pregnantInformation.TABLE_NAME,
                null,
                dbColumnList.pregnantInformation.COLUMN_HID +" = ? Limit 1",
                new String[]{regno},
                null,
                null,null);
    }

//    *************************************************************************
//*********************** NOTICE ***********************************************************

    public Cursor verifyNoticeExist(String noticeid){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM "+dbColumnList.abuadNotice.TABLE_NAME + " WHERE "+dbColumnList.abuadNotice.COLUMN_ID +"= '" + noticeid +"' Limit 1";
        return database.rawQuery(sql, null);
    }
    public void saveNotice(String noticeid,String noticedesc, String author,String title, String date, String status){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbColumnList.abuadNotice.COLUMN_ID, noticeid);
        cv.put(dbColumnList.abuadNotice.COLUMN_TITLE, title);
        cv.put(dbColumnList.abuadNotice.COLUMN_DESCRIPTION, noticedesc);
        cv.put(dbColumnList.abuadNotice.COLUMN_NOTICEDATE, date);
        cv.put(dbColumnList.abuadNotice.COLUMN_AUTHOR, author);
        cv.put(dbColumnList.abuadNotice.COLUMN_STATUS, status);

        Cursor cursor = verifyNoticeExist(noticeid);
        if(cursor.getCount() >= 1) {
            database.update(dbColumnList.abuadNotice.TABLE_NAME, cv, dbColumnList.abuadNotice.COLUMN_ID + " = ?", new String[]{noticeid});
        }else{
            database.insert(dbColumnList.abuadNotice.TABLE_NAME,null,cv);
        }
    }
    public Cursor getANotice(String noticeId){
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.abuadNotice.TABLE_NAME,
                null,
                dbColumnList.abuadNotice.COLUMN_ID +" = ?  AND "+ dbColumnList.abuadNotice.COLUMN_STATUS + " = ? Limit 1",
                new String[]{noticeId,"0"},
                null,
                null,null);
    }

    public Cursor getAllNotice(){
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.abuadNotice.TABLE_NAME,
                null,
                 dbColumnList.abuadNotice.COLUMN_STATUS + " = ?",
                new String[]{"0"},
                null,
                null,
                null);
    }



//    **************************PROFILE PICS********************************


    public Cursor verifyImageExist(String regno){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM "+dbColumnList.userProfilePics.TABLE_NAME + " WHERE "+dbColumnList.userProfilePics.COLUMN_REGNO +"= '" + regno +"' Limit 1";
        return database.rawQuery(sql, null);
    }
    public void saveProfilePics(String regno,byte[] imageData){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbColumnList.userProfilePics.COLUMN_REGNO, regno);
        cv.put(dbColumnList.userProfilePics.COLUMN_PROFILEPICS, imageData);

        Cursor cursor = verifyImageExist(regno);
        if(cursor.getCount() >= 1) {
            database.update(dbColumnList.userProfilePics.TABLE_NAME, cv, dbColumnList.userProfilePics.COLUMN_REGNO + " = ?", new String[]{regno});
        }else{
            database.insert(dbColumnList.userProfilePics.TABLE_NAME,null,cv);
        }
    }
    public Cursor getAProfilePics(String regno){
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.userProfilePics.TABLE_NAME,
                null,
                dbColumnList.userProfilePics.COLUMN_REGNO + " = ? Limit 1",
                new String[]{regno},
                null,
                null,null);
    }


    //    **************************PROFILE PICS********************************

    public Cursor verifyScheduleExist(String scheduleid){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM "+dbColumnList.userSchedule.TABLE_NAME + " WHERE "+dbColumnList.userSchedule.COLUMN_HID +" = '" + scheduleid +"' Limit 1";
        return database.rawQuery(sql, null);
    }
    public void saveSchedule(String scheduleid, String scheduleDate, String scheduleTime,
                             String scheduleDoctor,String doctype, String scheduleStatus,
                             String scheduleOutcome,String schedulePurpose,String DateReg){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbColumnList.userSchedule.COLUMN_HID, scheduleid);
        cv.put(dbColumnList.userSchedule.COLUMN_SCHEDULEDATE, scheduleDate);
        cv.put(dbColumnList.userSchedule.COLUMN_SCHEDULETIME, scheduleTime);
        cv.put(dbColumnList.userSchedule.COLUMN_DOCTYPE, doctype);
        cv.put(dbColumnList.userSchedule.COLUMN_SCHEDULEDOCTOR, scheduleDoctor);
        cv.put(dbColumnList.userSchedule.COLUMN_SCHEDULESTATUS, scheduleStatus);
        cv.put(dbColumnList.userSchedule.COLUMN_SCHEDULEOUTCOME, scheduleOutcome);
        cv.put(dbColumnList.userSchedule.COLUMN_PURPOSE, schedulePurpose);
        cv.put(dbColumnList.userSchedule.COLUMN_DATEREG, DateReg);

        Cursor cursor = verifyScheduleExist(scheduleid);
        if(cursor.getCount() >= 1) {
            database.update(dbColumnList.userSchedule.TABLE_NAME, cv, dbColumnList.userSchedule.COLUMN_HID + " = ? ", new String[]{scheduleid});
        }else{
            database.insert(dbColumnList.userSchedule.TABLE_NAME,null,cv);
        }
    }
    public Cursor getSchedule(String scheduleid){
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.userSchedule.TABLE_NAME,
                null,
                dbColumnList.userSchedule.COLUMN_HID + " = ?",
                new String[]{scheduleid},
                null,
                null,dbColumnList.userSchedule.COLUMN_DATEREG + " desc");
    }
    public Cursor getAllSchedule(){
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.userSchedule.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,dbColumnList.userSchedule.COLUMN_DATEREG + " desc");
    }
    public Cursor getAllPatientPendingSchedule(){
        String status = "0";
        SQLiteDatabase database = getReadableDatabase();
        return database.query(dbColumnList.userSchedule.TABLE_NAME,
                null,
                dbColumnList.userSchedule.COLUMN_SCHEDULESTATUS + " = ? ",
                new String[]{status},
                null,
                null,dbColumnList.userSchedule.COLUMN_HID + " desc Limit 1");
    }

    //delete all schedule
    public void deleteSchedule(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(dbColumnList.userSchedule.TABLE_NAME,
                null,null);
    }

}
