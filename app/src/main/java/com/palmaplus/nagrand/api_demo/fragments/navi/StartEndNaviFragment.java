package com.palmaplus.nagrand.api_demo.fragments.navi;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.api_demo.fragments.map.BaseMapFragment;
import com.palmaplus.nagrand.core.Types;
import com.palmaplus.nagrand.data.FeatureCollection;
import com.palmaplus.nagrand.easyapi.LBSManager;
import com.palmaplus.nagrand.easyapi.Map;
import com.palmaplus.nagrand.geos.Coordinate;
import com.palmaplus.nagrand.navigate.NavigateManager;
import com.palmaplus.nagrand.view.MapView;
import com.palmaplus.nagrand.view.gestures.OnSingleTapListener;
import com.palmaplus.nagrand.view.overlay.ImageOverlay;

/**
 * Created by jian.feng on 2017/6/6.
 */

public class StartEndNaviFragment extends BaseMapFragment {
    protected ImageOverlay startOverlay;
    protected ImageOverlay endOverlay;
    private int state = 0;

    protected LBSManager lbsManager;

    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);
        final Map map = mapView.getMap();
        lbsManager = new LBSManager(map, getContext(), "");
        lbsManager.addOnNavigationListener(new LBSManager.OnNavigationListener() {
            @Override
            public void onNavigateComplete(NavigateManager.NavigateState navigateState, FeatureCollection featureCollection) {
                state = 0;
            }

            @Override
            public void onNavigateError(NavigateManager.NavigateState navigateState) {
                state = 0;
            }
        });

        ViewGroup container = (ViewGroup) view.findViewById(R.id.overlay_container);
        map.setOverlayContainer(container);
        startOverlay = new ImageOverlay(this.getContext());
        startOverlay.setBackgroundResource(R.mipmap.ic_map_pin_start);
        startOverlay.init(new double[] { 0, 0 });
        map.addOverlay(startOverlay);
        endOverlay = new ImageOverlay(this.getContext());
        endOverlay.setBackgroundResource(R.mipmap.ic_map_pin_end);
        endOverlay.init(new double[] { 0, 0 });
        map.addOverlay(endOverlay);

        mapView.setOnSingleTapListener(new OnSingleTapListener() {
            @Override
            public void onSingleTap(MapView mapView, float v, float v1) {
                Types.Point point = mapView.converToWorldCoordinate(v, v1);
                switch (state) {
                    case 0:
                        lbsManager.getNaviLayer().clearFeatures();
                        startOverlay.init(new double[] { point.x, point.y });
                        startOverlay.mFloorId = map.getFloorId();
                        endOverlay.mFloorId = 0;
                        endOverlay.setVisibility(View.GONE);
                        state++;
                        break;
                    case 1:
                        endOverlay.setVisibility(View.VISIBLE);
                        endOverlay.init(new double[] { point.x, point.y });
                        endOverlay.mFloorId = map.getFloorId();
                        lbsManager.navigateFromPoint(
                            new Coordinate(startOverlay.getGeoCoordinate()[0], startOverlay.getGeoCoordinate()[1]),
                            startOverlay.mFloorId,
                            new Coordinate(endOverlay.getGeoCoordinate()[0], endOverlay.getGeoCoordinate()[1]),
                            endOverlay.mFloorId,
                            endOverlay.mFloorId
                        );
                        state++;
                        break;
                }
                mapView.getOverlayController().refresh();
            }
        });
    }

    @Override
    public void onDestroyView() {
        lbsManager.close();
        super.onDestroyView();
    }
}
