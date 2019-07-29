package com.feyon.myapplication.MVP.acticity;

import com.feyon.myapplication.MVP.base.AbstractView;
import com.feyon.myapplication.MVP.base.BasePresenter;

/**
 * Created by DS on 2018/8/1.
 */

public class myGetPresenter extends BasePresenter {


    @Override
    public void attach(AbstractView View) {
        super.attach(View);

    }

    public void hello(){
        System.out.println("ssssss");

        view.Show();
    }


}
