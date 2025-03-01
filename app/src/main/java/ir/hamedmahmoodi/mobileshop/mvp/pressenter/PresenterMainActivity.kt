package ir.hamedmahmoodi.mobileshop.mvp.pressenter

import ir.hamedmahmoodi.mobileshop.mvp.ext.BaseLifeCycle
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelMainActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewMainActivity

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity,
):BaseLifeCycle {
    override fun onCreate() {
        view.initialize()
        showNavigationDrawer()
       initBottomNavigation()
    }

    private fun showNavigationDrawer() {
        view.showNavDrawer()
    }

    private fun initBottomNavigation(){
        view.initBottomNav()
    }
}