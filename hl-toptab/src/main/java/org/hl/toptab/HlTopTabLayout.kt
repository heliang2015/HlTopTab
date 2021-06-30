package org.hl.toptab

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import org.hl.toptab.utils.Dip2PixleUtil
import org.hl.toptab.utils.ScreenUtils

/**
 * tab容器
 */
class HlTopTabLayout @JvmOverloads constructor(context: Context, attributeSet: AttributeSet?=null, defstyle: Int?=0) :
    HorizontalScrollView(context, attributeSet, defstyle!!), IHlTopTabLayout<HlTopTabInfo> {

    /*控制属性*/
    // 是否是固定宽度(按等比平分)的tab
    var isFixWidth: Boolean = true
    var tabWidth: Int = 0

    /* indator 属性*/
    var indatorHeight:Int = Dip2PixleUtil.dp2px(context,3f)
    var indatorWidth:Int = Dip2PixleUtil.dp2px(context,30f)

    /* tab属性 */
    var tabSelTextColor: Int = R.color.tab_sel_text
    var tabUnSelTextColor: Int = R.color.tab_unsel_text

    @ColorInt
    var indatorColor:Int = resources.getColor(R.color.indator)

    private var mHltopTabIfo: HlTopTabInfo? = null
    private var mTabInfos: MutableList<HlTopTabInfo>? = null
    private var mTabListenners: MutableList<IHlTopTabLayout.OnTabClickListener<HlTopTabInfo>>? = null

    /**
     * 初始化选中
     */
    override fun inflateView(tabs: List<HlTopTabInfo>) {
        if (tabs.isEmpty()) return
        getRootView(true)
        restoreView()
        mTabInfos!!.addAll(tabs)
        // 设置tab 宽度
        if (tabWidth == 0) countWidth()

        /* 创建View */
        for (item in mTabInfos!!) {
            var hlTopTab = HlTopTab(context)
            hlTopTab.setIndatorAttr(indatorWidth,indatorHeight,indatorColor)
            hlTopTab.tabSelTextColor = tabSelTextColor
            hlTopTab.tabUnSelTextColor = tabUnSelTextColor
            hlTopTab.inflateView(item)
            var params = LinearLayout.LayoutParams(tabWidth,ViewGroup.LayoutParams.MATCH_PARENT)
            hlTopTab.layoutParams = params

            getRootView(false).addView(hlTopTab)
            hlTopTab.setOnClickListener {
                onTabStatusChange(item)
                mHltopTabIfo = item
            }

            addTabListenner(hlTopTab)
        }
    }

    /**
     * 防止多次初始化
     */
    private fun restoreView() {
        // 清除数据
        if (mTabInfos!==null &&  mTabInfos!!.isNotEmpty())
            mTabInfos!!.clear()
         else mTabInfos = mutableListOf()

        // 清除订阅者
        if (mTabListenners!= null && mTabListenners!!.isNotEmpty()) {
            var iterator = mTabListenners!!.iterator()
            while (iterator.hasNext()) {
                if (iterator.next() is HlTopTab) {
                    mTabListenners!!.remove(iterator.next())
                }
            }
        }
    }

    /**
     * 设置默认选中
     */
    override fun setdefaultSel(tabInfo: HlTopTabInfo) {
        onTabStatusChange(tabInfo!!)
        mHltopTabIfo = tabInfo
    }

    /**
     * 添加订阅者
     */
    override fun addTabListenner(listenner: IHlTopTabLayout.OnTabClickListener<HlTopTabInfo>) {
        if (mTabListenners == null) mTabListenners = mutableListOf()
        mTabListenners!!.add(listenner)
    }

    /**
     * 处理tab点击，通知订阅者
     */
    override fun onTabStatusChange(tabInfo: HlTopTabInfo) {
        for (item in mTabListenners!!) {
            item.onTabSelectedChange(mTabInfos!!.indexOf(mHltopTabIfo), mHltopTabIfo, tabInfo)
        }
    }

    /**
     * 计算tab宽度
     */
    private fun countWidth() {
        if (tabWidth == 0) {
            if (isFixWidth) {
                tabWidth = ScreenUtils.getScreenWidth(context) / mTabInfos!!.size
            } else {
                tabWidth = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }
    }

    /**
     * 创建容器
     */
    fun getRootView(isClear: Boolean): LinearLayout {
        var rootView: View? = getChildAt(0)
        if (rootView == null) {
            rootView = LinearLayout(context)
            val layoutParams = LinearLayout.LayoutParams(
               LayoutParams.MATCH_PARENT,
               LayoutParams.WRAP_CONTENT
            )
            rootView.orientation = LinearLayout.HORIZONTAL
            rootView.layoutParams = layoutParams

            addView(rootView)
        } else if (isClear) {
            (rootView as LinearLayout).removeAllViews()
        }

        return rootView as LinearLayout
    }


}
