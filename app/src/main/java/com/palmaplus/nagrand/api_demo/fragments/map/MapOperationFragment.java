package com.palmaplus.nagrand.api_demo.fragments.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.view.MapOptions;
import com.palmaplus.nagrand.view.MapView;
import com.palmaplus.nagrand.view.gestures.OnSingleTapListener;

/**
 * Created by jian.feng on 2017/6/2.
 */

public class MapOperationFragment extends BaseMapFragment {

    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);

        final MapOptions mapOptions = new MapOptions();

        RelativeLayout controlLayout = (RelativeLayout) view.findViewById(R.id.control_container);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.map_operation, controlLayout);
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getText().toString()) {
                    case "旋转":
                        mapOptions.setRotateEnabled(isChecked);
                        break;
                    case "俯仰":
                        mapOptions.setSkewEnabled(isChecked);
                        break;
                    case "移动":
                        mapOptions.setMoveEnabled(isChecked);
                        break;
                    case "缩放":
                        mapOptions.setZoomEnabled(isChecked);
                        break;
                    case "点击":
                        mapOptions.setSigleTapEnabled(isChecked);
                        break;
                }
                mapView.setMapOptions(mapOptions);
            }
        };
        ((Switch) inflate.findViewById(R.id.switch1)).setOnCheckedChangeListener(onCheckedChangeListener);
        ((Switch) inflate.findViewById(R.id.switch2)).setOnCheckedChangeListener(onCheckedChangeListener);
        ((Switch) inflate.findViewById(R.id.switch3)).setOnCheckedChangeListener(onCheckedChangeListener);
        ((Switch) inflate.findViewById(R.id.switch4)).setOnCheckedChangeListener(onCheckedChangeListener);
        ((Switch) inflate.findViewById(R.id.switch5)).setOnCheckedChangeListener(onCheckedChangeListener);

        mapView.setOnSingleTapListener(new OnSingleTapListener() {
            @Override
            public void onSingleTap(MapView mapView, float v, float v1) {
                Toast.makeText(getContext(), "点击地图", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
