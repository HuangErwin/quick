package com.huang.test

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.huang.test.bean.TabEntity
import com.huang.test.base.BaseActivity
import com.huang.test.ui.home.DiscountFragment
import com.huang.test.ui.home.DlistFragment
import com.huang.test.ui.home.HomeFragment
import com.huang.test.ui.home.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun start() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    private val mTitles = arrayOf("首页", "优惠", "清单", "我的")
    // 未被选中的图标
    private val mIconSelectIds = intArrayOf(R.drawable.main_home_s, R.drawable.main_youhuiq_s, R.drawable.main_qingdan_s, R.drawable.main_wode_s)
    // 被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.drawable.main_home_u, R.drawable.main_youhuiq_un, R.drawable.main_qingdan_un, R.drawable.main_wode_un)

    private var mHomeFragment: HomeFragment? = null
    //优惠
    private var mDiscountFragment: DiscountFragment? = null
    private var mDlistFragment: DlistFragment? = null
    private var mMineFragment: MineFragment? = null
    private val mTabEntities = ArrayList<CustomTabEntity>()

    //初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {
            }
        })
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 首页
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance().let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1  //发现
            -> mDiscountFragment?.let {
                transaction.show(it)
            } ?: DiscountFragment.getInstance().let {
                mDiscountFragment = it
                transaction.add(R.id.fl_container, it, "discount")
            }
            2 //我的
            -> mDlistFragment?.let {
                transaction.show(it)
            } ?: DlistFragment.getInstance().let {
                mDlistFragment = it
                transaction.add(R.id.fl_container, it, "dlist")
            }
            3 //我的
            -> mMineFragment?.let {
                transaction.show(it)
            } ?: MineFragment.getInstance().let {
                mMineFragment = it
                transaction.add(R.id.fl_container, it, "mine")
            }
            else -> {
            }
        }

        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mDiscountFragment?.let { transaction.hide(it) }
        mDlistFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    private var mIndex = 0

    /**
     * 返回首页
     */
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mIndex = intent!!.getIntExtra("position", 0)
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}
