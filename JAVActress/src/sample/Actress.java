package sample;


public class Actress {
    public String id;
    public String name;
    public String japaneseName;
    public String bust;
    public String waist;
    public String hip;
    public String height;
    public String birthday;
    public String imageUrl;
    public String siteUrl;

    public Actress(String id, String name, String japaneseName, String bust, String waist, String hip, String height, String birthday, String imageUrl, String siteUrl) {
        this.id = id;
        this.name = name;
        this.japaneseName = japaneseName;
        this.bust = bust;
        this.waist = waist;
        this.hip = hip;
        this.height = height;
        this.birthday = birthday;
        this.imageUrl = imageUrl;
        this.siteUrl = siteUrl;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
}
