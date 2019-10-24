package com.app.crm_app.Adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.crm_app.FragmentUserReplyScreen;
import com.app.crm_app.MainActivity;
import com.app.crm_app.R;

import java.util.ArrayList;

/**
 * Created by monika on 22-10-2019.
 */

public class SupportListAdapter extends RecyclerView.Adapter<SupportListAdapter.MyHolder> {
    private Activity activity;
    private ArrayList<String> data;
    FragmentManager fragmentManager;

    public SupportListAdapter(FragmentActivity activity, ArrayList<String> data) {
        this.data= data;
        this.activity=activity;
        fragmentManager = activity.getSupportFragmentManager();
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

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cardViewTicket;
        private TextView name, subject, department, date, status;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            subject = itemView.findViewById(R.id.subject);
            department = itemView.findViewById(R.id.department);
            date = itemView.findViewById(R.id.date);
            status = itemView.findViewById(R.id.status);
            cardViewTicket = itemView.findViewById(R.id.card_view_ticket);
            cardViewTicket.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.card_view_ticket:

                    Fragment fragment = new FragmentUserReplyScreen();
                   // Bundle bundle=new Bundle();
                  //  bundle.putString("company_id", Id);
                   // fragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    break;
            }
        }
    }
}
