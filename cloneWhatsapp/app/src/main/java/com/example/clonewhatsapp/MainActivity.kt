package com.example.clonewhatsapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clonewhatsapp.data.model.Message
import com.example.clonewhatsapp.ui.ChatViewModel
import com.example.clonewhatsapp.ui.adapter.AdapterChat
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ChatViewModel
    private lateinit var adapter: AdapterChat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val txtMessage = findViewById<EditText>(R.id.editTextMessage)
        val btnSend = findViewById<ImageButton>(R.id.sendButton)

        // Inicializar ViewModel
        viewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        // Configurar RecyclerView y Adapter
        adapter = AdapterChat(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observar mensajes
        viewModel.messages.observe(this) { messages ->
            adapter.updateMessages(messages)
            recyclerView.scrollToPosition(messages.size - 1)
        }

        viewModel.loadMessage()

        // Enviar mensaje
        btnSend.setOnClickListener {
            val messageText = txtMessage.text.toString().trim()
            if (messageText.isNotEmpty()) {
                val message = Message(
                    id = UUID.randomUUID().toString(),
                    sendId = "user123",  // Aquí podrías usar el ID real del usuario logueado
                    message = messageText
                )
                viewModel.sendMessage(message)
                txtMessage.text.clear()
            }
        }
    }
}


