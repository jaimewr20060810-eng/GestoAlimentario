package com.example.gestoalimentario
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.gestoalimentario.ui.theme.navegacion.AppNavHost
import com.example.gestoalimentario.ui.theme.navegacion.MyAppBottomBar
import com.example.gestoalimentario.ui.theme.navegacion.MyAppTopBar
import com.example.gestoalimentario.ui.theme.theme.GestoAlimentarioTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            GestoAlimentarioTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    topBar = {
                        MyAppTopBar(
                            navController = navController
                        )
                    },

                    bottomBar = {
                        MyAppBottomBar(
                            navController = navController
                        )
                    }

                ) { innerPadding ->

                    AppNavHost(
                        navController = navController,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}