package com.palmaplus.nagrand.api_demo.fragments.overlay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;

import com.palmaplus.nagrand.api_demo.R;
import com.palmaplus.nagrand.api_demo.fragments.map.BaseMapFragment;
import com.palmaplus.nagrand.core.Types;
import com.palmaplus.nagrand.easyapi.Map;
import com.palmaplus.nagrand.view.MapView;
import com.palmaplus.nagrand.view.gestures.OnSingleTapListener;
import com.palmaplus.nagrand.view.overlay.ImageOverlay;

/**
 * Created by jian.feng on 2017/6/5.
 */

public class ImageOverlayFragment extends BaseMapFragment {
    @Override
    public void onInitFragment(Bundle savedInstanceState) {
        super.onInitFragment(savedInstanceState);
        final Map map = mapView.getMap();

        ViewGroup container = (ViewGroup) view.findViewById(R.id.overlay_container);
        map.setOverlayContainer(container);

        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_map_pin_end);
        map.mapView().setOnSingleTapListener(new OnSingleTapListener() {
            @Override
            public void onSingleTap(MapView mapView, float v, float v1) {
                Types.Point point = mapView.converToWorldCoordinate(v, v1);
                ImageOverlay imageOverlay = new ImageOverlay(getContext());
                imageOverlay.setImageBitmap(bitmap);
                imageOverlay.mFloorId = map.getFloorId();
                imageOverlay.init(new double[] { point.x, point.y });
                map.addOverlay(imageOverlay);
            }
        });
    }
}
