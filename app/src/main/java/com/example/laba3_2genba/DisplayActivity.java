package com.example.laba3_2genba;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        dbHelper = new DatabaseHelper(this);
        displayText = findViewById(R.id.display_text);

        displayData();
    }

    private void displayData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);

        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            String lastName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME));
            String firstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME));
            String middleName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MIDDLE_NAME));
            String timestamp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TIMESTAMP));

            stringBuilder.append("ID: ").append(id)
                    .append(", Фамилия: ").append(lastName)
                    .append(", Имя: ").append(firstName)
                    .append(", Отчество: ").append(middleName)
                    .append(", Время добавления: ").append(timestamp)
                    .append("\n");
        }

        cursor.close();
        db.close();

        displayText.setText(stringBuilder.toString());
    }

}