package me.aluceps.tamboon.domain.entities

import java.net.URI

interface Primitive<T> {
    val value: T
}

interface IntPrimitive : Primitive<Int>
interface StringPrimitive : Primitive<String>
interface UrlPrimitive : Primitive<URI>

