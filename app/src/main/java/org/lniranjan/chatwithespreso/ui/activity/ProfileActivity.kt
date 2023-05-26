package org.lniranjan.chatwithespreso.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import org.lniranjan.chatwithespreso.R
import org.lniranjan.chatwithespreso.databinding.ActivityProfileBinding
import org.lniranjan.chatwithespreso.ui.viewmodal.ProfileViewModal

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var viewModal: ProfileViewModal
    var isEditMode: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        viewModal = ProfileViewModal()
        setEditMode()
        binding.updateSettingsBtn.setOnClickListener {
            toggleEditMode()
        }
    }

    private fun toggleEditMode() {
        isEditMode = !isEditMode
        if (isEditMode) {
            binding.updateSettingsBtn.text = "Save Profile"
            setEditMode()
        } else {
            binding.updateSettingsBtn.text = "Edit Profile"
            setEditMode()
        }
    }

    private fun setEditMode() {
        binding.setUserName.isEnabled = isEditMode
        binding.setBio.isEnabled = isEditMode
    }
}