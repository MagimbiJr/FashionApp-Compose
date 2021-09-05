package com.tana.fashionappcompose.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tana.fashionappcompose.data.Fashionista
import com.tana.fashionappcompose.data.Fashionista.Companion.toModel
import com.tana.fashionappcompose.data.Gallery
import com.tana.fashionappcompose.data.Gallery.Companion.toGallery
import kotlinx.coroutines.tasks.await

object Repository {
    private val db = Firebase.firestore
    private val fashionistaCollectionRef = db.collection("fashionistas")
    private val galleryCollectionRef = db.collection("gallery")

    suspend fun getModels(): List<Fashionista> {
         return try {
            fashionistaCollectionRef.get().await().documents.mapNotNull { it.toModel() }
        } catch (error: Exception) {
            emptyList()
        }
    }

    suspend fun getGallery(): List<Gallery> {
        return try {
            galleryCollectionRef.get().await().documents.mapNotNull { documentSnapshot ->
                documentSnapshot.toGallery()
            }
        } catch (error: Exception) {
            emptyList()
        }
    }
}

private const val TAG = "TAG la repo"