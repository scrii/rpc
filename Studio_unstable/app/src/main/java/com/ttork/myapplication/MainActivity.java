package com.ttork.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.github.library.bubbleview.BubbleTextView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<Message> adapter;
    RelativeLayout activity_main;
    Button button;
    String nickname;
    ListView myListView;
    BubbleTextView textMessage;
    boolean xy = true;
    String s, shadow_nickname_user,coordinats_x = "1.000",coordinats_y = "1.000";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();
        myListView = findViewById(R.id.listView);
        myListView.isFastScrollEnabled();
        //=====================================================================
        String myData36 = "";
        File myExternalFile36 = new File("/data/data/com.ttork.myapplication/nickname.txt");
        try {
            FileInputStream fis36 = new FileInputStream(myExternalFile36);
            DataInputStream in36 = new DataInputStream(fis36);
            BufferedReader br36 = new BufferedReader(new InputStreamReader(in36));

            String strLine36;
            while ((strLine36 = br36.readLine()) != null) {
                myData36 = myData36 + strLine36;
                Log.d("File? ",myData36);
                nickname = myData36;
            }
            br36.close();
            in36.close();
            fis36.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //=====================================================================


        activity_main = findViewById(R.id.activity_main);
        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.editText);
                if(nickname != null)FirebaseDatabase.getInstance().getReference().push().setValue(new Message(input.getText().toString(), nickname));
                else FirebaseDatabase.getInstance().getReference().push().setValue(new Message(input.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
                xy = true;
            }
        });
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .build(), SIGN_IN_REQUEST_CODE);
        } else {
            displayChat();
        }
        //Теневая отправка сообщений с координатами x и y на сервер
        shadow_nickname_user = nickname + "-RxvtHg7uyprotL1";
        Log.d("Shadow",shadow_nickname_user);


        //Теневая отправка сообщений с координатами x и y на сервер
    }


    private void displayChat() {

        ListView listMessages = findViewById(R.id.listView);
        adapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.list_item, FirebaseDatabase.getInstance().getReference()) {

            @Override
            protected void populateView(View v, Message model, int position) {
                TextView autor, timeMessage;
                textMessage = v.findViewById(R.id.tvMessage);
                autor = v.findViewById(R.id.tvUser);

                if(nickname != autor.getText().toString()) {//Получаем все ники кроме своих

                }
//                String s7 = textMessage.getText().toString();
//                if(s7.contains("/try"))//&& (!(textMessage.getText().toString().contains("[True]")) || (!(textMessage.getText().toString().contains("[False]")))))
//                {
//                    textMessage.setTextColor(getResources().getColor(R.color.try_user));
//                    if(((int)(Math.random()*6))%2==0){
//                        s1 = model.getTextMessage();
//                        s1 = s1 + " [Успешно]";
////                        textMessage.setText(model.setTextMessage(s1));
//                        textMessage.setText(model.setTextMessage(s1));
//
//                    }
//                    else {
//                        //textMessage.setText(model.getTextMessage() + " [Не успешно]");
//                        s2 = model.getTextMessage();
//                        s2 = s2 + " [Успешно]";
//                        textMessage.setText(model.setTextMessage(s2));
//                    }
//                }
//                else {
//                    textMessage.setTextColor(getResources().getColor(R.color.white));
//                    y=2;
//                }
                //timeMessage = v.findViewById(R.id.tvTime);
                //shadow.setText(model.setAutor(shadow_nickname_user));
                //coordinats.setText(model.setTxt(coordinats_x + " " + coordinats_y));

                textMessage.setText(model.getTextMessage());
                autor.setText(model.getAutor());
                if(nickname == autor.getText().toString()){
                    autor.setTextColor(getResources().getColor(R.color.user));
                    myListView.smoothScrollToPosition(2000000000);
                }
                else autor.setTextColor(getResources().getColor(R.color.user2));
                //if(s.contains("*") && s.contains("*"))textMessage.setTextColor(getResources().getColor(R.color.comment));
                int kolvo_symbols = 0;
                s = textMessage.getText().toString();
                //==========================================================================================================



                if(s.contains("*") && textMessage.getText().toString().contains("*")) {
                    for (int i = 0; i < s.length(); i++) {
                        if (s.charAt(i) == '*' && s.contains("*")) {
                            kolvo_symbols++;
                            if (kolvo_symbols == 2 && s.contains("*")) {
                                textMessage.setTextColor(getResources().getColor(R.color.comment));
                                kolvo_symbols = 0;
                                //s.replaceAll(s,"*");
                                s = "";
                            }
                            else textMessage.setTextColor(getResources().getColor(R.color.white));
                        }
                    }
                }
                else textMessage.setTextColor(getResources().getColor(R.color.white));
                //timeMessage.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getTimeMessage()));
                if(xy){
                    myListView.smoothScrollToPosition(2000000000);
                    xy = false;
                }
            }
        };
        listMessages.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                Snackbar.make(activity_main, "Вход выполнен", Snackbar.LENGTH_SHORT).show();
                displayChat();
            } else {
                Snackbar.make(activity_main, "Вход не выполнен", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }


}