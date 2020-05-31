package com.yoji.elementeventinterception;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDataAdapter extends BaseAdapter {

    private List<Map<String,String>> itemList;
    private LayoutInflater inflater;

    private View.OnClickListener deleteButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            removeItem((Integer) v.getTag());
        }
    };

    private View.OnLongClickListener viewOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(v.getContext(), itemList.get((Integer) v.getTag()).get(Key.TEXT_KEY), Toast.LENGTH_LONG).show();
            return true;
        }
    };

    void removeItem(int position){
        itemList.remove(position);
        notifyDataSetChanged();
    }

    public ItemDataAdapter (Context context, List<Map<String,String>> itemList){
        if (itemList == null){
            this.itemList = new ArrayList<>();
        }else {
            this.itemList = itemList;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = inflater.inflate(R.layout.item_to_display, parent, false);
        }

        Map<String, String> item = itemList.get(position);

        TextView mainTxtView = view.findViewById(R.id.mainTextTxtViewId);
        TextView numOfSymbolsTxtView = view.findViewById(R.id.numOfSymbolTxtViewId);
        Button deleteButton = view.findViewById(R.id.deleteButtonId);

        mainTxtView.setText(item.get(Key.TEXT_KEY));
        numOfSymbolsTxtView.setText((item.get(Key.NUM_OF_SYMBOL_KEY)));
        deleteButton.setOnClickListener(deleteButtonOnClickListener);
        deleteButton.setOnLongClickListener(viewOnLongClickListener);
        deleteButton.setTag(position);
        mainTxtView.setOnLongClickListener(viewOnLongClickListener);
        mainTxtView.setTag(position);
        numOfSymbolsTxtView.setOnLongClickListener(viewOnLongClickListener);
        numOfSymbolsTxtView.setTag(position);

        return view;
    }
}
