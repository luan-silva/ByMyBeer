package lsl.studio.com.bymybeer.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class Beer {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("tagline")
    @Expose
    private String tagline;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("isFavorite")
    boolean isFavorite;


    public Beer(int id, String name, String imageUrl, String tagline, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.tagline = tagline;
        this.isFavorite = isFavorite;
    }

    public boolean getIsFavourite() {
        return isFavorite;
    }

    public void setFavourite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTagline() { return tagline; }

    public void setTagline(String tagline) { this.tagline = tagline; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
