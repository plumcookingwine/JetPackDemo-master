package com.plumcookingwine.jetpack

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.plumcookingwine.jetpack.base.ui.activity.BaseActivity
import com.plumcookingwine.jetpack.databinding.ActivityWebBinding

class WebActivity : BaseActivity() {

    private val mBinding: ActivityWebBinding by binding()

    override fun layoutId(): Int {
        return R.layout.activity_web
    }

    override fun initData() {
        super.initData()
        val url = intent.getStringExtra(INTENT_KEY_WEB_URL)
        val title = intent.getStringExtra(INTENT_KEY_WEB_TITLE)

        mBinding.toolBar.title = title ?: url ?: "没有传递URL"
        mBinding.webView.loadUrl(url ?: "")
    }

    companion object {

        private const val INTENT_KEY_WEB_TITLE = "INTENT_KEY_WEB_TITLE"
        private const val INTENT_KEY_WEB_URL = "INTENT_KEY_WEB_URL"

        fun toWebView(activity: FragmentActivity, url: String?, title: String?) {

            activity.startActivity(Intent(activity, WebActivity::class.java).apply {
                putExtra(INTENT_KEY_WEB_TITLE, title)
                putExtra(INTENT_KEY_WEB_URL, url)
            })

        }
    }
}