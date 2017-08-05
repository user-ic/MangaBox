package model;

/**
 * Created by Panda on 05-08-2017.
 */

public class Favorite {
    String source;
    String mangaId;
    String state;
    String title;
    String img;
    //...
 /*   enum FavoriteType {
        ADD , DEL
    }
*/
    public Favorite(String source, String mangaId, String state, String title, String img) {
        this.source = source;
        this.mangaId = mangaId;
        this.title = title;
        this.state = state;
        this.img = img;
    }

    public String getSource() {
        return source;
    }

    public String getMangaId() {
        return mangaId;
    }

    public String getState() {
        return state;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }







}
