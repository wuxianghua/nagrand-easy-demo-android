package com.palmaplus.nagrand.api_demo.fragments.navi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.data.FeatureCollection;
import com.palmaplus.nagrand.easyapi.LBSManager;
import com.palmaplus.nagrand.navigate.NavigateManager;
import com.palmaplus.nagrand.navigate.StepInfo;

/**
 * Created by jian.feng on 2017/6/6.
 */

public class NaviTextFragment extends StartEndNaviFragment {
    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);

        final RelativeLayout controlContainer = (RelativeLayout) view.findViewById(R.id.control_container);
        final ListView listView = new ListView(getContext());
        listView.setVisibility(View.GONE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        controlContainer.addView(listView, layoutParams);

        final ArrayAdapter<StepInfo> arrayAdapter = new ArrayAdapter<StepInfo>(getContext(), 0) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView view = (TextView) convertView;
                if (view == null) {
                    view = new TextView(getContext());
                    StepInfo item = getItem(position);
                    view.setText(String.format("直行%.2f米，%s", item.mLength, item.mActionName));
                }
                return view;
            }
        };
        listView.setAdapter(arrayAdapter);

        lbsManager.addOnNavigationListener(new LBSManager.OnNavigationListener() {
            @Override
            public void onNavigateComplete(NavigateManager.NavigateState navigateState, FeatureCollection featureCollection) {
                final StepInfo[] allStepInfo = lbsManager.navigateManager().getAllStepInfo();
                if (allStepInfo.length > 0) {
                    listView.post(new Runnable() {
                        @Override
                        public void run() {
                            listView.setVisibility(View.VISIBLE);
                            arrayAdapter.clear();
                            arrayAdapter.addAll(allStepInfo);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public void onNavigateError(NavigateManager.NavigateState navigateState) {
            }
        });
    }
}
