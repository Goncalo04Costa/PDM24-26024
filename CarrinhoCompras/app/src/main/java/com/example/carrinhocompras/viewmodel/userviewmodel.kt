package com.example.carrinhocompras.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class userviewmodel: ViewModel(){
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    suspend fun signIn(email:String, password:String):Boolean{
        return try{
            val result = auth.signInWithEmailAndPassword(email,password).await()
            Log.d("MenuUtilizadorViewModel","Resultado: ${result}")
            true
        }
        catch (e:Exception){
            Log.d("MenuUtilizadorViewModel","Não funcionei excecao:${e}")
            false
        }
    }
    fun signOut(){
        try{
            auth.signOut()
        }catch(e:Exception){
            Log.d("MenuUtilizadorViewModel","Não funcionei excecao:${e}")
        }
    }
}