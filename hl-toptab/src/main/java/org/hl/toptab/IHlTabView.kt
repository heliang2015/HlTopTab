package org.hl.toptab

/**
 * function: tabView 必须实现的接口
 *
 * <p></p>
 *
 * Created by elvis on  2021/06/17 13:21
 */
interface IHlTabView<T>{

    /**
     * 初始化UI
     */
    fun inflateView(tabInfo:T)

    /**
     * 选中状态
     */
    fun onSelStatus(tabInfo:T)

    /**
     * 未选中状态
     */
    fun onUnSelSattus(tabInfo: T)
}