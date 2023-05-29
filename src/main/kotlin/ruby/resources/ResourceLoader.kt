package ruby.resources

fun interface ResourceLoader<T> {

    operator fun get(path: String): T?

}