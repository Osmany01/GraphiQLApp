package com.example.graphiqlapp.ui.common

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

const val GENERIC_ERROR_MESSAGE = "Oopps, something went wrong"

fun <T> CoroutineScope.collectFlow(flow: Flow<T>, body: suspend (T) -> Unit) {
    flow.onEach { body(it) }
        .launchIn(this)
}

@ExperimentalCoroutinesApi
val View.onClickEvents: Flow<View>
    get() = callbackFlow {
        val onClickListener = View.OnClickListener { offer(it) }
        setOnClickListener(onClickListener)
        awaitClose { setOnLongClickListener(null) }
    }.conflate()


var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }


fun ImageView.loadWithGlide(path: String) {
    Glide
        .with(this.context)
        .load(path)
        .centerInside()
        .into(this)
}


fun Context.toast(message: String? = GENERIC_ERROR_MESSAGE) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
