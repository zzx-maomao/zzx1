package vo;

public class User {
    private String useName;
    private String passWord;
    private String charName;


    public User(String usename, String password, String charname) {
        this.setUseName(usename);
        this.setPassWord(password);
        this.setCharName(charname);

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


}
