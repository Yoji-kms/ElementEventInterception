package com.yoji.elementeventinterception;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Map<String, String>> content;
    private SharedPreferences itemList;
    private String[] values;
    private BaseAdapter listItemDataAdapter;

    private SwipeRefreshLayout.OnRefreshListener swipeOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            getContentFromSharedPrefs();
            content.clear();
            initList();
            listItemDataAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, R.string.data_updated_message, Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView list = findViewById(R.id.list);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshId);

        itemList = getSharedPreferences("Item List", MODE_PRIVATE);
        content = new ArrayList<>();

        if (itemList.getInt(Key.ITEM_QTY_KEY, 0) == 0) {
            values = prepareContent();
            putValuesToSharedPrefs(values);
        } else {
            getContentFromSharedPrefs();
        }
        initList();

        listItemDataAdapter = createItemDataAdapter();

        list.setAdapter(listItemDataAdapter);
        swipeRefreshLayout.setOnRefreshListener(swipeOnRefreshListener);
    }

    private BaseAdapter createItemDataAdapter(){
        return new ItemDataAdapter(this, content);
    }

    @NonNull
    private String[] prepareContent() {
        return getString(R.string.large_text).split("\n\n");
    }

    private void putValuesToSharedPrefs(String[] values) {
        SharedPreferences.Editor editor = itemList.edit();
        for (int i = 0; i < values.length; i++) {
            editor.putString(Key.ITEM_KEY + i, values[i]);
            editor.putInt(Key.ITEM_QTY_KEY, i);
        }
        editor.apply();
    }

    private void getContentFromSharedPrefs (){
        values = new String[itemList.getInt(Key.ITEM_QTY_KEY, 0)];
        for (int i=0; i<values.length; i++){
            values[i] = itemList.getString(Key.ITEM_KEY + i, "");
        }
    }

    private void initList() {
        for (String value : values) {
            Map<String, String> map = new HashMap<>();
            map.put(Key.TEXT_KEY, value);
            map.put(Key.NUM_OF_SYMBOL_KEY, String.valueOf(value.length()));
            content.add(map);
        }
    }
}
