package com.nguyen.jetreader

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.firestore.FirebaseFirestore
import com.nguyen.jetreader.ui.theme.JetReaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetReaderTheme {
                val user = mutableMapOf<String, Any>()
                user["first"] = "Ada"
                user["last"] = "Lovelace"
                val database = FirebaseFirestore.getInstance()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    database.collection("users").add(user).addOnSuccessListener {
                        Log.d("TAGG", "onCreate: ${it.id}")
                    }.addOnFailureListener {
                        Log.d("TAGG", "onCreate: ${it.message}")
                    }

                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetReaderTheme {
        Greeting("Android")
    }
}