package com.yayanheryanto.larismotor.adapter;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

import static com.yayanheryanto.larismotor.config.config.BASE_URL;

public class MainSliderAdapter extends SliderAdapter {


    private String gambar, gambar1, gambar2 ;

    @Override
    public int getItemCount() {
        return 3;
    }

    public MainSliderAdapter(String gambar, String gambar1, String gambar2) {
        this.gambar = gambar;
        this.gambar1 = gambar1;
        this.gambar2 = gambar2;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide(BASE_URL+"storage/motor/"+gambar);
                break;
            case 1:
                viewHolder.bindImageSlide(BASE_URL+"storage/motor/"+gambar1);
                break;
            case 2:
                viewHolder.bindImageSlide(BASE_URL+"storage/motor/"+gambar2);
                break;
        }
    }
}