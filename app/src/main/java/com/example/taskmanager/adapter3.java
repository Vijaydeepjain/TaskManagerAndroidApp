package com.example.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class adapter3 extends FirebaseRecyclerAdapter<model3,adapter3.myviewholderdone> {
Context context;

    public adapter3(@NonNull FirebaseRecyclerOptions<model3> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholderdone holder, int position, @NonNull model3 model) {
        holder.tasknamee.setText(model.getName());
        holder.deletee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth=FirebaseAuth.getInstance();
                FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid())
                        .child("complete")
                        .child(getRef(position).getKey()).removeValue();
            }
        });
    }

    @NonNull
    @Override
    public myviewholderdone onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.taskdone,parent,false);
        return new myviewholderdone(view);
    }

    class myviewholderdone extends RecyclerView.ViewHolder{
        public TextView tasknamee;
        public ImageView deletee;
        public myviewholderdone(@NonNull View itemView) {
            super(itemView);
            tasknamee=itemView.findViewById(R.id.tasknamedone);
            deletee=itemView.findViewById(R.id.deletedone);
        }
    }
}
