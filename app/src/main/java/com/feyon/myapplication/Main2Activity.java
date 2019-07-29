package com.feyon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private ArrayList<model> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final String ss=getIntent().getStringExtra("sss");

        findViewById(R.id.my_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sss();
            }
        });

        RecyclerView recyclerView=findViewById(R.id.myRecyclerView);

        recyclerView.addItemDecoration(new itemDecoration(this) {
            @Override
            public boolean isHeadView(int position) {
                return arrayList.get(position).isFirst;
            }

            @Override
            public String setTextTile(int position) {
                return arrayList.get(position).name;
            }

            @Override
            public boolean isLastOne(int position) {
                int i=position+1;
                if (i%5==0)
                    return true;

                else {
                    return false;
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList=new ArrayList<model>();
        for (int i = 0; i < 50; i++) {
            model model=new model();
            if (i%5==0)
                model.isFirst=true;

            model.name=i+"";

            arrayList.add(model);

        }

        recyclerView.setAdapter(new myAdatpter());

        GravitySnapHelper gravitySnapHelper=new GravitySnapHelper(Gravity.TOP);
        gravitySnapHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        moveTaskToBack(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private class myAdatpter extends RecyclerView.Adapter<myAdatpter.myholder>{

        public myAdatpter() {
        }

        @Override
        public myholder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new myholder(getLayoutInflater().inflate(R.layout.item_text,parent,false));
        }

        @Override
        public void onBindViewHolder(myholder holder, int position) {
            holder.textView.setText(arrayList.get(position).name);

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Main2Activity.this,Main2Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    Main2Activity.this.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class myholder extends RecyclerView.ViewHolder{

            private TextView textView;

            public myholder(View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.test);
            }
        }
    }

    private class model{
        private String name;
        private boolean isFirst;
    }

    public void sss(){
        for (int i = 0; i < 50; i++) {
            model model=new model();
            if (i%5==0)
                model.isFirst=true;

            model.name=i+"";

            arrayList.add(model);

        }
    }
}
