package org.lniranjan.chatwithespreso.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.lniranjan.chatwithespreso.R
import org.lniranjan.chatwithespreso.databinding.ActivityProfileBinding
import org.lniranjan.chatwithespreso.ui.viewmodal.ProfileViewModal

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    private lateinit var viewModal: ProfileViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
    }
}