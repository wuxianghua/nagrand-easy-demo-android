package com.palmaplus.nagrand.api_demo.fragments.control;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.api_demo.fragments.map.BaseMapFragment;
import com.palmaplus.nagrand.easyapi.Map;

/**
 * Created by jian.feng on 2017/6/5.
 */

public class SwitchControlFragment extends BaseMapFragment {

    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);
        final Map map = mapView.getMap();

        RelativeLayout viewById = (RelativeLayout) view.findViewById(R.id.control_container);
        map.setDefaultWidgetContrainer(viewById);

        map.getScale().setVisibility(View.GONE);
        map.getFloorLayout().setVisibility(View.GONE);
        map.getCompass().setVisibility(View.GONE);
    }
}
