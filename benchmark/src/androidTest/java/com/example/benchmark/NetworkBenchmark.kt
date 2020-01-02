package com.example.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.*

@LargeTest
@RunWith(AndroidJUnit4::class)
class CalculBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun facteurPremierBenchmark() {