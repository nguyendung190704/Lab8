package com.example.lab8

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.lab8.room.MovieEntity
import com.example.lab8.viewmodel.MovieViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MovieViewModel, navController: NavController) {
    var inputTenphim by remember { mutableStateOf("") }
    var inputThoiLuong by remember { mutableStateOf("") }
    var inputImg by remember { mutableStateOf("") }
    var inputNoidung by remember { mutableStateOf("") }
    var inputQuocgia by remember { mutableStateOf("") }
    var inputNamphathanh by remember { mutableStateOf("") }
    val emty by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val movies by viewModel.movies.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "LIST MOVIE",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.background
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null
                )
            }
        }
    ) {
        if (movies.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Text(text = "No data")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(movies) {
                    Card(
                        onClick = {
                            navController.navigate("${ROUTE_NAME_SCREEN.Detail.name}/${ Uri.encode((it.uid.toString()))}/${Uri.encode(it.tenphim)}/${Uri.encode(it.thoiluong)}/${Uri.encode(it.img)}/${Uri.encode(it.noidung)}/${Uri.encode(it.quocgia)}/${Uri.encode(it.namphathanh)}")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row {
                            AsyncImage(
                                model = it.img,
                                contentDescription = null,
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .padding(10.dp)
                                    .width(100.dp)
                                    .height(150.dp)
                                    .clip(RoundedCornerShape( 10.dp)),
                                contentScale = ContentScale.FillBounds
                            )
                            Column(
                            ) {
                                Text(
                                    text = "Tên phim: " + it.tenphim,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(5.dp),
                                )
                                Text(
                                    text = "Thời lượng: " + it.thoiluong,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(5.dp),
                                )

                                Text(
                                    text = "Năm phát hành: " + it.namphathanh,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(5.dp),
                                )
                                Text(
                                    text = "Quốc gia: " + it.quocgia,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(5.dp),
                                )
                            }
                        }

                    }
                }
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        inputTenphim = emty
                        inputThoiLuong = emty
                        inputImg = emty
                        inputNoidung = emty
                        inputQuocgia = emty
                        inputNamphathanh = emty

                    }
                ) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                if (inputTenphim.isNotEmpty() && inputThoiLuong.isNotEmpty()&& inputImg.isNotEmpty() && inputNoidung.isNotEmpty() && inputQuocgia.isNotEmpty()&& inputNamphathanh.isNotEmpty()) {

                    Button(
                        onClick = {
                            viewModel.addMovie(
                                MovieEntity(
                                    0,
                                    inputTenphim,
                                    inputThoiLuong,
                                    inputImg,
                                    inputNoidung,
                                    inputQuocgia,
                                    inputNamphathanh
                                )
                            )
                            showDialog = false
                            inputTenphim = emty
                            inputThoiLuong = emty
                            inputImg = emty
                            inputNoidung = emty
                            inputQuocgia = emty
                            inputNamphathanh = emty
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
            },
            title = {
                Text(
                    text = "Add Movie",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(5.dp)
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = inputTenphim,
                        onValueChange = {inputTenphim = it},
                        label = {
                            Text(text = "Tên phim")
                        },
                        placeholder = { Text(text = "Nhập Tên Phim")}
                    )
                    OutlinedTextField(
                        value = inputThoiLuong,
                        onValueChange = {inputThoiLuong = it},
                        label = {
                            Text(text = "Thời lượng ")
                        },
                        placeholder = { Text(text = "Nhập Thời Lượng")}
                    )
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = inputImg,
                        onValueChange = {inputImg = it},
                        label = {
                            Text(text = "IMG")
                        },
                        placeholder = { Text(text = "Nhập Img")}
                    )
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = inputNoidung,
                        onValueChange = {inputNoidung = it},
                        label = {
                            Text(text = "Nội dung")
                        },
                        placeholder = { Text(text = "Nhập Nội Dung")}
                    )
                    OutlinedTextField(
                        value = inputQuocgia,
                        onValueChange = {inputQuocgia = it},
                        label = {
                            Text(text = "Quốc gia")
                        },
                        placeholder = { Text(text = "Nhập quốc gia")}
                    )
                    OutlinedTextField(
                        value = inputNamphathanh,
                        onValueChange = {inputNamphathanh = it},
                        label = {
                            Text(text = "Năm phát hành")
                        },
                        placeholder = { Text(text = "Nhập năm phát hành ")}
                    )

                }
            }
        )
    }
}