package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Panda on 18-07-2017.
 */

public class ChapterInfo implements Parcelable{
    String number;
    String date;
    String title;
    String id;

    public ChapterInfo(String number, String date, String title, String id) {
        this.number = number;
        this.date = date;
        this.title = title;
        this.id = id;
    }

    protected ChapterInfo(Parcel in) {
        number = in.readString();
        date = in.readString();
        title = in.readString();
        id = in.readString();
    }

    public static final Creator<ChapterInfo> CREATOR = new Creator<ChapterInfo>() {
        @Override
        public ChapterInfo createFromParcel(Parcel in) {
            return new ChapterInfo(in);
        }

        @Override
        public ChapterInfo[] newArray(int size) {
            return new ChapterInfo[size];
        }
    };

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(number);
        parcel.writeString(date);
        parcel.writeString(title);
        parcel.writeString(id);
    }
}