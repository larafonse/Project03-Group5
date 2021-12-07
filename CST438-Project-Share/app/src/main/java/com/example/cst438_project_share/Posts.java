package com.example.cst438_project_share;

import com.google.gson.annotations.SerializedName;

import java.net.URL;

public class Posts {

    @SerializedName("userID")
    private int userId;
    @SerializedName("description")
    private String description;
    @SerializedName("picture_url")
    private String imgURL;
    @SerializedName("tech_stack")
    private String techStack;
    @SerializedName("projectName")
    private String projectName;
    @SerializedName("external_link")
    private String externalLink;

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
