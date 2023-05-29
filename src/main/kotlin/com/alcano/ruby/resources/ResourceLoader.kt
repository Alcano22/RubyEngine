package com.alcano.ruby.resources

fun interface ResourceLoader<T> {

    operator fun get(path: String): T?

}