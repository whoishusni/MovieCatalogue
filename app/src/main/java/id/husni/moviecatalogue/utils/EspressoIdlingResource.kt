package id.husni.moviecatalogue.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private val RESOURCE: String? = "GLOBAL"
    private val espressoTestIdling = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoTestIdling.increment()
    }

    fun decrement() {
        if (!espressoTestIdling.isIdleNow) {
            espressoTestIdling.decrement()
        }
    }

    fun getEspressoIdling(): IdlingResource = espressoTestIdling

}