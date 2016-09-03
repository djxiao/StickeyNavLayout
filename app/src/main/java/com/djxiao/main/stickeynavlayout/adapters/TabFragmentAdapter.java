package com.djxiao.main.stickeynavlayout.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djxiao.main.stickeynavlayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author djxiao
 * @create 2016/8/31  15:50
 * @Desc:
 */
public class TabFragmentAdapter extends RecyclerView.Adapter<TabFragmentAdapter.ViewHolder> {

    private List<String> dataArr = new ArrayList<String>();

    public TabFragmentAdapter(List<String> arr){
        this.dataArr = arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_tab,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvContent.setText(dataArr.get(position));
    }

    @Override
    public int getItemCount() {
        return dataArr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvContent;
        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
