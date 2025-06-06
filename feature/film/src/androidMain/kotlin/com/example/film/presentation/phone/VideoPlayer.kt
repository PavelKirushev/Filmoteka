package com.example.film.presentation.phone

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubeVideoPlayer(
    videoUrl: String,
    modifier: Modifier = Modifier
) {
    val videoId = extractVideoId(videoUrl)
    if (videoId != null) {
        AndroidView(
            factory = { context ->
                YouTubePlayerView(context).apply {
                    // Обязательно lifecycleOwner для корректного управления плеером

                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(videoId = videoId, 0f)
                        }
                    })
                }
            },
            modifier = modifier
        )
    }

}

fun extractVideoId(youtubeUrl: String): String? {
    val regex = Regex("(?:v=|\\/)([0-9A-Za-z_-]{11}).*")
    val matchResult = regex.find(youtubeUrl)
    return matchResult?.groups?.get(1)?.value
}