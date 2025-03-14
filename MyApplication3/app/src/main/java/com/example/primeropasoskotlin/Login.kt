package com.example.primeropasoskotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.PixelCopy.Request
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.primeropasoskotlin.models.Posts
import com.example.primeropasoskotlin.ui.Home
import com.example.primeropasoskotlin.viewmodel.PostViewModel

class Login : AppCompatActivity() {

    lateinit var btnLogin:Button

    private val postViewModel:PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarR()
        estadoButon()

        //observandoHttp()
        observandoGuardaro()
        val newPost = Posts(102, 1,"Alan", "Brito ... brito...")
        postViewModel.getGuardar(newPost)

        //loadHttp()
        //cargarVolley()
    }
    //crear la funcion de cargar R
    fun cargarR(){
        btnLogin = findViewById(R.id.btnRegistrarProveedor)
    }

    //estado button
    fun estadoButon(){
        btnLogin.setOnClickListener{
            var aa = Intent(this, Home::class.java)
            startActivity(aa)
        }
    }

    //listado de datos, con listviews o resacleview

    private fun observandoHttp(){
        postViewModel.getAll.observe(this){ posts ->
            posts?.forEach {

                Log.d("Posts","Datos de la HTTP: ${it.body}")
            }
        }
        postViewModel.error.observe(this){errors ->
            errors?.let {
                Log.d("Post","Eroor en la peticion: ${it}")
            }
        }
    }

    //esperandod el estado del guardaro
    private fun observandoGuardaro(){
        Toast.makeText(this,"---------------Registro exitoso!!!", Toast.LENGTH_SHORT).show()
        postViewModel.result.observe(this) { isSuccess  ->
            Toast.makeText(this,"---------------Registro exitoso!!!", Toast.LENGTH_SHORT).show()
            if (isSuccess ) {
                Toast.makeText(this,"---------------Registro exitoso!!!", Toast.LENGTH_SHORT).show()
                Log.d("Post", "---------------Registro exitoso!!!")
            } else {
                Toast.makeText(this,"---------------Error en el registro", Toast.LENGTH_SHORT).show()

                Log.d("Post", "---------------Error en el registro")
            }
        }
    }

    private fun loadHttp(){
        postViewModel.getPost()
    }



    /*fun cargarVolley(){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/posts"


        val stringRequest = StringRequest(com.android.volley.Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                print("Response is: ${response}")
            },
            Response.ErrorListener {print("error")})
        queue.add(stringRequest)

    }*/
}