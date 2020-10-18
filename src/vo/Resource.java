package vo;

public class Resource {
    private int resourceid;
    private String resourecName;
    private String url;

    public Resource(int id,String name,String url){
        this.setResourceid(id);
        this.setResourecName(name);
        this.setUrl(url);
    }

    public int getResourceid() {
        return resourceid;
    }

    public void setResourceid(int resourceid) {
        this.resourceid = resourceid;
    }

    public String getResourecName() {
        return resourecName;
    }

    public void setResourecName(String resourecName) {
        this.resourecName = resourecName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
