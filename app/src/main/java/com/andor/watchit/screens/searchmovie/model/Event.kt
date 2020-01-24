package com.andor.watchit.screens.searchmovie.model

sealed class Event {
    data class FindMovie(val query: String) : Event()
}