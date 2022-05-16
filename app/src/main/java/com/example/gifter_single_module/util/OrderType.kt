package com.example.gifter_single_module.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()

    fun copy(): OrderType {
        return when(this){
            is Ascending ->  Descending
            is Descending -> Ascending
        }
    }
    operator fun not(): OrderType {
        return when(this){
            is Ascending ->  Descending
            is Descending -> Ascending
        }
    }
}