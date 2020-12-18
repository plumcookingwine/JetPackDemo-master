package com.plumcookingwine.jetpack.base.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.plumcookingwine.jetpack.base.delegate.FragmentDataBindingDelegate
import com.plumcookingwine.jetpack.loadsir.EmptyCallback
import com.plumcookingwine.jetpack.loadsir.ErrorCallback
import com.plumcookingwine.jetpack.loadsir.LoadResult
import com.plumcookingwine.jetpack.loadsir.LoadingCallback


/**
 * Created by kangf on 2020/12/14.
 */
abstract class BaseFragment : Fragment() {

    lateinit var mActivity: FragmentActivity

    private var mLazyLoaded = false

    /**
     * 对ViewBinding进行代理
     */
    inline fun <reified T : ViewBinding> Fragment.binding() =
        FragmentDataBindingDelegate(T::class.java, this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as FragmentActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { getBundle(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutId().let {
            return if (it == 0) {
                null
            } else {
                inflater.inflate(layoutId(), container, false)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
        if (!enableLazyLoad()) {
            Looper.myLooper()?.let {
                Handler(it).post { lazyLoad() }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (enableLazyLoad() && !mLazyLoaded) {
            Looper.myLooper()?.let {
                Handler(it).post {
                    lazyLoad()
                    mLazyLoaded = true
                }
            }
        }
    }

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun initData()

    protected open fun initListener() {}

    protected open fun getBundle(bundle: Bundle) {}

    protected open fun lazyLoad() {}

    protected open fun enableLazyLoad(): Boolean {
        return false
    }

    protected open fun reload() {}

    fun registerLoadSir(liveData: LiveData<LoadResult>, view: View) {

        var pageManager: LoadService<Callback>? = null

        @Suppress("UNCHECKED_CAST")
        pageManager = LoadSir.getDefault().register(view) {
            pageManager?.showCallback(LoadingCallback::class.java)
            reload()
        }
            .setCallBack(LoadingCallback::class.java) { _, _ -> }
            .setCallBack(EmptyCallback::class.java) { _, _ -> }
            .setCallBack(ErrorCallback::class.java) { _, _ -> }
                as LoadService<Callback>

        pageManager.showCallback(LoadingCallback::class.java)
        liveData.observe(this) {
            when (it) {
                is LoadResult.LOADING -> pageManager.showCallback(LoadingCallback::class.java)
                is LoadResult.ERROR -> pageManager.showCallback(ErrorCallback::class.java)
                is LoadResult.EMPTY -> pageManager.showCallback(EmptyCallback::class.java)
                is LoadResult.SUCCESS -> pageManager.showSuccess()
                else -> pageManager.showSuccess()
            }

        }
    }

}