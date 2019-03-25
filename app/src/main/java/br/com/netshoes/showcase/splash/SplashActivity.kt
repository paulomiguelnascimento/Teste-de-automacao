package br.com.netshoes.showcase.splash

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.netshoes.showcase.*
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        disposable.add(
            Completable.complete().delay(SPLASH_DELAY_MILLI, TimeUnit.MILLISECONDS)
                .subscribe {
                    if (getLogged()) {
                        this.goListMovies()
                    } else {
                        this.goLogin()
                    }
                }
        )
    }

    override fun onPause() {
        super.onPause()
        disposable.clear()
    }

    private fun getLogged(): Boolean = getSharedPreferences(NAME_DATABASE, Context.MODE_PRIVATE)
        .getBoolean(USER_LOGGED_KEY, false)

}
