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

    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("TAGG", "createUserWithEmailAndPassword successful: ${it.result}")
                        home()
                    } else {
                        Log.d("TAGG", "createUserWithEmailAndPassword failed: ${it.result}")
                    }
                    _loading.value = false
                }
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("TAGG", "signInWithEmailAndPassword successful: ${task.result}")
                            home()
                        } else {
                            Log.d("TAGG", "signInWithEmailAndPassword failed: ${task.result}")
                        }
                        _loading.value = false
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("TAGG", "signInWithEmailAndPassword exception: ${e.message}")
            }
        }
}