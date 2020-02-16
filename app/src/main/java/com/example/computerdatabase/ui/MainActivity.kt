package com.example.computerdatabase.ui

import android.net.Uri
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

class MainActivity : AppCompatActivity(), ComputerListFragment.OnFragmentInteractionListener,
    ComputerDetailFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
    }

    private fun getNavController(): NavController {
        return findNavController(R.id.host)
    }

    private fun getNavDirection(count: Int): NavDirections? {
        return when(getNavController().currentDestination?.id) {
            R.id.computerDetailFragment -> ComputerDetailFragmentDirections.toDetail(count)
            R.id.computerListFragment -> ComputerListFragmentDirections.navigateToDetailFragment(count)
            else -> {
                null
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return getNavController().popBackStack() || super.onSupportNavigateUp()
    }
}
