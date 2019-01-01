package com.android.africannewsv4;

public class NewsData {

    public String webTitle;
    public String sectionName;
    public String articleDate;
    public String authorName;
    public String webUrl;

    public NewsData(String webTitle, String sectionName, String articleDate, String authorName, String webUrl) {
        this.webTitle = webTitle;
        this.sectionName = sectionName;
        this.articleDate = articleDate;
        this.authorName = authorName;
        this.webUrl = webUrl;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
