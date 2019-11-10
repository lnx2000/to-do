package com.example.recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
MyAdapter adapter;

List<Listitem> listitems;
FloatingActionButton flo,flo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapter(listitems,this);
        recyclerView.setAdapter(adapter);
        flo=findViewById(R.id.flo);
        flo1=findViewById(R.id.flo1);
        loaddata();
        flo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,fill.class);
                overridePendingTransition(0,0);
                startActivity(i);
                finish();
            }
        });
flo1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        SharedPreferences sp=getSharedPreferences("listitems",MODE_PRIVATE);
        SharedPreferences.Editor e=sp.edit();
        e.clear();
        e.apply();
        loaddata();
    }
});
adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
    @Override
    public void OnItemClick(final int position) {
        if(listitems.size()!=0){


            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            listitems.remove(position);
                            change();
                            loaddata();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("You want to delete?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();







        }
    }

});

    }
    public void loaddata(){
        SharedPreferences sp=getSharedPreferences("listitems",MODE_PRIVATE);

        //ed.clear();
        //ed.apply();
        Gson gson=new Gson();
        String json=sp.getString("items","");
        Type type=new TypeToken<ArrayList<Listitem>>(){}.getType();
        listitems=gson.fromJson(json,type);
        if(listitems==null){
            listitems=new ArrayList<>();
        }
        adapter=null;
        adapter=new MyAdapter(listitems,this);
        recyclerView.setAdapter(adapter);



    }
void change(){
    SharedPreferences sp=getSharedPreferences("listitems",MODE_PRIVATE);
        SharedPreferences.Editor e=sp.edit();
    e.clear();
    e.apply();
    Gson g=new Gson();
    String j=g.toJson(listitems);
    e.putString("items",j);
    e.apply();
}
}
