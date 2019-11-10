package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class fill extends AppCompatActivity {

    EditText head,des;
    CardView c;
    String d,h;
    SeekBar seek;
List<Listitem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill);
        head=findViewById(R.id.head);
        des=findViewById(R.id.des);
        c=findViewById(R.id.sub);
        seek=findViewById(R.id.seek);
        list=new ArrayList<Listitem>();
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h=head.getText().toString();
                d=des.getText().toString();
                Intent i=new Intent(fill.this,MainActivity.class);
                //i.putExtra("head",h);
                //i.putExtra("des",d);
                //startActivity(i);
                SharedPreferences sp=getSharedPreferences("listitems",MODE_PRIVATE);
                Gson gson=new Gson();
                String json=sp.getString("items",null);
                Type type=new TypeToken<ArrayList<Listitem>>(){}.getType();
                list=gson.fromJson(json,type);
                if(list==null)
                    list=new ArrayList<>();
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                String z=sdf.format(new Date());
                int y=seek.getProgress();
                Listitem l=new Listitem(h,d,z,y);
                list.add(l);
                    fun();
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                editor.apply();
                Gson g=new Gson();
                String j=g.toJson(list);
                editor.putString("items",j);
                editor.apply();
                startActivity(i);
                finish();



            }
        });



    }
    public void fun(){

        Collections.sort(list, new Comparator<Listitem>() {
            @Override
            public int compare(Listitem listitem, Listitem t1) {
                String x=""+listitem.getI();
                String y=""+t1.getI();
                if(x.compareTo(y)>0)
                    return -1;
                else if(x.compareTo(y)<0)
                    return 1;
                else
                    return 0;
            }
        });


    }
}
