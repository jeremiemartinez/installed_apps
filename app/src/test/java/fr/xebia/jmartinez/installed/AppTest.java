package fr.xebia.jmartinez.installed;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class AppTest {

    @Test
    public void should_restore_from_parcelable() {
        // Given
        App app = new App("MyTitle");

        // When
        Parcel parcel = Parcel.obtain();
        app.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        // Then
        App fromParcel = App.CREATOR.createFromParcel(parcel);
        assertThat(fromParcel.title).isEqualTo("MyTitle");
    }

    @Test
    public void should_match() {
        // Given
        App app = new App("MyTitle");

        // When
        boolean result = app.matches("My");

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void should_not_match() {
        // Given
        App app = new App("MyTitle");

        // When
        boolean result = app.matches("My");

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void should_not_match_null() {
        // Given
        App app = new App("MyTitle");

        // When
        boolean result = app.matches(null);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void should_not_match_null_empty() {
        // Given
        App app = new App("MyTitle");

        // When
        boolean result = app.matches("");

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void should_compare_less() {
        // Given
        App app = new App("AAA");

        // When
        int result = app.compareTo(new App("BBB"));

        // Then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void should_compare_greater() {
        // Given
        App app = new App("BBB");

        // When
        int result = app.compareTo(new App("AAA"));

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void should_compare_same() {
        // Given
        App app = new App("AAA");

        // When
        int result = app.compareTo(new App("AAA"));

        // Then
        assertThat(result).isEqualTo(0);
    }
}
