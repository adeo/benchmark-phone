package com.mobility.benchmark.network

import android.util.Log
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.InputStream


@LargeTest
@RunWith(AndroidJUnit4::class)
class NetworkBenchmark {
    private val FILE_ADDRESS = "http://rbx.proof.ovh.net"
    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun dowloadFile() {
        val retrofit = Retrofit.Builder()
            .baseUrl(FILE_ADDRESS)
            .client(OkHttpClient.Builder().build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        val downloadService = retrofit.create(api::class.java)
        benchmarkRule.measureRepeated {
            Log.d("download","bonjour")
            downloadService.downloadFile().subscribe({
                    writeResponseBodyToDisk(it.body()!!)
                }, {})
        }
    }

    private fun writeResponseBodyToDisk(body: ResponseBody): Boolean {
        try {
            var inputStream: InputStream? = null
            try {
                val fileReader = ByteArray(4096)
                inputStream = body.byteStream()
                while (true) {
                    val read = inputStream!!.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    Log.d("download","bonjour")
                }
                Log.d("download","test")
                return true
            } catch (e: Exception) {
                Log.d("download","${e}")
                return false
            } finally {
                inputStream?.close()
            }
        } catch (e: Exception) {
            Log.d("download","${e}")
            return false
        }
    }

}