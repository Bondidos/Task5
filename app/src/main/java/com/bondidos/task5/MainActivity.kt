package com.bondidos.task5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        setContentView(binding.root)
    }

    override fun navigateListFragment() {
        fragment        = CatListFragment.newInstance()
        transaction     = supportFragmentManager.beginTransaction()
        transaction.replace(binding.Container.id,fragment) .commit()

    }

    override fun navigateDetailsFragment() {
        fragment        = DetailsFragment.newInstance()
        transaction     = supportFragmentManager.beginTransaction()
        transaction.replace(binding.Container.id,fragment) .commit()
    }
}
