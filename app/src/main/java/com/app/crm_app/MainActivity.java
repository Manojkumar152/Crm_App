package com.app.crm_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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

        initView();
    }

    private void initView() {
        support_layout= findViewById(R.id.support_layout);
        projects_layout= findViewById(R.id.projects_layout);
        invoice_layout=findViewById(R.id.invoice_layout);
        company_profile_layout=findViewById(R.id.company_profile_layout);
        proposal_layout=findViewById(R.id.proposal_layout);
        contracts_layout=findViewById(R.id.contracts_layout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.support_layout:
                break;
            case R.id.projects_layout:
                break;
            case R.id.invoice_layout:
                break;
            case R.id.company_profile_layout:
                break;
            case R.id.proposal_layout:
                break;
            case R.id.contracts_layout:
                break;
        }
    }
}
