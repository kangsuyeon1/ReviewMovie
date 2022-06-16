package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.database.sqlite.SQLiteCursorCompat;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int version = 1;
    DatabaseOpenHelper helper;
    SQLiteDatabase database;
    Button btnlogin, btnjoin;
    EditText etID, etPW;
    String sql;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin = findViewById(R.id.btnlogin);
        btnjoin = findViewById(R.id.btnjoin);
        etID = findViewById(R.id.etID);
        etPW = findViewById(R.id.etPW);

        helper = new DatabaseOpenHelper(MainActivity.this, DatabaseOpenHelper.tableName,null, version);
        database = helper.getWritableDatabase();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etID.getText().toString();
                String pw = etPW.getText().toString();

                if(id.length() == 0 || pw.length() == 0){
                    Toast toast = Toast.makeText(MainActivity.this,
                            "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT id FROM " + helper.tableName + "WHERE id = '" + id +"'";
                cursor = database.rawQuery(sql, null);

                if (cursor.getCount() != 1){
                    Toast toast = Toast.makeText(MainActivity.this,
                            "존재하지 않는 아이디입니다", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT pw FROM " + helper.tableName + "WHERE id = '" + pw +"'";
                cursor = database.rawQuery(sql, null);

                cursor.moveToNext();

                if (!pw.equals((cursor.getString(0)))){
                    Toast toast = Toast.makeText(MainActivity.this,
                            "존재하지 않는 비밀번호입니다", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent intent = new Intent(getApplicationContext(),ChoiceMovie.class);
                    startActivity(intent);

                    finish();
                }
                cursor.close();
            }
        });

        btnjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),JoinAccept.class);
                startActivity(intent);
            }
        });
    }


    public class DatabaseOpenHelper extends SQLiteOpenHelper{

        public static  final  String tableName = "Users";

        public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                                  int version){
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            Log.i("tag","db 생성_db가 없을때만 최초로 실행함");
            createTable(sqLiteDatabase);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }

        public void createTable(SQLiteDatabase sqLiteDatabase){
            String sql = "CREATE TABLE" + tableName + "(id text, pw text)";

            try {
                sqLiteDatabase.execSQL(sql);
            }catch (SQLException e){
            }
        }

        public void insertUser(SQLiteDatabase db, String id, String pw){
            Log.i("tag","회원가입을 했을 때 실행함");
            db.beginTransaction();
            try{
                String sql = "INSERT INTO" + tableName + "(id,pw)" + "values('"+id +"', '"+ pw + "')";
                db.execSQL(sql);
                db.setTransactionSuccessful();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                db.endTransaction();
            }
        }
    }
}

