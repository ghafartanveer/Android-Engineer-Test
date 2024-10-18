package com.example.demoweatherapp.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.demoweatherapp.databinding.LayoutBottomSheetBinding
import com.example.demoweatherapp.interfaces.OnLocationSelectedListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LocationBottomSheet(val locationCallBack:OnLocationSelectedListener) : BottomSheetDialogFragment() {
    lateinit var binding: LayoutBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = LayoutBottomSheetBinding.inflate(inflater, container, false)


        // Set click listener for the button
        binding.btnSearch.setOnClickListener {
            val location = binding.etSearch.text.toString()
            if (location.isNotEmpty()) {
                locationCallBack.onLocationSelected(location);
                dismiss()
            } else {
                Toast.makeText(context, "Please enter a location", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}
