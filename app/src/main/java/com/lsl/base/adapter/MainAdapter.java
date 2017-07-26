package com.lsl.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lsl.base.R;
import com.lsl.base.imgloader.ImageLoader;

import java.util.List;

/**
 * Created by Forrest
 * on 2017/7/26 10:43
 * 首页的Adapter
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<String> mData ;
    private Context context;

    public MainAdapter(List<String> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(new ImageView(context));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        new ImageLoader(context).url(mData.get(position))
                .defaultImage(R.mipmap.ic_launcher)
                .imageView(holder.imgView)
                .load();
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        public ViewHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView;
        }
    }
}
