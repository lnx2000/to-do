package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<Listitem> listitems;
    Context context;
private OnItemClickListener mlistener;
    public MyAdapter(List<Listitem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }
public interface OnItemClickListener{
        void OnItemClick(int position);
}
public void setOnItemClickListener(OnItemClickListener listener){
        mlistener=listener;
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent , false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
Listitem listitem=listitems.get(position);
holder.head.setText(listitem.getHead());
holder.des.setText(listitem.getDes());
holder.date.setText(listitem.getDate());
//holder.prio.setText(listitem.getI());
        String s=""+listitem.getI();
        holder.prio.setText(s);

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
TextView head,des,date,prio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             head=itemView.findViewById(R.id.head);
             des=itemView.findViewById(R.id.des);
             date=itemView.findViewById(R.id.date);
             prio=itemView.findViewById(R.id.prio);
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                        if(mlistener!=null){
                            int position=getAdapterPosition();
                            if(position!=RecyclerView.NO_POSITION){
                                mlistener.OnItemClick(position);
                            }



                        }
                 }
             });
        }
    }
}
