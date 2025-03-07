package ir.hamedmahmoodi.mobileshop.androidWrapper

import android.widget.ImageView
import com.squareup.picasso.Picasso
import ir.hamedmahmoodi.mobileshop.R

object PicassoHandler {

    fun setPicasso(img: ImageView, url: String) {

        Picasso.get()
            .load(url)
            .placeholder(R.drawable.progress_animation)
            //TODO change img_place_holder
            .error(R.drawable.img_place_holder)
            .fit()
            .into(img)

    }

    fun setPicassoBanner(img: ImageView, url: String) {

        Picasso.get()
            .load(url)
            //TODO change img_banner_place_holder
            .placeholder(R.drawable.img_banner_place_holder)
            .error(R.drawable.img_banner_place_holder)
            .fit()
            .into(img)

    }

    fun setPicassoCats(img: ImageView, url: String) {

        Picasso.get()
            .load(url)
            .placeholder(R.drawable.progress_animation)
            //TODO change ic_mobile_place_holder
            .error(R.drawable.ic_mobile_place_holder)
            .fit()
            .into(img)

    }

}