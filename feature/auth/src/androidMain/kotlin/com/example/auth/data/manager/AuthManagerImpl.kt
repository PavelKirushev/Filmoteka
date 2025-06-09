package com.example.auth.data.manager

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.auth.domain.AuthManager
import com.example.auth.domain.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthManagerImpl(
    private val context: Context
) : AuthManager {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("USER_SETTINGS", Context.MODE_PRIVATE)
    }

    private val _isLoggedIn = MutableStateFlow(prefs.getBoolean("IS_LOGGED_IN", false))
    override val isLoggedInFlow = _isLoggedIn.asStateFlow()

    override fun isLoggedIn(): Boolean = _isLoggedIn.value

    override fun login(name: String, email: String, age: Int) {
        _isLoggedIn.value = true
        prefs.edit {
            putBoolean("IS_LOGGED_IN", true)
            putString("NAME", name)
            putString("EMAIL", email)
            putInt("AGE", age)
        }
    }

    override fun logout() {
        _isLoggedIn.value = false
        prefs.edit {
            remove("IS_LOGGED_IN")
            remove("NAME")
            remove("EMAIL")
            remove("AGE")
        }
    }

    override fun getUser(): User {
        return User(
            login = prefs.getString("NAME", null) ?: "null",
            email = prefs.getString("EMAIL", null) ?: "null",
            password = "-",
            age = prefs.getInt("AGE", -1)
        )
    }

    override fun getAuthName(): String? {
        return prefs.getString("NAME", null)
    }
}
