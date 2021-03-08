package com.ttork.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class PersonActivity extends AppCompatActivity {
    EditText name_person;
    Button confirm;
    Button delete_me;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //what
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        name_person = findViewById(R.id.name_person);
        confirm = findViewById(R.id.confirm);
        String s = name_person.getText().toString();                                //Получение вводимого имени в String (true)
        //===========================================================
        File file36 = new File("/data/data/com.ttork.myapplication/nickname.txt");
        Log.d(file36.exists() + "", "true!");
        try {
            if (!file36.exists()) file36.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter printWriter36 = new PrintWriter(file36);
            printWriter36.write(s);
            //printWriter5.write(String.valueOf(0));
            printWriter36.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = name_person.getText().toString();
                try {
                    PrintWriter printWriter36 = new PrintWriter(file36);
                    printWriter36.write(s);
                    //printWriter5.write(String.valueOf(0));
                    printWriter36.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            PrintWriter printWriter36 = new PrintWriter(file36);
            printWriter36.write(s);
            //printWriter5.write(String.valueOf(0));
            printWriter36.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //===========================================================
        //Запрещено трогать код ниже!!!!!!
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.person, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            //setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Не трогать код выше
}