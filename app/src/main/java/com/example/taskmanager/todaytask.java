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


public class todaytask extends Fragment {
    RecyclerView rec_todaylist;
    adapter adaptor;
FirebaseRecyclerOptions options;

    public todaytask(FirebaseRecyclerOptions options) {
        this.options = options;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_todaytask, container, false);
        rec_todaylist=view.findViewById(R.id.rec_todaylist);
        rec_todaylist.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptor=new adapter(options,getContext());
        rec_todaylist.setAdapter(adaptor);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adaptor.startListening();
        //adapter2.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adaptor.stopListening();
        //adapter2.stopListening();
    }
}