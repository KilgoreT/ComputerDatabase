package com.example.computerdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.computerdatabase.api.NetworkService
import com.example.computerdatabase.interactor.ComputerDbInteractor
import com.example.computerdatabase.interactor.ComputerDbInteractorInterface
import com.example.computerdatabase.repository.NetworkRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var interactor: ComputerDbInteractorInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.instance.getAppComponent().inject(this)

        interactor.getComputers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    run {
                        Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
                    }
                }, {
                    Log.d("###", "error")
                }
            )

    }
}
