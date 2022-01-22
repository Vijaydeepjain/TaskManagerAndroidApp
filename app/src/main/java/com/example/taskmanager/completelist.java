package com.example.taskmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;

public class completelist extends Fragment {
    RecyclerView rec_donelist;
    adapter3 adaptor;
    FirebaseRecyclerOptions optionssss;

    public completelist(FirebaseRecyclerOptions optionssss) {
        this.optionssss = optionssss;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_completelist, container, false);
        rec_donelist=view.findViewById(R.id.donerec_done);
        rec_donelist.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptor=new adapter3(optionssss,getContext());
        rec_donelist.setAdapter(adaptor);
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