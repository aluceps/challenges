package me.aluceps.tamboon.data.responses

import me.aluceps.tamboon.domain.entities.Charity
import me.aluceps.tamboon.domain.entities.Id
import me.aluceps.tamboon.domain.entities.LogoUrl
import me.aluceps.tamboon.domain.entities.Name
import java.net.URI

data class Charity(
    val id: Int,
    val name: String,
    val logo_url: String
) {
    fun toEntity() =
        Charity(Id(id), Name(name), LogoUrl(URI(logo_url)))
}