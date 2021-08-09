package com.example.workindia.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workindia.R
import com.example.workindia.data.model.Item
import com.example.workindia.databinding.FragmentHorizontalListBinding
import com.example.workindia.databinding.FragmentVerticalListBinding
import com.example.workindia.ui.viewmodel.MainActivityViewModel
import com.example.workindia.ui.viewmodel.MyViewModelFactory
import com.example.workindia.utils.Status

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HorizontalListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HorizontalListFragment : Fragment() {
    private lateinit var viewModel: MainActivityViewModel

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentHorizontalListBinding
    private lateinit var adapter: HorizontalAdapter
    var productList: ArrayList<Item> = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHorizontalListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(
            requireActivity(),
            MyViewModelFactory()
        ).get(MainActivityViewModel::class.java)

        setHorizontalAdapter()
        startObserver()
    }

    private fun setHorizontalAdapter() {

        GridLayoutManager(
            requireContext(), // context
            3, // span count
            RecyclerView.VERTICAL, // orientation
            false // reverse layout
        ).apply {
            // specify the layout manager for recycler view
            binding.rvHorizontal.layoutManager = this
        }
        adapter = HorizontalAdapter(productList)
        binding.rvHorizontal.adapter = adapter

    }

    private fun startObserver() {

        viewModel.getProductList().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.horizontalShimmerLayout.startShimmer()
                    binding.rvHorizontal.visibility = View.GONE
                    binding.ivError.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.horizontalShimmerLayout.stopShimmer()
                    binding.horizontalShimmerLayout.visibility = View.GONE
                    binding.rvHorizontal.visibility = View.VISIBLE
                    binding.ivError.visibility = View.GONE

                    it.data?.let { it1 -> productList.addAll(it1) }
                }
                Status.ERROR -> {
                    binding.horizontalShimmerLayout.stopShimmer()
                    binding.horizontalShimmerLayout.visibility = View.GONE
                    binding.rvHorizontal.visibility = View.GONE
                    binding.ivError.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        binding.horizontalShimmerLayout.stopShimmer()
    }

    override fun onStop() {
        super.onStop()
        binding.horizontalShimmerLayout.stopShimmer()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HorizontalListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HorizontalListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}