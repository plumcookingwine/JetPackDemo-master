package com.plumcookingwine.jetpack.recyclerview

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.utils.getResColor
import com.yanyusong.y_divideritemdecoration.Y_Divider
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder

/**
 * Created by kangf on 2020/11/9.
 */
class CommonLinearDivider(
    context: Context?,
    private val dividerWidth: Float = 1f,
    colorId: Int = R.color.common_color_line,
    private val padding: Float = 0f
) : BaseDividerItemDecoration(context) {

    private var mDividerColor: Int = 0

    init {
        mDividerColor = getResColor(colorId)
    }

    override fun getDivider(itemPosition: Int, parent: RecyclerView): Y_Divider {
        return Y_DividerBuilder()
            .setBottomSideLine(true, mDividerColor, dividerWidth, padding, padding)
            .create()
    }


}