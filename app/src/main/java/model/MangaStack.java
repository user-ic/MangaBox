package model;

/**
 * Created by Panda on 11-07-2017.
 */

public class MangaStack {
    String a;       //aka... why not
    String[] c;     //categories
    int h;          //number of hits
    String i;       //id
    String im;      //img cover
    String ld;      //last update
    int s;          //status.. not sure
    String t;       //title


    public MangaStack(String im, String t, String i) {
        this.i = i;
        this.im = im;
        this.t = t;

    }
    public MangaStack(String a, String[] c, int h, String i, String im, String ld, int s, String t) {
        this.a = a;
        this.c = c;
        this.h = h;
        this.i = i;
        this.im = im;
        this.ld = ld;
        this.s = s;
        this.t = t;

    }
    public MangaStack(){}

    public String getAka() {
        return a;
    }

    public String[] getCategories() {
        return c;
    }

    public int getHits() {
        return h;
    }

    public String getId() {
        return i;
    }

    public String getCover() {
        return im;
    }

    public String getLastUpdate() {
        return ld;
    }

    public int getStatus() {
        return s;
    }

    public String getTitle() {
        return t;
    }

    public void setAka(String a) {
        this.a = a;
    }

    public void setCategories(String[] c) {
        this.c = c;
    }

    public void setHits(int h) {
        this.h = h;
    }

    public void setId(String i) {
        this.i = i;
    }

    public void setCover(String im) {
        this.im = im;
    }

    public void setLastUpdate(String ld) {
        this.ld = ld;
    }

    public void setStatus(int s) {
        this.s = s;
    }

    public void setTitle(String t) {
        this.t = t;
    }
}
