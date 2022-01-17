package com.example.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class adapter2 extends FirebaseRecyclerAdapter<model2,adapter2.myviewholder2> {
    Context context;

    public adapter2(@NonNull FirebaseRecyclerOptions<model2> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder2 holder, int position, @NonNull model2 model) {

        holder.taskname.setText(model.getName());
        holder.time.setText(model.getTime());
        holder.date.setText(model.getDate());
    }

    @NonNull
    @Override
    public myviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task,parent,false);
        return new myviewholder2(view);
    }

    class myviewholder2 extends RecyclerView.ViewHolder {
        public TextView taskname;
        public TextView date,time,edit,delete,complete;
        ImageButton butt;
        public myviewholder2(@NonNull View itemView) {
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
