package yijiayou.wp.net.ejyh5navdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feyon.myapplication.R;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class Main43Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String TAG="Main43Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main43);

        recyclerView=findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new MarqueeManager(Main43Activity.this));

        List<String> strings=new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            strings.add("测试"+i);
        }
        BaseAdapter baseAdapter=new BaseAdapter(this,strings,3);
        recyclerView.setAdapter(baseAdapter);

        final RvScrollerTimer rvScrollerTimer=new RvScrollerTimer(recyclerView);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvScrollerTimer.start();
            }
        });

        findViewById(R.id.pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvScrollerTimer.pause();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i(TAG, "onScrollStateChanged: "+newState);
                if (newState==RecyclerView.SCROLL_STATE_DRAGGING)
                {
                    if (rvScrollerTimer!=null)
                    {
                        rvScrollerTimer.pause();
                    }
                }else if (newState==RecyclerView.SCROLL_STATE_IDLE)
                {
                    if (rvScrollerTimer!=null)
                    {
                        rvScrollerTimer.reStart();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        setMaxFlingVelocity(recyclerView,recyclerView.getMaxFlingVelocity()/100);
    }

    //设定RecyclerView最大滑动速度
    private void setMaxFlingVelocity(RecyclerView recycleview, int velocity) {
        try {
            Field field = recycleview.getClass().getDeclaredField("mMaxFlingVelocity");
            field.setAccessible(true);
//            int mMaxFlingVelocity= (int) field.get("mMaxFlingVelocity");
            field.set(recycleview, velocity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyHolder>{
        private Context mContext;
        private List<String> arrayList=new ArrayList<>();

        public BaseAdapter(Context mContext, List<String> arrs,int minNum) {
            this.mContext = mContext;
            this.arrayList = arrs;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_test,null));
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            int index=position%arrayList.size();
            if (arrayList.size()<index+1)
            {
                return;
            }
            String s=arrayList.get(index);
            holder.textView.setText(s);
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        public static class MyHolder extends RecyclerView.ViewHolder{
            private TextView textView;
            public MyHolder(View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.test);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    public class RvScrollerTimer{
        private RecyclerView recyclerView;
        private Disposable disposable;
        private int scrollPosition=0;
        private int step=3;
        private int delay=5,interval=5;

        public RvScrollerTimer(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        public void start(){
            //只支持linearLayoutManager
            if (recyclerView.getLayoutManager()==null||!(recyclerView.getLayoutManager() instanceof LinearLayoutManager))
            {
                return;
            }

            if (disposable!=null)
                disposable.dispose();

            disposable= Observable.interval(delay,interval,TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Exception {
                    int scrollState=recyclerView.getScrollState();
                    if (scrollState==SCROLL_STATE_IDLE)
                    {
                        if (recyclerView.getLayoutManager()!=null&&recyclerView.getLayoutManager() instanceof LinearLayoutManager)
                        {
                            scrollPosition=((LinearLayoutManager)(recyclerView.getLayoutManager())).findFirstVisibleItemPosition();
                            scrollPosition=scrollPosition+step;
                            recyclerView.smoothScrollToPosition(scrollPosition);
                        }
                    }
                }
            });
        }

        public void reStart(){
            clear();
            start();
        }

        public void pause(){
            if (disposable!=null)
                disposable.dispose();
        }

        public void clear(){
            scrollPosition=0;
            if (disposable!=null)
            {
                disposable.dispose();
                disposable=null;
            }
        }

    }

    public class MarqueeManager extends LinearLayoutManager{
        private TopSnapHelper linearSnapHelper;
        private RecyclerView recyclerView;
        private LinearSmoothScroller linearSmoothScroller;
        private float speedRatio =0.7f;

        public MarqueeManager(Context context) {
            super(context);
        }

        public MarqueeManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        @Override
        public void scrollToPosition(int position) {
            super.scrollToPosition(position);
        }

        @Override
        public void onAttachedToWindow(RecyclerView view) {
            super.onAttachedToWindow(view);
            recyclerView=view;
            new GravitySnapHelper(Gravity.TOP).attachToRecyclerView(view);
            linearSnapHelper=new TopSnapHelper();
//            linearSnapHelper.attachToRecyclerView(view);
        }

//        @Override
//        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
////            super.smoothScrollToPosition(recyclerView, state, position);
//            if (linearSmoothScroller==null)
//            {
//                linearSmoothScroller=new LinearSmoothScroller(recyclerView.getContext()){
//                    @Override
//                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
//                        // 返回：滑过1px时经历的时间(ms)。
//                        float f = 500f / displayMetrics.densityDpi;
//                        return f;
//                    }
//
//                    @Override
//                    public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
//                        return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);//返回的就是我们item置顶需要的偏移量
//                    }
//                };
//            }
//
//            linearSmoothScroller.setTargetPosition(position);
//            startSmoothScroll(linearSmoothScroller);
//        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
//            super.smoothScrollToPosition(recyclerView, state, position);
            if (linearSmoothScroller==null)
            {
                linearSmoothScroller=new LinearSmoothScroller(recyclerView.getContext()){
                    @Override
                    protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        float f = 500f / displayMetrics.densityDpi;
                        return f;
                    }
                };
            }

            linearSmoothScroller.setTargetPosition(position);
            startSmoothScroll(linearSmoothScroller);
        }

//        @Override
//        public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
////            return super.scrollVerticallyBy(dy, recycler, state);
//            Log.i(TAG, "scrollVerticallyBy: "+recyclerView.getScrollState());
//            int a = super.scrollVerticallyBy((int)(speedRatio*dy), recycler, state);//屏蔽之后无滑动效果，证明滑动的效果就是由这个函数实现
//            if(a == (int)(speedRatio*dy)){
//                a= dy;
//            }
//
//            if (recyclerView.getScrollState()==RecyclerView.SCROLL_STATE_SETTLING||recyclerView.getScrollState()==SCROLL_STATE_IDLE)
//            {
//                return super.scrollVerticallyBy(dy, recycler, state);
//            }else {
//                return a;
//            }
//
//
//
//        }
    }
}
