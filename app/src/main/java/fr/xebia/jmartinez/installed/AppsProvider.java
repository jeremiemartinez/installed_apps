package fr.xebia.jmartinez.installed;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class AppsProvider {

    private final Context context;

    public AppsProvider(Context context) {
        this.context = context;
    }

    public List<App> findInstalledApps() {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        List<App> result = new ArrayList<>(installedApps.size());
        for (ApplicationInfo installedApp : installedApps) {
            result.add(new App(installedApp.loadLabel(packageManager).toString()));
        }
        return result;
    }

}
