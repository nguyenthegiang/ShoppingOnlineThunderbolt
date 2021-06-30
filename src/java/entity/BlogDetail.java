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
public class BlogDetail extends Blog{
    

    public BlogDetail() {
    }
    public BlogDetail(int id, String titel, String content,String imageLink) {
        super(id, titel, content, imageLink);
        
    }
    @Override
    public String toString() {
        return "Blog{" + "id=" + getId() + ", title=" + getTitle()+ ", content=" + getContent()+ ", imageLink=" + getImageLink() +  '}';
    }
}