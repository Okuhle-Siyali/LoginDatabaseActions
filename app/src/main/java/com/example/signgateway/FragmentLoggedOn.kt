package com.example.signgateway

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.signgateway.databinding.FragmentLoggedOnBinding

class FragmentLoggedOn : Fragment() {

    private lateinit var binding: FragmentLoggedOnBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_logged_on, container, false
        )
        return binding.root
    }

}