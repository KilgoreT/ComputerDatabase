package com.example.computerdatabase.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>) {
    text.observeForever {
        view.text = it
    }
}

@BindingAdapter("visibilityT")
fun setVisibilityT(view: View, visibility: MutableLiveData<Boolean>) {
    view.visibility = if (visibility.value == true) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun imageUrl(view: ImageView, url: String) {
    Picasso.get().load(url).into(view)
}