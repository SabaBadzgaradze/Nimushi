package com.example.saba_badzgaradze_group_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var phoneEditText: EditText
    private lateinit var smsCodeEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatPasswordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phoneEditText = findViewById(R.id.phone)
        smsCodeEditText = findViewById(R.id.SMSCode)
        passwordEditText = findViewById(R.id.password)
        repeatPasswordEditText = findViewById(R.id.repeatPassword)

        val resetButton = findViewById<Button>(R.id.btnReset)
        resetButton.setOnClickListener {
            if (validateForm()) {
                showToast("ახალ პაროლს მიიღებთ SMS-ით")
            }
        }

    }

    private fun validateForm(): Boolean {
        val phone = phoneEditText.text.toString()
        if (!isValidPhoneNumber(phone)) {
            showToast("გთხოვთ ჩაწერეთ ვალიდური ტელეფონის ნომერი")
            return false
        }

        val smsCode = smsCodeEditText.text.toString()
        if (smsCode.length != 4) {
            showToast("SMS კოდი უნდა შეიცავდეს 4 სიმბოლოს")
            return false
        }

        val password = passwordEditText.text.toString()
        val repeatPassword = repeatPasswordEditText.text.toString()

        if (password.length < 8 || !password.matches(".*\\d.*".toRegex())) {
            showToast("პაროლი უნდა შეიცავდეს მინიმუმ 8 სიმბოლოს და მინიმუმ 1 ციფრს")
            return false
        }

        if (password != repeatPassword) {
            showToast("პაროლი არ ემთხვევა ერთმანეთს")
            return false
        }

        return true
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
        return phone.length >= 9 && phone.startsWith("5") && phone.isNotEmpty()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}