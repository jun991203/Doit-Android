package com.june.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText dbNameEdittext, tableNameEdittext;
    TextView textView;

    SQLiteDatabase database;

    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbNameEdittext = findViewById(R.id.edittext_db);
        tableNameEdittext = findViewById(R.id.edittext_table);
        textView = findViewById(R.id.textview_result);

        Button dbButton = findViewById(R.id.btn_create_db);
        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dbName = dbNameEdittext.getText().toString();
                createDatabase(dbName);
            }
        });

        Button tableButton = findViewById(R.id.btn_create_table);
        tableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableName = tableNameEdittext.getText().toString();
                createTable(tableName);

                insertRecord();
            }
        });
    }

    private void createDatabase(String name) {
        printText("createDatabase 호출");

        database = openOrCreateDatabase(name, MODE_PRIVATE, null);
        printText(name + " 데이터베이스 생성");
        Log.d("SQLite", name);
    }

    private void createTable(String name) {
        printText("createTable 호출");

        if (database == null) {
            printText("데이터베이스를 먼저 생성하세요");
            return;
        }

        database.execSQL("create table if not exists " + name + "("
                        + "_id integer PRIMARY KEY autoincrement,"
                        + " name text, "
                        + " age integer, "
                        + " mobile text);");

        printText("테이블 생성 : " + name);
    }

    private void insertRecord() {
        Log.d("SQLite", "insertRecord 호출");
        if (database == null) {
           printText("데이터베이스를 먼저 생성하세요");
            return;
        }

        if (tableName == null) {
            printText("테이블을 먼저 생성하세요");
            return;
        }

        database.execSQL("insert into " + tableName
                        + "(name, age, mobile)"
                        + " values "
                        + "('June', 20, '010-1234-1234' )");

        printText("레코드 추가함");
    }

    public void printText(String data) {
        textView.append(data + '\n');
    }
}