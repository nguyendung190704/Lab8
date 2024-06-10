package com.example.lab8


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.lab8.room.MovieEntity
import com.example.lab8.viewmodel.MovieViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: MovieViewModel,
    movieId: String?,
    tenphim: String,
    thoiluong: String,
    img: String,
    noidung: String,
    quocgia: String,
    namphathanh:String
) {
    val showDialog = remember { mutableStateOf(false) }
    val showUpdateDialog = remember { mutableStateOf(false) }
    var inputTenphim by remember { mutableStateOf(tenphim) }
    var inputThoiLuong by remember { mutableStateOf(thoiluong) }
    var inputImg by remember { mutableStateOf(img) }
    var inputNoidung by remember { mutableStateOf(noidung) }
    var inputQuocgia by remember { mutableStateOf(quocgia) }
    var inputNamphathanh by remember { mutableStateOf(namphathanh) }
    val emty by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().safeDrawingPadding()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(15.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                AsyncImage(
                    model = img,
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(10.dp)
                        .width(160.dp)
                        .height(200.dp)
                        .clip(RoundedCornerShape( 10.dp)),
                    contentScale = ContentScale.FillBounds
                )
                Row {
                    Text(
                        text = "ID: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "" + movieId,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Row {
                    Text(
                        text = "Tên phim: ",
                        fontSize = 16.sp,
                    )
                    Text(
                        text = "" + tenphim,
                        fontSize = 16.sp,
                    )
                }
                Row {
                    Text(
                        text = "Thời lượng : ",
                        fontSize = 16.sp,
                    )
                    Text(
                        text = "" + thoiluong,
                        fontSize = 16.sp,
                    )
                }
                Row {
                    Text(
                        text = "Quốc gia : ",
                        fontSize = 16.sp,
                    )
                    Text(
                        text = "" + quocgia,
                        fontSize = 16.sp,
                    )
                }
                Row {
                    Text(
                        text = "Năm phát hành : ",
                        fontSize = 16.sp,
                    )
                    Text(
                        text = "" + namphathanh,
                        fontSize = 16.sp,
                    )
                }
                Row {
                    Text(
                        text = "Nội dung : ",
                        fontSize = 16.sp,
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "" + noidung,
                        fontSize = 16.sp,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.background)
                ) {

                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    OutlinedIconButton(
                        onClick = { showDialog.value = true },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.size(height = 50.dp, width = 100.dp)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_delete),
                                contentDescription = null
                            )
                            Text(text = "Delete")
                        }
                    }
                    OutlinedIconButton(
                        onClick = {showUpdateDialog.value = true},
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.size(height = 50.dp, width = 100.dp)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = null
                            )
                            Text(text = "Update")
                        }
                    }
                }
            }
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog.value = false
                        navController.popBackStack()
                    }
                ) {
                    Text(text = "No")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (movieId != null) {
                            viewModel.deleteMovie(
                                movie = MovieEntity(
                                    uid = movieId.toInt(),
                                    tenphim = tenphim,
                                    thoiluong = thoiluong,
                                    img = img,
                                    quocgia = quocgia,
                                    noidung = noidung,
                                    namphathanh = namphathanh
                                )
                            )
                        }
                        showDialog.value = false
                        navController.popBackStack()
                    }
                ) {
                    Text(text = "Yes")
                }
            },
            title = {
                Text(
                    text = "Delete movie",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            },
            text = {
                Text(
                    text = "Are you sure?",
                    fontSize = 25.sp
                )
            }
        )
    }

    if (showUpdateDialog.value) {
        AlertDialog(
            onDismissRequest = { showUpdateDialog.value = false },
            dismissButton = {
                Button(
                    onClick = {
                        showUpdateDialog.value = false
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
                if (inputTenphim.isNotEmpty() || inputThoiLuong.isNotEmpty()|| inputImg.isNotEmpty() || inputNoidung.isNotEmpty() || inputQuocgia.isNotEmpty() || inputNamphathanh.isNotEmpty()) {

                    Button(
                        onClick = {
                            val newMovie = MovieEntity(movieId!!.toInt(), inputTenphim, inputThoiLuong,inputImg, inputNoidung, inputQuocgia,inputNamphathanh)
                            viewModel.updateMovie(newMovie)
                            navController.popBackStack()
                            showUpdateDialog.value = false
                            inputTenphim = emty
                            inputThoiLuong = emty
                            inputImg = emty
                            inputNoidung = emty
                            inputQuocgia = emty
                            inputNamphathanh = emty
                        }
                    ) {
                        Text(text = "Update")
                    }
                }
            },
            title = {
                Text(
                    text = "Update Movie",
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