package com.bondidos.task5.fragments

interface FragmentNavigation {
    fun navigateListFragment()
    fun navigateDetailsFragment(id: String)
    fun finish()
}
