package cf.quanganh.nhaptichduc.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import cf.quanganh.nhaptichduc.Models.Question;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private static Cursor cursor;


    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return  instance;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public  void close(){
        if(database != null){
            this.database.close();
        }
    }

    public static List<Question> getData(int u, Context c){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(c);
        databaseAccess.open();

        int x,y;
        switch (u){
            case 1 : x = 1;  y = 30;break;
            case 2 : x = 31; y = 60;break;
            case 3 : x = 61; y = 90;break;
            case 4 : x = 91; y = 120;break;
            case 5 : x = 121; y = 150;break;
            case 6 : x = 151; y = 180;break;
            case 7 : x = 181; y = 210;break;
            case 8 : x = 211; y = 240;break;
            case 9 : x = 241; y = 270;break;
            case 10 : x = 271; y = 300;break;
            case 12 : x= 301; y = 310;break;    // Baden
            case 13 : x= 311; y = 320;break;    // Bayern
            case 14 : x= 321; y = 330;break;    // Berlin
            case 15 : x= 331; y = 340;break;    // Brandenburg
            case 16 : x= 341; y = 350;break;    // Bremen
            case 17 : x= 351; y = 360;break;    // Hamburg
            case 18 : x= 361; y = 370;break;    // Hessen
            case 19 : x= 371; y = 380;break;    // Meckenburg
            case 20 : x= 381; y = 390;break;    // Niedersachsen
            case 21 : x= 391; y = 400;break;    // NRW
            case 22 : x= 401; y = 410;break;    // Rheinland pfalz
            case 23 : x= 411; y = 420;break;    // Saarland
            case 24 : x= 421; y = 430;break;    // Sachsen
            case 25 : x= 431; y = 440;break;    // Sachsen Anhalt
            case 26 : x= 441; y = 450;break;    // Schleswig Holstein
            case 27 : x= 451; y = 460;break;    // Thüringen
            case 99 : x= 0; y= 20;break;
            default: x =1; y = 30;
        }


        List<Question> list = new ArrayList<>();

        if(u == 11){
             cursor = databaseAccess.database.rawQuery("SELECT * FROM einbuergerung WHERE id= 21 " +
                     "UNION SELECT * FROM einbuergerung WHERE id=130 " +
                     "UNION SELECT * FROM einbuergerung WHERE id=209 " +
                     "UNION SELECT * FROM einbuergerung WHERE id=226", null);


        } else {
            cursor = databaseAccess.database.rawQuery("SELECT * FROM einbuergerung e WHERE e.id>=" + x + " AND e.id<=" + y +
                    " EXCEPT SELECT * FROM einbuergerung ei WHERE ei.id=21 " +
                    "EXCEPT SELECT * FROM einbuergerung ei WHERE ei.id=130 " +
                    "EXCEPT SELECT * FROM einbuergerung ei WHERE ei.id=209 " +
                    "EXCEPT SELECT * FROM einbuergerung ei WHERE ei.id=226 ;", null);
        }
        if (cursor.moveToFirst()) {
            do {
                Question f = new Question();
                f.setId(cursor.getString(0));
                f.setQuest(cursor.getString(1));
                f.setA(cursor.getString(2));
                f.setB(cursor.getString(3));
                f.setC(cursor.getString(4));
                f.setD(cursor.getString(5));
                f.setResult(cursor.getString(6));
                list.add(f);
            } while (cursor.moveToNext());
        }
        cursor.close();


        databaseAccess.close();
        return list;
    }


}
