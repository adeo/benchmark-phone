package com.example.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.*

@LargeTest
@RunWith(AndroidJUnit4::class)
class AmgpBenchmark {
    @get:Rule
    val benchmarkRule = BenchmarkRule()

    //@get:Rule
    //val activityRule = ActivityTestRule(MainActivity::class.java)


    @UiThreadTest
    @Test
    fun GesteMetierBenchmark() {
        benchmarkRule.measureRepeated {

        }
    }
}