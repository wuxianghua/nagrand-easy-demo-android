package com.palmaplus.nagrand.api_demo.fragments.control;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.api_demo.fragments.map.BaseMapFragment;
import com.palmaplus.nagrand.data.PlanarGraph;
import com.palmaplus.nagrand.easyapi.Map;
import com.palmaplus.nagrand.view.MapView;

/**
 * Created by jian.feng on 2017/6/5.
 */

public class FloorControlFragment extends BaseMapFragment {
    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);
        final Map map = mapView.getMap();

        RelativeLayout viewById = (RelativeLayout) view.findViewById(R.id.control_container);
        map.setDefaultWidgetContrainer(viewById);

        map.getScale().setVisibility(View.INVISIBLE);
        map.getSwitch().setVisibility(View.GONE);
        map.getCompass().setVisibility(View.GONE);

        map.addOnChangePlanarGraph(new MapView.OnChangePlanarGraph() {
            @Override
            public void onChangePlanarGraph(PlanarGraph planarGraph, PlanarGraph planarGraph1, long l, long l1) {
                Toast.makeText(getContext(), String.format("from %s -- to %s", l, l1), Toast.LENGTH_LONG).show();
            }
        });
    }
}
