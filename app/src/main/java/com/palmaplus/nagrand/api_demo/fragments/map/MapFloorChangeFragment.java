package com.palmaplus.nagrand.api_demo.fragments.map;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.easyapi.Map;

/**
 * Created by jian.feng on 2017/6/2.
 */

public class MapFloorChangeFragment extends BaseMapFragment {
    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);
        Map map = mapView.getMap();
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.control_container);
        map.setDefaultWidgetContrainer(relativeLayout);
        map.getCompass().setVisibility(View.GONE);
        map.getScale().setVisibility(View.INVISIBLE);
        map.getSwitch().setVisibility(View.GONE);
    }
}
