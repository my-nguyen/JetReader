package com.nguyen.jetreader.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // extract displayName from the email
                        val displayName = task.result.user?.email?.split('@')?.get(0)
                        createUser(displayName)
                        home()
                    } else {
                        Log.d("TAGG", "createUser FAILED: ${task.result}")
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
                            Log.d("TAGG", "signIn SUCCESSFUL: ${task.result}")
                            home()
                        } else {
                            Log.d("TAGG", "signIn FAILED: ${task.result}")
                        }
                        _loading.value = false
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("TAGG", "signInWithEmailAndPassword exception: ${e.message}")
            }
        }

    private fun LoginViewModel.createUser(displayName: String?) {
        // extract userId from Auth
        val userId = auth.currentUser?.uid
        val user = mutableMapOf<String, Any>()
        user["user_id"] = userId.toString()
        user["display_name"] = displayName.toString()
        Firebase.firestore.collection("users")
            .add(user)
    }
}