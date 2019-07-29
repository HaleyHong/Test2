package com.feyon.myapplication.MVP.base;

/**
 * Created by DS on 2018/8/1.
 */

public interface AbstractPresenter<T extends AbstractView> {
    public void attach(T View);

    public void destroy();

}
