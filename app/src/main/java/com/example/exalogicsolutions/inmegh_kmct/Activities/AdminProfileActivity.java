package com.example.exalogicsolutions.inmegh_kmct.Activities;

import android.content.res.ColorStateList;
import android.support.design.button.MaterialButton;
import android.support.design.card.MaterialCardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.exalogicsolutions.inmegh_kmct.R;

public class AdminProfileActivity extends AppCompatActivity {

    private MaterialCardView cardPersonDetails, cardPersonDetailsExpand, cardAddressDetails,
            cardAddressDetailsExpand, cardEmergencyDetailsExpand, cardAdditionalDetailsExpand,
            cardHealthDetailsExpand, cardParentDetailsExpand, cardSpouseDetailsExpand, cardExperienceDetailsExpand, cardDocumentDetailsExpand;
    private LinearLayout llrow1, llrow2, llrow3, llrow4, llrow5, llrow6, llrow7, llMajorLayout,
            llMajorLayout1, llMajorLayout2, llMajorLayout3, llMajorLayout4, llMajorLayout5, llMajorLayout6;
    private View view1, view2, view3, view4;
    private TextView txTitle1, txTitle2, txDesignation, txEmail, txNumber, txTitle3, txTitle5, txTitle6,
            txTitle4, txTitle7, txTitle8, txTitle9;
    private ImageButton imgBtnDown, imgBtnUp, imgBtnDown2, imgBtnUp2, imgBtnDown3, imgBtnUp3,
            imgBtnDown4, imgBtnUp4, imgBtnDown5, imgBtnUp5, imgBtnDown6, imgBtnUp6, imgBtnDown8,
            imgBtnUp8, imgBtnDown9, imgBtnUp9, imgBtnDown10, imgBtnUp10;
    private boolean statusAddress = true, statusPersonal = true, statusEmergency = true,
            statusAdditional = true, statusHealth = true, statusParent = true, statusSpouse = true, statusExp = true, statusDoc = true;

    private MaterialButton btnPersonalDetail, btnAdditionalDetail, btnParentDetail, btnExperienceDetail;
    private ScrollView scrlView, scrlView1, scrlView2, scrlView3;

    private RecyclerView rcvExperienceInfo, rcvDocumentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cardPersonDetails = findViewById(R.id.cardPersonDetails);
        cardPersonDetailsExpand = findViewById(R.id.cardPersonDetailsExpand);
        cardAddressDetails = findViewById(R.id.cardAddressDetails);
        cardAddressDetailsExpand = findViewById(R.id.cardAddressDetailsExpand);
        cardEmergencyDetailsExpand = findViewById(R.id.cardEmergencyDetailsExpand);
        cardAdditionalDetailsExpand = findViewById(R.id.cardAdditionalDetailsExpand);
        cardHealthDetailsExpand = findViewById(R.id.cardHealthDetailsExpand);
        cardParentDetailsExpand = findViewById(R.id.cardParentDetailsExpand);
        cardSpouseDetailsExpand = findViewById(R.id.cardSpouseDetailsExpand);
        cardExperienceDetailsExpand = findViewById(R.id.cardExperienceDetailsExpand);
        cardDocumentDetailsExpand = findViewById(R.id.cardDocumentDetailsExpand);

        btnPersonalDetail = findViewById(R.id.btnPersonalDetail);
        btnAdditionalDetail = findViewById(R.id.btnAdditionalDetail);
        btnParentDetail = findViewById(R.id.btnParentDetail);
        btnExperienceDetail = findViewById(R.id.btnExperienceDetail);

        rcvExperienceInfo = findViewById(R.id.rcvExperienceInfo);
        rcvDocumentInfo = findViewById(R.id.rcvDocumentInfo);

        scrlView = findViewById(R.id.scrlView);
        scrlView1 = findViewById(R.id.scrlView1);
        scrlView2 = findViewById(R.id.scrlView2);
        scrlView3 = findViewById(R.id.scrlView3);

        llrow1 = findViewById(R.id.llrow1);
        llrow2 = findViewById(R.id.llrow2);
        llrow3 = findViewById(R.id.llrow3);

