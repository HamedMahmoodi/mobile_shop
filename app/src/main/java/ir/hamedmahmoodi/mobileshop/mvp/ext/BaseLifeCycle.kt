package ir.hamedmahmoodi.mobileshop.mvp.ext

interface BaseLifeCycle {

    fun onCreate()

    fun onDestroy() {}

    fun onStart() {}

    fun onResume() {}

    fun onStop() {}

}