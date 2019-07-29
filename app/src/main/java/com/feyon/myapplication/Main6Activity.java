package com.feyon.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Main6Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        recyclerView=findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Myadapter());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    private class Myadapter extends RecyclerView.Adapter<Myadapter.myholder>{

        @Override
        public myholder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new myholder(LayoutInflater.from(Main6Activity.this).inflate(R.layout.item_text,parent,false));
        }

        @Override
        public void onBindViewHolder(myholder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 30;
        }

        public class myholder extends RecyclerView.ViewHolder{

            public myholder(View itemView) {
                super(itemView);
            }
        }
    }

}
