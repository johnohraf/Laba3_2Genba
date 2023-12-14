package com.example.laba3_2genba;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
    }

    public void viewData(View view) {
        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);
    }

    public void openAddRecordActivity(View view) {
        Intent intent = new Intent(this, AddRecordActivity.class);
        startActivityForResult(intent, 1);
    }

    public void updateLastRecord(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_LAST_NAME, "Иванов");
        values.put(DatabaseHelper.COLUMN_FIRST_NAME, "Иван");
        values.put(DatabaseHelper.COLUMN_MIDDLE_NAME, "Иванович");

        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(getLastRecordId())};

        int count = db.update(DatabaseHelper.TABLE_NAME, values, selection, selectionArgs);

        if (count > 0) {
            Toast.makeText(this, "Запись обновлена!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Произошла ошибка!", Toast.LENGTH_SHORT).show();
        }
    }

    private int getLastRecordId() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(" + DatabaseHelper.COLUMN_ID + ") FROM " + DatabaseHelper.TABLE_NAME, null);

        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }

        cursor.close();

        return id;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Запись добавлена!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Произошла ошибка!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}