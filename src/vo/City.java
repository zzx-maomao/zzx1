package vo;

public class City {
    private int id;
    private String name;
    private int provinceid;
    public City(int id,String name,int provinceid){
        this.setId(id);
        this.setName(name);
        this.setProvinceid(provinceid);
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

    public int getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(int provinceid) {
        this.provinceid = provinceid;
    }
}
