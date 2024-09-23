package com.example.ppapb_ticketapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ppapb_ticketapp.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedDate: String = ""
    private var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupDatePicker()
        setupTimePicker()
        setupBookButton()
    }

    private fun setupSpinner() {
        val destinations = arrayOf("Jakarta", "Bandung", "Surabaya", "Yogyakarta")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, destinations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDestination.adapter = adapter
    }

    private fun setupDatePicker() {
        binding.btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                R.style.CustomDatePickerStyle,
                { _, selectedYear, selectedMonth, selectedDay ->
                    selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    binding.btnSelectDate.text = "$selectedDate"
                },
                year, month, day
            )
            datePickerDialog.show()
        }
    }

    private fun setupTimePicker() {
        binding.btnSelectTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this,
                R.style.CustomTimePickerStyle,
                { _, selectedHour, selectedMinute ->
                    selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                    binding.btnSelectTime.text = "$selectedTime"
                },
                hour, minute, true
            )
            timePickerDialog.show()
        }
    }

    private fun setupBookButton() {
        binding.btnBook.setOnClickListener {
            val name = binding.edtPemesan.text.toString().trim()
            val destination = binding.spinnerDestination.selectedItem.toString()
            val price = when (destination) {
                "Jakarta" -> 100000
                "Bandung" -> 75000
                "Surabaya" -> 150000
                "Yogyakarta" -> 125000
                else -> 0
            }

            if (name.isEmpty()) {
                Toast.makeText(this, "Masukkan nama pemesan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedDate.isEmpty() || selectedTime.isEmpty()) {
                Toast.makeText(this, "Pilih tanggal dan waktu terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            showConfirmationDialog(name, destination, price)
        }
    }

    private fun showConfirmationDialog(name: String, destination: String, price: Int) {
        val confirmationDialog = ConfirmationDialog.newInstance(name, destination, price, selectedDate, selectedTime)
        confirmationDialog.show(supportFragmentManager, "ConfirmationDialog")
    }

}