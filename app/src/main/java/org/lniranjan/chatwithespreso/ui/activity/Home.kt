package org.lniranjan.chatwithespreso.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.lniranjan.chatwithespreso.R
import org.lniranjan.chatwithespreso.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
    }
}