package com.example.lab8

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab8.room.MovieDB
import com.example.lab8.viewmodel.MovieViewModel
import com.example.roomdb.repository.Repository

enum class ROUTE_NAME_SCREEN {
    Main,
    Detail
}
@Composable
fun AppNavHost() {
    val context = LocalContext.current
    val db = MovieDB.getIntance(context)
    val repository = Repository(db)
    val myViewModel = MovieViewModel(repository)
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ROUTE_NAME_SCREEN.Main.name){
        composable(ROUTE_NAME_SCREEN.Main.name){ MainScreen(viewModel = myViewModel, navController)}
        composable("${ROUTE_NAME_SCREEN.Detail.name}/{movieId}/{tenphim}/{thoiluong}/{img}/{noidung}/{quocgia}/{namphathanh}"){
            DetailScreen(
                navController,
                viewModel = myViewModel,
                movieId = it.arguments?.getString("movieId"),
                tenphim = it.arguments?.getString("tenphim")!!,
                thoiluong = it.arguments?.getString("thoiluong")!!,
                img = it.arguments?.getString("img")!!,
                noidung = it.arguments?.getString("noidung")!!,
                quocgia = it.arguments?.getString("quocgia")!!,
                namphathanh = it.arguments?.getString("namphathanh")!!
            )
        }

    }
}