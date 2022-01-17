package com.example.taskmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class adapter extends FirebaseRecyclerAdapter<model,adapter.myviewholder>{
Context context;

    public adapter(@NonNull FirebaseRecyclerOptions<model> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder,final int position, @NonNull model model)
    {
        holder.taskname.setText(model.getName());
        holder.time.setText(model.getTime());
        holder.date.setText(model.getDate());


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.task,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {
        public TextView taskname;
        public TextView date,time,edit,delete,complete;
        ImageButton butt;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            taskname=itemView.findViewById(R.id.task_layout);
            date=itemView.findViewById(R.id.date_layout);
            time=itemView.findViewById(R.id.time_layout);
            edit=itemView.findViewById(R.id.edit_layout);
            complete=itemView.findViewById(R.id.checkBox);
            delete=itemView.findViewById(R.id.delete_layout);
        }
    }
}
