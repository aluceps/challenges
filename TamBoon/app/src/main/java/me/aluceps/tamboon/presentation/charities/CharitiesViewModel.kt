package me.aluceps.tamboon.presentation.charities

import androidx.databinding.ObservableArrayList
import me.aluceps.tamboon.domain.entities.Charity
import me.aluceps.tamboon.presentation.common.BaseViewModel
import java.net.URI
import javax.inject.Inject

class CharitiesViewModel @Inject constructor() : BaseViewModel() {

    val items = ObservableArrayList<Charity>().apply {
        add(Charity(1, "aaa1", URI("https://dummyimage.com/1600x900/000/fff&text=test1")))
        add(Charity(2, "aaa2", URI("https://dummyimage.com/1600x900/000/fff&text=test2")))
        add(Charity(3, "aaa3", URI("https://dummyimage.com/1600x900/000/fff&text=test3")))
        add(Charity(4, "aaa4", URI("https://dummyimage.com/1600x900/000/fff&text=test4")))
        add(Charity(5, "aaa5", URI("https://dummyimage.com/1600x900/000/fff&text=test5")))
    }
}