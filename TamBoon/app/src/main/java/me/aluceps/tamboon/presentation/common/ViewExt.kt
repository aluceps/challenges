package me.aluceps.tamboon.presentation.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import me.aluceps.tamboon.domain.entities.IntPrimitive
import me.aluceps.tamboon.domain.entities.StringPrimitive
import me.aluceps.tamboon.domain.entities.UrlPrimitive

@BindingAdapter("logo")
fun ImageView.setLogoImage(url: UrlPrimitive?) {
    if (url == null) return
    Glide.with(context)
            .load(url.value.toString())
            .centerCrop()
            .into(this)
}

@BindingAdapter("android:text")
fun TextView.setText(value: IntPrimitive?) {
    if (value == null) return
    text = value.value.toString()
}

@BindingAdapter("android:text")
fun TextView.setText(value: StringPrimitive?) {
    if (value == null) return
    text = value.value
}