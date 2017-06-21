package com.palmaplus.nagrand.api_demo.fragments.search;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.api_demo.fragments.map.BaseMapFragment;
import com.palmaplus.nagrand.core.Value;
import com.palmaplus.nagrand.data.Feature;
import com.palmaplus.nagrand.easyapi.Map;
import com.palmaplus.nagrand.geos.Coordinate;
import com.palmaplus.nagrand.tools.ViewUtils;
import com.palmaplus.nagrand.view.overlay.ImageOverlay;

import java.util.List;

/**
 * Created by jian.feng on 2017/6/5.
 */

public class CategorySearchFragment extends BaseMapFragment {

    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);
        final Map map = mapView.getMap();

        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.control_container);
        Button button = new Button(getContext());
        button.setBackgroundResource(R.mipmap.ic_map_pin_elevator);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams( ViewUtils.dip2px(getContext(), 40),  ViewUtils.dip2px(getContext(), 40));
        layoutParams.setMargins(ViewUtils.dip2px(getContext(), 10), 0, 0, ViewUtils.dip2px(getContext(), 10));
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        relativeLayout.addView(button, layoutParams);

        ViewGroup container = (ViewGroup) view.findViewById(R.id.overlay_container);
        map.setOverlayContainer(container);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_map_pin_elevator);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.removeAllOverlay();
                List<Feature> features = map.mapView().searchFeature("Facility", "category", new Value(24091000l));
                for (Feature feature : features) {
                    Coordinate centroid = feature.getCentroid();
                    ImageOverlay imageOverlay = new ImageOverlay(getContext());
                    imageOverlay.mFloorId = map.getFloorId();
                    imageOverlay.setImageBitmap(bitmap);
                    imageOverlay.init(new double[] { centroid.getX(), centroid.getY() });
                    map.addOverlay(imageOverlay);
                }
            }
        });
    }
}
