package ir.hamedmahmoodi.mobileshop.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.hamedmahmoodi.mobileshop.R
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils

class MainActivity : AppCompatActivity(),ActivityUtils {

    /*private lateinit var presenter:PresnterMainActivity*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}