package com.example.vincentg.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int dbversion = 1;
    public String QUERY = "CREATE TABLE IF NOT EXISTS "+DBData.TableInfo.USER_TABLE_NAME +"("+DBData.TableInfo.USER_NAME +" TEXT,"+DBData.TableInfo.USER_PASSWD+" VARCHAR(32));";
    DatabaseOperations ctx = this;
    public DatabaseOperations(Context context) {
        super(context, DBData.TableInfo.DATABASE_NAME, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(QUERY);
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean InsertLogin(DatabaseOperations dop, String username, String passwd){
        SQLiteDatabase sq = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(checkIfDataAllreadyExists(dop,username)){
            cv.put(DBData.TableInfo.USER_NAME,username);
            cv.put(DBData.TableInfo.USER_PASSWD,computeMD5Hash(passwd));
            long k = sq.insert(DBData.TableInfo.USER_TABLE_NAME,null,cv);
            Log.i("DatabaseOperation", "one row inserted");
            return true;
        }
        return false;
    }
    public Cursor RetrieveLoginData(DatabaseOperations dop){
        SQLiteDatabase sq = dop.getReadableDatabase();
        ContentValues cv = new ContentValues();
        String[] columns = {DBData.TableInfo.USER_NAME, DBData.TableInfo.USER_PASSWD};
        Cursor Cr = sq.query(DBData.TableInfo.USER_TABLE_NAME,columns,null,null,null,null,null);

        return Cr;
    }
    public boolean checkIfDataAllreadyExists(DatabaseOperations dop,String username){
        SQLiteDatabase sq = dop.getReadableDatabase();
        String strSQL = "SELECT * FROM "+ DBData.TableInfo.USER_TABLE_NAME+" WHERE "+ DBData.TableInfo.USER_NAME+"='"+username+"';";
        Cursor cursor = sq.rawQuery(strSQL, null);
        String[] data = null;
        if (!cursor.moveToFirst()) {
           return true;
        }
        return false;
    }
    public void fetchAllData(DatabaseOperations dop){
        SQLiteDatabase db = dop.getReadableDatabase();
        Cursor  cursor = db.rawQuery("select * from "+ DBData.TableInfo.USER_TABLE_NAME,null);
        if (cursor .moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                String name = cursor.getString(cursor.getColumnIndex(DBData.TableInfo.USER_NAME));
                cursor.moveToNext();
                Log.i("DEBUGING; ",name);
            }
        }else{
            Log.i("ERROR RETRIEVING DATA","COULD NOT FIND DATA");
        }
    }

    public void UpdatePassword(DatabaseOperations dop, String password, String username){
        SQLiteDatabase sq = dop.getWritableDatabase();
        String strSQL = "UPDATE "+ DBData.TableInfo.USER_TABLE_NAME+" SET "+ DBData.TableInfo.USER_PASSWD+" = "+computeMD5Hash(password)+" WHERE "+ DBData.TableInfo.USER_NAME+" = '"+ username+"';";
        sq.execSQL(strSQL);
    }
    public void ClearDataBase(DatabaseOperations dop){
        String strSql = "DELETE * FROM "+ DBData.TableInfo.USER_TABLE_NAME+";";
        SQLiteDatabase sq = dop.getWritableDatabase();
        sq.execSQL(strSql);
    }
    public void DeleteAccount(DatabaseOperations dop,String password, String username){
        SQLiteDatabase sq = dop.getWritableDatabase();
        String strSQL = "DELETE FROM "+ DBData.TableInfo.USER_TABLE_NAME+" WHERE "+ DBData.TableInfo.USER_PASSWD+" = '"+computeMD5Hash(password)+"' AND "+ DBData.TableInfo.USER_NAME+" = '"+ username+"';";
        sq.execSQL(strSQL);
    }

    /*
    public Cursor getUserPass(DatabaseOperations dop, String user){
        SQLiteDatabase sq = dop.getReadableDatabase();
        String selection = DBData.TableInfo.USER_NAME + "LIKE ?";
        String columns[] = {DBData.TableInfo.USER_PASSWD};
        String args[] = {user};
        Cursor Cr = sq.query(DBData.TableInfo.USER_TABLE_NAME, columns,selection,args,null,null,null);
        return true;

    }*/
    public Cursor RetrieveHouseData(DatabaseOperations dop){
        SQLiteDatabase sq  = dop.getReadableDatabase();
        String[] columns = {DBData.TableInfo.HOUSE_STREET, DBData.TableInfo.HOUSE_SPACE, DBData.TableInfo.HOUSE_KOMMUN, DBData.TableInfo.HOUSE_salesPerson};
        Cursor cr = sq.query(DBData.TableInfo.HOUSE_TABLE,columns,null,null,null,null,null);

        return cr;
    }

    /*
    * ENCRYPTION FUNCTION
    * */
//Could SHA1 sucks so it will have to be MD5 Hash
    private String computeMD5Hash(String password)
    {
        String str;

        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }

            //result.setText("MD5 hash generated is: " + " " + MD5Hash);
            str = MD5Hash.toString();
            return str;

        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }


        return password;
    }
    //TODO
    //stäng databasen för i helvete.


}
