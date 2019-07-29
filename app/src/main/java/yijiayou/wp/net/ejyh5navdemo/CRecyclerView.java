package yijiayou.wp.net.ejyh5navdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class CRecyclerView extends RecyclerView {
    public CRecyclerView(Context context) {
        super(context);
    }

    public CRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//    @Override
//    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
//        return false;
//    }
//
//    @Override
//    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
//        return false;
//    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
//        velocityY = Math.max(-getMaxFlingVelocity()/2, Math.min(velocityY, getMaxFlingVelocity()/2));
        return false;
    }


}
