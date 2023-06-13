package nfc.nfcreader.nfcwriter.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbManager extends SQLiteOpenHelper {
    private static final String DBNAME = "Details";
    private static final String TABLE_NAME="NFC_Data";

    public DbManager(Context context) {
        super(context, DBNAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  String query="create table table_name (id integer primary key autoincrement, ApiUrl text, Data text, Method text)";
        db.execSQL("create table NFC_Data  (id integer primary key autoincrement, ApiUrl text, Data text, Method text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists NFC_Data");
        onCreate(db);

    }
    public String insertRecord(String url, String data,String method)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("ApiUrl",url);
        cv.put("Data",data);
        cv.put("Method",method);
       long res= db.insert("NFC_Data",null,cv);
       if(res==-1)
           return "Failed";
       else
           return "Successfully Inserted";

    }
}
