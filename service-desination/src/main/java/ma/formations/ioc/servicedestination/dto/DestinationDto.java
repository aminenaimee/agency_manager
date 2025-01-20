package ma.formations.ioc.servicedestination.dto;

import java.util.List;

public class DestinationDto {


    private Long id;
    private String name;
    private String country;
    private String imageUrl;
    private String imagePaths;
    private List<String> activities;

    public DestinationDto() {
    }

    public DestinationDto(Long id, String name, String country, List<String> activities) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public DestinationDto(Long id, String name, String country, String imageUrl, String imagePaths, List<String> activities) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.imageUrl = imageUrl;
        this.imagePaths = imagePaths;
        this.activities = activities;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(String imagePaths) {
        this.imagePaths = imagePaths;
    }
}