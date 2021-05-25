
package entity;

public class Cart {
    private Product p;
    private int amount;

    public Cart() {
    }

    public Cart(Product p, int amount) {
        this.p = p;
        this.amount = amount;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Cart{" + "p=" + p + ", amount=" + amount + '}';
    }
    
    
}
