package com.palmaplus.nagrand.api_demo.fragments.overlay;

import android.os.Bundle;
import android.view.ViewGroup;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.api_demo.fragments.map.BaseMapFragment;
import com.palmaplus.nagrand.core.Types;
import com.palmaplus.nagrand.easyapi.Map;
import com.palmaplus.nagrand.view.MapView;
import com.palmaplus.nagrand.view.gestures.OnSingleTapListener;
import com.palmaplus.nagrand.view.overlay.TextOverlay;

/**
 * Created by jian.feng on 2017/6/5.
 */

public class TextOverlayFragment extends BaseMapFragment {
    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);
        final Map map = mapView.getMap();

        ViewGroup container = (ViewGroup) view.findViewById(R.id.overlay_container);
        map.setOverlayContainer(container);
        map.mapView().setOnSingleTapListener(new OnSingleTapListener() {
            @Override
            public void onSingleTap(MapView mapView, float v, float v1) {
                Types.Point point = mapView.converToWorldCoordinate(v, v1);
                TextOverlay textOverlay = new TextOverlay(getContext());
                textOverlay.setText("Palmap+");
                textOverlay.mFloorId = map.getFloorId();
                textOverlay.init(new double[] { point.x, point.y });
                map.addOverlay(textOverlay);
            }
        });
    }
}
