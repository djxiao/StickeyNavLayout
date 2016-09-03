package com.djxiao.main.stickeynavlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djxiao.main.stickeynavlayout.adapters.TabFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author djxiao
 * @create 2016/8/31  15:34
 * @Desc:
 */
public class TabFragment extends Fragment {

    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";

    private RecyclerView mRecyclerView;
    private List<String> arr = new ArrayList<String>();
    private TabFragmentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        for(int i = 0;i<50;i++){
            arr.add(mTitle+i);
        }
        adapter = new TabFragmentAdapter(arr);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);

        return view;

    }

    public static TabFragment newInstance(String title){
        TabFragment fragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE,title);
        fragment.setArguments(bundle);
        return fragment;
    }
}
