package com.example.loja.view



import android.inputmethodservice.Keyboard
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.carrinhodecompras.Presentation.ViewModel.RegistoUtilizadorViewModel
import com.example.loja.elementos.CreateText
import com.example.loja.elementos.CreateTextField
import  com.example.loja.elementos.CreateTextTitle

import kotlinx.coroutines.launch

@Composable
fun RegistoUtilizador(viewModel: RegistoUtilizadorViewModel,
                      navController: NavController,
                      email:String?=null){
    var userEmail by remember { mutableStateOf(email?:"")}
    var userPassword by remember{ mutableStateOf("")}
    var confirmPassword by remember{ mutableStateOf("")}
    var successMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CreateTextTitle("Registo",Modifier.fillMaxWidth()
                .padding(16.dp), Color.Black,32.sp)
            CreateTextField(userEmail,Modifier.fillMaxWidth().padding(16.dp),"Email:",
                valueChange = {userEmail = it}, KeyboardType.Text,false)

            CreateTextField(userPassword,Modifier.fillMaxWidth().padding(16.dp),"Password:",
                valueChange = {userPassword=it}, KeyboardType.Password,true)

            CreateTextField(confirmPassword,Modifier.fillMaxWidth().padding(16.dp),"Password:",
                valueChange = {confirmPassword=it}, KeyboardType.Password,true)

            Button(onClick={
                if(verifyPasswords(userPassword, confirmPassword)){
                    Log.d("RegisterScreen","São iguais")
                    try{
                        viewModel.viewModelScope.launch {
                            val isSuccessful = viewModel.createAccount(userEmail,userPassword)
                            if(isSuccessful){
                                successMessage= "Registo efetuado com sucesso para a conta:${userEmail}"
                            }
                            else{
                                successMessage ="Registo não efetuado"
                            }
                        }

                    }
                    catch (e:Exception){
                        successMessage= "Ocorreu um erro:${e}"
                    }
                }else{
                    Log.d("RegisterScreen","Não são iguais")
                    successMessage = "Passwords não coincidem."
                }
            }){
                Text("Registar")
            }
            successMessage?.let{
                CreateText(successMessage!!,Modifier.fillMaxWidth(),Color.Black, TextAlign.Center,16.sp)
            }
        }
    }
}

fun verifyPasswords(firstPw:String, secondPw:String):Boolean{
    return (firstPw == secondPw)
}