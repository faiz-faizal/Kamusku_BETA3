package com.maxibi.kamusku_beta3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by User on 8/11/2017
 * //////////////////////////// CREATE BY MOHD FAIZ FAIZAL A.K.A FAIZ-FAIZAL /////////////////////////////////
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    final static String DB_NAME = "kamusku_database.db";
    final static int DB_VERSION = 1;

    final static String TB_NAME = "word";
    final static String CLM_ID = "word_id";
    final static String CLM_EN = "word_en";
    final static String CLM_MS = "word_ms";

    final static String CRT_DB_QUERY = "CREATE TABLE "+TB_NAME+" ( "+CLM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CLM_MS+" TEXT, "+CLM_EN+" TEXT )";
    final static String UPD_DB_QUERY = "DROP TABLE IF EXISTS" + TB_NAME;

    Context context;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CRT_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(UPD_DB_QUERY);
        onCreate(sqLiteDatabase);

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // C..R..U..D.. METHODS


    //READ
    //READ ALL WORD(GET)
    public ArrayList<Word> getAllWord()
    {
        ArrayList<Word> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecAllQuery = "SELECET * FROM" + DB_NAME;
        Cursor csr = db.rawQuery(selecAllQuery,null);

        if( csr.moveToFirst()){
            do{
                Word dic_word = new Word(csr.getString(csr.getColumnIndex(CLM_MS)), csr.getString(csr.getColumnIndex(CLM_EN)));
                arrayList.add(dic_word);
            }while (csr.moveToNext());
        }
        return arrayList;
    }

    //READ FOR SPECIFIC WORD
    public Word getWord(String w)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Word dic_word = null;

        //SQL Command to select specific word/////////////////////////////////////
        String selectWordSQL = "SELECT * FROM "+DB_NAME+" WHERE "+CLM_MS+" = '"+w+"' ";
        Cursor csr = db.rawQuery(selectWordSQL,null);

        if(csr.moveToFirst())
        {
            dic_word = new Word(csr.getString(csr.getColumnIndex(CLM_MS)), csr.getString(csr.getColumnIndex(CLM_EN)));
        }
        return dic_word;
    }

    //INITIALIZE DATABASE FOR THE FIRST TIME
    public void initializeDatabaseFortheFirstTime(ArrayList<Word> wordDefinitions) {

    SQLiteDatabase database=this.getWritableDatabase();
    database.execSQL("BEGIN");

    ContentValues contentValues=new ContentValues();

    for (Word wordDefinition : wordDefinitions) {
        contentValues.put(CLM_MS, wordDefinition.word_MS);
        contentValues.put(CLM_EN, wordDefinition.word_EN);
        database.insert(DB_NAME, null, contentValues);
    }
    database.execSQL("COMMIT");

    }
}
