package com.example.signgateway

class PresenterMainActivity {

    fun isEmailOrNumberCorrectFormat(text: String): Boolean {
        if (text.matches("^[0-9]+$".toRegex()) && text[0] == '0' && text.length == 10) {
            return true
        }
        val emailArrays = text.split('@')
        if (text.length > 5 && emailArrays.size == 2 && emailArrays[1].contains('.')) {
            return true
        }
        return false
    }

    fun isAttributeLengthCorrect(text: String) : Boolean {
        return text.length > 4
    }
}