package com.example.hp.newimprovelistview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button GotoEditPage;

    Spinner spList;
    AutoCompleteTextView actvList;


    static int Number;
    static int LoginNumber;
    String[] Names = {"asdasdf", "rtefdfd", "eftwverg", "sdfsdfew", "ewcvwerfw", "gergevv", "rrdgbc", "ewverve", "rweverv", "ebddsfjl"};
    String Name;

    static ArrayList<String> Namelist;

    SharedPreferences preferences;
    SharedPreferences preference;
    SharedPreferences preferenc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("Name_List", Context.MODE_PRIVATE);
        preference = getSharedPreferences("Login", Context.MODE_PRIVATE);
        preferenc = getSharedPreferences("First_Time", Context.MODE_PRIVATE);

        spList = (Spinner) findViewById(R.id.spList);
        actvList= (AutoCompleteTextView) findViewById(R.id.actvList);
        GotoEditPage = (Button) findViewById(R.id.btnGotoEditPage);

        //String SelectedName = spList.getSelectedItem().toString();
        //This Value Have not Used but can be used for  future requirement.
        Toast.makeText(MainActivity.this, "This is onCreate", Toast.LENGTH_SHORT).show();

        GotoEditPage.setOnClickListener(this);

        Initialization();
        StartSharedPreference();
        SavingNameIntoString();
        ShowListView();
    }

    private void Initialization() {
        Number = preference.getInt("Number", 10);
        LoginNumber = preferenc.getInt("First_Time", 0);
        Toast.makeText(MainActivity.this, "This is Initialization Number "+Number+"Login Number "+LoginNumber, Toast.LENGTH_SHORT).show();
    }

    private void StartSharedPreference() {
        Toast.makeText(MainActivity.this, "This is StartSharedPrefrence", Toast.LENGTH_SHORT).show();

        if(LoginNumber==0){
            Toast.makeText(MainActivity.this, "This is StartSharedPrefrence for Login NUmber 0", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = preferences.edit();
            for (int i = 0; i < Names.length; i++) {
                editor.putString("Name " + i, Names[i]);
            }
            editor.commit();

            SharedPreferences.Editor editors = preferenc.edit();
            editors.putInt("First_Time",1);
            editors.commit();
        }
        else {
            Toast.makeText(MainActivity.this, "This is StartSharedPrefrence for Login NUmber 1", Toast.LENGTH_SHORT).show();
        }
    }

    private void SavingNameIntoString() {
        Toast.makeText(MainActivity.this, "This is SavingNameIntoString for NUmber "+Number, Toast.LENGTH_SHORT).show();

        Namelist = new ArrayList<String>();
        for (int i = 0; i < Number; i++) {
            Name = preferences.getString("Name " + i, null);
            Namelist.add(Name);
         }
    }

    private void ShowListView() {
        Toast.makeText(MainActivity.this, "This is void ShowListView "+Number, Toast.LENGTH_SHORT).show();

        ArrayAdapter ad = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,Namelist);
        spList.setAdapter(ad);
        actvList.setThreshold(1); /* 1 dia decide kora hoy koto digit porjonto likle suggetion dea soro korbe( default 2)*/
        actvList.setAdapter(ad);
    }



    @Override
    public void onClick(View v) {

        Intent intent=new Intent(MainActivity.this,Edit_Page.class);
        startActivity(intent);
    }
}
