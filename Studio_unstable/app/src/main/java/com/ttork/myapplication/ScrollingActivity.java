package com.ttork.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.Toolbar;

import android.os.CountDownTimer;
import android.os.Handler;
import android.telephony.ims.ImsMmTelManager;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import androidx.appcompat.view.menu.*;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import cn.zhaiyifan.rememberedittext.RememberEditText;

import static com.ttork.myapplication.R.color.background_primary;

public class ScrollingActivity extends AppCompatActivity{

    public int file_change_checking_system_experience = 1;
    public int seconds;
    int experience;
    int real_xp;
    int real_money;
    int money;
    int real_sign;
    int level;
    int real_level;
    int mathematical_sequence_xp_to_level;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        //==================================
        String myData9 = "";
        File myExternalFile9 = new File("/data/data/com.ttork.myapplication/Sign.txt");
        try {
            FileInputStream fis9 = new FileInputStream(myExternalFile9);
            DataInputStream in9 = new DataInputStream(fis9);
            BufferedReader br9 = new BufferedReader(new InputStreamReader(in9));

            String strLine9;
            while ((strLine9 = br9.readLine()) != null) {
                myData9 = myData9 + strLine9;
                Log.d("File? ",myData9);
                real_sign = Integer.parseInt(myData9);
            }
            br9.close();
            in9.close();
            fis9.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(real_sign != 1)startActivity(new Intent(ScrollingActivity.this,EmailPasswordActivity.class));
        //==================================



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        TextView info_level = findViewById(R.id.level);
        TextView info_money = findViewById(R.id.money);
        Button room1  = findViewById(R.id.room_one_button);
        seconds = 60;
        experience = 0;
        money = 0;
        level = 0;
        //Чтение из файла money
        String myData1 = "";
        File myExternalFile1 = new File("/data/data/com.ttork.myapplication/guardian_money.txt");
        try {
            FileInputStream fis1 = new FileInputStream(myExternalFile1);
            DataInputStream in1 = new DataInputStream(fis1);
            BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));

            String strLine1;
            while ((strLine1 = br1.readLine()) != null) {
                myData1 = myData1 + strLine1;
                Log.d("File? ",myData1);
                real_money = Integer.parseInt(myData1);
            }
            br1.close();
            in1.close();
            fis1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Чтение из файла money

        //Чтение из файла xp
        String myData = "";
        File myExternalFile = new File("/data/data/com.ttork.myapplication/guardian_exp.txt");
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
                Log.d("File? ",myData);
                real_xp = Integer.parseInt(myData);
            }
            br.close();
            in.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Чтение из файла xp
        //Чтение из файла level
        String myData6 = "";
        File myExternalFile6 = new File("/data/data/com.ttork.myapplication/guardian_level.txt");
        try {
            FileInputStream fis6 = new FileInputStream(myExternalFile6);
            DataInputStream in6 = new DataInputStream(fis6);
            BufferedReader br6 = new BufferedReader(new InputStreamReader(in6));

            String strLine6;
            while ((strLine6 = br6.readLine()) != null) {
                myData6 = myData6 + strLine6;
                Log.d("File? ",myData6);
                real_level = Integer.parseInt(myData6);
            }
            br6.close();
            in6.close();
            fis6.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Чтение из файла level

        //Создание таймера
        experience = real_xp;
        money = real_money;
        level = real_level;
        mathematical_sequence_xp_to_level = 50;
        if (file_change_checking_system_experience == 1) {
            countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d("seconds: ", seconds + "");
                    seconds--;
                    info_money.setText(money + "");
                    info_level.setText(level+"");
                }

                @Override
                public void onFinish() {
                    Log.d("Status: ", "Finished!!!");
                    experience = experience + 1;
                    money = money + 1;
                    info_money.setText(money + "");
                    //Toast.makeText(getApplicationContext(), experience + "", Toast.LENGTH_SHORT).show();
                    if (experience % 50 == 0) {
                        level = level + 1;
                        info_level.setText(level+"");
                    }

                    //Сохранение money в файл
                    File file5 = new File("/data/data/com.ttork.myapplication/guardian_money.txt");
                    Log.d(file5.exists() + "", "true!");
                    try {
                        if (!file5.exists()) file5.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        PrintWriter printWriter5 = new PrintWriter(file5);
                        printWriter5.write(String.valueOf(money));
                        //printWriter5.write(String.valueOf(0));
                        printWriter5.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    //Сохранение money в файл
                    //Сохранение xp в файл
                    File file1 = new File("/data/data/com.ttork.myapplication/guardian_exp.txt");
                    Log.d(file1.exists() + "", "true!");
                    try {
                        if (!file1.exists()) file1.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        PrintWriter printWriter1 = new PrintWriter(file1);
                        printWriter1.write(String.valueOf(experience));
                        //printWriter1.write(String.valueOf(0));
                        printWriter1.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    //Сохранение xp в файл

                    //Сохранение файла--------------------------------------------------------------------------
                    File file = new File("/data/data/com.ttork.myapplication/guardian_level.txt");
                    Log.d(file.exists() + "","true!");
                    try {
                        if(!file.exists())file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        PrintWriter printWriter = new PrintWriter(file);
                        printWriter.write(String.valueOf(level));
                        //printWriter.write(String.valueOf(0));
                        printWriter.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    //Сохранение файла--------------------------------------------------------------------------
                    if (countDownTimer != null){
                        seconds = 60;
                        countDownTimer.start();
                    }

                }
            };//.start();
            if (countDownTimer!=null){
                seconds = 60;
                countDownTimer.start();
            }
        }
        //Создание таймера

        room1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScrollingActivity.this,MainActivity.class));
            }
        });
        Button creation;
        creation = findViewById(R.id.create1);
        creation.setBackgroundColor(getResources().getColor(background_primary));
        creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(real_money >= 100)startActivity(new Intent(ScrollingActivity.this,Creation_of_a_work.class));
                else Toast.makeText(getApplicationContext(),"Для создание собственного произведения требуется от 100 монет",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        int positionOfMenuItem = 0;                                                           //Красный цвет Person в menu_scrolling
        MenuItem item = menu.getItem(positionOfMenuItem);
        SpannableString s = new SpannableString("Person");
        s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
        item.setTitle(s);

        MenuItem item2 = menu.getItem(1);                                              //Красный цвет Shop в menu_scrolling
        SpannableString s1 = new SpannableString("Shop");
        s1.setSpan(new ForegroundColorSpan(Color.RED), 0, s1.length(), 0);
        item2.setTitle(s1);

        MenuItem item3 = menu.getItem(2);                                              //Красный цвет Settings в menu_scrolling
        SpannableString s2 = new SpannableString("Settings");
        s2.setSpan(new ForegroundColorSpan(Color.RED), 0, s2.length(), 0);
        item3.setTitle(s2);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_person){
            startActivity(new Intent(this,PersonActivity.class));
            return true;
        }
        if(id == R.id.shop){
            startActivity(new Intent(this,ShopActivity.class));
            return true;
        }
        if (id == R.id.action_settings) { //здесь предствален обработчик нажатия типа setOnClickListener
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

