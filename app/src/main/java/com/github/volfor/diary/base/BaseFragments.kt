package com.github.volfor.diary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.github.volfor.diary.BR
import org.koin.androidx.viewmodel.ext.getViewModel
import kotlin.reflect.KClass


abstract class BaseFragment(
    protected val layoutId: Int
) : Fragment() {
    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createView(inflater, container, savedInstanceState)
    }

    protected open fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(layoutId, container, false)
    }
}

abstract class BaseBoundFragment<out TBinding : ViewDataBinding>(layoutId: Int) : BaseFragment(layoutId) {
    private lateinit var innerBinding: TBinding
    protected val binding
        get() = innerBinding

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        innerBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)!!
        innerBinding.setLifecycleOwner(this)
        return innerBinding.root
    }
}

abstract class BaseBoundVmFragment<out TBinding : ViewDataBinding, out TViewModel : ViewModel>(
    layoutId: Int,
    private val vmClass: KClass<TViewModel>
) : BaseBoundFragment<TBinding>(layoutId) {
    protected val vm: TViewModel by lazy { getViewModel(vmClass) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.vm, vm)
        initObservers()
    }

    open fun initObservers() {
    }
}
