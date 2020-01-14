package me.aluceps.tamboon.presentation.charities

import me.aluceps.tamboon.domain.entities.Charity
import me.aluceps.tamboon.domain.entities.Id
import me.aluceps.tamboon.domain.entities.LogoUrl
import me.aluceps.tamboon.domain.entities.Name
import me.aluceps.tamboon.presentation.common.BaseViewModel
import java.net.URI
import javax.inject.Inject

class CharitiesViewModel @Inject constructor() : BaseViewModel() {

    val items = mutableListOf<Charity>().apply {
        add(Charity(Id(1), Name("aaa1"), LogoUrl(URI("http://rkdretailiq.com/news/img-corporate-baankrunoi.jpg"))))
        add(Charity(Id(2), Name("aaa2"), LogoUrl(URI("http://rkdretailiq.com/news/img-corporate-baankrunoi.jpg"))))
        add(Charity(Id(3), Name("aaa3"), LogoUrl(URI("http://rkdretailiq.com/news/img-corporate-baankrunoi.jpg"))))
        add(Charity(Id(4), Name("aaa4"), LogoUrl(URI("http://rkdretailiq.com/news/img-corporate-baankrunoi.jpg"))))
        add(Charity(Id(5), Name("aaa5"), LogoUrl(URI("http://rkdretailiq.com/news/img-corporate-baankrunoi.jpg"))))
    }
}