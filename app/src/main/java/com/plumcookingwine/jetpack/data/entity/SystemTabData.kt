package com.plumcookingwine.jetpack.data.entity

/**
 * Created by kangf on 2020/12/21.
 */
data class SystemTabData(
    val children: List<SystemTabData>?,
    val courseId: Int?,
    val id: Int?,
    val name: String?,
    val order: Int?,
    val parentChapterId: Int?,
    val userControlSetTop: Boolean?,
    val visible: Int?
)