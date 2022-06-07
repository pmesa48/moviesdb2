package com.pmesa48.moviedatabase.data.common

abstract class InteractorMapper<T, V, D> {

    abstract fun convertModelToView(input: T) : V

    abstract fun convertDtoToModel(input: D) : T
}