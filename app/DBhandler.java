import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
public class DBhandler extends SQLiteOpenHelper{

    public DBhandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE trial ( sno INTEGER PRIMARY KEY AUTOINCREMENT ,  purpose TEXT NOT NULL ,  amount INTEGER NOT NULL ,  date DATE NOT NULL DEFAULT CURRENT_TIMESTAMP)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from trial", null );
        return res;
    }
    public void addData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO trial (purpose,amount) VALUES (\"1st try\",800)");
    }
}
