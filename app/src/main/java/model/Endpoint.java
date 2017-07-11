package model;

/**
 * Created by Panda on 11-07-2017.
 */

public enum Endpoint {

    MANGA_EDEN_ALL("http://mangabox.sylach.com/meden_json.php?req_type=0&lang=en"),
    MANGA_EDEN_MANGA("http://mangabox.sylach.com/meden_json.php?req_type=1&mid="),
    MANGA_EDEN_CHAPTER("http://mangabox.sylach.com/meden_json.php?req_type=2&cid="),
    MANGA_EDEN_IMG("http://mangabox.sylach.com/meden_json.php?req_type=3&img=");

    private String value;
    Endpoint(String value) {
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}
