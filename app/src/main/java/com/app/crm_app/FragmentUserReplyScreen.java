package com.app.crm_app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.app.crm_app.ApiCalls.ApiInterface;
import com.app.crm_app.ApiCalls.RetrofitClient;
import com.app.crm_app.ApiCalls.Urls;
import com.app.crm_app.Common.ConnectionDetection;
import com.app.crm_app.Common.DisplaySnackBar;
import com.app.crm_app.Model.AddStaffModel;
import com.app.crm_app.Model.CompanyProfile;
import com.app.crm_app.Model.StaffDetailModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentUserReplyScreen extends Fragment implements View.OnClickListener {

    private ProgressDialog dialog;
    private static String TAG="StaffDetail";
    TextView mStaffName,mStaff,mContent;
    EditText mEditReply;
    Button mBtnAddReply;
    String staff_id = "";

    List<StaffDetailModel.DataBean> listData = new ArrayList<>();
    String userId  ="1";

    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_user_reply_screen,container,false);
        iniId(v);
        if(ConnectionDetection.isInternetAvailable(getActivity())){
            getCompanyProfile(userId);
        }else {
            DisplaySnackBar.showSnackBar(getActivity(),getString(R.string.network_error));
        }
        return v;
    }

    private void iniId(View v){
        dialog= new ProgressDialog(getActivity());
        dialog.setMessage(getString(R.string.wait_msg));

        mStaffName = v.findViewById(R.id.tv_staff_name);
        mStaff = v.findViewById(R.id.tv_staff);
        mContent = v.findViewById(R.id.tv_content);

        mEditReply = v.findViewById(R.id.ed_reply);
        mBtnAddReply = v.findViewById(R.id.btn_add_reply);
        mBtnAddReply.setOnClickListener(this);

    }


    private void getCompanyProfile(String id) {
        dialog.show();
        ApiInterface service= RetrofitClient.getClient().create(ApiInterface.class);
        Call<StaffDetailModel> call= service.getStaffDeatail(id,Urls.API_KEY);
        call.enqueue(new Callback<StaffDetailModel>() {
            @Override
            public void onResponse(Call<StaffDetailModel> call, Response<StaffDetailModel> response) {
                Log.e(TAG,"Response"+ response.body());
                dialog.dismiss();
                if(response.body().getStatus()==1) {
                    setData(response.body());
                }else{
                    DisplaySnackBar.showSnackBar(getActivity(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<StaffDetailModel> call, Throwable t) {
                Log.e(TAG,"Failure"+ t.toString());
                dialog.dismiss();
            }
        });
    }

    private void setData(StaffDetailModel body){

        if(body.getData().size()==0){
            DisplaySnackBar.showSnackBar(getActivity(),body.getMessage());
        }else {
            List<StaffDetailModel.DataBean> tempData = body.getData();
            listData.clear();
            listData.addAll(tempData);
            for(int i=0;i<listData.size();i++){

                mStaffName.setText(listData.get(i).getFirstname());
                mContent.setText(listData.get(i).getContent());
                staff_id = listData.get(i).getStaffid();
            }

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_reply:
                AddReply(mEditReply.getText().toString().trim());
                break;
        }
    }
    private void AddReply(String add_reply){

        dialog.show();
        ApiInterface service= RetrofitClient.getClient().create(ApiInterface.class);
        Call<AddStaffModel> call= service.AddStaff(staff_id,Urls.API_KEY,add_reply);
        call.enqueue(new Callback<AddStaffModel>() {
            @Override
            public void onResponse(Call<AddStaffModel> call, Response<AddStaffModel> response) {
                Log.e(TAG,"Response"+ response.body());
                dialog.dismiss();
                if(response.body().getSuccess()==200) {
                    DisplaySnackBar.showSnackBar(getActivity(),response.body().getMessage());
                    getCompanyProfile(userId);
                    mEditReply.setText("");
                }
                else if(response.body().getSuccess()==400){
                    DisplaySnackBar.showSnackBar(getActivity(),response.body().getMessage());

                }
            }

            @Override
            public void onFailure(Call<AddStaffModel> call, Throwable t) {
                Log.e(TAG,"Failure"+ t.toString());
                DisplaySnackBar.showSnackBar(getActivity(),t.getMessage());
                dialog.dismiss();
            }
        });

    }
}
