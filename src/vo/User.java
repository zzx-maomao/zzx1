package vo;

public class User {
    private String useName;
    private String passWord;
    private String charName;
    private String email;
    private String province;
    private String city;


    public User(String usename, String password, String charname) {
        this.setUseName(usename);
        this.setPassWord(password);
        this.setCharName(charname);

    }
    public User(String usename, String password, String charname,String email,String province,String city) {
        this.setUseName(usename);
        this.setPassWord(password);
        this.setCharName(charname);
        this.setEmail(email);
        this.setProvince(province);
        this.setCity(city);
    }

    public String getUseName() {
        return this.useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCharName() {
        return this.charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
