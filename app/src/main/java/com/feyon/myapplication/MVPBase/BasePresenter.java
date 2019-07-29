package com.feyon.myapplication.MVPBase;

/**
 * Created by DS on 2018/4/18.
 */

public abstract class BasePresenter {
    private BaseViewImp baseViewImp;

    public BasePresenter() {
    }

    abstract public void getData();

    public void attachView(BaseViewImp baseViewImp){
        this.baseViewImp=baseViewImp;
    }

    public void detachView(){
        baseViewImp=null;
    }

    public boolean isViewAttached(){
        if (baseViewImp==null)
        {
            return false;
        }

        return true;
    }

    public BaseViewImp getBaseViewImp() {
        return baseViewImp;
    }
}
