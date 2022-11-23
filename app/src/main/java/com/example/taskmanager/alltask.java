package com.example.taskmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class alltask extends Fragment {
RecyclerView rec_alltask;
adapter2 adapter2;
FirebaseRecyclerOptions optionss;

    public alltask(FirebaseRecyclerOptions optionss) {
        this.optionss = optionss;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_alltask, container, false);

        rec_alltask=view.findViewById(R.id.rec_alllist);
        rec_alltask.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter2=new adapter2(optionss,getContext());
        rec_alltask.setAdapter(adapter2);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter2.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter2.stopListening();
    }
}