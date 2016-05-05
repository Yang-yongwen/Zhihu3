package com.yangyongwen.zhihu3.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "STORY".
 */
public class Story {

    private Integer id;
    private Integer type;
    private Boolean multipic;
    private Boolean readed;
    private String title;
    private String images;
    private String ga_prefix;
    private String date;

    public Story() {
    }

    public Story(Integer id) {
        this.id = id;
    }

    public Story(Integer id, Integer type, Boolean multipic, Boolean readed, String title, String images, String ga_prefix, String date) {
        this.id = id;
        this.type = type;
        this.multipic = multipic;
        this.readed = readed;
        this.title = title;
        this.images = images;
        this.ga_prefix = ga_prefix;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getMultipic() {
        return multipic;
    }

    public void setMultipic(Boolean multipic) {
        this.multipic = multipic;
    }

    public Boolean getReaded() {
        return readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
