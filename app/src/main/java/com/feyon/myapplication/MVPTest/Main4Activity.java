package com.feyon.myapplication.MVPTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.feyon.myapplication.R;

public class Main4Activity extends AppCompatActivity implements TestBaseView {

    private  TestPresenter testPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        testPresenter=new TestPresenter();
//        testPresenter.attachView(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                testPresenter.getData();
//            }
//        });
    }


//    @Override
//    public void onShowLoading() {
//
//    }
//
//    @Override
//    public void onHideLoading() {
//
//    }
//
//    @Override
//    public void onSuccess(Object data) {
//        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onFail(String msg) {
//
//    }
//
//    @Override
//    public void onComplete(String msg) {
//
//    }
//
//    @Override
//    public Context onGetContext() {
//        return this;
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        testPresenter.detachView();
    }
}
