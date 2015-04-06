package fr.xebia.jmartinez.installed;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AppsActivity extends ActionBarActivity implements TextWatcher {

    private AppsAdapter adapter;
    private AppsPresenter appsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        appsPresenter = new AppsPresenter(this, new AppsProvider(this));
        ListView listView = (ListView) findViewById(R.id.list);
        EditText filterView = (EditText) findViewById(R.id.filter);
        adapter = new AppsAdapter();
        listView.setAdapter(adapter);
        filterView.addTextChangedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        appsPresenter.showAllApps();
    }

    public void showApps(List<App> installedApps) {
        adapter.setApps(installedApps);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        appsPresenter.filterApps(charSequence.toString());
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        // Do nothing
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // Do nothing
    }

    private class AppsAdapter extends BaseAdapter {

        private List<App> apps = new ArrayList<>();

        @Override
        public int getCount() {
            return apps.size();
        }

        @Override
        public App getItem(int position) {
            return apps.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).hashCode();
        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        @Override
        public TextView getView(int position, View convertView, ViewGroup viewGroup) {
            final TextView view;
            if (convertView == null) {
                view = new TextView(AppsActivity.this);
                view.setTextSize(20f);
                view.setPadding(30, 30, 30, 30);
            } else {
                view = (TextView) convertView;
            }
            view.setText(apps.get(position).title);
            return view;
        }

        public void setApps(List<App> installedApps) {
            apps.clear();
            apps.addAll(installedApps);
            notifyDataSetChanged();
        }
    }
}
