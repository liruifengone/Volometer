package com.hearing.volometer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.TextView;

import com.hearing.volometer.R;

import org.w3c.dom.Text;


public class MeasureFragment extends BaseFragment {
    private TextView table_11;
    private TextView tv_start;
    private TextView tv_save;
    private View view;
    //实时电压
    private float Va_rms,Vb_rms,Vc_rms;
    private float Va1_rms,Vb1_rms,Vc1_rms;
    private float Va1_ph,Vb1_ph,Vc1_ph;
    //进线电流
    private float Iain_rms,Ibin_rms,Icin_rms;
    private float Iain1_rms,Ibin1_rms,Icin1_rms;
    private float Iain1_ph,Ibin1_ph,Icin1_ph;
    //出线电流
    private float Iaout_rms,Ibout_rms,Icout_rms;
    private float Iaout1_rms,Ibout1_rms,Icout1_rms;
    private float Iaout1_ph,Ibout1_ph,Icout1_ph;
    //不对称分量
    //电压不对称分量
    private float VP_rms,VN_rms,VO_rms;
    private float VP1_rms,VN1_rms,VO1_rms;
    private float VP1_ph,VN1_ph,VO1_ph;
    //进线电流不对称分量
    private float IinP_rms,IinN_rms,IinO_rms;
    private float IinP1_rms,IinN1_rms,IinO1_rms;
    private float IinP1_ph,IinN1_ph,IinO1_ph;
    //出线电流不对称分量
    private float IoutP_rms,IoutN_rms,IoutO_rms;
    private float IoutP1_rms,IoutN1_rms,IoutO1_rms;
    private float IoutP1_ph,IoutN1_ph,IoutO1_ph;

    //功率
    private float Pa,Pb,Pc;
    private float Qa,Qb,Qc;
    private float Sa,Sb,Sc;
    private float Psum,Qsum,Ssum;
    private float PFa,PFb,PFc;
    private float PFsum;
    //谐波

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_measure, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        int width  = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        float density = displayMetrics.density;

        int screenWidth = (int) (width / density);

        table_11 = (TextView)view.findViewById(R.id.table_11);
        table_11.setWidth(screenWidth/4);
        table_11.setText("hello wprld");

        return view;
    }

    private void initView(){
        tv_save = view.findViewById(R.id.tv_save);
        tv_start = view.findViewById(R.id.tv_start);

    }

    private void initListener(){
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
