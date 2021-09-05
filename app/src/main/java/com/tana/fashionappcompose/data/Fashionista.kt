package com.tana.fashionappcompose.data

import android.app.Activity
import android.os.Parcelable
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fashionista(
    val name: String = "",
    val city: String = "",
    val bio: String = "",
    val photo: String = ""
) : Parcelable {
    companion object {
        fun DocumentSnapshot.toModel(): Fashionista {
            return try {
                val name = getString("name")!!
                val city = getString("city")!!
                val bio = getString("bio")!!
                val photo = getString("photo")!!
                Fashionista(name, city, bio, photo)
            } catch (error: Exception) {
                Toast.makeText(Activity().applicationContext, error.localizedMessage, Toast.LENGTH_SHORT).show()
                Fashionista()
            }
        }
    }
}