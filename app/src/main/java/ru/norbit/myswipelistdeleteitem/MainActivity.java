package ru.norbit.myswipelistdeleteitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupList();
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        // Only if you need to restore open/close state when
//        // the orientation is changed
//        if (adapter != null) {
//            adapter.saveStates(outState);
//        }
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        // Only if you need to restore open/close state when
//        // the orientation is changed
//        if (adapter != null) {
//            adapter.restoreStates(savedInstanceState);
//        }
//    }

    private void setupList() {
        ListView listView = (ListView) findViewById(R.id.list_view);
        adapter = new MyAdapter(this, createList(20));
        listView.setAdapter(adapter);
    }

    private List<String> createList(int n) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add("View " + i);
        }

        return list;
    }
}
