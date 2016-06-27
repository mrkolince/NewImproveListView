package com.example.hp.newimprovelistview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Edit_Page extends AppCompatActivity implements View.OnClickListener {

    Button btnSave;
    Button btnGoBack;

    EditText NewName;

    ListView lvListview;

    static int Number;

    static ArrayList<String> Namelists;

    SharedPreferences preferences;
    SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__page);

        preferences = getSharedPreferences("Name_List", Context.MODE_PRIVATE);
        preference = getSharedPreferences("Login", Context.MODE_PRIVATE);

        btnGoBack= (Button) findViewById(R.id.btnGoBack);
        btnSave = (Button) findViewById(R.id.btnSave);

        NewName= (EditText) findViewById(R.id.etNewName);

        lvListview= (ListView) findViewById(R.id.lvListview);

        btnSave.setOnClickListener(this);
        btnGoBack.setOnClickListener(this);

        GettingDataintoArrayList();
        PrintingData();
    }

    private void GettingDataintoArrayList() {
        Number = preference.getInt("Number", 10);

        Namelists = new ArrayList<String>();
        for (int i = 0; i < Number; i++) {
            String Name = preferences.getString("Name " + i, null);
            Namelists.add(Name);
        }
    }

    private void StoringDataintoSharedfile() {
        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i < Namelists.size(); i++) {
            editor.putString("Name " + i, Namelists.get(i));
        }
        editor.commit();
    }

    private void PrintingData() {
        CustomAdapter adapter=new CustomAdapter(Edit_Page.this, Namelists);
        lvListview.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSave:
                String name=NewName.getText().toString();
                Namelists.add(name);
                NewName.setText("");

                SharedPreferences.Editor edito=preference.edit();
                Number=Number+1;
                edito.putInt("Number",Number);
                edito.commit();

                StoringDataintoSharedfile();
                PrintingData();

                break;

            case R.id.btnGoBack:
                Intent intent=new Intent(Edit_Page.this,MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}
