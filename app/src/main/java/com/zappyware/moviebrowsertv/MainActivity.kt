package com.zappyware.moviebrowsertv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.navigation.Details
import com.zappyware.moviebrowser.navigation.Landing
import com.zappyware.moviebrowsertv.ui.theme.MovieBrowserTVTheme
import com.zappyware.page.detail.DetailsScreen
import com.zappyware.page.landing.LandingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)
        setContent {
            enableEdgeToEdge()
            MovieBrowserTVTheme {
                val backStack = remember { mutableStateListOf<Any>(Landing) }
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ) { innerPadding ->
                    NavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        entryDecorators = listOf(
                            rememberSaveableStateHolderNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator(),
                        ),
                        entryProvider = entryProvider {
                            entry<Landing> {
                                LandingScreen(
                                    viewModel = hiltViewModel(),
                                    onDetailsClicked = { selectedMovie ->
                                        when(selectedMovie) {
                                            is MovieWidget -> backStack.add(Details(selectedMovie.id, selectedMovie.mediaType))
                                        }
                                    },
                                )
                            }
                            entry<Details> {
                                DetailsScreen(
                                    viewModel = hiltViewModel(),
                                    movieId = it.contentId,
                                    mediaType = it.mediaType,
                                )
                            }
                        },
                        transitionSpec = {
                            slideInHorizontally(
                                animationSpec = tween(AnimationDuration),
                                initialOffsetX = { it }
                            ) togetherWith slideOutHorizontally(
                                animationSpec = tween(AnimationDuration),
                                targetOffsetX = { -it }
                            )
                        },
                        popTransitionSpec = {
                            fadeIn(tween(AnimationDuration)) + slideInHorizontally(
                                animationSpec = tween(AnimationDuration),
                                initialOffsetX = { -it }
                            ) togetherWith slideOutHorizontally(
                                animationSpec = tween(AnimationDuration),
                                targetOffsetX = { it }
                            ) + fadeOut(tween(AnimationDuration))
                        },
                        predictivePopTransitionSpec = {
                            slideInHorizontally(
                                animationSpec = tween(AnimationDuration),
                                initialOffsetX = { -it }
                            ) togetherWith slideOutHorizontally(
                                animationSpec = tween(AnimationDuration),
                                targetOffsetX = { it }
                            )
                        },
                    )
                }
            }
        }
    }

    companion object {
        private const val AnimationDuration = 500
    }
}
