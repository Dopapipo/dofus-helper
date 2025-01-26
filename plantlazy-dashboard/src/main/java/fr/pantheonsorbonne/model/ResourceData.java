package fr.pantheonsorbonne.model;

public class ResourceData {
    private String resourceType;
    private int currentValue;

    public ResourceData(String resourceType, int currentValue) {
        this.resourceType = resourceType;
        this.currentValue = currentValue;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    @Override
    public String toString() {
        return "ResourceData{" +
                "resourceType='" + resourceType + '\'' +
                ", currentValue=" + currentValue +
                '}';
    }
}
