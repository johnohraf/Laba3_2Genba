package com.example.laba3_2genba;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddRecordActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText inputLastName, inputFirstName, inputMiddleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        dbHelper = new DatabaseHelper(this);
        inputLastName = findViewById(R.id.input_last_name);
        inputFirstName = findViewById(R.id.input_first_name);
        inputMiddleName = findViewById(R.id.input_middle_name);
    }

    public void saveRecord(View view) {
        String lastName = inputLastName.getText().toString().trim();
        String firstName = inputFirstName.getText().toString().trim();
        String middleName = inputMiddleName.getText().toString().trim();

        if (!lastName.isEmpty() && !firstName.isEmpty() && !middleName.isEmpty()) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_LAST_NAME, lastName);
            values.put(DatabaseHelper.COLUMN_FIRST_NAME, firstName);
            values.put(DatabaseHelper.COLUMN_MIDDLE_NAME, middleName);

            long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);

            if (newRowId != -1) {
                setResult(RESULT_OK);
                finish();
            } else {
                setResult(RESULT_CANCELED);
                finish();
            }

            db.close();
        }
    }
}