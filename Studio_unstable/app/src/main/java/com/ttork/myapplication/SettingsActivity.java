package com.ttork.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;


import com.squareup.picasso.Picasso;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.google.firestore.v1.Write.OperationCase.UPDATE;

public class SettingsActivity extends AppCompatActivity {
    Switch theme1;
    Bitmap bitmap,bitmap1;
    static final int GALLERY_REQUEST = 1;
    ImageView imageView;
    Intent imageReturnedIntent;
    int theme_dark_or_day;
    Context themedContext;
    SQLiteDatabase sql1;
    ProjectHelper sql2;
    String i;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        imageView = findViewById(R.id.imageView);

        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    Log.d("Uri",selectedImage.toString());
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmap);
                }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        theme1 = findViewById(R.id.theme1);
        imageView = findViewById(R.id.imageView);
        //SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        //SharedPreferences.Editor myEditor = myPreferences.edit();
        //--------------------------------------------------------------
        try {
            switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) { //Узнаем какая тема стоит у пользователя темная/светлая
                case Configuration.UI_MODE_NIGHT_YES:
                    theme_dark_or_day = 1;
                    //myEditor.putInt("Theme",theme_dark_or_day);
                    break;
                case Configuration.UI_MODE_NIGHT_NO:
                    theme_dark_or_day = 0;
                    //myEditor.putInt("Theme",theme_dark_or_day);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //--------------------------------------------------------------
        try {
            Uri selectedImage = imageReturnedIntent.getData();
            imageView.setImageURI(selectedImage);
        }catch (Exception e){
            e.printStackTrace();
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
        //----------------------------------------------
        //Реализация сохранения настроек через SQL
        ContentValues cv = new ContentValues();
        sql1 = sql2.getWritableDatabase();
        //sql1.update("settings",cv,"theme = ?", new String[] {i});
        //Cursor dbObsorver = sql1.query(DBConstants.TABLENAME,new String[]{DBConstants.COLOMN1},null,null,null,null,DBConstants.COLOMN1);
        theme1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                   themedContext = new ContextThemeWrapper(getBaseContext(), R.style.Theme_MaterialComponents_DayNight_DarkActionBar);
                    sql1.update("settings",cv,"theme = ?", new String[] {"1"});
                }
                else {
                    themedContext = new ContextThemeWrapper(getBaseContext(), R.style.Theme_AppCompat_Light_DarkActionBar);
                    sql1.update("settings",cv,"theme = ?", new String[] {"0"});
                }
            }
        });
        //----------------------------------------------


        //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        else Toast.makeText(getApplicationContext(),"Error 1: SettingsActivity",Toast.LENGTH_LONG).show();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        else Toast.makeText(getApplicationContext(),"Error 2: SettingsActivity",Toast.LENGTH_LONG).show();
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
    //|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| если что случить - удаляй внизу
    public void save(View v){
        ProjectHelper helper = new ProjectHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        //подготовили данные для добавления
        ContentValues data = new ContentValues();
        //data.put(DBConstants.COLOMN1,id.getText().toString());
        //data.put(DBConstants.COLOMN2,name.getText().toString());
        //data.put(DBConstants.COLOMN3,weight.getText().toString());
        //добавили запись в таблицу (INSERT INTO...)
        db.insert(DBConstants.TABLENAME,null,data);
        //закрыли активность
        finish();
    }

//    public void showData(View v){
//        myPets = pets.getReadableDatabase();
//        Cursor dbObsorver = myPets.query(DBConstants.TABLENAME,new String[]{DBConstants.COLOMN2,DBConstants.COLOMN3},null,null,null,null,DBConstants.COLOMN2);
//        if (dbObsorver.getCount()>0) {
//            petss.clear();
//            dbObsorver.moveToFirst();
//            do{
//                int petsId = dbObsorver.getInt(0);
//                String petsName = dbObsorver.getString(1);
//                int petsWeight = dbObsorver.getInt(2);
//                int petsPhoto = dbObsorver.getInt(3);
//                petss.add(""+petsId+(new Pet(petsName,petsWeight,petsPhoto).toString()));
//            }while (dbObsorver.moveToNext());
//            dbObsorver.close();
//        }
//        else petss.add((new Pet("Noname",0,-1).toString()));
//        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,petss);
//        dbShow.setAdapter(myAdapter);
//    }
//}
//
//class Pet {
//    String name;
//    int weight;
//    int photo;
//
//    public Pet(String name, int weight, int photo) {
//        this.name = name;
//        this.weight = weight;
//        this.photo = photo;
//    }
//
//    @Override
//    public String toString() {
//        return "Pet{" +
//                "name='" + name + '\'' +
//                ", weight=" + weight +
//                '}';
//    }
}