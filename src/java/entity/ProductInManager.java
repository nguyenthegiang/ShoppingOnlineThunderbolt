
package entity;

/*Java Class storing Products*/
public class ProductInManager {

    private int id;
    private String name;
    private String description;
    private int price;
    private String imageLink; //Link to Product's Image
    private int CategoryID;
    private int SellerID;
    private int amount;

    public ProductInManager() {
    }

    public ProductInManager(int id, String name, String description, int price, String imageLink, int CategoryID, int SellerID, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageLink = imageLink;
        this.CategoryID = CategoryID;
        this.SellerID = SellerID;
        this.amount = amount;
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

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public int getSellerID() {
        return SellerID;
    }

    public void setSellerID(int SellerID) {
        this.SellerID = SellerID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    @Override
    public String toString() {
        return "ProductInManager{" + "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", imageLink=" + imageLink + ", CategoryID=" + CategoryID + ", SellerID=" + SellerID + ", amount=" + amount + '}';
    }
}
