package org.hl.toptab

/**
 * function: 定义tab容器接口
 *
 * <p>
 *      提供接口：
 *          1.初始化数据；
 *          2.添加监听；
 *          3.选中状态改变；
 *          4.默认选中
 *
 *       提供方法：
 *          1.计算tab宽度
 * </p>
 *
 * Created by elvis on  2021/06/17 10:21
 */
interface IHlTopTabLayout<T>{
    /**
     * 初始化tabView
     */
    fun inflateView(tabs:List<T>)

    /**
     * 设置默认选中tab
     */
    fun setdefaultSel(tabInfo:T)

    /**
     * tab加上点击事件
     */
    fun addTabListenner(listenner:OnTabClickListener<T>)

    /**
     * tab 点击后状态改变
     */
    fun onTabStatusChange(tabInfo: T)

    /**
     * 计算每个tab宽度
     */
    fun countWidth(size:Int):Int{
        return 0
    }

    open interface OnTabClickListener<T> {
        fun onTabSelectedChange(index: Int, prevInfo: T?, nextInfo: T)
    }
}

