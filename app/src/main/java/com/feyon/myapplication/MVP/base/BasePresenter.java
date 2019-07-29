package com.feyon.myapplication.MVP.base;

/**
 * Created by DS on 2018/8/1.
 */

public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T> {

    public AbstractView view;


    @Override
    public void attach(AbstractView view) {
        this.view=view;
    }

    @Override
    public void destroy() {
        view=null;
    }
}
