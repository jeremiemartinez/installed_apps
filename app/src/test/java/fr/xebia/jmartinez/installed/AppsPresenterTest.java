package fr.xebia.jmartinez.installed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppsPresenterTest {

    @Mock AppsActivity activity;
    @Mock AppsProvider appsProvider;
    @InjectMocks AppsPresenter appsPresenter;

    @Test
    public void should_filter() throws Exception {
        // Given
        when(appsProvider.findInstalledApps()).thenReturn(Arrays.asList(new App("AAA"), new App("BBB")));

        // When
        List<App> result = appsPresenter.filter("A");

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).title).isEqualTo("AAA");
    }

    @Test
    public void should_sort() throws Exception {
        // Given
        List<App> apps = Arrays.asList(new App("BBB"), new App("AAA"));

        // When
        List<App> result = appsPresenter.sort(apps);

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).title).isEqualTo("AAA");
        assertThat(result.get(1).title).isEqualTo("BBB");
    }

    @Test
    public void should_filter_apps() throws Exception {
        // Given
        when(appsProvider.findInstalledApps()).thenReturn(Arrays.asList(new App("BBB"), new App("AAA"), new App("ABB")));

        // When
        appsPresenter.filterApps("A");

        // Then
        verify(activity).showApps(Arrays.asList(new App("AAA"), new App("ABB")));
    }


    @Test
    public void should_show_all_apps() throws Exception {
        // Given
        when(appsProvider.findInstalledApps()).thenReturn(Arrays.asList(new App("BBB"), new App("AAA")));

        // When
        appsPresenter.showAllApps();

        // Then
        verify(activity).showApps(Arrays.asList(new App("AAA"), new App("BBB")));
    }

}
