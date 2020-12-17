package com.plumcookingwine.jetpack.navigator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

/**
 * Created by kangf on 2020/12/14.
 *
 * Navigation跳转方式是通过replace实现的，这样每次 返回的时候都会重新创建fragment，
 * 为了避免这个问题， 我们自己实现一个导航
 */
@Navigator.Name("single_fragment")
class SingleNavigator(
    private val context: Context,
    private val manager: FragmentManager,
    private val containerId: Int
) : FragmentNavigator(context, manager, containerId) {


    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination {

        val tag = destination.id.toString()
        val transaction = manager.beginTransaction()
//        val currentFragment = manager.primaryNavigationFragment
//
//        if (currentFragment != null) {
//            transaction.hide(currentFragment)
//        }
        manager.fragments.forEach { transaction.hide(it) }
        var fragment = manager.findFragmentByTag(tag)
        if (fragment == null) {
            @Suppress("DEPRECATION")
            fragment = instantiateFragment(context, manager, destination.className, args)
            fragment.arguments = args
            transaction.add(containerId, fragment, tag)
        } else {
            transaction.show(fragment)
        }

        transaction.setPrimaryNavigationFragment(fragment)
        transaction.setReorderingAllowed(true)
        transaction.commit()
        return destination
    }
}