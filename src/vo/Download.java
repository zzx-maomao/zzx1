package vo;

import javax.xml.crypto.Data;
import java.sql.Date;

public class Download {
    private int id;
    private String name;
    private String path;
    private String description;
    private String size;
    private int star;
    private String image;
   private java.sql.Date time;
    public Download(int id,String name,String path,String description,String size,int star,String image,java.sql.Date time ){
        this.setId(id);
        this.setName(name);
        this.setPath(path);
        this.setDescription(description);
        this.setSize(size);
        this.setStar(star);
        this.setImage(image);
        this.setTime(time);

    }

    public Download(int id, String name, String path, String description, long size, int star,java.sql.Date time) {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
