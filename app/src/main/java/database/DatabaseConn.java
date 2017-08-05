package database;

/**
 * Created by Average on 06-12-2016.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import model.Favorite;
import model.HistoryEntry;
import util.GeneralUtils;

import static util.GeneralUtils.GetDateTime;


public class DatabaseConn extends SQLiteOpenHelper {
    //to enum
    public static final String DATABASE_NAME = "local_db.db";

    public static final String TABLE1 = "history";
    public static final String TB1_COL_1 = "entry_id";
    public static final String TB1_COL_2 = "manga_src";
    public static final String TB1_COL_3 = "manga_id";
    public static final String TB1_COL_4 = "chap_id";
    public static final String TB1_COL_5 = "date";
    public static final String TB1_COL_6 = "manga_name";
    public static final String TB1_COL_7 = "image";
    public static final String TB1_COL_8 = "chap_num";
    public static final String TB1_COL_9 = "position";
    public static final String TB1_COL_10 = "size";

    public static final String TABLE2 = "favorites";
    public static final String TB2_COL_1 = "favorite_id";
    public static final String TB2_COL_2 = "manga_src";
    public static final String TB2_COL_3 = "manga_id";
    public static final String TB2_COL_4 = "date";
    public static final String TB2_COL_5 = "state";
    public static final String TB2_COL_6 = "manga_name";
    public static final String TB2_COL_7 = "image";

    public static final String TABLE3 = "main";
    public static final String TB3_COL_1 = "entry_id";
    public static final String TB3_COL_2 = "manga_img";
    public static final String TB3_COL_3 = "manga_id";
    public static final String TB3_COL_4 = "manga_name";


    public DatabaseConn(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE1 +
                " (" + TB1_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TB1_COL_2 + " TEXT," +
                TB1_COL_3 + " TEXT," +
                TB1_COL_4 + " TEXT," +
                TB1_COL_5 + " TEXT," +
                TB1_COL_7 + " TEXT," +
                TB1_COL_8 + " TEXT," +
                TB1_COL_9 + " TEXT," +
                TB1_COL_10 + " TEXT," +
                TB1_COL_6 + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE2 +
                " (" + TB2_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TB2_COL_2 + " TEXT," +
                TB2_COL_3 + " TEXT," +
                TB2_COL_7 + " TEXT," +
                TB2_COL_5 + " TEXT," +
                TB2_COL_6 + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE3 +
                " (" + TB3_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TB2_COL_2 + " TEXT," +
                TB3_COL_3 + " TEXT," +
                TB3_COL_4 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE3);
        onCreate(db);
    }
    public boolean deleteFavorite(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE2, TB2_COL_3 + "='" + id +"'", null) > 0;
    }
    public boolean insertHistoryEntry(String src, String manga_id, String chap_id, String name, String cover, String chap_num, String pos, String size) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB1_COL_2, src);
        contentValues.put(TB1_COL_3, manga_id);
        contentValues.put(TB1_COL_4, chap_id);
        contentValues.put(TB1_COL_5, GeneralUtils.GetDateTime());
        contentValues.put(TB1_COL_6, name);
        contentValues.put(TB1_COL_7, cover);
        contentValues.put(TB1_COL_8, chap_num);
        contentValues.put(TB1_COL_10, size);
        contentValues.put(TB1_COL_9, pos);

        long result = db.insert(TABLE1, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


/*
    public boolean insertInitialData(ArrayList<MangaCommon> arl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        db.execSQL("delete from "+ TABLE3);

        for(int i = 0; i < arl.size(); i++){
            contentValues.put(TB3_COL_2,arl.get(i).getImg_path());
            contentValues.put(TB3_COL_3,arl.get(i).getId());
            contentValues.put(TB3_COL_4,arl.get(i).getTitle());
        }

        long result = db.insert(TABLE3, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
*/
    public boolean insertFavorite(String src, String manga_id, String name, String state, String img) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB2_COL_2, src);
        contentValues.put(TB2_COL_3, manga_id);
        contentValues.put(TB2_COL_5, state);
        contentValues.put(TB2_COL_6, name);
        contentValues.put(TB2_COL_7, img);

        long result = db.insert(TABLE2, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
/*
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE1 + " LEFT JOIN " + TABLE2 + " ON " + TABLE1 + ".user_id=" + TABLE2 + ".u_id", null);
        return res;
    }
*/
public ArrayList<HistoryEntry> getHistoryEntries() {
    ArrayList<HistoryEntry> arl = new ArrayList<>();
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor res = db.rawQuery("SELECT * FROM " + TABLE1, null);
    while(res.moveToNext()) {
        arl.add(new HistoryEntry(res.getString(res.getColumnIndex(TB1_COL_6)).toString(),
                res.getString(res.getColumnIndex(TB1_COL_3)).toString(),
                res.getString(res.getColumnIndex(TB1_COL_4)).toString(),
                res.getString(res.getColumnIndex(TB1_COL_2)).toString(),
                res.getString(res.getColumnIndex(TB1_COL_1)).toString(),
                res.getString(res.getColumnIndex(TB1_COL_5)).toString(),
                res.getString(res.getColumnIndex(TB1_COL_7)).toString(),
                res.getString(res.getColumnIndex(TB1_COL_8)).toString(),
                res.getString(res.getColumnIndex(TB1_COL_9)).toString(),
                res.getString(res.getColumnIndex(TB1_COL_10)).toString())
        );
    }
    return arl;
}

      public ArrayList<Favorite> getFavoriteEntries() {

          ArrayList<Favorite> arl = new ArrayList<>();
          SQLiteDatabase db = this.getWritableDatabase();
          Cursor res = db.rawQuery("SELECT * FROM " + TABLE2, null);

          while(res.moveToNext()) {
              arl.add(new Favorite(
                      res.getString(res.getColumnIndex(TB2_COL_2)),
                      res.getString(res.getColumnIndex(TB2_COL_3)),
                      res.getString(res.getColumnIndex(TB2_COL_5)),
                      res.getString(res.getColumnIndex(TB2_COL_6)),
                      res.getString(res.getColumnIndex(TB2_COL_7))
              ));
          }
          return arl;
      }
      /*
      public ArrayList<MangaCommon> getMainEntries() {
          ArrayList<MangaCommon> arl = new ArrayList<>();
          SQLiteDatabase db = this.getWritableDatabase();
          Cursor res = db.rawQuery("SELECT * FROM " + TABLE3, null);
          while(res.moveToNext()) {
              MangaCommon tmp = new MangaCommon();
              tmp.setImg_path(res.getString(res.getColumnIndex(TB3_COL_2)).toString());
              tmp.setId(res.getString(res.getColumnIndex(TB3_COL_3)).toString());
              tmp.setTitle(res.getString(res.getColumnIndex(TB3_COL_4)).toString());
              arl.add(tmp);
          }
          return arl;
      }
  */
    /*
    public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();root
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }*/
    public void DeleteDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
    }


    public boolean entryExists(String mid, String sid){
        ArrayList<Favorite> favorites = getFavoriteEntries();
        for(int i = 0; i < favorites.size(); i++)
            if(sid.equals(favorites.get(i).getSource()) && mid.equals(favorites.get(i).getMangaId()))
                return true;

        return false;
    }

}