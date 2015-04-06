package fr.xebia.jmartinez.installed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppsPresenter {
    private final AppsActivity appsActivity;
    private final AppsProvider appsProvider;

    public AppsPresenter(AppsActivity appsActivity, AppsProvider appsProvider) {
        this.appsActivity = appsActivity;
        this.appsProvider = appsProvider;
    }

    public void showAllApps() {
        appsActivity.showApps(sort(appsProvider.findInstalledApps()));
    }

    public void filterApps(String text) {
        appsActivity.showApps(sort(filter(text)));
    }

    protected List<App> sort(List<App> apps) {
        Collections.sort(apps);
        return apps;
    }

    protected List<App> filter(String filter) {
        List<App> installedApps = appsProvider.findInstalledApps();
        List<App> result = new ArrayList<>(installedApps);
        for (App app : installedApps) {
            if (!app.matches(filter)) {
                result.remove(app);
            }
        }
        return result;
    }

}
