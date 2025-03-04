package ir.hamedmahmoodi.mobileshop.androidWrapper

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

interface ActivityUtils {

    fun setFragment(fragment: Fragment) {}

    //TODO setViewPagerFragment
    /*fun setViewPagerFragment(viewPager: ViewPager2, data: ArrayList<String>) {}*/

    fun finished() {}

    //TODO finishedAffinity
   /* fun finishedAffinity() {}*/

    //TODO showSnackBar
 /*   fun showSnackBar() {}*/

    fun activeNetwork() {}

}