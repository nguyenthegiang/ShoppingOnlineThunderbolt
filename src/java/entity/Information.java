
package entity;

/*Java Class storing additional information of the company*/
public class Information {

    private String description;
    private String address;
    private String email;
    private String phone;
    private String fax;

    public Information() {
    }

    public Information(String description, String address, String email, String phone, String fax) {
        this.description = description;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.fax = fax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "Information{" + "description=" + description + ", address=" + address + ", email=" + email + ", phone=" + phone + ", fax=" + fax + '}';
    }

}
