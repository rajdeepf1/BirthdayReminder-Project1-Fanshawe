package com.rajdeepsingh.birthdayreminderapp.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.rajdeepsingh.birthdayreminderapp.R
import com.rajdeepsingh.birthdayreminderapp.activities.utils.Constant
import com.rajdeepsingh.birthdayreminderapp.activities.utils.SharedPref
import com.rajdeepsingh.birthdayreminderapp.model.BirthdayModelDataClass
import java.text.SimpleDateFormat
import java.util.*


class AddBirthdayFragment : Fragment(), View.OnClickListener {

    lateinit var calendarImageView: ImageView
    lateinit var date_edit_text: TextInputEditText
    lateinit var btnSaveBirthday: AppCompatButton
    lateinit var name_edit_text: TextInputEditText
    lateinit var list: ArrayList<BirthdayModelDataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_add_birthday, container, false)
        calendarImageView = view.findViewById(R.id.calendarImageView)
        date_edit_text = view.findViewById(R.id.date_edit_text)
        name_edit_text = view.findViewById(R.id.name_edit_text)
        btnSaveBirthday = view.findViewById(R.id.btnSaveBirthday)

        calendarImageView.setOnClickListener(this)
        btnSaveBirthday.setOnClickListener(this)

        val data: ArrayList<BirthdayModelDataClass> = SharedPref.getObject(requireContext())

        if (data.size > 0) {
            list = data
        } else {
            list = ArrayList()
        }


        return view
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.calendarImageView -> {
                var cal = Calendar.getInstance()
                val dateSetListener =
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, monthOfYear)
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        val myFormat = Constant.dateFormat
                        val sdf = SimpleDateFormat(myFormat, Locale.CANADA)
                        date_edit_text.setText(sdf.format(cal.time))
                    }
                DatePickerDialog(
                    requireContext(), dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            R.id.btnSaveBirthday -> {
                val name_of_a_person = name_edit_text.text.toString()
                val birthday_of_a_person = date_edit_text.text.toString()
                if (name_of_a_person.trim().isEmpty() || birthday_of_a_person.trim().isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Please enter details into the required fields !",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    list.add(BirthdayModelDataClass(name_of_a_person, birthday_of_a_person))
                    var res: Boolean = SharedPref.saveObject(requireContext(), list)
                    if (res) Toast.makeText(requireContext(), "Data Saved !", Toast.LENGTH_SHORT)
                        .show() else Toast.makeText(
                        requireContext(),
                        "Data Not Saved !",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

        }
    }

//    companion object {
//
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            AddBirthdayFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}