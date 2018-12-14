package com.github.volfor.diary.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.github.volfor.diary.BR
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.direct
import org.kodein.di.generic.instance
import kotlin.reflect.KClass

abstract class BaseActivity(
    private val layoutId: Int? = null
) : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutId?.let { setContentLayout(it) }
    }

    protected open fun setContentLayout(@LayoutRes layoutId: Int) {
        setContentView(layoutId)
    }
}

abstract class BaseBoundActivity<out TBinding : ViewDataBinding>(
    layoutId: Int
) : BaseActivity(layoutId) {

    private lateinit var innerBinding: TBinding
    protected val binding: TBinding by lazy { innerBinding }

    override fun setContentLayout(layoutId: Int) {
        innerBinding = DataBindingUtil.setContentView(this, layoutId)
        innerBinding.setLifecycleOwner(this)
    }
}

abstract class BaseBoundVmActivity<out TBinding : ViewDataBinding, out TViewModel : ViewModel>(
    layoutId: Int,
    private val vmClass: KClass<TViewModel>
) : BaseBoundActivity<TBinding>(layoutId) {

    protected val vm: TViewModel by lazy {
        ViewModelProviders.of(this, direct.instance()).get(vmClass.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setVariable(BR.vm, vm)
        initObservers()
    }

    open fun initObservers() {
    }
}