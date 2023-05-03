package com.jitheshxavier.dobcalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDateTextView: TextView? = null
    private var selectedDateInMinutesTextView: TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        selectedDateTextView = findViewById(R.id.selectedDateTextView)
        selectedDateInMinutesTextView = findViewById(R.id.ageInMinutesTextView)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        var myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)

        var datePickerDialog = DatePickerDialog(this,
            { _, year, month, day ->
                val selectedDate = "$day/${month+1}/${year}"
                selectedDateTextView?.text = selectedDate
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = simpleDateFormat.parse(selectedDate)
                theDate.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    var currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                    currentDate.let {
                        val currentDateInMintues = currentDate.time / 60000
                        var differenceInMinutes = currentDateInMintues - selectedDateInMinutes
                        selectedDateInMinutesTextView?.text = differenceInMinutes.toString()
                    }
                }
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
    }
}