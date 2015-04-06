package fr.xebia.jmartinez.installed.internal;

import android.app.Activity;

import com.squareup.spoon.Spoon;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class SpoonRule extends TestWatcher {

    private String className;
    private String methodName;

    @Override
    protected void starting(Description description) {
        super.starting(description);
        className = description.getClassName();
        methodName = description.getMethodName();
    }

    public void takeScreenshot(Activity activity, String tag) {
        Spoon.screenshot(activity, tag, className, methodName);
    }

}
