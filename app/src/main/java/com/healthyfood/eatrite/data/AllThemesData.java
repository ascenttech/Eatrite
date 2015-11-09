package com.healthyfood.eatrite.data;

/**
 * Created by ADMIN on 05-11-2015.
 */
public class AllThemesData {

    String themeId,themeName,themeImage;

    public AllThemesData(String themeId, String themeName, String themeImage) {
        this.themeId = themeId;
        this.themeName = themeName;
        this.themeImage = themeImage;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeImage() {
        return themeImage;
    }

    public void setThemeImage(String themeImage) {
        this.themeImage = themeImage;
    }
}
