package com.plumcookingwine.jetpack.weigets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

/**
 * @author kangf
 * @date 2020/7/3
 * @email 13146505580@163.com
 * @description 流式布局
 */
class FlowLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    // 子控件默认的横向间距
    private var mWidthSpacing = 16

    // 子控件默认的纵向间距
    private var mHeightSpacing = 10

    // 纪录总行数
    private val mLineHeights = mutableListOf<Int>()

    // 纪录每一行的View
    private val mAllLines = mutableListOf<List<View>>()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        mAllLines.clear()
        mLineHeights.clear()

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // 当前行使用了多少宽度
        var lineUsedWidth = 0
        // 当前行高
        var lineHeight = 0

        // 当前被申请的宽高
        var applyWidth = 0
        var applyHeight = 0

        var lineViews = mutableListOf<View>()
        /**
         * 测量子View
         */
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            val childWidthSpec = getChildMeasureSpec(
                widthMeasureSpec,
                paddingLeft + paddingRight,
                childView.layoutParams.width
            )
            val childHeightSpec = getChildMeasureSpec(
                heightMeasureSpec,
                paddingTop + paddingBottom,
                childView.layoutParams.height
            )
            childView.measure(childWidthSpec, childHeightSpec)

            val childWidth = childView.measuredWidth
            val childHeight = childView.measuredHeight

            // 如果测量当前子view宽度超过了父控件能给的最大宽度，那么就换行吧，
            // modify: 2021.02.16 注意最大宽度需要减去自身的padding
            if (lineUsedWidth + mWidthSpacing + childWidth > widthSize - paddingLeft - paddingRight) {
                mAllLines.add(lineViews)
                mLineHeights.add(lineHeight)
                applyWidth = applyWidth.coerceAtLeast(lineUsedWidth + mWidthSpacing)
                applyHeight += lineHeight + mHeightSpacing

                lineViews = mutableListOf()
                lineHeight = 0
                lineUsedWidth = 0
            }

            lineUsedWidth += mWidthSpacing + childWidth
            lineHeight = lineHeight.coerceAtLeast(childHeight)
            lineViews.add(childView)
        }

        // 这最后一行，还是在循环结束以后加比较容易理解
        mAllLines.add(lineViews)
        mLineHeights.add(lineHeight)
        applyWidth = applyWidth.coerceAtLeast(lineUsedWidth + mWidthSpacing)
        applyHeight += lineHeight + mHeightSpacing + paddingBottom + paddingTop

        /**
         * 测量当前ViewGroup
         */
        var height = 0
        var width = 0

        when (widthMode) {
            MeasureSpec.EXACTLY -> {
                width = widthSize
            }
            MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> { // 儿子要多少就给多少
                width = applyWidth
            }
            else -> {

            }
        }

        when (heightMode) {
            MeasureSpec.EXACTLY -> {
                height = heightSize
            }
            MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> { // 儿子要多少就给多少
                height = applyHeight
            }
            else -> {

            }
        }

        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        val lineCount = mAllLines.size

        var left = paddingLeft
        var top = paddingTop
        var right: Int
        var bottom: Int

        for (i in 0 until lineCount) {

            val lineHeight = mLineHeights[i]

            for (view in mAllLines[i]) {
                right = left + view.measuredWidth
                bottom = top + view.measuredHeight

                view.layout(left, top, right, bottom)

                left = right + mWidthSpacing
            }

            top += lineHeight + mHeightSpacing
            left = paddingLeft

        }
    }
}