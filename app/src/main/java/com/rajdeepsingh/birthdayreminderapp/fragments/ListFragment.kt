package com.rajdeepsingh.birthdayreminderapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajdeepsingh.birthdayreminderapp.R
import com.rajdeepsingh.birthdayreminderapp.activities.utils.SharedPref
import com.rajdeepsingh.birthdayreminderapp.adapters.BirthdayRecyclerViewAdapter
import com.rajdeepsingh.birthdayreminderapp.model.BirthdayModelDataClass


class ListFragment : Fragment(), View.OnClickListener {

    private lateinit var recyclerView: RecyclerView
    lateinit var noDataFoundTV: TextView
    lateinit var deleteAllDataFab: ImageView
    lateinit var list: ArrayList<BirthdayModelDataClass>
    var adapter: BirthdayRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        noDataFoundTV = view.findViewById(R.id.noDataFoundTV)
        deleteAllDataFab = view.findViewById(R.id.deleteAllDataFab)
        deleteAllDataFab.setOnClickListener(this)
        list = ArrayList()
        setDataToRecyclerView()
        return view
    }

    fun setDataToRecyclerView() {
        list = SharedPref.getObject(requireContext())
        if (list.size > 0) {
            noDataFoundTV.visibility = View.GONE
            deleteAllDataFab.visibility = View.VISIBLE
            // This will pass the ArrayList to our Adapter
            adapter = BirthdayRecyclerViewAdapter(requireContext(), list,noDataFoundTV,deleteAllDataFab)

            // Setting the Adapter with the recyclerview
            recyclerView.adapter = adapter
        } else {
            noDataFoundTV.visibility = View.VISIBLE
            deleteAllDataFab.visibility = View.GONE
        }

        // this creates a vertical layout Manager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.deleteAllDataFab -> {
                showAlert()
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(requireContext())
        //set title for alert dialog
        builder.setTitle("Delete")
        //set message for alert dialog
        builder.setMessage("Are you sure you want to delete all items?")
        builder.setIcon(R.drawable.database)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            deleteAllData()
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun deleteAllData() {
        // clearing list and passing empty list to recyclerview adapter
        list.clear()
        adapter = BirthdayRecyclerViewAdapter(
            requireContext(),
            list,
            noDataFoundTV,
            deleteAllDataFab
        )
        recyclerView.adapter = adapter
        SharedPref.saveObject(requireContext(), list)
        noDataFoundTV.visibility = View.VISIBLE
        deleteAllDataFab.visibility = View.GONE
    }

}