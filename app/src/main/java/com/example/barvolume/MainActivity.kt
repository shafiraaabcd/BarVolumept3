package com.example.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvresult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvresult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

        if(savedInstanceState != null){
            var result = savedInstanceState.getString(STATE_RESULT) as String
            tvresult.text = result
        }
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_calculate){
            val inputLen = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            var isEmptyFields = false
            var isInvalidDouble = false

            if (TextUtils.isEmpty(inputLen)){
                isEmptyFields = true
                edtLength.error = "Field ini tidak boleh kosong"
            }
            if (TextUtils.isEmpty(inputWidth)){
                isEmptyFields = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }
            if (TextUtils.isEmpty(inputHeight)){
                isEmptyFields = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }

            val length = convertToDouble(inputLen)
            val width = convertToDouble(inputWidth)
            val height = convertToDouble(inputHeight)

            if (length == null) {
                isInvalidDouble = true
                edtLength.error = "Nilai tidak valid"
            }
            if (width == null) {
                isInvalidDouble = true
                edtWidth.error = "Nilai tidak valid"
            }
            if (height == null) {
                isInvalidDouble = true
                edtHeight.error = "Nilai tidak valid"
            }
            if(!isEmptyFields && !isInvalidDouble){
                val volume = height!!.toDouble() * length!!.toDouble() * width!!.toDouble()
                tvresult.text = volume.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvresult.text.toString())
    }

    private fun convertToDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException){
            null
        }
    }

    companion object{
        private const val STATE_RESULT = "state_result"
    }

}