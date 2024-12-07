package com.iamAsikur.omdbmovies.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class CoreBaseFragment< VB : ViewBinding> : Fragment() {

    val TAG by lazy { this.javaClass.simpleName }

    private var _binding: VB? = null
    val binding: VB
        get() = _binding!!

    abstract fun getViewBinding(): VB
    protected open fun setupObserver() {}
    protected open fun initializeData() {}
    protected abstract fun setupUI()
    protected open fun callInitialApi() {}
    protected open fun logView(){}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logView()
        initializeData()
        setupObserver()
        setupUI()
        callInitialApi()
    }

    protected fun showProgressBar(isLoading: Boolean, view: View) {
        if (isLoading) {
            view.visibility = View.VISIBLE
            requireActivity().window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            view.visibility = View.GONE
            requireActivity().window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    protected fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}