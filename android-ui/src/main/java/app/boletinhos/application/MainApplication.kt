package app.boletinhos.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import app.boletinhos.application.injection.AppComponent
import app.boletinhos.application.injection.DaggerAppComponent
import app.boletinhos.crashcat.CrashCat
import javax.inject.Inject

open class MainApplication : Application() {
    private lateinit var component: AppComponent

    private fun injector(): AppComponent {
        return DaggerAppComponent.factory()
            .create(this)
            .also { component -> component.inject(this) }
    }

    @Inject lateinit var crashCat: CrashCat

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        component = injector()
        crashCat.configure()
    }

    open fun appComponent() = component
}
