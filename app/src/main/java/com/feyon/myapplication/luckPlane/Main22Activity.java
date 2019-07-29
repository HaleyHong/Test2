package com.feyon.myapplication.luckPlane;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.feyon.myapplication.R;

import java.util.ArrayList;

public class Main22Activity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<Bean> arrayList;

    private Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        gridView=findViewById(R.id.gridView);

        arrayList=new ArrayList<>();

        for (int i = 0; i <9 ; i++) {
            Bean bean=new Bean();
            bean.setUrl("");
            bean.setText(""+i);
            arrayList.add(bean);
        }

        myadapter=new Myadapter();
        gridView.setAdapter(myadapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("TAG", "onAnimationUpdate: start");
                init();
            }
        });

//        gridView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("TAG", "onAnimationUpdate: start");
//                init();
//            }
//        });

    }

    private void init(){
        ValueAnimator valueAnimator=new ValueAnimator();
        valueAnimator.setDuration(2*1000);
        valueAnimator.setStartDelay(500);
//        valueAnimator.setRepeatCount(2);
        valueAnimator.setIntValues(0,8+8+7+1);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
//        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private int cur=-1;
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

//                View view=gridView.getChildAt(n-gridVIew.getFirstVisiblePosition());
                int position= (int) valueAnimator.getAnimatedValue();
                if (position!=cur)
                {
                    cur=position;
                    myadapter.doNext(gridView,position);
                }

            }
        });

//        ValueAnimator stop=new ValueAnimator();
//        stop.setDuration(2*1000);
//        stop.setIntValues(0,7+2);
//        stop.setInterpolator(new LinearInterpolator());
//        stop.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            private int cur=-1;
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                int position= (int) valueAnimator.getAnimatedValue();
//                if (position!=cur)
//                {
//                    cur=position;
//                    myadapter.doNext(gridView,position);
//                }
//            }
//        });

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(valueAnimator);
        animatorSet.start();
    }

    public class Myadapter extends BaseAdapter{

        private int cur_index=-1;
        private int old_index=-1;

        private Myholder cur_myholder;
        int[] arr_select={0,1,2,5,8,7,6,3};
        private int arr_index=-1;

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        private void refreshListView(GridView listView, int position) {

            View view=gridView.getChildAt(position-listView.getFirstVisiblePosition());
            if (view != null) {
                getView(position, view, listView);
            }
        }

        public void doNext(GridView listView,int position)
        {
            if (arr_index+1>arr_select.length-1)
            {
                arr_index=0;
            }else {
                arr_index+=1;
            }

            old_index=cur_index;
            cur_index=arr_select[arr_index];

            Log.i("TAG", "doNext: "+cur_index);
            refreshListView(listView,old_index);
            refreshListView(listView,cur_index);
        }



        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Myholder myholder;
            if (view==null)
            {
                myholder=new Myholder();
                view= LayoutInflater.from(Main22Activity.this).inflate(R.layout.item_hello,null);
                view.setTag(myholder);
                myholder.bg=view.findViewById(R.id.hello_bg);
//                myholder.imageView=view.findViewById(R.id.hello);
                myholder.textView=view.findViewById(R.id.hello_text);
            }else {
                myholder= (Myholder) view.getTag();
            }

            Bean bean=arrayList.get(i);
            myholder.textView.setText(bean.getText());


            if (i==cur_index)
            {
                myholder.bg.setSelected(true);
            }else {
                myholder.bg.setSelected(false);
            }

            return view;
        }

        private class Myholder{
            private View bg;
            private ImageView imageView;
            private TextView textView;
        }
    }
}
