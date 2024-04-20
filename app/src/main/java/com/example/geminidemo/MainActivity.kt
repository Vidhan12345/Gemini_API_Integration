package com.example.geminidemo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.geminidemo.databinding.ActivityMainBinding
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val generativeModel = GenerativeModel(
            // For text-only input, use the gemini-pro model
            modelName = "gemini-pro",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = "AIzaSyB0pXvlYljli5fcST_jSoNRVeOOgDK5id8"
        )

        binding.send.setOnClickListener {

            val prompt = binding.prompt.text.toString()

            runBlocking {

                val response = try {
                    generativeModel.generateContent(prompt)
                } catch (e: Exception) {
                    // Handle the exception (e.g., display an error message to the user)
                    // You can consider retrying with a slightly modified prompt or suggesting prompt improvements
                    Log.d("Error","Error generating content")

                    null
                }

                if (response != null) {
                    binding.output.text = response.text.toString()
                    binding.prompt.setText("")
                }
            }
        }
    }
}