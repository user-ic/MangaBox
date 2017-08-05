package model;

/**
 * Created by Average on 09-12-2016.
 */

public class HistoryEntry {
    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String entry;
    public String name;
    public String mangaId;
    public String chapterId;
    public String chapterNum;
    public String src;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String size;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String position;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String cover;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String date;

    public HistoryEntry(String name, String mangaId, String chapterId, String src, String entry, String date, String cover, String chapterNum, String position, String size) {
        this.name = name;
        this.mangaId = mangaId;
        this.chapterId = chapterId;
        this.src = src;
        this.entry = entry;
        this.cover = cover;
        this.chapterNum = chapterNum;
        this.date = date;
        this.position = position;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMangaId() {
        return mangaId;
    }

    public void setMangaId(String mangaId) {
        this.mangaId = mangaId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(String chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
