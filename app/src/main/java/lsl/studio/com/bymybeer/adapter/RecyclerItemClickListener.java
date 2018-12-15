package lsl.studio.com.bymybeer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
         void onItemClick(View view, int position);

         void onLongItemClick(View view, int position);
    }

    GestureDetector mGestureDetector;

     public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

 public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }


 public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
}
