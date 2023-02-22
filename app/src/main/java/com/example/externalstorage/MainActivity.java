package com.example.externalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private TextView promptTextView;
    private EditText inputEditText;
    private TextView vowelCountTextView;
    private TextView consonantCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        promptTextView = findViewById(R.id.prompt_textview);
        inputEditText = findViewById(R.id.input_edittext);
        vowelCountTextView = findViewById(R.id.vowel_count_textview);
        consonantCountTextView = findViewById(R.id.consonant_count_textview);

        Button countButton = findViewById(R.id.count_button);
        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputEditText.getText().toString().toLowerCase();
                int vowelCount = 0;
                int consonantCount = 0;

                for (int i = 0; i < input.length(); i++) {
                    char c = input.charAt(i);
                    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                        vowelCount++;
                    } else if (c >= 'a' && c <= 'z') {
                        consonantCount++;
                    }
                }

                vowelCountTextView.setText("Vowels: " + vowelCount);
                consonantCountTextView.setText("Consonants: " + consonantCount);
            }
        });

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputEditText.getText().toString();
                saveToSDCard(input);
                Toast.makeText(MainActivity.this, "Input saved to SD card", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToSDCard(String input) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "input.txt");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(input);
            osw.flush();
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

