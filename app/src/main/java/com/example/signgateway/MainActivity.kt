package com.example.signgateway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.signgateway.database.SignGatewayDatabase
import com.example.signgateway.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val presenter = PresenterMainActivity()
    private var emailOrNumber: String = ""
    private var password: String = ""
    var numberOfUsers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signUpClickListener()
        logInClickListener()
    }

    override fun onBackPressed() {
        val intent = Intent(this@MainActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSignUpFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, FragmentSignUp)

        //Prevent excessive backStack
        if (supportFragmentManager.backStackEntryCount < 2) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    private fun signUpClickListener() {
        binding.signUp.setOnClickListener {
            navigateToSignUpFragment(FragmentSignUp(this@MainActivity))
            binding.signUp.visibility = View.GONE
            binding.password.visibility = View.GONE
            binding.emailOrNumber.visibility = View.GONE
            binding.logIn.visibility = View.GONE
        }
    }

    private fun logInClickListener() {
        if (!presenter.isAttributeLengthCorrect(binding.password.text.toString()) || emailOrNumber.length < 4) binding.logIn.isClickable = true
        else binding.logIn.isClickable =  true
        binding.logIn.setOnClickListener {
            password = binding.password.text.toString()
            emailOrNumber = binding.emailOrNumber.text.toString()
            if (presenter.isAttributeLengthCorrect(binding.password.text.toString()) && presenter.isEmailOrNumberCorrectFormat(binding.emailOrNumber.text.toString())) {
                SignGatewayDatabase.getDatabase(this).signGatewayDao().getAllSignInUsers().forEach {
                    if (it.password == password && it.username == emailOrNumber) {
                        binding.emailOrNumber.text = null
                        binding.password.text = null
                        resetAllView()
                        navigateToSignUpFragment(FragmentLoggedOn())
                        return@forEach
                    } else if (it.username == emailOrNumber && it.password != password) {
                        binding.emailOrNumber.text = null
                        binding.password.text = null
                        Toast.makeText(
                            this,
                            "Looks like your password wrong, try again or opt to rest",
                            Toast.LENGTH_LONG
                        ).show()
                        return@forEach
                    }

                }
            }
            else {
                binding.emailOrNumber.text = null
                binding.password.text = null
                Toast.makeText(this, "Looks your credential seem to be wrong, try again or Sign Up", Toast.LENGTH_LONG ).show()
            }
        }
    }

    private fun resetAllView() {
        binding.logIn.visibility = View.GONE
        binding.password.visibility = View.GONE
        binding.signUp.visibility = View.GONE
        binding.emailOrNumber.visibility = View.GONE
    }

    companion object {
        const val FragmentSignUp = "fragment_sign_up"
    }
}