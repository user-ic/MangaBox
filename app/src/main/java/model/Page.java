package model;

/**
 * Created by android on 28-10-2016.
 */

public class Page {
    String page_id, img_path, img_width, img_height;

    public Page(String page_id, String img_path, String img_width, String img_height) {
        this.page_id = page_id;
        this.img_path = img_path;
        this.img_width = img_width;
        this.img_height = img_height;
    }



    public String getPage_id() {
        return page_id;
    }

    public String getImg_path() {
        return img_path;
    }

    public String getImg_width() {
        return img_width;
    }

    public String getImg_height() {
        return img_height;
    }
}
