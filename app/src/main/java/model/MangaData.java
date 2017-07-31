package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Panda on 13-07-2017.
 */

public class MangaData implements Parcelable{
    String[] aka;
    String[] aka_alias;
    String alias;
    String artist;
    String[] artist_kw;
    String author;
    String[] author_kw;
    boolean autoManga;
    String baka;
    String categories;
    ChapterInfo[] chapters;
    int chapters_len;
    long created;
    String description;
    int hits;
    String image;
    String language;
    long last_chapter_date;
    String[] random;
    int released;
    String startsWith;
    String title;
    String title_kw;
    int type;
    boolean updatedKeywords;
    String url;

    protected MangaData(Parcel in) {
        aka = in.createStringArray();
        aka_alias = in.createStringArray();
        alias = in.readString();
        artist = in.readString();
        artist_kw = in.createStringArray();
        author = in.readString();
        author_kw = in.createStringArray();
        autoManga = in.readByte() != 0;
        baka = in.readString();
        categories = in.readString();
        chapters_len = in.readInt();
        created = in.readLong();
        description = in.readString();
        hits = in.readInt();
        image = in.readString();
        language = in.readString();
        last_chapter_date = in.readLong();
        random = in.createStringArray();
        released = in.readInt();
        startsWith = in.readString();
        title = in.readString();
        title_kw = in.readString();
        type = in.readInt();
        updatedKeywords = in.readByte() != 0;
        url = in.readString();
    }

    public static final Creator<MangaData> CREATOR = new Creator<MangaData>() {
        @Override
        public MangaData createFromParcel(Parcel in) {
            return new MangaData(in);
        }

        @Override
        public MangaData[] newArray(int size) {
            return new MangaData[size];
        }
    };

    public String[] getAka() {
        return aka;
    }

    public void setAka(String[] aka) {
        this.aka = aka;
    }

    public String[] getAka_alias() {
        return aka_alias;
    }

    public void setAka_alias(String[] aka_alias) {
        this.aka_alias = aka_alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String[] getArtist_kw() {
        return artist_kw;
    }

    public void setArtist_kw(String[] artist_kw) {
        this.artist_kw = artist_kw;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String[] getAuthor_kw() {
        return author_kw;
    }

    public void setAuthor_kw(String[] author_kw) {
        this.author_kw = author_kw;
    }

    public boolean isAutoManga() {
        return autoManga;
    }

    public void setAutoManga(boolean autoManga) {
        this.autoManga = autoManga;
    }

    public String getBaka() {
        return baka;
    }

    public void setBaka(String baka) {
        this.baka = baka;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public ChapterInfo[] getChapters() {
        return chapters;
    }

    public void setChapters(ChapterInfo[] chapters) {
        this.chapters = chapters;
    }

    public int getChapters_len() {
        return chapters_len;
    }

    public void setChapters_len(int chapters_len) {
        this.chapters_len = chapters_len;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getLast_chapter_date() {
        return last_chapter_date;
    }

    public void setLast_chapter_date(long last_chapter_date) {
        this.last_chapter_date = last_chapter_date;
    }

    public String[] getRandom() {
        return random;
    }

    public void setRandom(String[] random) {
        this.random = random;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_kw() {
        return title_kw;
    }

    public void setTitle_kw(String title_kw) {
        this.title_kw = title_kw;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isUpdatedKeywords() {
        return updatedKeywords;
    }

    public void setUpdatedKeywords(boolean updatedKeywords) {
        this.updatedKeywords = updatedKeywords;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(aka);
        parcel.writeStringArray(aka_alias);
        parcel.writeString(alias);
        parcel.writeString(artist);
        parcel.writeStringArray(artist_kw);
        parcel.writeString(author);
        parcel.writeStringArray(author_kw);
        parcel.writeByte((byte) (autoManga ? 1 : 0));
        parcel.writeString(baka);
        parcel.writeString(categories);
        parcel.writeInt(chapters_len);
        parcel.writeLong(created);
        parcel.writeString(description);
        parcel.writeInt(hits);
        parcel.writeString(image);
        parcel.writeString(language);
        parcel.writeLong(last_chapter_date);
        parcel.writeStringArray(random);
        parcel.writeInt(released);
        parcel.writeString(startsWith);
        parcel.writeString(title);
        parcel.writeString(title_kw);
        parcel.writeInt(type);
        parcel.writeByte((byte) (updatedKeywords ? 1 : 0));
        parcel.writeString(url);
    }
}
