package com.hl.hltab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.hl.toptab.HlTopTabInfo
import org.hl.toptab.IHlTopTabLayout
import org.hl.toptab.utils.Dip2PixleUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTab()
    }

    /**
     * 初始化标签
     */
    fun initTab(){
        var tabInfoOne = HlTopTabInfo("标签一")
        var tabInfoTwo = HlTopTabInfo("标签二")
        var tabInfoThree = HlTopTabInfo(R.mipmap._crown,R.mipmap._crown)
        var tabInfoFour = HlTopTabInfo("标签四",R.mipmap._crown,R.mipmap._crown)

        var tabList = mutableListOf<HlTopTabInfo>()
        tabList.add(tabInfoOne)
        tabList.add(tabInfoTwo)
        tabList.add(tabInfoThree)
        tabList.add(tabInfoFour)

        hl_tab_layout.isFixWidth = true
        hl_tab_layout.indatorWidth = Dip2PixleUtil.dp2px(this,20f)
        hl_tab_layout.indatorHeight = Dip2PixleUtil.dp2px(this,3f)
        hl_tab_layout.inflateView(tabList)
        hl_tab_layout.setdefaultSel(tabInfoOne)

        hl_tab_layout.addTabListenner(object : IHlTopTabLayout.OnTabClickListener<HlTopTabInfo> {
            override fun onTabSelectedChange(index: Int, prevInfo: HlTopTabInfo?, nextInfo: HlTopTabInfo) {
                Toast.makeText(this@MainActivity,"点击了：${nextInfo.mSelTabName}",Toast.LENGTH_LONG).show()
            }
        })
    }
}
