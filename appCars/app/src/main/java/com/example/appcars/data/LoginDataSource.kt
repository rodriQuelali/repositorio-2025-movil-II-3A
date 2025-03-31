package com.example.appcars.data

import android.content.Context
import com.example.appcars.data.model.LoggedInUser
import com.example.appcars.data.model.LoginRequest
import com.example.appcars.data.services.ApiClient
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(private val context: Context) {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // Crear la solicitud
            val loginRequest = LoginRequest(email = username, password = password)

            // Hacer la llamada usando Retrofit
            val response = ApiClient.create(context).login(loginRequest).execute()

            if (response.isSuccessful) {
                val loginResponse = response.body()
                // Guardar el token de acceso si la autenticación es exitosa
                loginResponse?.let {
                    saveAccessToken(it.access)
                }

                // Crear un usuario ficticio con los datos obtenidos
                val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Alna Brito ////")

                // Retornar el resultado exitoso
                return Result.Success(fakeUser)
            } else {
                return Result.Error(IOException("Error en la autenticación: ${response.message()}"))
            }
        } catch (e: Exception) {
            return Result.Error(IOException("Error en la conexión", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }

    // Función para guardar el token en SharedPreferences
    private fun saveAccessToken(token: String) {
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("ACCESS_TOKEN", token)
            apply()
        }
    }

    // Función para borrar el token al cerrar sesión
    private fun clearAccessToken() {
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("ACCESS_TOKEN")
            apply()
        }
    }
}