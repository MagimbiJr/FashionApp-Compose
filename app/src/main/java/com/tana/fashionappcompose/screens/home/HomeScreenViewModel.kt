package com.tana.fashionappcompose.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tana.fashionappcompose.data.Fashionista
import com.tana.fashionappcompose.data.Gallery
import com.tana.fashionappcompose.repository.Repository
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeScreenViewModel : ViewModel() {
    //private val fashionista = mutableStateOf(Fashionista())
    val fashionistas = mutableStateOf(listOf(Fashionista()))
    val gallery = mutableStateOf(listOf(Gallery()))
    init {
        viewModelScope.launch {
            val modelsResult = Repository.getModels()
            fashionistas.value = modelsResult

            val galleryResult = Repository.getGallery()
            gallery.value = galleryResult
        }
    }

    fun randomColors(): Color = Color(
        red = Random.nextInt(0, 200),
        green = Random.nextInt(0, 200),
        blue = Random.nextInt(0, 200)
    )

}