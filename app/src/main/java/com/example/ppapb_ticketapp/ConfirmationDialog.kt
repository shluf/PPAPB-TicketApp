package com.example.ppapb_ticketapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.ppapb_ticketapp.databinding.ConfirmationDialogBinding
import java.text.NumberFormat
import java.util.Locale

class ConfirmationDialog : DialogFragment() {
    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_DESTINATION = "destination"
        private const val ARG_PRICE = "price"
        private const val ARG_DATE = "date"
        private const val ARG_TIME = "time"

        fun newInstance(name: String, destination: String, price: Int, date: String, time: String): ConfirmationDialog {
            val fragment = ConfirmationDialog()
            val args = Bundle().apply {
                putString(ARG_NAME, name)
                putString(ARG_DESTINATION, destination)
                putInt(ARG_PRICE, price)
                putString(ARG_DATE, date)
                putString(ARG_TIME, time)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity(), R.style.CustomAlertDialog)
        val inflater = requireActivity().layoutInflater
        val binding = ConfirmationDialogBinding.inflate(inflater)

        val name = arguments?.getString(ARG_NAME) ?: ""
        val destination = arguments?.getString(ARG_DESTINATION) ?: ""
        val price = arguments?.getInt(ARG_PRICE) ?: 0
        val date = arguments?.getString(ARG_DATE) ?: ""
        val time = arguments?.getString(ARG_TIME) ?: ""

        val formattedPrice = NumberFormat.getNumberInstance(Locale("in", "ID")).format(price)

        with(binding) {
            txtTarif.text = "Rp $formattedPrice"
            btnBatal.setOnClickListener {
                dismiss()
            }
            btnBeli.setOnClickListener {
                val intent = Intent(requireContext(), ConfirmationActivity::class.java).apply {
                    putExtra("NAME", name)
                    putExtra("DESTINATION", destination)
                    putExtra("PRICE", price)
                    putExtra("DATE", date)
                    putExtra("TIME", time)
                }
                startActivity(intent)
                dismiss()
            }
        }

        builder.setView(binding.root)
        return builder.create()
    }

}