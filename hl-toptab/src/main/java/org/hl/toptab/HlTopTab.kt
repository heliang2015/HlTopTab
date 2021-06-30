package org.hl.toptab

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.NonNull

/**
 * HlTopTab view类
 */

class HlTopTab @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context!!, attrs, defStyleAttr), IHlTabView<HlTopTabInfo>,
    IHlTopTabLayout.OnTabClickListener<HlTopTabInfo> {

    /* tabText属性 */
    var tabTextSize: Float = 12f
    var tabSelTextColor: Int = R.color.tab_sel_text
    var tabUnSelTextColor: Int = R.color.tab_unsel_text

    /* 指示器属性*/
    var selTabPointColor: Int = 0
    var pointWidth:Int = 0
    var pointHeight:Int = 0

    var tvTab: TextView? = null
    var tvIndator: TextView? = null
    var ivIcon: ImageView? = null

    var mHlTopTabInfo: HlTopTabInfo? = null

    /**
     * 设置属性
     */
    fun setIndatorAttr(indatorWidth:Int,indatorHeight:Int,@ColorInt indatorColor:Int){
        pointWidth = indatorWidth
        pointHeight = indatorHeight
        selTabPointColor = indatorColor
    }

    override fun inflateView(@NonNull tabInfo: HlTopTabInfo) {
        mHlTopTabInfo = tabInfo
        val view = LayoutInflater.from(context).inflate(R.layout.view_tab, null)
        tvTab = view.findViewById(R.id.tv_tab)
        ivIcon = view.findViewById(R.id.iv_icon)
        tvIndator  = view.findViewById(R.id.tv_indator)

        /* 设置指示器属性 */
        val params = tvIndator!!.layoutParams
        if(pointWidth != 0) params.width = pointWidth
        if(pointHeight != 0) params.height = pointHeight
        if(selTabPointColor != 0) tvIndator!!.setBackgroundColor(selTabPointColor)

        /* 设置tab属性 */
        tvTab!!.textSize = tabTextSize

        /* 处理模式 */
        if(tabInfo.modle == TabModel.TEXT){
            ivIcon!!.visibility = View.GONE
            tvTab!!.visibility = View.VISIBLE
            tvTab!!.text = tabInfo.mSelTabName
        }else if(tabInfo.modle == TabModel.IMG){
            ivIcon!!.visibility = View.VISIBLE
            tvTab!!.visibility = View.GONE
        }else{
            ivIcon!!.visibility = View.VISIBLE
            tvTab!!.visibility = View.VISIBLE
            tvTab!!.text = tabInfo.mSelTabName
        }

        onUnSelSattus(tabInfo)
        addView(view)
    }

    /**
     * 选中样式
     */
    override fun onSelStatus(tabInfo: HlTopTabInfo) {
        tvIndator!!.visibility = View.VISIBLE
        tvTab!!.setTextColor(resources.getColor(tabSelTextColor))
    }

    /**
     * 未选中样式
     */
    override fun onUnSelSattus(tabInfo: HlTopTabInfo) {
        tvIndator!!.visibility = View.INVISIBLE
        tvTab!!.setTextColor(resources.getColor(tabUnSelTextColor))
    }

    /**
     * 处理tab点击
     */
    override fun onTabSelectedChange(index: Int, prevInfo: HlTopTabInfo?, nextInfo: HlTopTabInfo) {
        if ((mHlTopTabInfo != prevInfo && mHlTopTabInfo != nextInfo) || (prevInfo == nextInfo)) return
        if (mHlTopTabInfo == prevInfo) {
            // 复位不选中
            onUnSelSattus(mHlTopTabInfo!!)
        } else {
            // 选中
            onSelStatus(mHlTopTabInfo!!)
        }
    }
}