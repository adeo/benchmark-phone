/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        benchmarkRule.measureRepeated {
            for (x in 0..10000) {
                decompose(999999999999999999)
            }
        }
    }

    @Test
    fun ackleyBenchmark() {
        var x: MutableList<Double> = mutableListOf()
        for (i in -31..32)  {
            x.add(i.toDouble())
        }
        benchmarkRule.measureRepeated {
            for (i in 0..100000) {
                ackley(x.toTypedArray())
            }
        }
    }

    @Test
    fun bealeBenchmark() {
        benchmarkRule.measureRepeated {
            for (i in 0..3000000) {
                beale(999999999, 999999999.8)
            }
        }
    }

    @Test
    fun xinSheBenchmark() {
        var x: MutableList<Double> = mutableListOf()
        for (i in -10..9)  {
            x.add(i.toDouble())
        }
        benchmarkRule.measureRepeated {
            for (i in 0..200000) {
                xinShe(x.toTypedArray())
            }
        }
    }


    private fun xinShe(x: Array<Double>): Double {
        return (sum(x.size, this::fcnXinSheT1, x) - exp(sum(x.size, this::fcnPow, x))) *
                exp(-sum(x.size, this::fcnXinSheT2, x))
    }

    private fun ackley(x: Array<Double>): Double {
        var a = 20
        var b = 0.2
        var c = 2 * PI
        var n = x.size
        var t1 = exp(
            -b * sqrt(
                1 / n * sum (n, this::fcnPow, x)
            ))
        var t2 = exp(
            1 / n * sum(
                n, this::fcnCos, x
            )
        )
        return -a * t1 - t2 + a + exp(1.0)
    }

    private fun fcnPow(x: Double) : Double {
        return x * x
    }

    private fun fcnCos(x: Double) : Double {
        return cos(2 * PI * x)
    }

    private fun fcnXinSheT1(x: Double): Double {
        return sin(x).pow(2)
    }

    private fun fcnXinSheT2(x: Double): Double {
        return sin(sqrt(x)).pow(2)
    }


    private fun sum(n: Int, fcn: (x: Double) -> Double, x: Array<Double>): Double {
        var sum: Double = 0.0
        for (i in 0 until x.size) {
            sum += fcn(x[i])
        }
        return sum
    }

    private fun beale(x: Int, y: Double): Double {
        return (1.5 - x + x * y).pow(2) +
                (2.25 - x + (x * y).pow(2)).pow(2) +
                ( 2.625 - x + (x * y).pow(3)).pow(2)
    }

    private fun decompose(_n: Long): Int {
        var facteur: MutableList<Long> = mutableListOf()
        var n = _n
        var d : Long = 3
        var i = 0
        while(n % 2 == 0L) {
            facteur.add(i, 2)
            i++
            n /= 2
        }
        while ( d * d <= n) {
            if (n % d == 0L) {
                facteur.add(i, d)
                i++
                n /= d
            } else {
                d += 2
            }
        }
        if (n > 1) {
            facteur.add(i, n)
            i++
        }
        return i
    }
}
