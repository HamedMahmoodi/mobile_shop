package ir.hamedmahmoodi.mobileshop.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.hamedmahmoodi.mobileshop.R
import ir.hamedmahmoodi.mobileshop.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customAppBar.showNavDrawer(this)
        binding.customAppBar.getBackIcon().setOnClickListener {
            finish()
        }

    }
}