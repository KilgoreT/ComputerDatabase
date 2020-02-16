package com.example.computerdatabase.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.computerdatabase.R
import com.example.computerdatabase.interactor.ComputerDbInteractorInterface
import com.example.computerdatabase.ui.computerDetail.ComputerDetailFragment
import com.example.computerdatabase.ui.computerDetail.ComputerDetailFragmentDirections
import com.example.computerdatabase.ui.computerList.ComputerListFragment
import com.example.computerdatabase.ui.computerList.ComputerListFragmentDirections
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ComputerListFragment.OnComputerListListener,
    ComputerDetailFragment.OnComputerDetailListener {

    @Inject
    lateinit var interactor: ComputerDbInteractorInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAppBar()
    }

    private fun initAppBar() {
        val appBarConfiguration = AppBarConfiguration
            .Builder(R.id.computerListFragment)
            .build()
        setupActionBarWithNavController(getNavController(), appBarConfiguration)

        getNavController().addOnDestinationChangedListener{ controller, destination, arguments ->
            if (destination.id == R.id.computerDetailFragment) {
                supportActionBar?.title = arguments?.get("name").toString()
            }
        }
    }

    private fun getNavController(): NavController {
        return findNavController(R.id.host)
    }

    private fun getNavDirection(id: Int, name: String): NavDirections? {
        return when(getNavController().currentDestination?.id) {
            R.id.computerDetailFragment -> ComputerDetailFragmentDirections.toDetail(id, name)
            R.id.computerListFragment -> ComputerListFragmentDirections.navigateToDetailFragment(id, name)
            else -> {
                null
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return getNavController().popBackStack() || super.onSupportNavigateUp()
    }

    override fun navigateToComputerDetail(id: Int, name: String) {
        val direction = getNavDirection(id, name)
        if (direction != null) {
            getNavController().navigate(direction)
        }
    }

    override fun navigateToSimilar(id: Int, name: String) {
        val direction = getNavDirection(id, name)
        if (direction != null) {
            getNavController().navigate(direction)
        }
    }
}
