package com.example.ppapb_ticketapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ppapb_ticketapp.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("NAME")
        val destination = intent.getStringExtra("DESTINATION")
        val date = intent.getStringExtra("DATE")
        val time = intent.getStringExtra("TIME")

        val confirmationText = """
            Nama    : $name
            Jam       : $time WIB
            Tanggal : $date
            Tujuan   : $destination
        """.trimIndent()

        binding.tvConfirmation.text = confirmationText
        binding.tvConfirmationTitle.text = "Berhasil Mendapatkan Tiket!"
    }
}