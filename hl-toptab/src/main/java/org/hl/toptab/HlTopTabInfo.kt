package org.hl.toptab

import androidx.annotation.DrawableRes

/**
 * tab模型类
 */
open class HlTopTabInfo {

    var modle: TabModel? = null

    /* Icon相关属性*/
    @DrawableRes
    var mTabIcon: Int? = 0
    @DrawableRes
    var mUnSelTabIcon: Int? = 0

    /* 文字相关属性*/
    var mSelTabName: String? = null

    /**
     * 只有文字
     */
    constructor(tabName: String) {
        mSelTabName = tabName
        modle = TabModel.TEXT
    }

    /**
     * 只有图片
     */
    constructor(tabIcon: Int, unSelTabIcon: Int) {
        mTabIcon = tabIcon
        mUnSelTabIcon = unSelTabIcon
        modle = TabModel.IMG
    }

    /**
     * 混合模式
     */
    constructor(tabName: String, tabIcon: Int, unSelTabIcon: Int) {
        mSelTabName = tabName
        mTabIcon = tabIcon
        mUnSelTabIcon = unSelTabIcon
        modle = TabModel.IMG
        modle = TabModel.MIX
    }
}

/**
 * tab模式
 */
enum class TabModel {
    IMG,
    TEXT,
    MIX
}