package com.palmaplus.nagrand.api_demo.fragments.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.api_demo.base.BaseFragment;
import com.palmaplus.nagrand.api_demo.utils.Constants;
import com.palmaplus.nagrand.view.MapView;

/**
 * Created by jian.feng on 2017/6/2.
 */

public class BaseMapFragment extends BaseFragment {

    protected MapView mapView;

    @Override
    public View provideView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_map, container, false);
    }

    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.getMap().startWithMapID(Constants.mapId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.drop();
    }
}
