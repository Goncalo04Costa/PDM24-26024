package com.example.loja.navigation


import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loja.view.AddCarrinho
import com.example.loja.view.RegistoUtilizador
import com.example.loja.view.CarrinhosScreen

import com.example.carrinhodecompras.Presentation.Screens.MenuUtilizador
import com.example.loja.classes.Produto
import com.example.loja.view.ProductItemBox
import com.example.loja.viewmodel.LoginViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Login"){
        composable("Login") {
            LoginViewModel(viewModel(),navController)
        }
        composable("MenuUtilizador/{utlzObject}",
            arguments = listOf(navArgument("utlzObject"){
                type = NavType.StringType
                nullable = false
            })
        ){ navBackStackEntry->
            val utlzEncoded =navBackStackEntry.arguments?.getString("utlzObject")
            val utlzDecoded =utlzEncoded?.let { Uri.decode(it) }
            MenuUtilizador(viewModel(),navController,utlzDecoded)
        }
        composable("RegistoUtilizador/{loginUtilizador}"){navBackStackEntry->
            val usernameEncoded = navBackStackEntry.arguments?.getString("loginUtilizador")
            if(usernameEncoded !=null){
                val usernameDecoded = usernameEncoded?.let { Uri.decode(it) }
                RegistoUtilizador(viewModel(),navController,usernameDecoded)
            }else{
                RegistoUtilizador(viewModel(),navController,"")
            }
        }
        composable("ProdutosScreen"){
            ProductItemBox(viewModel(),viewModel(), navController.toString(),)
        }
        composable("AddCarrinho"){
            AddCarrinho(viewModel(), viewModel(),navController)
        }
        composable("CarrinhosScreen"){
            CarrinhosScreen(viewModel(),viewModel(),navController)
        }
    }
}