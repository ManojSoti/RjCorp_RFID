package com.example.book_rfid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter2 extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final List<ProductStatus> mlist;

    public Adapter2(Context context, List<ProductStatus> list) {
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
        TextView tvProductTitle;

        ImageView ivBoxFound, ivBoxNotFound;
        TextView tvBoxFound, tvBoxNotFound;

        ImageView ivLeftFound, ivLeftNotFound;
        TextView tvLeftFound, tvLeftNotFound;

        ImageView ivRightFound, ivRightNotFound;
        TextView tvRightFound, tvRightNotFound;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_unknown, null);
            holder = new ViewHolder();

            // Product title
            holder.tvProductTitle = convertView.findViewById(R.id.tvProductTitle);

            // Box
            holder.ivBoxFound = convertView.findViewById(R.id.ivBoxFound);
            holder.ivBoxNotFound = convertView.findViewById(R.id.ivBoxNotFound);
            holder.tvBoxFound = convertView.findViewById(R.id.tvBoxFound);
            holder.tvBoxNotFound = convertView.findViewById(R.id.tvBoxNotFound);

            // Left shoe
            holder.ivLeftFound = convertView.findViewById(R.id.ivLeftFound);
            holder.ivLeftNotFound = convertView.findViewById(R.id.ivLeftNotFound);
            holder.tvLeftFound = convertView.findViewById(R.id.tvLeftFound);
            holder.tvLeftNotFound = convertView.findViewById(R.id.tvLeftNotFound);

            // Right shoe
            holder.ivRightFound = convertView.findViewById(R.id.ivRightFound);
            holder.ivRightNotFound = convertView.findViewById(R.id.ivRightNotFound);
            holder.tvRightFound = convertView.findViewById(R.id.tvRightFound);
            holder.tvRightNotFound = convertView.findViewById(R.id.tvRightNotFound);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ProductStatus status = mlist.get(position);

        holder.tvProductTitle.setText(status.productTitle != null ? status.productTitle : "N/A");

        // Box
        if (status.isBoxFound) {
            holder.ivBoxFound.setVisibility(View.VISIBLE);
            holder.tvBoxFound.setVisibility(View.VISIBLE);
            holder.ivBoxNotFound.setVisibility(View.GONE);
            holder.tvBoxNotFound.setVisibility(View.GONE);
        } else {
            holder.ivBoxFound.setVisibility(View.GONE);
            holder.tvBoxFound.setVisibility(View.GONE);
            holder.ivBoxNotFound.setVisibility(View.VISIBLE);
            holder.tvBoxNotFound.setVisibility(View.VISIBLE);
        }

        // Left
        if (status.isLeftFound) {
            holder.ivLeftFound.setVisibility(View.VISIBLE);
            holder.tvLeftFound.setVisibility(View.VISIBLE);
            holder.ivLeftNotFound.setVisibility(View.GONE);
            holder.tvLeftNotFound.setVisibility(View.GONE);
        } else {
            holder.ivLeftFound.setVisibility(View.GONE);
            holder.tvLeftFound.setVisibility(View.GONE);
            holder.ivLeftNotFound.setVisibility(View.VISIBLE);
            holder.tvLeftNotFound.setVisibility(View.VISIBLE);
        }

        // Right
        if (status.isRightFound) {
            holder.ivRightFound.setVisibility(View.VISIBLE);
            holder.tvRightFound.setVisibility(View.VISIBLE);
            holder.ivRightNotFound.setVisibility(View.GONE);
            holder.tvRightNotFound.setVisibility(View.GONE);
        } else {
            holder.ivRightFound.setVisibility(View.GONE);
            holder.tvRightFound.setVisibility(View.GONE);
            holder.ivRightNotFound.setVisibility(View.VISIBLE);
            holder.tvRightNotFound.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
