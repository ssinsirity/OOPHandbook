package com.example.oophandbook.utils

fun String.withArgs(vararg args: Pair<String, Any>): String {
    var route = this
    args.forEach { (key, arg) -> route = route.replace("{$key}", arg.toString()) }

    return route
}