package entity;

/*Java Class storing Accounts: Customers, Sellers and Admin*/
public class Account {

    private int id;
    private String user;
    private String pass;
    private String email;
    private String activeCode;
    private int isSell; //If Account is Seller, isSell = 1
    private int isAdmin; //If Account is Admin, isAdmin = 1
    private int status;

    public Account() {
    }

    public Account(int id, String user, String pass, String email, String activeCode, int isSell, int isAdmin, int status) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.email = email;
        this.activeCode = activeCode;
        this.isSell = isSell;
        this.isAdmin = isAdmin;
        this.status = status;
    }


    /**
     * 
     * @return the ID of the Account object
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsSell() {
        return isSell;
    }

    public void setIsSell(int isSell) {
        this.isSell = isSell;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", user=" + user + ", pass=" + pass + ", email=" + email + ", activeCode=" + activeCode + ", isSell=" + isSell + ", isAdmin=" + isAdmin + ", status=" + status + '}';
    }    

}
