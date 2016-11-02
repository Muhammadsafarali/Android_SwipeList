package ru.norbit.myswipelistdeleteitem;

/**
 * Created by safarali.alisultanov on 02.11.2016.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {

    private final LayoutInflater mInflater;
    private final ViewBinderHelper binderHelper;

    public MyAdapter(Context context, List<String> objects) {
        super(context, R.layout.row_list, objects);
        mInflater = LayoutInflater.from(context);
        binderHelper = new ViewBinderHelper();

        // uncomment if you want to open only one row at a time
        // binderHelper.setOpenOnlyOne(true);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_list, parent, false);

            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.text);
            holder.deleteView = convertView.findViewById(R.id.delete_layout);
            holder.swipeLayout = (SwipeRevealLayout) convertView.findViewById(R.id.swipe_layout);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final String item = getItem(position);
        if (item != null) {
            holder.swipeLayout.setSwipeListener(new SwipeRevealLayout.SimpleSwipeListener() {
                @Override
                public void onSlide(SwipeRevealLayout view, float slideOffset) {
                    if (slideOffset >= 0.95) {
                        view.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                remove(item);
                            }
                        }, 100);
                    }
                }
            });
            binderHelper.bind(holder.swipeLayout, item);

            holder.textView.setText(item);
            holder.deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(item);
                }
            });
        }

        return convertView;
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onSaveInstanceState(Bundle)}
     */
    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onRestoreInstanceState(Bundle)}
     */
    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

    private class ViewHolder {
        TextView textView;
        View deleteView;
        SwipeRevealLayout swipeLayout;
    }
}
