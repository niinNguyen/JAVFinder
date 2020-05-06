package sample;

public class Video {
    public String year;
    public String code;
    public String title;

    public Video(String year, String code, String title) {
        this.year = year;
        this.code = code;
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
