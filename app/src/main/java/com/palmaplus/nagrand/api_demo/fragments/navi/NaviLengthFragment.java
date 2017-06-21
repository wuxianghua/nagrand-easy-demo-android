package com.palmaplus.nagrand.api_demo.fragments.navi;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.data.FeatureCollection;
import com.palmaplus.nagrand.easyapi.LBSManager;
import com.palmaplus.nagrand.easyapi.Map;
import com.palmaplus.nagrand.navigate.NavigateManager;

/**
 * Created by jian.feng on 2017/6/6.
 */

public class NaviLengthFragment extends StartEndNaviFragment {
    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);

        final RelativeLayout controlContainer = (RelativeLayout) view.findViewById(R.id.control_container);
        Map map = mapView.getMap();
        map.setDefaultWidgetContrainer(controlContainer);
        map.getFloorLayout().setVisibility(View.GONE);
        map.getCompass().setVisibility(View.GONE);
        map.getSwitch().setVisibility(View.GONE);

        final TextView lengthView = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        controlContainer.addView(lengthView, layoutParams);

        lbsManager.addOnNavigationListener(new LBSManager.OnNavigationListener() {
            @Override
            public void onNavigateComplete(NavigateManager.NavigateState navigateState, FeatureCollection featureCollection) {
                lengthView.post(new Runnable() {
                    @Override
                    public void run() {
                        lengthView.setText(String.format("导航线总长度: %.2f", lbsManager.navigateManager().getTotalLineLength()));
                    }
                });
            }

            @Override
            public void onNavigateError(NavigateManager.NavigateState navigateState) {
            }
        });
    }
}
