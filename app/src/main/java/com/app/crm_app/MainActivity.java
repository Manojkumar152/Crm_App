package com.app.crm_app;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.app.crm_app.Fragments.ProposalListFragment;
import com.app.crm_app.Fragments.SupportScreenFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FragmentManager fragmentManager;
    private ImageView backbtn;
    private CardView support_layout,
            projects_layout,
            invoice_layout,
            company_profile_layout,
            proposal_layout,
            contracts_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        initView();
    }

    private void initView() {
        backbtn=findViewById(R.id.back_btn);
        support_layout= findViewById(R.id.support_layout);
        projects_layout= findViewById(R.id.projects_layout);
        invoice_layout=findViewById(R.id.invoice_layout);
        company_profile_layout=findViewById(R.id.company_profile_layout);
        proposal_layout=findViewById(R.id.proposal_layout);
        contracts_layout=findViewById(R.id.contracts_layout);

        support_layout.setOnClickListener(this);
        proposal_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.support_layout:
                setFragment(new SupportScreenFragment());
                break;
            case R.id.projects_layout:
                break;
            case R.id.invoice_layout:
                break;
            case R.id.company_profile_layout:
                break;
            case R.id.proposal_layout:
                setFragment(new ProposalListFragment());
                break;
            case R.id.contracts_layout:
                break;
        }
    }

    public void setFragment(Fragment fragment) {
        checkConnection();
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack("hye").commit();
        } else {
            Log.e("HomeActivity", "Error in creating fragment");
        }
    }

    private void checkConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        alertMassege(activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }

    private void alertMassege(boolean isConnected) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog;
        if (!isConnected) {

            alertDialogBuilder.setMessage("No Internet Connection Available." +
                    "Do you want to try again?");
            alertDialogBuilder.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.super.recreate();
                        }
                    });
            alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            });
            alertDialogBuilder.setCancelable(false);
            alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

}
