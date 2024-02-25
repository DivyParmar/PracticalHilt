package com.practical.divy.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.practical.divy.viewModels.ApiViewModel
import com.practical.divy.base.BaseActivity
import com.practical.divy.databinding.ActivityMainBinding
import com.practical.divy.viewModels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding

    val viewModel by viewModels<ApiViewModel>()
    val roomViewModel by viewModels<RoomViewModel>()

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        attachObservers()
        initView()
        viewModel.callDogApi()

        roomViewModel.insertName("Divy Parmar")
    }

    private fun attachObservers() {
        viewModel.dogResponse.observe(this) { response ->
            manageApiResult(response) { data, message ->
                Log.e("MainActivity", "onCreate: data " + data + " message " + message)
            }
        }

        roomViewModel.insertNameResponse.observe(this){
            Log.e("MainActivity", "attachObservers: insertNameResponse "+it)
        }
    }

    private fun initView() {

    }


/*
    <androidx.viewpager2.widget.ViewPager2
    android:id="@+id/viewpager"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_marginTop="@dimen/_10sdp"
    android:overScrollMode="never"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/tvAll" />


    var fragmentList = mutableListOf<Fragment>()
    fragmentList.clear()
//        fragmentList.add(MatchDetailsFragment(0, matchModel._id.toString()))
//        fragmentList.add(MatchDetailsFragment(1, matchModel._id.toString()))
    fragmentList.add(MatchDetailsFragmentAll(matchModel._id.toString()))
    fragmentList.add(MatchDetailsFragmentMyTrades(matchModel._id.toString()))

    setupViewPager()


    private fun setupViewPager() {

        val demoAdapter = ScreenSlidePagerAdapter(this)
        binding.viewpager.apply {
            adapter = demoAdapter
            registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int, positionOffset: Float, positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    selectTab(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })

            post {
                setCurrentItem(0, false)
            }
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }

    private fun selectTab(position: Int) {
        binding.apply {
            when (position) {
                0 -> {
                    tvAll.background = getDrawable(R.drawable.bg_card_darkblack)
                    tvMyTrades.background = getDrawable(R.drawable.bg_cardsqure)

                    tvAll.setTextColor(
                        ContextCompat.getColor(
                            this@MatchDetailsActivity,
                            R.color.colorWhite
                        )
                    )
                    tvMyTrades.setTextColor(
                        ContextCompat.getColor(
                            this@MatchDetailsActivity,
                            R.color.colorDarkBlack
                        )
                    )

                }

                1 -> {
                    tvAll.background = getDrawable(R.drawable.bg_cardsqure)
                    tvMyTrades.background = getDrawable(R.drawable.bg_card_darkblack)

                    tvAll.setTextColor(
                        ContextCompat.getColor(
                            this@MatchDetailsActivity,
                            R.color.colorDarkBlack
                        )
                    )
                    tvMyTrades.setTextColor(
                        ContextCompat.getColor(
                            this@MatchDetailsActivity,
                            R.color.colorWhite
                        )
                    )

                }
            }
        }
    }
*/

}