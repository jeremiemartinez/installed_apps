package fr.xebia.jmartinez.installed;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import fr.xebia.jmartinez.installed.internal.JUnitTestCase;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class AppsActivityTest extends JUnitTestCase<AppsActivity> {

    public AppsActivityTest() {
        super(AppsActivity.class);
    }

    @Test
    public void testFilterInstalledApp() {
        onView(withId(R.id.filter)).check(matches(isDisplayed()));
        takeScreenshot("Display_All_Installed_Apps");
        onView(withId(R.id.filter)).perform(typeText("Installed"));
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
        takeScreenshot("Display_Filtered_Installed_Apps");
    }
}
