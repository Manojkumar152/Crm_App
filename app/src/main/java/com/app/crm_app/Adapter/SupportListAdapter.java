package com.app.crm_app.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.crm_app.R;

import java.util.ArrayList;

/**
 * Created by monika on 22-10-2019.
 */

public class SupportListAdapter extends RecyclerView.Adapter<SupportListAdapter.MyHolder> {
    private Activity activity;
    private ArrayList<String> data;

    public SupportListAdapter(FragmentActivity activity, ArrayList<String> data) {
        this.data= data;
        this.activity=activity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.support_list_items,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.name.setText(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView name,subject,department,date,status;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.txt_name);
            subject=itemView.findViewById(R.id.subject);
            department=itemView.findViewById(R.id.department);
            date=itemView.findViewById(R.id.date);
            status=itemView.findViewById(R.id.status);
        }
    }
}
