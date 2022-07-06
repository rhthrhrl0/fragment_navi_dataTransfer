package com.example.data_transfer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.data_transfer.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    //이렇게 쓰면 액티비티가 주인이 뷰모델이 아니라 이 프래그먼트가 주인인 뷰모델이 됨.
    //val mainViewModel:MainViewModel by viewModels() // 뷰모델을 하나 두고 서로 데이터를 주고 받음.
    val mainViewModel:MainViewModel by activityViewModels() // 이 프래그먼트가 포함된 액티비티의 뷰모델을 가져옴.
    //=> 하지만 이렇게 뷰모델을 토대로 데이터 교환하는 것도 안할거임.

    // 프래그먼트가 생성될때 실행되는 생명주기 함수임.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment 한 것을 리턴해야함. 프래그먼트의 UI가 없다면 널리턴하면 됨.
        binding= FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // onCreateView에서는 레이아웃의 뷰에 접근하면 안됨. onCreateView자체가 인플레이트하는 과정중이므로 불완전함.
        // 그래서 인플레이트한 레이아웃의 뷰에 대해서는 여기에서 접근해야 함.

        setFragmentResultListener("toastStringData2"){ requestKey, bundle ->
            val result: String=bundle.getString("bundleKey","빈 값")
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show()
            Log.d("tag","first")
        }

        binding.button.setOnClickListener {
            //mainViewModel.data="Hello" => 너무 비효율적임 안씀.

            //키가 이중 구조임.
            setFragmentResult("toastStringData", bundleOf("bundleKey" to "hello"))
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    override fun onPause() {
        super.onPause()
        //사용자가 프래그먼트를 떠나면 첫번째로 호출되는 메서드.

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}