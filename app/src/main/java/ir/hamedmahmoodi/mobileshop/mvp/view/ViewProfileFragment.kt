package ir.hamedmahmoodi.mobileshop.mvp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.UserData
import ir.hamedmahmoodi.mobileshop.databinding.FragmentProfileBinding
import ir.hamedmahmoodi.mobileshop.ui.activity.UserActivity

class ViewProfileFragment(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        FragmentProfileBinding.inflate(LayoutInflater.from(context))

    fun startGetData() {
        binding.mainView.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun endGetData() {
        binding.mainView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun setUserData(userData: UserData) {

        binding.txtName.text = userData.fullname
        binding.txtPhone.text = userData.phone

    }

    fun onClick() {

        binding.imgUserInfo.setOnClickListener {
            context.startActivity(Intent(context, UserActivity::class.java))
        }

    /*    //TODO FavoriteActivity
        binding.imgFavorite.setOnClickListener {
            context.startActivity(Intent(context, FavoriteActivity::class.java))
        }*/

     /*   //TODO AddressActivity
        binding.imgAddress.setOnClickListener {
            context.startActivity(Intent(context, AddressActivity::class.java))
        }*/

  /*      //TODO CustomCakeActivity
        binding.imgCake.setOnClickListener {
            context.startActivity(Intent(context, CustomCakeActivity::class.java))
        }*/

    }

}