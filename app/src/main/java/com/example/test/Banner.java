package com.example.test;

public class Banner {
    int bannerId;
    String bannerImage;
    String bannerRedirUrl;

    public Banner() {
    }

    public Banner(int bannerId, String bannerImage, String bannerRedirUrl) {
        this.bannerId = bannerId;
        this.bannerImage = bannerImage;
        this.bannerRedirUrl = bannerRedirUrl;
    }

    public int getBannerId() {
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getBannerRedirUrl() {
        return bannerRedirUrl;
    }

    public void setBannerRedirUrl(String bannerRedirUrl) {
        this.bannerRedirUrl = bannerRedirUrl;
    }
}
