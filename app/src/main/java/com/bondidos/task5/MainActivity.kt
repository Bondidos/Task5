package com.bondidos.task5

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bondidos.task5.databinding.ActivityMainBinding
import com.bondidos.task5.fragments.CatListFragment
import com.bondidos.task5.fragments.DetailsFragment
import com.bondidos.task5.fragments.FragmentNavigation

class MainActivity : AppCompatActivity(), FragmentNavigation{

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        //toolbar
        binding.toolbarActionbar.setNavigationOnClickListener {
            navigateListFragment()
        }

        setContentView(binding.root)
    }

    override fun navigateListFragment() {

        binding.toolbarActionbar.navigationIcon = null

        fragment        = CatListFragment.newInstance()
        transaction     = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.flip_in,
            R.anim.flip_out
        )
        transaction.replace(binding.Container.id,fragment).commit()

    }

    override fun navigateDetailsFragment() {

        binding.toolbarActionbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)

        fragment        = DetailsFragment.newInstance()
        transaction     = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.flip_in,
            R.anim.flip_out
        )
        transaction.replace(binding.Container.id,fragment).commit()
    }
    //todo save instance
}
