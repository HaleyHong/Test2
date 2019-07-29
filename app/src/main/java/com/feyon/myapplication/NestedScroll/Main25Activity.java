package com.feyon.myapplication.NestedScroll;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feyon.myapplication.DensityUtil;
import com.feyon.myapplication.R;

public class Main25Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ParentNestedScrollView parentNestedScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main25);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BasicAdapter());

        parentNestedScrollView=findViewById(R.id.parentScrollView);
//        parentNestedScrollView.setScrollUpHeight(200);
//        parentNestedScrollView.setRecyclerView(recyclerView);
    }

    public class BasicAdapter extends RecyclerView.Adapter<BasicAdapter.MyHolder>{

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(Main25Activity.this).inflate(R.layout.item_test,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.textView.setText(position+"");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parentNestedScrollView.setScrollUpHeight(DensityUtil.dp2px(Main25Activity.this,200));
                }
            });
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        public class MyHolder extends RecyclerView.ViewHolder{
            private TextView textView;
            public MyHolder(View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.test);
            }
        }
    }
}
