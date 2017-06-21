package com.palmaplus.nagrand.api_demo;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.palmaplus.nagrand.api_demo.data.FunctionListDataProvider;
import com.palmaplus.nagrand.api_demo.model.FunctionItem;
import com.palmaplus.nagrand.api_demo.utils.FragmentUtils;
import com.palmaplus.nagrand.api_demo.widgets.adapter.GroupAdapter;
import com.palmaplus.nagrand.tools.ViewUtils;

public class MainActivity extends AppCompatActivity {

    protected TextView subTitle;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        subTitle = (TextView) findViewById(R.id.toolbar_subtitle);
        title = (TextView) findViewById(R.id.toolbar_title);

        // ListView
        ListView listView = new ListView(this);
        FunctionListDataProvider provider = new FunctionListDataProvider();
        final GroupAdapter<FunctionItem> groupAdapter = new GroupAdapter<FunctionItem>(this, provider.provideFunctionList(), R.layout.group_list_view, R.layout.item_list_view) {
            @Override
            public void bindView(FunctionItem s, View view) {
                ((TextView) view.findViewById(R.id.addexam_list_item_text)).setText(s.getName());
            }

            @Override
            public void bindGroup(FunctionItem s, View view) {
                ((TextView) view.findViewById(R.id.addexam_list_item_text)).setText(s.getName());
            }
        };
        listView.setAdapter(groupAdapter);
        final PopupWindow popupWindow = new PopupWindow(listView,  ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewUtils.dip2px(this, 150));
        popupWindow.setHeight(ViewUtils.dip2px(this, 300));
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectFunctionItem(groupAdapter.getItem(position));
                popupWindow.dismiss();
            }
        });

        // add Event Listener
        subTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(subTitle);
            }
        });

        selectFunctionItem(groupAdapter.getItem(1));
    }

    private void selectFunctionItem(FunctionItem functionItem) {
        try {
            if (functionItem.getClazz() == null) {
                return;
            }
            title.setText(functionItem.getName());
            FragmentUtils.replaceFragment(getSupportFragmentManager(), (Fragment) functionItem.getClazz().newInstance(), R.id.fragment_container, false);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
