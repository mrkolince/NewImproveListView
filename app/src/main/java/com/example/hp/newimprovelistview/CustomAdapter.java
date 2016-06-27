package com.example.hp.newimprovelistview;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by MARS on 6/11/2016.
 */
public class CustomAdapter extends BaseAdapter {

    Context context;
    static ArrayList<String> name;
    static ArrayList<String> Namelist;
    SharedPreferences preferences;
    SharedPreferences preference;


    public CustomAdapter(Context c, ArrayList<String> n){

        context=c;
        name=n;
    }

    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int pos) {
        return pos;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        if (convertView==null){
            convertView=inflater.inflate(R.layout.listview_layout,null);
        }
        TextView tvlistview= (TextView) convertView.findViewById(R.id.tvListview);
        tvlistview.setText(name.get(position));

        tvlistview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.remove(position);
                notifyDataSetChanged();

                Namelist = new ArrayList<String>();
                Namelist = name;

                preferences = context.getSharedPreferences("Name_List", Context.MODE_PRIVATE);
                Editor editor = preferences.edit();

                Toast.makeText(context, "The Size of Namelist is "+Namelist.size(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < Namelist.size(); i++) {
                    editor.putString("Name " + i, Namelist.get(i));
                }
                editor.commit();

                preference = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
                int Num=Namelist.size();
                Editor editors = preference.edit();
                editors.putInt("Number", Num);
                editors.commit();
            }
        });

        return convertView;
    }
}
