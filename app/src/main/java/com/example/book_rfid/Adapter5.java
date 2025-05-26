package com.example.book_rfid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter5 extends BaseAdapter {
    private final LayoutInflater mInflater;
    private final List<ProductStatus> mlist;
    private final Context mcontext;

    public Adapter5(Context context, List<ProductStatus> list) {
        this.mcontext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mlist = list;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        protected TextView tvProductTitle;
        // You can also declare the Not Found views if you want to manipulate them dynamically later
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;

        if (view == null) {
            view = mInflater.inflate(R.layout.list_missingitems, null, true);
            viewHolder = new ViewHolder();
            viewHolder.tvProductTitle = view.findViewById(R.id.tvProductTitle);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ProductStatus product = mlist.get(position);
        viewHolder.tvProductTitle.setText(product.getProductTitle());

        return view;
    }
}
