package com.example.temaayarlar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton radioAcik, radioKaranlik;
        radioAcik = findViewById(R.id.radioAcik);
        radioKaranlik = findViewById(R.id.radioKaranlik);

        sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (!sharedPreferences.contains("tema")) {
            editor.putInt("tema", AppCompatDelegate.MODE_NIGHT_NO);
            editor.apply();
        }
        if (sharedPreferences.contains("tema")) {
            int tema = sharedPreferences.getInt("tema", AppCompatDelegate.MODE_NIGHT_NO);
            if (tema == AppCompatDelegate.MODE_NIGHT_NO) {
                radioAcik.setChecked(true);
            }
            else if (tema != AppCompatDelegate.MODE_NIGHT_NO) {
                radioKaranlik.setChecked(true);
            }
        }
    }

    public void onRadioClicked(View view){
        boolean checked=((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.radioAcik:
                if(checked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putInt("tema",AppCompatDelegate.MODE_NIGHT_NO);
                    editor.apply();
                }
                break;
            case R.id.radioKaranlik:
                if(checked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putInt("tema",AppCompatDelegate.MODE_NIGHT_YES);
                    editor.apply();
                }
                break;
        }
    }

    protected void onDestroy() {
        sharedPreferences=null;
        super.onDestroy();
    }
}