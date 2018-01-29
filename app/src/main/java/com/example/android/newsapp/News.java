package com.example.android.newsapp;

public class News {
    private final String mSectionId;
    private final String mWebPublicationDate;
    private final String mWebPublicationTime;
    private final String mWebTitle;
    private final String mPillarName;
    private final String mWebUrl;


    public News(String sectionId, String webTitle, String webPublicationDate, String webPublicationTime, String webUrl, String pillarName) {
        mSectionId = sectionId;
        mWebPublicationDate = webPublicationDate;
        mWebPublicationTime = webPublicationTime;
        mWebTitle = webTitle;
        mPillarName = pillarName;
        mWebUrl = webUrl;

    }


    public String getSectionId() {
        return mSectionId;
    }

    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }

    public String getWebPublicationTime() {
        return mWebPublicationTime;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public String getPillarName() {
        return mPillarName;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

}
