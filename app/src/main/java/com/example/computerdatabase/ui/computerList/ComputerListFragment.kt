package com.example.computerdatabase.ui.computerList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.computerdatabase.R
import com.example.computerdatabase.ui.base.ErrorResult
import com.example.computerdatabase.ui.base.LoadingResult
import com.example.computerdatabase.ui.base.SuccessEmptyResult
import com.example.computerdatabase.ui.base.SuccessNonEmptyResult
import com.example.computerdatabase.ui.computerList.adapter.ComputerListAdapter
import kotlinx.android.synthetic.main.error_status.*
import kotlinx.android.synthetic.main.fragment_computer_list.*

class ComputerListFragment : Fragment(), ComputerListAdapter.ComputerListAdapterListener {

    private var listener: OnComputerListListener? = null

    private val model: ComputerListViewModel by viewModels()
    private val adapter = ComputerListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_computer_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initState()
        initRecycler()

        etSearch.apply {
            addTextChangedListener(model.watcher)
        }
    }

    private fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter

        model.pagedListLiveData.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnComputerListListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnComputerListListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun initState() {
        model.getState().observe(this,
            Observer {
                when (it) {
                    is LoadingResult -> showLoading()
                    is SuccessNonEmptyResult -> showPointList()
                    is SuccessEmptyResult -> showEmpty()
                    is ErrorResult -> showError(it.message)
                }
            }
        )
    }


    private fun showLoading() {
        recycler.visibility = View.GONE
        empty.visibility = View.GONE
        error.visibility = View.GONE
        loading.visibility = View.VISIBLE
    }

    private fun showPointList() {
        loading.visibility = View.GONE
        empty.visibility = View.GONE
        error.visibility = View.GONE
        recycler.visibility = View.VISIBLE
    }

    private fun showEmpty() {
        recycler.visibility = View.GONE
        loading.visibility = View.GONE
        error.visibility = View.GONE
        empty.visibility = View.VISIBLE
    }

    private fun showError(message: String) {
        recycler.visibility = View.GONE
        loading.visibility = View.GONE
        empty.visibility = View.GONE
        tvError.text = message
        error.visibility = View.VISIBLE
    }

    override fun onClick(id: Int, name: String) {
        listener?.navigateToComputerDetail(id, name)
    }

    interface OnComputerListListener {
        fun navigateToComputerDetail(id: Int, name: String)
    }
}
