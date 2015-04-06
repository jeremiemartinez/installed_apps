package fr.xebia.jmartinez.installed;

import android.os.Parcel;
import android.os.Parcelable;

public class App implements Parcelable, Comparable<App> {

    public final String title;

    public App(String title) {
        this.title = title;
    }

    public boolean matches(String filter) {
        return filter != null && title.contains(filter);
    }

    @Override
    public int compareTo(App app) {
        return title.compareTo(app.title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        App app = (App) o;

        if (title != null ? !title.equals(app.title) : app.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }

    private App(Parcel in) {
        this.title = in.readString();
    }

    public static final Creator<App> CREATOR = new Creator<App>() {
        public App createFromParcel(Parcel source) {
            return new App(source);
        }

        public App[] newArray(int size) {
            return new App[size];
        }
    };

}
