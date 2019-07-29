package com.feyon.myapplication.MVP.acticity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.feyon.myapplication.R;

public class Main27Activity extends BaseActivity<myGetPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main27);

        Button v=findViewById(R.id.hello);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.hello();
            }
        });


    }

    @Override
    public void Show() {
        super.Show();
        System.out.println("show");
    }

    @Override
    myGetPresenter createPresenter() {
        return new myGetPresenter();
    }
}
