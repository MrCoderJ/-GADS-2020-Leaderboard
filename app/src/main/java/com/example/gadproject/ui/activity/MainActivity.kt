package com.example.gadproject.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gadproject.R
import com.example.gadproject.ui.fragment.LearningLeadersFragment
import com.example.gadproject.ui.fragment.SkillLeadersFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        btn_submit.setOnClickListener {
            startActivity(Intent(this, ProjectSubmissionActivity::class.java))
        }

        init()
    }

    companion object{
        const val HOURS = "hours"
    }

    private fun init(){
        viewPager2.adapter = object: FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return 2
            }

            override fun createFragment(position: Int): Fragment {
                return when (position){
                    0 -> {
                        LearningLeadersFragment.newInstance()
                    }
                    else -> {
                        SkillLeadersFragment.newInstance()
                    }
                }
            }

        }
        TabLayoutMediator(tabLayout, viewPager2){ tab: TabLayout.Tab, position: Int ->
            tab.text = when (position){
                0 -> "Learning Leaders"
                else -> "Skill IQ Leaders"
            }
        }.attach()
    }
}