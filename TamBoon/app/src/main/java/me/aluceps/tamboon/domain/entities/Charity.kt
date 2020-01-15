package me.aluceps.tamboon.domain.entities

import java.io.Serializable
import java.net.URI

class Id(override val value: Int) : Serializable, IntPrimitive
class Name(override val value: String) : Serializable, StringPrimitive
class LogoUrl(override val value: URI) : Serializable, UrlPrimitive

data class Charity(val id: Id, val name: Name, val logoUrl: LogoUrl)