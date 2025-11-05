package com.example.healthapp.bean;

import java.io.Serializable;

public class ArticleBean implements Serializable {

        private int id;
        private Integer img;
        private String title;
        private String content;
        private String time;

    public ArticleBean(int id, Integer img, String title, String content, String time) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public ArticleBean(Integer img, String title, String content, String time) {
        this.img = img;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
