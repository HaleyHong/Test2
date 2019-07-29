package com.feyon.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//        RecyclerView recyclerView=findViewById(R.id.myRecyclerView);
//
//        RecyclerView.RecycledViewPool recycledViewPool=new RecyclerView.RecycledViewPool();
//        recyclerView.setRecycledViewPool(recycledViewPool);
//        recycledViewPool.setMaxRecycledViews(0,50);
//
//        VirtualLayoutManager virtualLayoutManager=new VirtualLayoutManager(this);
//        recyclerView.setLayoutManager(virtualLayoutManager);
//
//        LinearLayoutHelper linearLayoutHelper=new LinearLayoutHelper();
//        linearLayoutHelper.setDividerHeight(20);
//        linearLayoutHelper.setPadding(10,0,10,0);
////        linearLayoutHelper.setMarginBottom(20);
//        linearLayoutHelper.setBgColor(Color.RED);
//        linearLayoutHelper.setMarginBottom(20);
////
//        DelegateAdapter delegateAdapter=new DelegateAdapter(virtualLayoutManager);
////
//        MyAdapter myAdapter=new MyAdapter(this,linearLayoutHelper,50);
////
//        List<DelegateAdapter.Adapter> list=new LinkedList<>();
////
//        list.add(myAdapter);
//
//        //
//        StickyLayoutHelper stickyLayoutHelper=new StickyLayoutHelper();
//        stickyLayoutHelper.setAspectRatio(4);
//
//        list.add(new MyAdapter(this,stickyLayoutHelper,1));
//        //
//        GridLayoutHelper gridLayoutHelper=new GridLayoutHelper(5);
//        gridLayoutHelper.setHGap(10);
//        gridLayoutHelper.setVGap(10);
//        gridLayoutHelper.setMargin(10,0,10,10);
//        gridLayoutHelper.setAutoExpand(true);
//        list.add(new MyAdapter(this,gridLayoutHelper,50));
//
//
//        OnePlusNLayoutHelperEx plusNLayoutHelper=new OnePlusNLayoutHelperEx();
////        plusNLayoutHelper.setColWeights(new float[]{0f,100f,0f});
//        plusNLayoutHelper.setAspectRatio(4);
////        plusNLayoutHelper.setRowWeight(60);
//
//        plusNLayoutHelper.setMargin(10,10,10,10);
//
////        plusNLayoutHelper.setRowWeight(2);
////        plusNLayoutHelper.setColWeights();
//
//        list.add(new MyAdapter(this,plusNLayoutHelper,6){
//
//        });
//
//        Calendar calendar=Calendar.getInstance();
//
//        Calendar N=Calendar.getInstance();
//        N.set(Calendar.MINUTE,50);
//        N.set(Calendar.HOUR_OF_DAY,18);
//
//        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd-hh");
//       String ss= df.format(N.getTime());
//       String ssss=df.format(calendar.getTime());
////        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
////            @Override
////            public int getSpanSize(int position) {
////                return 1;
////            }
////        });
//
//
//        //
////        FloatLayoutHelper floatLayoutHelper=new FloatLayoutHelper();
////        floatLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);
////
////
////        list.add(new MyAdapter(this,floatLayoutHelper,1));
//
//        //
//
//
//
//
//        delegateAdapter.setAdapters(list);
//        recyclerView.setAdapter(delegateAdapter);
//    }
//
//    private void getData(){
//        Retrofit retrofit= new Retrofit.Builder().baseUrl("").build();
//        retrofit.create(getData.class);
//    }
//
//    private class MyAdapter extends DelegateAdapter.Adapter<MyAdapter.hold>{
//
//        private Context context;
//        private LayoutHelper helper;
//        private int count;
//        public MyAdapter(Context context,LayoutHelper helper,int count) {
//            this.context=context;
//            this.helper=helper;
//            this.count=count;
//        }
//
//        @Override
//        public LayoutHelper onCreateLayoutHelper() {
//            return helper;
//        }
//
//        @Override
//        public hold onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new hold(LayoutInflater.from(context).inflate(R.layout.item_text,parent,false));
//        }
//
//        @Override
//        public void onBindViewHolder(hold holder, int position) {
//            holder.textView.setText(""+position);
//            Glide.with(context).load("http://www.10000s.com//b2cupload/top/advertimg/20180222085719851.jpg").into(holder.imageView);
//        }
//
//        @Override
//        public int getItemCount() {
//            return count;
//        }
//
//        public class hold extends RecyclerView.ViewHolder{
//            private TextView textView;
//            private ImageView imageView;
//            public hold(View itemView) {
//                super(itemView);
//                textView=itemView.findViewById(R.id.test);
//                imageView=itemView.findViewById(R.id.icon);
//            }
//        }
//
//    }
}
