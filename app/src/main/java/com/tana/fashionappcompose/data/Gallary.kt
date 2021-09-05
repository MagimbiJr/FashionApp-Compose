package com.tana.fashionappcompose.data

import android.app.Activity
import android.os.Parcelable
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gallery(
    val imageUrl: String = ""
) : Parcelable {
    companion object {
        fun DocumentSnapshot.toGallery(): Gallery {
            return try {
                val imageUrl = getString("photo")!!
                Gallery(imageUrl)
            } catch (error: Exception) {
                Toast.makeText(Activity().applicationContext, error.localizedMessage, Toast.LENGTH_SHORT).show()
                Gallery()
            }
        }
    }
}