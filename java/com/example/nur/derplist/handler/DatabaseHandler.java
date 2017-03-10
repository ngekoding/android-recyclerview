package com.example.nur.derplist.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nur.derplist.model.Inspiration;
import com.example.nur.derplist.model.InspirationData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nur on 2/7/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "inspirationManager";

    protected static final String TABLE_INSPIRATION = "inspirations";
    protected static final String KEY_ID = "id";
    protected static final String KEY_NAME = "name";
    protected static final String KEY_CONTENT = "content";
    protected static final String KEY_BOOKMARK = "bookmark";

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INSPIRATION_TABLE = "CREATE TABLE " + TABLE_INSPIRATION + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " VARCHAR(100),"
                + KEY_CONTENT + " TEXT,"
                + KEY_BOOKMARK + " INTEGER DEFAULT 0" + ")";
        db.execSQL(CREATE_INSPIRATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop old table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSPIRATION);
        onCreate(db);
    }

    /*
    * -----------------------------------------
    * CREATE, READ, UPDATE & DELETE
    * -----------------------------------------
    * */

    public void addInspiration(Inspiration inspiration) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, inspiration.getName());
        values.put(KEY_CONTENT, inspiration.getContent());

        // Inserting row
        db.insert(TABLE_INSPIRATION, null, values);
        db.close();
    }

    public Inspiration getInspiration(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INSPIRATION, new String[]{KEY_ID, KEY_NAME, KEY_CONTENT, KEY_BOOKMARK}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
        String content = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
        boolean bookmark = cursor.getString(cursor.getColumnIndex(KEY_BOOKMARK)).equals("1") ? true : false;

        return new Inspiration(name, content, bookmark);
    }

    public List<Inspiration> getAllInspirations() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Inspiration> inspirationList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_INSPIRATION;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Inspiration inspiration = new Inspiration();
                inspiration.setId(Integer.parseInt(cursor.getString(0)));
                inspiration.setName(cursor.getString(1));
                inspiration.setContent(cursor.getString(2));
                boolean bookmark = cursor.getString(3).equals("1") ? true : false;
                inspiration.setBookmark(bookmark);
                // Add data
                inspirationList.add(inspiration);
            } while (cursor.moveToNext());
        }
        return inspirationList;
    }

    public int updateInspiration(Inspiration inspiration) {
        SQLiteDatabase db = this.getReadableDatabase();

        int bookmark = inspiration.isBookmark() ? 1 : 0;

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, inspiration.getName());
        values.put(KEY_CONTENT, inspiration.getContent());
        values.put(KEY_BOOKMARK, bookmark);

        return db.update(TABLE_INSPIRATION, values, KEY_ID + "=?", new String[] {String.valueOf(inspiration.getId())});
    }

    public void deleteInspiration(Inspiration inspiration) {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_INSPIRATION, KEY_ID + "=?", new String[] {String.valueOf(inspiration.getId())});
        db.close();
    }

    public void deleteInspiration(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_INSPIRATION, KEY_ID + "=?", new String[] {String.valueOf(id)});
        db.close();
    }
}
