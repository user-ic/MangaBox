package model;

/**
 * Created by Panda on 11-07-2017.
 */

public class TempBase {
    int end;                //?
    MangaStack[] manga;      //manga list
    int page;               //?
    int start;              //?
    int total;              //manga number

    public TempBase(int end, MangaStack[] manga, int page, int start, int total) {
        this.end = end;
        this.manga = manga;
        this.page = page;
        this.start = start;
        this.total = total;
    }

    public int getEnd() {
        return end;
    }

    public MangaStack[] getMangaList() {
        return manga;
    }

    public int getPage() {
        return page;
    }

    public int getStart() {
        return start;
    }

    public int getTotal() {
        return total;
    }
}
