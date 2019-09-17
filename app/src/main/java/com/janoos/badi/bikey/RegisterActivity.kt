package com.janoos.badi.bikey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern



class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var editTextPhoneNumber: EditText
    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText


    private lateinit var buttonRegister: Button
    private lateinit var buttonCancel: Button
    private lateinit var textViewLoginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // initializing the views
        initViews()

        // initializing the listeners
        initListeners()
    }

    /**
     * Method is to initialize views
     */
    private fun initViews() {

        editTextPhoneNumber = findViewById<View>(R.id.txtRegPhoneNumber) as EditText
        editTextFirstName = findViewById<View>(R.id.txtRegFirstName) as EditText
        editTextLastName = findViewById<View>(R.id.txtRegLastName) as EditText
        editTextEmail = findViewById<View>(R.id.txtRegEmail) as EditText
        editTextPassword = findViewById<View>(R.id.txtRegPass) as EditText

        buttonRegister = findViewById<View>(R.id.bttnReg) as Button
        buttonCancel = findViewById<View>(R.id.bttnRegCancel) as Button

        textViewLoginLink = findViewById<View>(R.id.txtRegLogin) as TextView

    }

    private fun initListeners() {
        buttonRegister!!.setOnClickListener(this)
        buttonCancel!!.setOnClickListener(this)
        textViewLoginLink!!.setOnClickListener(this)

    }


    override fun onClick(v: View) {
        when (v.id) {

            R.id.bttnReg -> registerUser()

            R.id.bttnRegCancel -> finish()

            R.id.txtRegLogin -> finish()
        }
    }


    private fun registerUser() {
        var cancel = false
        var view: View? = null
        val phoneRegEx = "^(2547)\\d{8}$"
        val txtEditTextLastName = editTextLastName.text.toString().trim()

        if (txtEditTextLastName.isEmpty()) {
            editTextLastName.setError("This field must be filled")
            view = editTextLastName
            cancel = true
        }


        val txtEditTextFirstName = editTextFirstName.text.toString().trim()

        if (txtEditTextFirstName.isEmpty()) {
            editTextFirstName.setError("This field must be filled")
            view = editTextFirstName
            cancel = true
        }

        val txtEditTextPassword = editTextPassword.text.toString().trim()
        if (txtEditTextPassword.isEmpty()) {
            editTextPassword.setError("This field must be filled")
            view = editTextPassword
            cancel = true
        }else if (txtEditTextPassword.length < 8) {
            editTextPassword.setError("Password must be at least 8 characters")
            view = editTextPassword
            cancel = true
        }

        val txtEditTextPhoneNumber = editTextPhoneNumber.text.toString().trim()
        if (txtEditTextPhoneNumber.isEmpty()) {
            editTextPhoneNumber.setError("This field must be filled")
            view = editTextPhoneNumber
            cancel = true
        }else if (!Pattern.compile(phoneRegEx).matcher(txtEditTextPhoneNumber).matches()) {
            editTextPhoneNumber.setError("Wrong format phone for number")
            view = editTextPhoneNumber
            cancel = true
        }

        val txtEditTextEmail = editTextEmail.text.toString().trim()
        if (txtEditTextEmail.isEmpty()){
            editTextEmail.setError("This field must be filled")
            view = editTextEmail
            cancel = true
        }
        else if ( !android.util.Patterns.EMAIL_ADDRESS.matcher(txtEditTextEmail).matches()){
            editTextEmail.setError("Incorrect email format")
            view = editTextEmail
            cancel = true
        }

        if (cancel) {
            view!!.requestFocus()
        } else {
            finish()
        }

    }

}
