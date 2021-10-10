package com.example.superhero.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.superhero.presenter.BasePresenter

abstract class BaseFragment<VB : ViewBinding , P : BasePresenter> : Fragment() {


    //region initialize variables
    internal lateinit var myDialog: Dialog
    abstract val LOG_TAG: String
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    private var _binding: ViewBinding? = null
    protected val binding
        get() = _binding as VB?

    abstract val presenterType : BasePresenter

    private lateinit var _presenter : BasePresenter
    protected val presenter :P ?
        get() = _presenter as P?

    //endregion

    //region fragment creation override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        _presenter = presenterType

        _binding = bindingInflater(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallbacks()
    }
    //endregion

    //region abstract functions
    abstract fun setup()
    abstract fun addCallbacks()
    //endregion

    protected fun log(value: String) {
        Log.v(LOG_TAG, value)
    }


}