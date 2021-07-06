/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author thong
 */
public class BlogInManager {
    private int id;
    private String title;
    private String content;
    private String imageLink;
    private int SellerID;

    public BlogInManager() {
    }

    public BlogInManager(int id, String title, String content, String imageLink, int SellerID) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageLink = imageLink;
        this.SellerID = SellerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getSellerID() {
        return SellerID;
    }

    public void setSellerID(int SellerID) {
        this.SellerID = SellerID;
    }

    @Override
    public String toString() {
        return "BlogInManager{" + "id=" + id + ", title=" + title + ", content=" + content + ", imageLink=" + imageLink + ", SellerID=" + SellerID + '}';
    }
     
}
