package com.feyon.myapplication.MVP.acticity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.feyon.myapplication.MVP.base.AbstractPresenter;
import com.feyon.myapplication.MVP.base.AbstractView;

public  abstract  class BaseActivity<T extends AbstractPresenter> extends AppCompatActivity implements AbstractView {

    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter=createPresenter();
        mPresenter.attach(this);

//        savedInstanceStatesetContentView(R.layout.activity_main26);
    }

    private void test(){

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
        {
            mPresenter.destroy();
        }
    }

    @Override
    public void Show() {

    }



    abstract T  createPresenter();
}
