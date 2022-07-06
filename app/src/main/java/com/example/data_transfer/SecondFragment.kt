package com.example.data_transfer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.data_transfer.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding
    val mainViewModel:MainViewModel by activityViewModels() // 이 프래그먼트가 포함된 액티비티의 뷰모델을 가져옴.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSecondBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Toast.makeText(context,mainViewModel.data,Toast.LENGTH_SHORT).show()
        setFragmentResultListener("toastStringData"){ requestKey, bundle ->  
            val result: String=bundle.getString("bundleKey","빈 값")
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show()
            Log.d("tag","second")
        }

        binding.button2.setOnClickListener {
            setFragmentResult("toastStringData2", bundleOf("bundleKey" to "okay"))
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}