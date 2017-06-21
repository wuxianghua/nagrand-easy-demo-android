package com.palmaplus.nagrand.api_demo.fragments.map;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.palmaplus.nagrand.api_demo.R;

/**
 * Created by jian.feng on 2017/6/2.
 */

public class MapPositionFragment extends BaseMapFragment {

    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);
        mapView.initRotate(30);
        mapView.initRatio(0.6f);
        mapView.setMaxScale(500);
        mapView.setMinScale(3000);

        RelativeLayout controlLayout = (RelativeLayout) view.findViewById(R.id.control_container);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.map_position_message, controlLayout);
        ((TextView) inflate.findViewById(R.id.init_rotate)).setText("30度");
        ((TextView) inflate.findViewById(R.id.init_ratio)).setText("60%");
        ((TextView) inflate.findViewById(R.id.max_scale)).setText("1:500");
        ((TextView) inflate.findViewById(R.id.min_scale)).setText("1:3000");
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//        controlLayout.addView(controlLayout, layoutParams);

    }
}
