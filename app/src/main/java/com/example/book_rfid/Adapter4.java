package com.example.book_rfid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.lights.LightState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter4 extends BaseAdapter {
    private final LayoutInflater mInflater;
    private final List<ProductStatus> mlist;
    private final Context mcontext;

    public Adapter4(Context context, List<ProductStatus> list) {
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
        TextView tvProductTitle;

        // Box
        ImageView ivBoxFound, ivBoxNotFound;
        TextView tvBoxFound, tvBoxNotFound;

        // Left
        ImageView ivLeftFound, ivLeftNotFound;
        TextView tvLeftFound, tvLeftNotFound;

        // Right
        ImageView ivRightFound, ivRightNotFound;
        TextView tvRightFound, tvRightNotFound;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_founditems, null);
            holder = new ViewHolder();

            holder.tvProductTitle = convertView.findViewById(R.id.tvProductTitle);

            // Box
            holder.ivBoxFound = convertView.findViewById(R.id.ivBoxFound);
            holder.ivBoxNotFound = convertView.findViewById(R.id.ivBoxNotFound);
            holder.tvBoxFound = convertView.findViewById(R.id.tvBoxFound);
            holder.tvBoxNotFound = convertView.findViewById(R.id.tvBoxNotFound);

            // Left
            holder.ivLeftFound = convertView.findViewById(R.id.ivLeftFound);
            holder.ivLeftNotFound = convertView.findViewById(R.id.ivLeftNotFound);
            holder.tvLeftFound = convertView.findViewById(R.id.tvLeftFound);
            holder.tvLeftNotFound = convertView.findViewById(R.id.tvLeftNotFound);

            // Right
            holder.ivRightFound = convertView.findViewById(R.id.ivRightFound);
            holder.ivRightNotFound = convertView.findViewById(R.id.ivRightNotFound);
            holder.tvRightFound = convertView.findViewById(R.id.tvRightFound);
            holder.tvRightNotFound = convertView.findViewById(R.id.tvRightNotFound);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ProductStatus product = mlist.get(position);
        holder.tvProductTitle.setText(product.productTitle);

        // Box status
        if (product.isBoxFound) {
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

        // Left Shoe status
        if (product.isLeftFound) {
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

        // Right Shoe status
        if (product.isRightFound) {
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
