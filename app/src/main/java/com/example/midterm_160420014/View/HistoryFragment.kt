package com.example.midterm_160420014.View

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.R
import com.example.midterm_160420014.ViewModel.ListHistoryViewModel
import com.example.midterm_160420014.ViewModel.ListMenuViewModel
import com.example.midterm_160420014.ViewModel.ListRestoViewModel

class HistoryFragment : Fragment() {
    private lateinit var historyVM:ListHistoryViewModel
    private lateinit var menuVM:ListMenuViewModel
    private val historyAdapter=HistoryListAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE)
        historyVM = ViewModelProvider(this).get(ListHistoryViewModel::class.java)
        menuVM = ViewModelProvider(this).get(ListMenuViewModel::class.java)
        sharedPref.getString("id","")?.let {
            menuVM.refreshAll()
            historyVM.refreshData(it.toInt())
        }
        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager= LinearLayoutManager(context)
        recView.adapter=historyAdapter
        observe()
    }
    fun observe(){
        historyVM.historyList.observe(viewLifecycleOwner, Observer {
            menuVM.menuList.observe(viewLifecycleOwner, Observer {menu->
                historyAdapter.updatehistoryList(it,menu)
            })
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }
}