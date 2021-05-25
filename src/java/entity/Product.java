
package entity;

/*Java Class storing Products*/
public class Product {

    private int id;
    private String name;
    private String description;
    private int price;
    private String imageLink; //Link to Product's Image

    public Product() {
    }

    public Product(int id, String name, String description, int price, String imageLink) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageLink = imageLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", imageLink=" + imageLink + '}';
    }

    /*Function to write the Product's Price in proper form:
    Example: 1.000.000*/
    public String getPriceWithDot() {
        String priceDot = "" + price;
        int i = priceDot.length() - 3;
        while (i > 0) {
            priceDot = priceDot.substring(0, i) + "." + priceDot.substring(i, priceDot.length());
            i -= 3;
        }
        return priceDot;
    }
}
