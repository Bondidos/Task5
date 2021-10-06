package com.bondidos.task5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bondidos.task5.databinding.ActivityMainBinding
import com.bondidos.task5.fragments.CatListFragment
import com.bondidos.task5.fragments.DetailsFragment
import com.bondidos.task5.fragments.FragmentNavigation

private const val SHOW_NAVI_ICON = "naviIcon"

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
        savedInstanceState?.let {
            if (savedInstanceState.getBoolean(SHOW_NAVI_ICON)) {
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
                R.animator.flip_in,
                R.animator.flip_out
            )
            .replace(binding.Container.id, CatListFragment.newInstance())
            .commit()
    }

    override fun navigateDetailsFragment(id: String) {
        binding.toolbarActionbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
        isShowNavigationIcon = true
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.animator.flip_in,
                R.animator.flip_out
            )
            .replace(binding.Container.id, DetailsFragment.newInstance(id))
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(SHOW_NAVI_ICON, isShowNavigationIcon)
        super.onSaveInstanceState(outState)
    }
}
