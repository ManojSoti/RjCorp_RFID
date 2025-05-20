package com.example.book_rfid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.lights.LightState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<String> mlist;
    private Context mcontext;

    public Adapter(Context context,List<String> list) {
        this.mcontext=context;
        this.mInflater = LayoutInflater.from(context);
        this.mlist=list;
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
    static class ViewHolder{
        protected TextView textView;
        protected TextView tvTagCount;
        protected TextView tvTagRssi;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (view==null){
            view=mInflater.inflate(R.layout.listtag_items,null,true);
            viewHolder = new ViewHolder();

            viewHolder.textView=(TextView)view.findViewById(R.id.TvTagUii);
            viewHolder.tvTagCount = (TextView) view.findViewById(R.id.TvTagCount);
            viewHolder.tvTagRssi = (TextView) view.findViewById(R.id.TvTagRssi);
            view.setTag(viewHolder);
            view.setTag(R.id.TvTagUii, viewHolder.textView);
            view.setTag(R.id.TvTagCount,viewHolder.tvTagCount);
            view.setTag(R.id.TvTagRssi,viewHolder.tvTagRssi);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setTag(position);
        viewHolder.textView.setText(mlist.get(position));
       // viewHolder.tvTagCount.setText(mlist.get(position));
        //viewHolder.tvTagRssi.setText(mlist.get(position));

        return view;
    }
}
