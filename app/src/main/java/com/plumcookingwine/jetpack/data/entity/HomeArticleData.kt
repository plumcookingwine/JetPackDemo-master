package com.plumcookingwine.jetpack.data.entity

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by kangf on 2020/12/14.
 */

//@Entity
data class HomeArticleData(
    //@PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val curPage: Int?,
    val offset: Int?,
    val over: Boolean?,
    val pageCount: Int?,
    val size: Int?,
    val total: Int?,
    //@Embedded
    val datas: List<Data>?
) {
    data class Data(
        val id: Int?,
        val apkLink: String?,
        val audit: Int?,
        var author: String?,
        val canEdit: Boolean?,
        val chapterId: Int?,
        val chapterName: String?,
        val collect: Boolean?,
        val courseId: Int?,
        val desc: String?,
        val descMd: String?,
        val envelopePic: String?,
        val fresh: Boolean?,
        val link: String?,
        val niceDate: String?,
        val niceShareDate: String?,
        val origin: String?,
        val prefix: String?,
        val projectLink: String?,
        val publishTime: Long?,
        val realSuperChapterId: Int?,
        val selfVisible: Int?,
        val shareDate: Long?,
        val shareUser: String?,
        val superChapterId: Int?,
        val superChapterName: String?,
        val tags: List<Any>?,
        val title: String?,
        val type: Int?,
        val userId: Int?,
        val visible: Int?,
        val zan: Int?,
        var isTicked: Boolean = false
    ) {

        companion object {

            val mDiffer = object : DiffUtil.ItemCallback<Data>() {
                override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                    return oldItem.id == newItem.id && oldItem.title == newItem.title
                }

                override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                    return oldItem == newItem
                }

            }
        }
    }
}