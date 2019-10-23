package com.app.crm_app.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.crm_app.Adapter.ProposalAdapter;
import com.app.crm_app.Adapter.SupportListAdapter;
import com.app.crm_app.R;

import java.util.ArrayList;

/**
 * Created by dell on 22-10-2019.
 */

public class ProposalListFragment extends Fragment {
    private RecyclerView supportList;
    private ProposalAdapter adapter;
    private LinearLayoutManager linearManager;
    private ArrayList<String> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proposal_list, container, false);
        addData();
        initView(view);
        return view;
    }

    private void addData() {
        data.add("#PRO 00345 ABC");
        data.add("#PRO 00345 DEF");
        data.add("#PRO 00345 GHI");
        data.add(("#PRO 00345 JKL"));
    }

    private void initView(View view) {
        supportList = view.findViewById(R.id.proposal_list);
        linearManager = new LinearLayoutManager(getActivity());
        supportList.setLayoutManager(linearManager);
        adapter = new ProposalAdapter(getActivity(), data);
        supportList.setAdapter(adapter);
    }
}