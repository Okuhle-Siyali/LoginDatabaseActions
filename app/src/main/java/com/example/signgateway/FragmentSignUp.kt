package com.example.signgateway

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.signgateway.database.SignGatewayDatabase
import com.example.signgateway.database.SignInUser
import com.example.signgateway.database.SignUpUser
import com.example.signgateway.databinding.FragmentSignUpBinding


class FragmentSignUp(private val thisContext: AppCompatActivity) : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private var checkForErrorSignUp = true
    private var mainActivity = MainActivity()
    private val presenter = PresenterMainActivity()
    var signUp = mutableListOf("", "", "", "", "", "", "", "")

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sign_up, container, false
        )
        signUpClickListener()
        return binding.root
    }

    private fun signUpClickListener() {
        do {
            binding.signUp.setOnClickListener {
                evaluateUserInput()
                if (checkForErrorSignUp) {
                    val signUpUser = SignUpUser(
                        signUp[0],
                        signUp[1],
                        signUp[2],
                        signUp[3],
                        signUp[4].toInt(),
                        signUp[5].toInt(),
                        signUp[6].toInt(),
                        signUp[7]
                    )
                    val signInUser = SignInUser(mainActivity.numberOfUsers++, signUp[2], signUp[3])
                    SignGatewayDatabase.getDatabase(thisContext).signGatewayDao().addSignUpUser(signUpUser)
                    SignGatewayDatabase.getDatabase(thisContext).signGatewayDao().addSignInUser(signInUser)
                    val intent = Intent(thisContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        } while (!checkForErrorSignUp)
    }

    private fun evaluateUserInput() {
        checkForErrorSignUp = true
        if (presenter.isAttributeLengthCorrect(binding.firstName.text.toString())) {
            signUp[0] = binding.firstName.text.toString()
        } else {
            wrongUserInput("Input below the minimum characters ${binding.firstName.hint.split(" ")[1]}")
            return
        }
        if (presenter.isAttributeLengthCorrect(binding.surname.text.toString())) {
            signUp[1] = binding.surname.text.toString()
        } else {
            wrongUserInput("Input below the minimum characters ${binding.surname.hint.split(" ")[1]}")
            return
        }
        if (presenter.isEmailOrNumberCorrectFormat(binding.emailOrNumber.text.toString())) {
            signUp[2] = binding.emailOrNumber.text.toString()
        } else {
            wrongUserInput("Email or number is incorrect ${binding.emailOrNumber.hint}")
            return
        }
        if (presenter.isAttributeLengthCorrect(binding.password.text.toString())) {
            signUp[3] = binding.password.text.toString()
        } else {
            wrongUserInput("Input below the minimum characters ${binding.password.hint}")
            return
        }
        if (binding.day.text.toString().isNotEmpty() && binding.day.text.toString().toInt() < 31) {
            signUp[4] = binding.day.text.toString()
        } else {
            wrongUserInput("No days can possibly be greater than 31")
            return
        }
        if (binding.day.text.toString().isNotEmpty() && binding.month.text.toString().toInt() < 12) {
            signUp[5] = binding.month.text.toString()
        } else {
            wrongUserInput("No month is greater than 12")
            return
        }
        if (binding.day.text.toString().isNotEmpty() && binding.year.text.toString().toInt() < 2011) {
            signUp[6] = binding.year.text.toString()
        } else {
            wrongUserInput("At least users need to older than 10 years")
            return
        }
        if (binding.male.isChecked) {
            binding.female.isChecked = false
            signUp[7] = "Male"
        } else if (binding.female.isChecked) {
            binding.male.isChecked = false
            signUp[7] = "Female"
        } else {
            wrongUserInput("Please tick on of the checkbox allocated")
            return
        }
    }

    private fun makeAToast(message : String){
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun wrongUserInput(string : String) {
        makeAToast(string)
        checkForErrorSignUp = false
    }
}