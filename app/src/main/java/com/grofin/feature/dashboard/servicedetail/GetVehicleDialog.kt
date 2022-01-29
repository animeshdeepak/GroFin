package com.grofin.feature.dashboard.servicedetail

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.grofin.databinding.VechileUiDialogBinding
import java.text.SimpleDateFormat
import java.util.*


class GetVehicleDialog : DialogFragment() {
    private lateinit var binding: VechileUiDialogBinding

    var onDialogOk: ((vehicleNo: String?) -> Unit)? = null

    val calender = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = VechileUiDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.cancelBtn.setOnClickListener {
            dismiss()
        }

        binding.okBtn.setOnClickListener {
            onDialogOk?.invoke(binding.vehicleEt.text.toString())
            dismiss()
        }

        val date =
            DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                calender.set(Calendar.YEAR, year);
                calender.set(Calendar.MONTH, monthOfYear);
                calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel()
            }

        binding.dateEt.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext(), date, calender
                .get(Calendar.YEAR), calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH))
            // disable all past date
            val currentTimeInMillis = System.currentTimeMillis() - 1000
            datePickerDialog.datePicker.minDate = currentTimeInMillis
            // only enable dates for week from current date
            datePickerDialog.datePicker.maxDate = currentTimeInMillis + (1000 * 60 * 60 * 24 * 7)
            datePickerDialog.show()
        }
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy" //In which you need put here

        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.dateEt.setText(sdf.format(calender.time))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    companion object {
        const val TAG = "VEHICLE_DIALOG"
    }
}