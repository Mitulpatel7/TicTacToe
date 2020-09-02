package com.mitulpatel.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.roger.catloadinglibrary.CatLoadingView;

import java.util.logging.ErrorManager;

public class Input_Activity extends AppCompatActivity {

    EditText player1 , player2;
    Button start;


    CatLoadingView loadingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        start = findViewById(R.id.start_game);

        loadingView = new CatLoadingView();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(player1.getText().toString().equals("") && player2.getText().toString().equals("")){

                    Toast.makeText(Input_Activity.this, "Plaese Enter PLayer's Name", Toast.LENGTH_SHORT).show();

                }
                else if(player1.getText().toString().equals("")){
                    Toast.makeText(Input_Activity.this, "Plaese Enter PLayer 1's Name", Toast.LENGTH_SHORT).show();
                }
                else if(player2.getText().toString().equals("")){
                    Toast.makeText(Input_Activity.this, "Plaese Enter PLayer 2's Name", Toast.LENGTH_SHORT).show();
                }
                else {

                    loadingView.show(getSupportFragmentManager(),"");

                    Intent intent = new Intent(Input_Activity.this, MainActivity.class);
                    intent.putExtra("player1", player1.getText().toString());
                    intent.putExtra("player2", player2.getText().toString());

                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}