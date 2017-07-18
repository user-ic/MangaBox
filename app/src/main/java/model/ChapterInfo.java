package model;

/**
 * Created by Panda on 18-07-2017.
 */

public class ChapterInfo{
    int number;
    String date;
    String title;
    String id;

    public ChapterInfo(int number, String date, String title, String id) {
        this.number = number;
        this.date = date;
        this.title = title;
        this.id = id;
    }

    public int getNumber() {
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


    public void setNumber(int number) {
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
}