package com.example.sqliteappmodel.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.sqliteappmodel.R;
import com.example.sqliteappmodel.data.DatabaseAdapter;
import com.example.sqliteappmodel.data.User;
import com.example.sqliteappmodel.data.UserAdapter;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    DatabaseAdapter databaseAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long recordId) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.editRecord: {
                                editRecord(recordId);
                                return true;
                            }
                            case R.id.delRecord: {
                                delRecord(recordId);
                                Toast.makeText(MainActivity.this, "Entry del", Toast.LENGTH_SHORT);
                                onResume();
                                return true;
                            }
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });
        databaseAdapter = new DatabaseAdapter(MainActivity.this);
    }

    private void delRecord(long recordId) {
        databaseAdapter.delUser(recordId);
    }

    private void editRecord(long recordId) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(MainActivity.this);

        databaseAdapter.open();

        ArrayAdapter adapter = new UserAdapter(this, R.layout.list_item, databaseAdapter.getUsers());

        listView.setAdapter(adapter);

        databaseAdapter.close();
    }

    public void addRecord(View view) {

        Dialog addDialog = new Dialog(this, R.style.Theme_SQLiteAppModel);

        addDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        addDialog.setContentView(R.layout.dialog_add_data);


        EditText name;
        EditText number;
        Button buttonSave;

        buttonSave = addDialog.findViewById(R.id.buttonSave);
        name = addDialog.findViewById(R.id.addPersonName);
        number = addDialog.findViewById(R.id.addPersonNumber);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.name = name.getText().toString();
                user.number = number.getText().toString();
                databaseAdapter.open();
                databaseAdapter.addUser(user);
                Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
                addDialog.hide();
                onResume();
                databaseAdapter.close();
            }
        });
        addDialog.show();
    }
}