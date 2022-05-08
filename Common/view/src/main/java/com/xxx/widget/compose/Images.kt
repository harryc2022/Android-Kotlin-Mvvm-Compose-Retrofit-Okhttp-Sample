package com.xxx.widget.compose

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.xxx.framework.tool.AppTool

@Composable
fun AssetImage(
    w: Dp = 0.dp, h: Dp = 0.dp, path: String,
    align: Alignment = Alignment.Center,
    colorTint: Color? = null,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
) {
    ImageF(
        painter = painterAssets(path),
        colorTint,
        w,
        h,
        align = align,
        contentScale = contentScale,
        alpha = alpha,
    )
}

@Composable
fun NetworkImage(
    w: Dp = 0.dp, h: Dp = 0.dp, link: String,
    align: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorTint: Color? = null,
) {
    ImageF(
        painter = rememberAsyncImagePainter(link),
        colorTint = colorTint,
        w,
        h,
        align = align,
        contentScale = contentScale,
        alpha = alpha,
    )
}

@Composable
fun ResImage(
    w: Dp = 0.dp, h: Dp = 0.dp, resId: Int,
    colorTint: Color? = null,
    align: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha
) {
    ImageF(
        painter = painterResource(id = resId),
        colorTint = colorTint,
        w,
        h,
        align = align,
        contentScale = contentScale,
        alpha = alpha,
    )
}

@Composable
private fun ImageF(
    painter: Painter,
    colorTint: Color?,
    w: Dp = 0.dp, h: Dp = 0.dp,
    align: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
) {
    var modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
    if (w != 0.dp && h != 0.dp) {
        modifier = Modifier.size(w, h)
    } else if (h != 0.dp) {
        modifier = Modifier.size(h, h)
    } else if (w != 0.dp) {
        modifier = Modifier.size(w, w)
    }
    Image(
        painter = painter,
        contentDescription = "image_drawable${painter.hashCode()}",
        modifier,
        alignment = align,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = if (colorTint != null) ColorFilter.tint(color = colorTint) else null,
    )
}

@Composable
fun painterAssets(path: String): Painter {
    val input = AppTool.getAppContext().assets.open(path)
    val bitmap = BitmapFactory.decodeStream(input).asImageBitmap()
    return BitmapPainter(bitmap)
}