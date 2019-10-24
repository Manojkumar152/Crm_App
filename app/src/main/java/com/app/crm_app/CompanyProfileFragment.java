package com.app.crm_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.crm_app.ApiCalls.ApiInterface;
import com.app.crm_app.ApiCalls.RetrofitClient;
import com.app.crm_app.ApiCalls.Urls;
import com.app.crm_app.Common.ConnectionDetection;
import com.app.crm_app.Common.DisplaySnackBar;
import com.app.crm_app.Model.CompanyProfile;
import com.app.crm_app.Model.UpdateCompanyProfileModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyProfileFragment extends Fragment implements View.OnClickListener {

    private TextView mCompanyName,mGstinNumber,mPhone,mWebsite,mCountry,mCity,mZipCode,mState;
    private EditText mEditCompanyName,mEditGstinNumber,mEditphone,mEditWebsite,mEditCountry,mEditCity,mEditZipcode,mEditState;
    private RelativeLayout mRelativeEditProfile;
    private ImageView mProfileImage;
    private RelativeLayout relativeLayout;
    private Button mBtnUpdate;
    private boolean isEdited;
    private String user_company_name,user_gstin_number,user_phone_no,user_wesite,user_country,user_city,user_zip_code,user_state,image ="";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String userId = "2";
    private static String TAG="CompanyProfile";
    private ProgressDialog dialog;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_update_profile,container,false);

        pref = getActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        editor = pref.edit();

       // userId=pref.getString("company_id",null);
        initId(v);

        if(ConnectionDetection.isInternetAvailable(getActivity())){
            getCompanyProfile(userId);
        }else {
            DisplaySnackBar.showSnackBar(getActivity(),getString(R.string.network_error));
        }

        return v;
    }


    private void initId(View v){
        isEdited=false;
        mCompanyName = v.findViewById(R.id.tv_company);
        mGstinNumber = v.findViewById(R.id.tv_gstin_number);
        mPhone = v.findViewById(R.id.tv_phone);
        mWebsite = v.findViewById(R.id.tv_website);
        mCountry = v.findViewById(R.id.tv_country);
        mCity = v.findViewById(R.id.tv_city);
        mZipCode = v.findViewById(R.id.tv_zip_code);
        mState = v.findViewById(R.id.tv_state);

        mBtnUpdate=v.findViewById(R.id.btn_update_profile);

        mEditCompanyName = v.findViewById(R.id.ed_company);
        mEditGstinNumber = v.findViewById(R.id.ed_gstin_number);
        mEditphone = v.findViewById(R.id.ed_phone);
        mEditWebsite = v.findViewById(R.id.ed_website);
        mEditCountry = v.findViewById(R.id.ed_country);
        mEditCity = v.findViewById(R.id.ed_city);
        mEditZipcode = v.findViewById(R.id.ed_zip_code);
        mEditState = v.findViewById(R.id.ed_state);


        mCompanyName.setOnClickListener(this);
        mGstinNumber.setOnClickListener(this);
        mPhone.setOnClickListener(this);
        mWebsite.setOnClickListener(this);
        mCountry.setOnClickListener(this);
        mCity.setOnClickListener(this);
        mZipCode.setOnClickListener(this);
        mState.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);

        dialog= new ProgressDialog(getActivity());
        dialog.setMessage(getString(R.string.wait_msg));
    }


    private void getCompanyProfile(String id) {
        dialog.show();
        ApiInterface service= RetrofitClient.getClient().create(ApiInterface.class);
        Call<CompanyProfile> call= service.getProfile(id,Urls.API_KEY);
        call.enqueue(new Callback<CompanyProfile>() {
            @Override
            public void onResponse(Call<CompanyProfile> call, Response<CompanyProfile> response) {
                Log.e(TAG,"Response"+ response.body());
                dialog.dismiss();
                if(response.body().getStatus()==1) {
                    setData(response.body());
                }else{
                    DisplaySnackBar.showSnackBar(getActivity(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<CompanyProfile> call, Throwable t) {
                Log.e(TAG,"Failure"+ t.toString());
                dialog.dismiss();
            }
        });
    }

    private void setData(CompanyProfile body) {
        Log.e("SetData","SetData"+" "+body.getData().get(0).getCompany());

        mEditCompanyName.setText(body.getData().get(0).getCompany());
        mEditGstinNumber.setText(body.getData().get(0).getGstin_number());
        mEditphone.setText(body.getData().get(0).getPhonenumber());
        mEditWebsite.setText(body.getData().get(0).getWebsite());
        mEditCountry.setText(body.getData().get(0).getCountry());
        mEditCity.setText(body.getData().get(0).getCity());
        mEditZipcode.setText(body.getData().get(0).getZip());
        mEditState.setText(body.getData().get(0).getState());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /*case R.id.rl_layoutEditProfile:
                isEdited = true;
                FragmentProfilePermissionsDispatcher.selectImageWithPermissionCheck(this);
                break;*/

            case R.id.tv_company:
                mEditCompanyName.setEnabled(true);
                mEditCompanyName.requestFocus();
                isEdited = true;

                break;

            case R.id.tv_gstin_number:
                mEditGstinNumber.setEnabled(true);
                mEditGstinNumber.requestFocus();
                isEdited = true;
                break;

            case R.id.tv_phone:
                mEditphone.setEnabled(true);
                mEditphone.requestFocus();
                isEdited = true;
                break;

            case R.id.tv_website:
                mEditWebsite.setEnabled(true);
                mEditWebsite.requestFocus();
                isEdited = true;
                break;

            case R.id.tv_country:
                mEditCountry.setEnabled(true);
                mEditCountry.requestFocus();
                isEdited = true;
                break;
            case R.id.tv_city:
                mEditCity.setEnabled(true);
                mEditCity.requestFocus();
                isEdited = true;
                break;
            case R.id.tv_zip_code:
                mEditZipcode.setEnabled(true);
                mEditZipcode.requestFocus();
                isEdited = true;
                break;
            case R.id.tv_state:
                mEditState.setEnabled(true);
                mEditState.requestFocus();
                isEdited = true;
                break;

            case R.id.btn_update_profile:
                if (isEdited) {

                    if (ConnectionDetection.isInternetAvailable(getActivity())) {
                        updateCompannyProfile(mEditCompanyName.getText().toString(), mEditGstinNumber.getText().toString(),
                                mEditphone.getText().toString(), mEditWebsite.getText().toString(),
                                mEditCountry.getText().toString(), mEditCity.getText().toString(),
                                mEditZipcode.getText().toString(),
                                mEditState.getText().toString());
                    } else {
                        DisplaySnackBar.showSnackBar(getActivity(), getString(R.string.network_error));
                    }
                } else {
                    DisplaySnackBar.showSnackBar(getActivity(), "There is Nothing to Update");
                }
                break;
        }
    }
    private void updateCompannyProfile(String company_proflie_name,String company_gstin,String company_phone_number,
                                       String company_website,String company_country,String company_city,
                                       String company_zip_code,String company_state){


        ApiInterface service= RetrofitClient.getClient().create(ApiInterface.class);
        Call<UpdateCompanyProfileModel> call=service.updateCompanyProfile(userId,Urls.API_KEY,company_proflie_name,company_gstin,
                company_phone_number,company_website,company_country,company_city,company_zip_code,
                company_state);
        call.enqueue(new Callback<UpdateCompanyProfileModel>() {
            @Override
            public void onResponse(Call<UpdateCompanyProfileModel> call, Response<UpdateCompanyProfileModel> response) {
               /* if(getActivity()!=null) {
                    ((BaseActivity) getActivity()).hideProgress();
                }*/
               try {
                   if (response.body().getSuccess() ==200) {

                       DisplaySnackBar.showSnackBar(getActivity(), response.body().getMessage());
                       getCompanyProfile(userId);
                   }else if(response.body().getSuccess()==400){
                       DisplaySnackBar.showSnackBar(getActivity(), response.body().getMessage());
                   }
               }catch (Exception e){
                   e.getStackTrace();
               }

            }

            @Override
            public void onFailure(Call<UpdateCompanyProfileModel> call, Throwable t) {
                Log.e("DashBoard","Failure"+t.toString());
               /* if(getActivity()!=null) {
                    ((BaseActivity) getActivity()).hideProgress();
                }*/
            }
        });
    }
}
