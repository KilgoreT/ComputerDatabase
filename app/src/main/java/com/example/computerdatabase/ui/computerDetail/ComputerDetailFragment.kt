package com.example.computerdatabase.ui.computerDetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.computerdatabase.R
import kotlinx.android.synthetic.main.fragment_computer_detail.*

class ComputerDetailFragment : Fragment(), ComputerDetailAdapter.ComputerDetailAdapterListener {

    private var listener: OnComputerDetailListener? = null

    private val model: ComputerDetailViewModel by viewModels()
    private val args: ComputerDetailFragmentArgs by navArgs()

    private val adapter = ComputerDetailAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_computer_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.adapter = adapter
        model.loadData(args.id)
        model.detailLiveData.observe(this, Observer {
            adapter.setData(it)
        })
        model.similarLiveData.observe(this, Observer {
            adapter.setSimilar(it)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnComputerDetailListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnComputerListListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun loadSimilar(id: Int) {
        model.loadSimilar(id)
    }

    override fun onSimilarClick(id: Int, name: String) {
        listener?.navigateToSimilar(id, name)
    }

    interface OnComputerDetailListener {
        fun navigateToSimilar(id: Int, name: String)
    }
}