        llrow4 = findViewById(R.id.llrow4);
        llrow5 = findViewById(R.id.llrow5);
        llrow6 = findViewById(R.id.llrow6);
        llrow7 = findViewById(R.id.llrow7);

        llMajorLayout = findViewById(R.id.llMajorLayout);
        llMajorLayout1 = findViewById(R.id.llMajorLayout1);
        llMajorLayout2 = findViewById(R.id.llMajorLayout2);
        llMajorLayout3 = findViewById(R.id.llMajorLayout3);
        llMajorLayout4 = findViewById(R.id.llMajorLayout4);
        llMajorLayout5 = findViewById(R.id.llMajorLayout5);
        llMajorLayout6 = findViewById(R.id.llMajorLayout6);

        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);

        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);

        txTitle1 = findViewById(R.id.txTitle1);
        txTitle2 = findViewById(R.id.txTitle2);
        txTitle3 = findViewById(R.id.txTitle3);
        txTitle5 = findViewById(R.id.txTitle5);
        txTitle6 = findViewById(R.id.txTitle6);
        txTitle4 = findViewById(R.id.txTitle4);
        txTitle7 = findViewById(R.id.txTitle7);
        txTitle8 = findViewById(R.id.txTitle8);
        txTitle9 = findViewById(R.id.txTitle9);

        txDesignation = findViewById(R.id.txDesignation);
        txEmail = findViewById(R.id.txEmail);
        txNumber = findViewById(R.id.txNumber);

        imgBtnDown = findViewById(R.id.imgBtnDown);
        imgBtnUp = findViewById(R.id.imgBtnUp);

        imgBtnUp2 = findViewById(R.id.imgBtnUp2);
        imgBtnDown2 = findViewById(R.id.imgBtnDown2);

        imgBtnUp3 = findViewById(R.id.imgBtnUp3);
        imgBtnDown3 = findViewById(R.id.imgBtnDown3);

        imgBtnUp4 = findViewById(R.id.imgBtnUp4);
        imgBtnDown4 = findViewById(R.id.imgBtnDown4);

        imgBtnUp5 = findViewById(R.id.imgBtnUp5);
        imgBtnDown5 = findViewById(R.id.imgBtnDown5);

        imgBtnUp6 = findViewById(R.id.imgBtnUp6);
        imgBtnDown6 = findViewById(R.id.imgBtnDown6);

        imgBtnUp8 = findViewById(R.id.imgBtnUp8);
        imgBtnDown8 = findViewById(R.id.imgBtnDown8);

        imgBtnUp9 = findViewById(R.id.imgBtnUp9);
        imgBtnDown9 = findViewById(R.id.imgBtnDown9);

        imgBtnUp10 = findViewById(R.id.imgBtnUp10);
        imgBtnDown10 = findViewById(R.id.imgBtnDown10);

        cardPersonDetailsExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Data Visibilty2", "--------------");

                if(statusPersonal){

                    Log.e("Data Visibilty", "--------------");

                    txTitle1.setVisibility(View.VISIBLE);
                    llMajorLayout1.setVisibility(View.VISIBLE);
                    imgBtnDown.setVisibility(View.GONE);
                    imgBtnUp.setVisibility(View.VISIBLE);
                    txDesignation.setVisibility(View.GONE);
                    txEmail.setVisibility(View.GONE);
                    txNumber.setVisibility(View.GONE);
                    Log.e("Address Status Value1 :", String.valueOf(statusAddress));
                    if (!statusAddress) {
                        Log.e("Address Status Value1 :", String.valueOf(statusAddress));

                        txTitle2.setVisibility(View.VISIBLE);
                        llMajorLayout.setVisibility(View.GONE);
                        imgBtnDown2.setVisibility(View.VISIBLE);
                        imgBtnUp2.setVisibility(View.GONE);
                        statusAddress=true;
                    }

                    Log.e("Address Status Value1 :", String.valueOf(statusAddress));
                    statusPersonal=false;

                } else if (statusPersonal == false) {

                    txTitle1.setVisibility(View.VISIBLE);
                    llMajorLayout1.setVisibility(View.GONE);
                    imgBtnDown.setVisibility(View.VISIBLE);
                    imgBtnUp.setVisibility(View.GONE);
                    txDesignation.setVisibility(View.VISIBLE);
                    txEmail.setVisibility(View.VISIBLE);
                    txNumber.setVisibility(View.VISIBLE);
                    statusPersonal=true;
                }
            }
        });

        cardAddressDetailsExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(statusAddress){

                    Log.e("Data Visibilty", "--------------");
                    txTitle2.setVisibility(View.VISIBLE);
                    llMajorLayout.setVisibility(View.VISIBLE);
                    imgBtnDown2.setVisibility(View.GONE);
                    imgBtnUp2.setVisibility(View.VISIBLE);
                    txDesignation.setVisibility(View.GONE);
                    txEmail.setVisibility(View.GONE);
                    txNumber.setVisibility(View.GONE);
                    if (!statusPersonal) {
                        Log.e("Address Status Value1 :", String.valueOf(statusAddress));

                        txTitle1.setVisibility(View.VISIBLE);
                        llMajorLayout1.setVisibility(View.GONE);
                        imgBtnDown.setVisibility(View.VISIBLE);
                        imgBtnUp.setVisibility(View.GONE);
                        statusPersonal=true;
                    }
                    statusAddress=false;

                } else if (statusAddress == false) {

                    txTitle2.setVisibility(View.VISIBLE);
                    llMajorLayout.setVisibility(View.GONE);
                    imgBtnDown2.setVisibility(View.VISIBLE);
                    imgBtnUp2.setVisibility(View.GONE);
                    txDesignation.setVisibility(View.VISIBLE);
                    txEmail.setVisibility(View.VISIBLE);
                    txNumber.setVisibility(View.VISIBLE);
                    statusAddress=true;
                }
            }
        });

        cardEmergencyDetailsExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(statusEmergency){

                    Log.e("Data Visibilty", "--------------");
                    txTitle3.setVisibility(View.VISIBLE);
                    llMajorLayout2.setVisibility(View.VISIBLE);
                    imgBtnDown3.setVisibility(View.GONE);
                    imgBtnUp3.setVisibility(View.VISIBLE);
                    txDesignation.setVisibility(View.GONE);
                    txEmail.setVisibility(View.GONE);
                    txNumber.setVisibility(View.GONE);
                    if (!statusAdditional) {
                        Log.e("Address Status Value1 :", String.valueOf(statusAddress));

                        txTitle5.setVisibility(View.VISIBLE);
                        llMajorLayout3.setVisibility(View.GONE);
                        imgBtnDown4.setVisibility(View.VISIBLE);
                        imgBtnUp4.setVisibility(View.GONE);
                        statusAdditional=true;
                    }

                    if (!statusHealth) {

                        Log.e("Address Status Value1 :", String.valueOf(statusHealth));

                        txTitle6.setVisibility(View.VISIBLE);
                        llMajorLayout4.setVisibility(View.GONE);
                        imgBtnDown5.setVisibility(View.VISIBLE);
                        imgBtnUp5.setVisibility(View.GONE);
                        statusHealth=true;
                    }
                    statusEmergency=false;

                } else if (statusEmergency == false) {

                    txTitle3.setVisibility(View.VISIBLE);
                    llMajorLayout2.setVisibility(View.GONE);
                    imgBtnDown3.setVisibility(View.VISIBLE);
                    imgBtnUp3.setVisibility(View.GONE);
                    txDesignation.setVisibility(View.VISIBLE);
                    txEmail.setVisibility(View.VISIBLE);
                    txNumber.setVisibility(View.VISIBLE);
                    statusEmergency=true;
                }
            }
        });

        cardAdditionalDetailsExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(statusAdditional){

                    Log.e("Data Visibilty", "--------------");
                    txTitle5.setVisibility(View.VISIBLE);
                    llMajorLayout3.setVisibility(View.VISIBLE);
                    imgBtnDown4.setVisibility(View.GONE);
                    imgBtnUp4.setVisibility(View.VISIBLE);
                    txDesignation.setVisibility(View.GONE);
                    txEmail.setVisibility(View.GONE);
                    txNumber.setVisibility(View.GONE);
                    if (!statusEmergency) {
                        Log.e("Address Status Value1 :", String.valueOf(statusEmergency));

                        txTitle3.setVisibility(View.VISIBLE);
                        llMajorLayout2.setVisibility(View.GONE);
                        imgBtnDown3.setVisibility(View.VISIBLE);
                        imgBtnUp3.setVisibility(View.GONE);
                        statusEmergency=true;
                    }

                    if (!statusHealth) {

                        Log.e("Address Status Value1 :", String.valueOf(statusHealth));

                        txTitle6.setVisibility(View.VISIBLE);
                        llMajorLayout4.setVisibility(View.GONE);
                        imgBtnDown5.setVisibility(View.VISIBLE);
                        imgBtnUp5.setVisibility(View.GONE);
                        statusHealth=true;
                    }
                    statusAdditional=false;

                } else if (statusAdditional == false) {

                    txTitle5.setVisibility(View.VISIBLE);
                    llMajorLayout3.setVisibility(View.GONE);
                    imgBtnDown4.setVisibility(View.VISIBLE);
                    imgBtnUp4.setVisibility(View.GONE);
                    txDesignation.setVisibility(View.VISIBLE);
                    txEmail.setVisibility(View.VISIBLE);
                    txNumber.setVisibility(View.VISIBLE);
                    statusAdditional=true;
                }
            }
        });

        cardHealthDetailsExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(statusHealth){

                    Log.e("Data Visibilty", "--------------");
                    txTitle6.setVisibility(View.VISIBLE);
                    llMajorLayout4.setVisibility(View.VISIBLE);
                    imgBtnDown5.setVisibility(View.GONE);
                    imgBtnUp5.setVisibility(View.VISIBLE);
                    txDesignation.setVisibility(View.GONE);
                    txEmail.setVisibility(View.GONE);
                    txNumber.setVisibility(View.GONE);
                    if (!statusEmergency) {
                        Log.e("Address Status Value1 :", String.valueOf(statusEmergency));

                        txTitle3.setVisibility(View.VISIBLE);
                        llMajorLayout2.setVisibility(View.GONE);
                        imgBtnDown3.setVisibility(View.VISIBLE);
                        imgBtnUp3.setVisibility(View.GONE);
                        statusEmergency=true;
                    }

                    if (!statusAdditional) {

                        Log.e("Address Status Value1 :", String.valueOf(statusAdditional));

                        txTitle5.setVisibility(View.VISIBLE);
                        llMajorLayout3.setVisibility(View.GONE);
                        imgBtnDown4.setVisibility(View.VISIBLE);
                        imgBtnUp4.setVisibility(View.GONE);
                        statusAdditional=true;
                    }
                    statusHealth=false;

                } else if (statusHealth == false) {

                    txTitle6.setVisibility(View.VISIBLE);
                    llMajorLayout4.setVisibility(View.GONE);
                    imgBtnDown5.setVisibility(View.VISIBLE);
                    imgBtnUp5.setVisibility(View.GONE);
                    txDesignation.setVisibility(View.VISIBLE);
                    txEmail.setVisibility(View.VISIBLE);
                    txNumber.setVisibility(View.VISIBLE);
                    statusHealth=true;
                }
            }
        });

        cardParentDetailsExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Data Visibilty2", "--------------");

                if(statusParent){

                    Log.e("Data Visibilty", "--------------");

                    txTitle4.setVisibility(View.VISIBLE);
                    llMajorLayout5.setVisibility(View.VISIBLE);
                    imgBtnDown6.setVisibility(View.GONE);
                    imgBtnUp6.setVisibility(View.VISIBLE);
                    txDesignation.setVisibility(View.GONE);
                    txEmail.setVisibility(View.GONE);
                    txNumber.setVisibility(View.GONE);
                    Log.e("Address Status Value1 :", String.valueOf(statusParent));
                    if (!statusSpouse) {
                        Log.e("Address Status Value1 :", String.valueOf(statusSpouse));

                        txTitle7.setVisibility(View.VISIBLE);
                        llMajorLayout6.setVisibility(View.GONE);
                        imgBtnDown8.setVisibility(View.VISIBLE);
                        imgBtnUp8.setVisibility(View.GONE);
                        statusSpouse=true;
                    }

                    Log.e("Address Status Value1 :", String.valueOf(statusParent));
                    statusParent=false;

                } else if (statusParent == false) {

                    txTitle4.setVisibility(View.VISIBLE);
                    llMajorLayout5.setVisibility(View.GONE);
                    imgBtnDown6.setVisibility(View.VISIBLE);
                    imgBtnUp6.setVisibility(View.GONE);
                    txDesignation.setVisibility(View.VISIBLE);
                    txEmail.setVisibility(View.VISIBLE);
                    txNumber.setVisibility(View.VISIBLE);
                    statusParent=true;
                }
            }
        });

        cardSpouseDetailsExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(statusSpouse){

                    Log.e("Data Visibilty", "--------------");
                    txTitle7.setVisibility(View.VISIBLE);
                    llMajorLayout6.setVisibility(View.VISIBLE);
                    imgBtnDown8.setVisibility(View.GONE);
                    imgBtnUp8.setVisibility(View.VISIBLE);
                    txDesignation.setVisibility(View.GONE);
                    txEmail.setVisibility(View.GONE);
                    txNumber.setVisibility(View.GONE);
                    if (!statusParent) {
                        Log.e("Address Status Value1 :", String.valueOf(statusParent));

                        txTitle4.setVisibility(View.VISIBLE);
                        llMajorLayout5.setVisibility(View.GONE);
                        imgBtnDown6.setVisibility(View.VISIBLE);
                        imgBtnUp6.setVisibility(View.GONE);
                        statusParent=true;
                    }
                    statusSpouse=false;

                } else if (statusSpouse == false) {

                    txTitle7.setVisibility(View.VISIBLE);
                    llMajorLayout6.setVisibility(View.GONE);
                    imgBtnDown8.setVisibility(View.VISIBLE);
                    imgBtnUp8.setVisibility(View.GONE);
                    txDesignation.setVisibility(View.VISIBLE);
                    txEmail.setVisibility(View.VISIBLE);
                    txNumber.setVisibility(View.VISIBLE);
                    statusSpouse=true;
                }
            }
        });

        cardExperienceDetailsExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Data Visibilty2", "--------------");

                if(statusExp){

                    Log.e("Data Visibilty", "--------------");

                    txTitle8.setVisibility(View.VISIBLE);
                    rcvExperienceInfo.setVisibility(View.VISIBLE);
                    imgBtnDown9.setVisibility(View.GONE);
                    imgBtnUp9.setVisibility(View.VISIBLE);
                    txDesignation.setVisibility(View.GONE);
                    txEmail.setVisibility(View.GONE);
                    txNumber.setVisibility(View.GONE);
                    Log.e("Address Status Value1 :", String.valueOf(statusExp));
                    if (!statusDoc) {
                        Log.e("Address Status Value1 :", String.valueOf(statusDoc));

                        txTitle9.setVisibility(View.VISIBLE);
                        rcvDocumentInfo.setVisibility(View.GONE);
                        imgBtnDown10.setVisibility(View.VISIBLE);
                        imgBtnUp10.setVisibility(View.GONE);
                        statusDoc=true;
                    }

                    Log.e("Address Status Value1 :", String.valueOf(statusAddress));
                    statusExp=false;

                } else if (statusExp == false) {

                    txTitle8.setVisibility(View.VISIBLE);
                    rcvExperienceInfo.setVisibility(View.GONE);
                    imgBtnDown9.setVisibility(View.VISIBLE);
                    imgBtnUp9.setVisibility(View.GONE);
                    txDesignation.setVisibility(View.VISIBLE);
                    txEmail.setVisibility(View.VISIBLE);
                    txNumber.setVisibility(View.VISIBLE);
                    statusExp=true;
                }
            }
        });

        cardDocumentDetailsExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(statusDoc){

                    Log.e("Data Visibilty", "--------------");
                    txTitle9.setVisibility(View.VISIBLE);
                    rcvDocumentInfo.setVisibility(View.VISIBLE);
                    imgBtnDown10.setVisibility(View.GONE);
                    imgBtnUp10.setVisibility(View.VISIBLE);
                    txDesignation.setVisibility(View.GONE);
                    txEmail.setVisibility(View.GONE);
                    txNumber.setVisibility(View.GONE);
                    if (!statusExp) {
                        Log.e("Address Status Value1 :", String.valueOf(statusExp));

                        txTitle8.setVisibility(View.VISIBLE);
                        rcvExperienceInfo.setVisibility(View.GONE);
                        imgBtnDown9.setVisibility(View.VISIBLE);
                        imgBtnUp9.setVisibility(View.GONE);
                        statusExp=true;
                    }
                    statusDoc=false;

                } else if (statusDoc == false) {

                    txTitle9.setVisibility(View.VISIBLE);
                    rcvDocumentInfo.setVisibility(View.GONE);
                    imgBtnDown10.setVisibility(View.VISIBLE);
                    imgBtnUp10.setVisibility(View.GONE);
                    txDesignation.setVisibility(View.VISIBLE);
                    txEmail.setVisibility(View.VISIBLE);
                    txNumber.setVisibility(View.VISIBLE);
                    statusDoc=true;
                }
            }
        });

        btnPersonalDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnPersonalDetail.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnPersonalDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));

                btnAdditionalDetail.setTextColor(getResources().getColor(R.color.white));
                btnAdditionalDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                btnParentDetail.setTextColor(getResources().getColor(R.color.white));
                btnParentDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                btnExperienceDetail.setTextColor(getResources().getColor(R.color.white));
                btnExperienceDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                scrlView1.setVisibility(View.GONE);
                scrlView.setVisibility(View.VISIBLE);
                scrlView2.setVisibility(View.GONE);
                scrlView3.setVisibility(View.GONE);
            }
        });

        btnAdditionalDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnPersonalDetail.setTextColor(getResources().getColor(R.color.white));
                btnPersonalDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                btnAdditionalDetail.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnAdditionalDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));

                btnParentDetail.setTextColor(getResources().getColor(R.color.white));
                btnParentDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                btnExperienceDetail.setTextColor(getResources().getColor(R.color.white));
                btnExperienceDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                scrlView1.setVisibility(View.VISIBLE);
                scrlView.setVisibility(View.GONE);
                scrlView2.setVisibility(View.GONE);
                scrlView3.setVisibility(View.GONE);
            }
        });

        btnParentDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnPersonalDetail.setTextColor(getResources().getColor(R.color.white));
                btnPersonalDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                btnAdditionalDetail.setTextColor(getResources().getColor(R.color.white));
                btnAdditionalDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                btnParentDetail.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnParentDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));

                btnExperienceDetail.setTextColor(getResources().getColor(R.color.white));
                btnExperienceDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                scrlView1.setVisibility(View.GONE);
                scrlView.setVisibility(View.GONE);
                scrlView2.setVisibility(View.VISIBLE);
                scrlView3.setVisibility(View.GONE);
            }
        });

        btnExperienceDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnPersonalDetail.setTextColor(getResources().getColor(R.color.white));
                btnPersonalDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                btnAdditionalDetail.setTextColor(getResources().getColor(R.color.white));
                btnAdditionalDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                btnParentDetail.setTextColor(getResources().getColor(R.color.white));
                btnParentDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                btnExperienceDetail.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnExperienceDetail.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));

                scrlView1.setVisibility(View.GONE);
                scrlView.setVisibility(View.GONE);
                scrlView2.setVisibility(View.GONE);
                scrlView3.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
