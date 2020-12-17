@file:Suppress("unused")

package com.plumcookingwine.jetpack.navigator

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

/**
 * Created by kangf on 2020/12/14.
 */
class SingleNavHostFragment : NavHostFragment() {

    override fun onCreateNavController(navController: NavController) {
        super.onCreateNavController(navController)

        navController.navigatorProvider.addNavigator(
            SingleNavigator(
                requireContext(), childFragmentManager, id
            )
        )
    }
}