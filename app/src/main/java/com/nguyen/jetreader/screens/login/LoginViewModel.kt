package com.nguyen.jetreader.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun createUser(email: String, password: String, home: () -> Unit) {
    }

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    TODO("Navigate to Home Screen")
                } else {
                    Log.d("TAGG", "signInWithEmailAndPassword unsuccessful: ${task.result}")
                }
                _loading.value = false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("TAGG", "signInWithEmailAndPassword exception: ${e.message}")
        }
    }
}