package com.palmaplus.nagrand.api_demo.fragments.dynamic;

import android.os.Bundle;

import com.palmaplus.nagrand.data.DataElement;
import com.palmaplus.nagrand.data.FeatureCollection;
import com.palmaplus.nagrand.easyapi.LBSManager;
import com.palmaplus.nagrand.geos.Coordinate;
import com.palmaplus.nagrand.geos.GeometryFactory;
import com.palmaplus.nagrand.geos.Point;
import com.palmaplus.nagrand.navigate.CoordinateInfo;
import com.palmaplus.nagrand.navigate.DynamicNavigateWrapper;
import com.palmaplus.nagrand.navigate.NavigateManager;
import com.palmaplus.nagrand.position.Filter;
import com.palmaplus.nagrand.position.Location;

import java.util.Random;

/**
 * Created by jian.feng on 2017/6/7.
 */

public class DynamicNaviRecalcFragment extends DynamicnaviFragment {

    private volatile boolean once = true;

    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);

        lbsManager.addOnNavigationListener(new LBSManager.OnNavigationListener() {
            @Override
            public void onNavigateComplete(NavigateManager.NavigateState navigateState, FeatureCollection featureCollection) {
                once = true;
//                lbsManager.startUpdatingLocation();
            }

            @Override
            public void onNavigateError(NavigateManager.NavigateState navigateState) {
            }
        });

        lbsManager.addOnDynamicListener(new LBSManager.OnDynamicListener() {
            @Override
            public void onDynamicChange(DynamicNavigateWrapper dynamicNavigateWrapper) {
            }

            @Override
            public void onLeaveNaviLine(Location location, float v) {
                if (once) {
//                    lbsManager.stopUpdatingLocation();
                    positionManager.reset();
                    once = false;
                    Point point = location.getPoint();
                    Long aLong = Location.floorId.get(location.getProperties());
                    double[] geoCoordinate = endOverlay.getGeoCoordinate();
                    lbsManager.navigateFromPoint(point.getCoordinate(), aLong, new Coordinate(geoCoordinate[0], geoCoordinate[1]), endOverlay.getFloorId(), aLong);
                }
            }
        });
        lbsManager.setCorrectionRadius(5);
        positionManager.setFilter(new Filter() {
            @Override
            public boolean filter(Location location, Location location1) {
                CoordinateInfo[] coordinateInfos = positionManager.getCoordinateInfos();
                int coordinateInfoIndex = positionManager.getCoordinateInfoIndex();
                if (coordinateInfos != null && ++coordinateInfoIndex == 4 && coordinateInfoIndex < coordinateInfos.length) {
                    coordinateInfos[coordinateInfoIndex].x += 15 + new Random().nextInt(15);
                    coordinateInfos[coordinateInfoIndex].y += 15 + new Random().nextInt(15);
                }
                return false;
            }
        });
    }
}
