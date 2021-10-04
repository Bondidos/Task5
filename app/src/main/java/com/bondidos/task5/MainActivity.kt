package com.bondidos.task5

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bondidos.task5.databinding.ActivityMainBinding
import com.bondidos.task5.fragments.CatListFragment
import com.bondidos.task5.fragments.DetailsFragment
import com.bondidos.task5.fragments.FragmentNavigation

private const val setNaviIcon = "naviIcon"

class MainActivity : AppCompatActivity(), FragmentNavigation {

    private lateinit var binding: ActivityMainBinding
    private var isShowNavigationIcon = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        // toolbar
        binding.toolbarActionbar.setNavigationOnClickListener {
            navigateListFragment()
        }
        setContentView(binding.root)

        // show icon
        if(savedInstanceState !=null) {
            val showIcon = savedInstanceState.getBoolean(setNaviIcon)
            if (showIcon) {
                binding.toolbarActionbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
                isShowNavigationIcon = true
            } else binding.toolbarActionbar.navigationIcon = null
        }
    }

    override fun navigateListFragment() {

        binding.toolbarActionbar.navigationIcon = null
        isShowNavigationIcon = false
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.flip_in,
                R.anim.flip_out
            )
            .replace(binding.Container.id,CatListFragment.newInstance())
            .commit()

    }

    override fun navigateDetailsFragment() {

        binding.toolbarActionbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
        isShowNavigationIcon = true
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.flip_in,
                R.anim.flip_out
            )
            .replace(binding.Container.id,DetailsFragment.newInstance())
            .commit()
        }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(setNaviIcon,isShowNavigationIcon)
        super.onSaveInstanceState(outState)
    }

}
