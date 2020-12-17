package com.plumcookingwine.jetpack.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.yanyusong.y_divideritemdecoration.Dp2Px
import com.yanyusong.y_divideritemdecoration.Y_Divider

abstract class BaseDividerItemDecoration(context: Context?) : RecyclerView.ItemDecoration() {
    private var mPaint: Paint? = null
    private var context: Context? = null

    init {
        this.context = context
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.style = Paint.Style.FILL
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        //left, top, right, bottom
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val itemPosition = (child.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
            val divider = getDivider(itemPosition, parent)
            if (divider.getLeftSideLine().isHave()) {
                val lineWidthPx = Dp2Px.convert(context, divider.getLeftSideLine().getWidthDp())
                val startPaddingPx =
                    Dp2Px.convert(context, divider.getLeftSideLine().getStartPaddingDp())
                val endPaddingPx =
                    Dp2Px.convert(context, divider.getLeftSideLine().getEndPaddingDp())
                drawChildLeftVertical(
                    child,
                    c,
                    parent,
                    divider.getLeftSideLine().getColor(),
                    lineWidthPx,
                    startPaddingPx,
                    endPaddingPx
                )
            }
            if (divider.getTopSideLine().isHave()) {
                val lineWidthPx = Dp2Px.convert(context, divider.getTopSideLine().getWidthDp())
                val startPaddingPx =
                    Dp2Px.convert(context, divider.getTopSideLine().getStartPaddingDp())
                val endPaddingPx =
                    Dp2Px.convert(context, divider.getTopSideLine().getEndPaddingDp())
                drawChildTopHorizontal(
                    child,
                    c,
                    parent,
                    divider.topSideLine.getColor(),
                    lineWidthPx,
                    startPaddingPx,
                    endPaddingPx
                )
            }
            if (divider.getRightSideLine().isHave()) {
                val lineWidthPx = Dp2Px.convert(context, divider.getRightSideLine().getWidthDp())
                val startPaddingPx =
                    Dp2Px.convert(context, divider.getRightSideLine().getStartPaddingDp())
                val endPaddingPx =
                    Dp2Px.convert(context, divider.getRightSideLine().getEndPaddingDp())
                drawChildRightVertical(
                    child,
                    c,
                    parent,
                    divider.getRightSideLine().getColor(),
                    lineWidthPx,
                    startPaddingPx,
                    endPaddingPx
                )
            }
            if (divider.getBottomSideLine().isHave()) {
                val lineWidthPx = Dp2Px.convert(context, divider.getBottomSideLine().getWidthDp())
                val startPaddingPx =
                    Dp2Px.convert(context, divider.getBottomSideLine().getStartPaddingDp())
                val endPaddingPx =
                    Dp2Px.convert(context, divider.getBottomSideLine().getEndPaddingDp())
                drawChildBottomHorizontal(
                    child,
                    c,
                    parent,
                    divider.getBottomSideLine().getColor(),
                    lineWidthPx,
                    startPaddingPx,
                    endPaddingPx
                )
            }
        }
    }

    private fun drawChildBottomHorizontal(
        child: View,
        c: Canvas,
        parent: RecyclerView,
        @ColorInt color: Int,
        lineWidthPx: Int,
        startPaddingPx: Int,
        endPaddingPx: Int
    ) {
        var leftPadding = 0
        var rightPadding = 0
        leftPadding = if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            -lineWidthPx
        } else {
            startPaddingPx
        }
        rightPadding = if (endPaddingPx <= 0) {
            lineWidthPx
        } else {
            -endPaddingPx
        }
        val params = child
            .layoutParams as RecyclerView.LayoutParams
        val left = child.left - params.leftMargin + leftPadding
        val right = child.right + params.rightMargin + rightPadding
        val top = child.bottom + params.bottomMargin
        val bottom = top + lineWidthPx
        mPaint!!.color = color
        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint!!)
    }

    private fun drawChildTopHorizontal(
        child: View,
        c: Canvas,
        parent: RecyclerView,
        @ColorInt color: Int,
        lineWidthPx: Int,
        startPaddingPx: Int,
        endPaddingPx: Int
    ) {
        var leftPadding = 0
        var rightPadding = 0
        leftPadding = if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            -lineWidthPx
        } else {
            startPaddingPx
        }
        rightPadding = if (endPaddingPx <= 0) {
            lineWidthPx
        } else {
            -endPaddingPx
        }
        val params = child
            .layoutParams as RecyclerView.LayoutParams
        val left = child.left - params.leftMargin + leftPadding
        val right = child.right + params.rightMargin + rightPadding
        val bottom = child.top - params.topMargin
        val top = bottom - lineWidthPx
        mPaint!!.color = color
        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint!!)
    }

    private fun drawChildLeftVertical(
        child: View,
        c: Canvas,
        parent: RecyclerView,
        @ColorInt color: Int,
        lineWidthPx: Int,
        startPaddingPx: Int,
        endPaddingPx: Int
    ) {
        var topPadding = 0
        var bottomPadding = 0
        topPadding = if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            -lineWidthPx
        } else {
            startPaddingPx
        }
        bottomPadding = if (endPaddingPx <= 0) {
            lineWidthPx
        } else {
            -endPaddingPx
        }
        val params = child
            .layoutParams as RecyclerView.LayoutParams
        val top = child.top - params.topMargin + topPadding
        val bottom = child.bottom + params.bottomMargin + bottomPadding
        val right = child.left - params.leftMargin
        val left = right - lineWidthPx
        mPaint!!.color = color
        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint!!)
    }

    private fun drawChildRightVertical(
        child: View,
        c: Canvas,
        parent: RecyclerView,
        @ColorInt color: Int,
        lineWidthPx: Int,
        startPaddingPx: Int,
        endPaddingPx: Int
    ) {
        var topPadding = 0
        var bottomPadding = 0
        topPadding = if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            -lineWidthPx
        } else {
            startPaddingPx
        }
        bottomPadding = if (endPaddingPx <= 0) {
            lineWidthPx
        } else {
            -endPaddingPx
        }
        val params = child
            .layoutParams as RecyclerView.LayoutParams
        val top = child.top - params.topMargin + topPadding
        val bottom = child.bottom + params.bottomMargin + bottomPadding
        val left = child.right + params.rightMargin
        val right = left + lineWidthPx
        mPaint!!.color = color
        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint!!)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        //outRect 看源码可知这里只是把Rect类型的outRect作为一个封装了left,right,top,bottom的数据结构,
        //作为传递left,right,top,bottom的偏移值来用的
        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
        val divider = getDivider(itemPosition, parent)
        val left = if (divider.getLeftSideLine().isHave()) Dp2Px.convert(
            context,
            divider.getLeftSideLine().getWidthDp()
        ) else 0
        val top = if (divider.getTopSideLine().isHave()) Dp2Px.convert(
            context,
            divider.getTopSideLine().getWidthDp()
        ) else 0
        val right = if (divider.getRightSideLine().isHave()) Dp2Px.convert(
            context,
            divider.getRightSideLine().getWidthDp()
        ) else 0
        val bottom = if (divider.getBottomSideLine().isHave()) Dp2Px.convert(
            context,
            divider.getBottomSideLine().getWidthDp()
        ) else 0
        outRect[left, top, right] = bottom
    }


    abstract fun getDivider(itemPosition: Int, parent: RecyclerView): Y_Divider
}