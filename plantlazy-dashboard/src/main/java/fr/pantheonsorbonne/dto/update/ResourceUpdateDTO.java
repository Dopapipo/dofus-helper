package fr.pantheonsorbonne.dto.update;

public class ResourceUpdateDTO {
    private String type;
    private String resourceType;
    private int before;
    private int after;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getBefore() {
        return before;
    }

    public void setBefore(int before) {
        this.before = before;
    }

    public int getAfter() {
        return after;
    }

    public void setAfter(int after) {
        this.after = after;
    }

    @Override
    public String toString() {
        return "ResourceUpdateDTO{" +
                "type='" + type + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", before=" + before +
                ", after=" + after +
                '}';
    }
}
