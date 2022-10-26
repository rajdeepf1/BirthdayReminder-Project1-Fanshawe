package com.rajdeepsingh.birthdayreminderapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.rajdeepsingh.birthdayreminderapp.R
import com.rajdeepsingh.birthdayreminderapp.activities.utils.SharedPref
import com.rajdeepsingh.birthdayreminderapp.model.BirthdayModelDataClass
import kotlin.Int

class BirthdayRecyclerViewAdapter(
    private val context: Context,
    private val birthDayArrayList: MutableList<BirthdayModelDataClass>,
    private val noDataFoundTV: TextView,
    private val deleteAllDataFab: ImageView
) :
    RecyclerView.Adapter<BirthdayRecyclerViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewName: TextView
        var textViewBirthday: TextView
        var deleteImageView: ImageView
        var cardView: CardView

        init {
            // Define click listener for the ViewHolder's View.
            textViewName = view.findViewById(R.id.textViewName)
            textViewBirthday = view.findViewById(R.id.textViewBirthday)
            deleteImageView = view.findViewById(R.id.deleteImageView)
            cardView = view.findViewById(R.id.cardView)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val itemPosition = birthDayArrayList[position]
        viewHolder.textViewName.setText(itemPosition.nameOfPerson)
        viewHolder.textViewBirthday.setText(itemPosition.birthDayOfPerson)

        viewHolder.cardView.animation =
            AnimationUtils.loadAnimation(viewHolder.itemView.context, R.anim.anim)

        viewHolder.deleteImageView.setOnClickListener {
            removeItem(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun removeItem(position: Int) {
        birthDayArrayList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, birthDayArrayList.size)
        notifyDataSetChanged()

        var res: Boolean = SharedPref.saveObject(
            context,
            birthDayArrayList as ArrayList<BirthdayModelDataClass>
        )
        if (res) {
            Toast.makeText(context, "Data Deleted !", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(
                context,
                "Data Not Deleted !",
                Toast.LENGTH_SHORT
            ).show()

        }

        if (birthDayArrayList.size > 0) {
            deleteAllDataFab.visibility = View.VISIBLE
            noDataFoundTV.visibility = View.GONE
        } else {
            deleteAllDataFab.visibility = View.GONE
            noDataFoundTV.visibility = View.VISIBLE
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return birthDayArrayList.size
    }


}