package com.ttork.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ListViewAutoScrollHelper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.github.library.bubbleview.BubbleTextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firestore.v1.Cursor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import cn.zhaiyifan.rememberedittext.RememberEditText;

public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<Message> adapter;
    RelativeLayout activity_main;
    Button button;
    String nickname;
    ListView myListView;
    BubbleTextView textMessage;
    ImageSwitcher imageSwitcher;
    int x = 1;
    String s1, s2,s3,s4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        myListView = findViewById(R.id.listView);
        imageSwitcher = findViewById(R.id.down);
        myListView.isFastScrollEnabled();

        //myListView.setFastScrollAlwaysVisible(true);
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
        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListView.smoothScrollToPosition(2000000000);

                EditText input = (EditText)findViewById(R.id.editText);
                Log.d("Nickname ", nickname + "");
                //myListView.smoothScrollToPosition(1000000000);
                //FirebaseDatabase.getInstance().getReference().push().setValue(new Message(input.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                if(nickname != null)FirebaseDatabase.getInstance().getReference().push().setValue(new Message(input.getText().toString(), nickname));
                else FirebaseDatabase.getInstance().getReference().push().setValue(new Message(input.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
                myListView.smoothScrollToPosition(2000000000);
            }
        });
        //myListView.smoothScrollToPosition(1000000000);
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .build(), SIGN_IN_REQUEST_CODE);
        } else {
            displayChat();
        }
    }

    private void displayChat() {

        ListView listMessages = (ListView)findViewById(R.id.listView);
        adapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.list_item, FirebaseDatabase.getInstance().getReference()) {

            @Override
            protected void populateView(View v, Message model, int position) {
                TextView autor, timeMessage;
                textMessage = v.findViewById(R.id.tvMessage);
                autor = v.findViewById(R.id.tvUser);
                //timeMessage = v.findViewById(R.id.tvTime);
                textMessage.setText(model.getTextMessage());
                autor.setText(model.getAutor());
                if(nickname == autor.getText().toString())autor.setTextColor(getResources().getColor(R.color.user));
                else autor.setTextColor(getResources().getColor(R.color.user2));
                //autor.setText(nickname);
                //timeMessage.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getTimeMessage()));
                String s7 = textMessage.getText().toString();
                if(s7.contains("/try"))//&& (!(textMessage.getText().toString().contains("[True]")) || (!(textMessage.getText().toString().contains("[False]")))))
                {
                    textMessage.setTextColor(getResources().getColor(R.color.try_user));
                    if(((int)(Math.random()*6))%2==0){
                        s1 = textMessage.getText().toString();
                       s2 = s1 + " "+ "[True]";
                        s2 = s2.replace("/try",""); //rh
                        textMessage.setText(s2);
                    }
                    else {
                        s3 = textMessage.getText().toString();
                        s4 = s3 + " " + "[False]";
                        s4 = s4.replace("/try","");
                        textMessage.setText(s4);
                    }
                }
                else textMessage.setTextColor(getResources().getColor(R.color.white));
                imageSwitcher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageSwitcher.showNext();
                        x++;
                    }
                });
                if(x%2!=0)myListView.smoothScrollToPosition(2000000000);
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