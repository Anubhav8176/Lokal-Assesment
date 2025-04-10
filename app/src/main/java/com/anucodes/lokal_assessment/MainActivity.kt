package com.anucodes.lokal_assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambdaN
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anucodes.lokal_assessment.BottomNavigation.BottomNavigationBar
import com.anucodes.lokal_assessment.BottomNavigation.BottomNavigationItems
import com.anucodes.lokal_assessment.core.viewmodel.JobsViewModel
import com.anucodes.lokal_assessment.ui.BookmarkScreen
import com.anucodes.lokal_assessment.ui.HomeScreen
import com.anucodes.lokal_assessment.ui.theme.LokalAssessmentTheme

class MainActivity : ComponentActivity() {

    val jobsViewModel by viewModels<JobsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            LokalAssessmentTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) {innerpadding->
                    NavHost(
                        navController = navController,
                        startDestination = BottomNavigationItems.Jobs.route
                    ) {
                        composable(route = BottomNavigationItems.Jobs.route) {
                            HomeScreen(
                                modifier = Modifier.padding(innerpadding),
                                jobsViewModel = jobsViewModel
                            )
                        }
                        composable(route = BottomNavigationItems.Bookmark.route) {
                            BookmarkScreen(
                                modifier = Modifier.padding(innerpadding),
                                jobsViewModel = jobsViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

