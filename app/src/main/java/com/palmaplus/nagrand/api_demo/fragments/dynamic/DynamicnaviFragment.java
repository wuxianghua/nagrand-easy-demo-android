package com.palmaplus.nagrand.api_demo.fragments.dynamic;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.api_demo.fragments.navi.StartEndNaviFragment;
import com.palmaplus.nagrand.easyapi.MockPositionManager;
import com.palmaplus.nagrand.navigate.DynamicNavigationMode;
import com.palmaplus.nagrand.tools.ViewUtils;

/**
 * Created by jian.feng on 2017/6/7.
 */

public class DynamicnaviFragment extends StartEndNaviFragment {

    private final static String flowMode = "跟随模式";
    private final static String norMode = "北模式";

    protected MockPositionManager positionManager;

    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);
        RelativeLayout controlContainer = (RelativeLayout) view.findViewById(R.id.control_container);
        Button button = new Button(getContext());
        button.setText("开始定位");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lbsManager.startDynamic();
                lbsManager.startUpdatingLocation();
            }
        });
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        layoutParams.setMargins(ViewUtils.dip2px(getContext(), 10), 0, ViewUtils.dip2px(getContext(), 10), ViewUtils.dip2px(getContext(), 10));
        controlContainer.addView(button, layoutParams);

        button = new Button(getContext());
        layoutParams.setMargins(ViewUtils.dip2px(getContext(), 10), ViewUtils.dip2px(getContext(), 10), 0, 0);
        button.setText(norMode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = ((Button) v);
                if (b.getText().equals(norMode)) {
                    b.setText(flowMode);
                    lbsManager.setNavigateMode(DynamicNavigationMode.NORTH);
                } else {
                    b.setText(norMode);
                    lbsManager.setNavigateMode(DynamicNavigationMode.FOLLOW);
                }
            }
        });
        layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        controlContainer.addView(button, layoutParams);

        positionManager = new MockPositionManager();
        lbsManager.switchPostionType(positionManager);
        lbsManager.locationOverlay().setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_map_myposition));
        positionManager.attchLBSManager(lbsManager);

    }
}
