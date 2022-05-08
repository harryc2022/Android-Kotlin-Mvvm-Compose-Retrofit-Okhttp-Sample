package com.xxx.widget.compose

import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.platform.NoInspectorInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*

object Container {
    val WRAP_CONTENT = (-2).dp
    val MATCH_CONTENT = (-1).dp

    object ObjectLock
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Container(
    modifier: Modifier = Modifier,
    width: Dp = Container.WRAP_CONTENT,
    height: Dp = Container.WRAP_CONTENT,
    margin: PaddingValues? = null,
    padding: PaddingValues? = null,
    align: Alignment = Alignment.TopStart,
    gradient: Brush? = null,
    shape: Shape = RectangleShape,
    border: BorderStroke? = null,
    background: Color = Color.Gray,
    onClick: () -> Unit = { },
    clickable: Boolean = true,
    child: @Composable BoxScope.() -> Unit = { Text(text = "") }
) {
    var attBox: Modifier = Modifier.background(Color.Transparent)
    val selected = remember { mutableStateOf(false) }
    val alpha = animateFloatAsState(if (selected.value) 0.3f else 1f)

    attBox = when (width) {
        Container.WRAP_CONTENT -> {
            attBox.wrapContentWidth()
        }
        Container.MATCH_CONTENT -> {
            attBox.fillMaxWidth()
        }
        else -> {
            attBox.width(width)
        }
    }

    attBox = when (height) {
        Container.WRAP_CONTENT -> {
            attBox.wrapContentHeight()
        }
        Container.MATCH_CONTENT -> {
            attBox.fillMaxHeight()
        }
        else -> {
            attBox.height(height)
        }
    }

    if (margin != null) {
        attBox = attBox
            .padding(margin)
            .then(Modifier)
    }
    if (clickable) {
        attBox = attBox
            .alpha(alpha.value)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        selected.value = true
                    }

                    MotionEvent.ACTION_UP -> {
                        selected.value = false
                        synchronized(Container.ObjectLock) {
                            onClick()
                        }
                    }
                }
                true
            }
    }
    Surface(
        modifier = attBox
            .colorClickable()
            .then(modifier),
        shape = shape,
        elevation = 5.dp,
        border = border ?: borderH(2.dp, listOf(background, background)),
    ) {
        var att: Modifier =
            if (gradient != null) Modifier.background(gradient) else Modifier.background(
                background
            )
        att = when (width) {
            Container.WRAP_CONTENT -> {
                att.wrapContentWidth()
            }
            else -> {
                att.fillMaxWidth()
            }
        }
        att = when (height) {
            Container.WRAP_CONTENT -> {
                att.wrapContentHeight()
            }
            else -> {
                att.fillMaxHeight()
            }
        }
        Box(
            modifier = att
                .align(align = align)
        ) {
            var attInSide: Modifier = Modifier
            if (padding != null) {
                attInSide = attInSide
                    .padding(padding)
                    .align(align)
                    .then(Modifier)
            }
            Box(modifier = attInSide) {
                child()
            }
        }
    }
}

@Composable
fun GapsH(modifier: Modifier = Modifier, vl: Dp = 0.dp) {
    val param: Modifier = Modifier
        .width(vl)
        .then(modifier)
    Spacer(modifier = param)
}

@Composable
fun GapsV(modifier: Modifier = Modifier, vl: Dp = 0.dp) {
    val param: Modifier = Modifier
        .height(vl)
        .then(modifier)
    Spacer(modifier = param)
}

@Stable
fun Modifier.align(
    align: Alignment = Alignment.TopStart,
) = this.then(
    BoxSurfaceData(
        alignment = align,
        matchParentSize = false,
        inspectorInfo = debugInspectorInfo {
            name = "align"
            value = align
        }
    )
)
@Composable
fun TextGradientShadow(modifier: Modifier, text:String, style: TextStyle, shadow: Shadow){
    Box{
        Text(text = text, style = style.copy(shadow = shadow))
        Text(text = text, style = style, modifier = modifier)
    }
}
class BoxSurfaceData(
    var alignment: Alignment,
    var matchParentSize: Boolean = false,
    inspectorInfo: InspectorInfo.() -> Unit = NoInspectorInfo
) : ParentDataModifier, InspectorValueInfo(inspectorInfo) {
    override fun Density.modifyParentData(parentData: Any?) = this@BoxSurfaceData

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val otherModifier = other as? BoxSurfaceData ?: return false

        return alignment == otherModifier.alignment &&
                matchParentSize == otherModifier.matchParentSize
    }

    override fun hashCode(): Int {
        var result = alignment.hashCode()
        result = 31 * result + matchParentSize.hashCode()
        return result
    }
}

fun borderV(w: Dp, colors: List<Color>): BorderStroke {
    return BorderStroke(
        w,
        Brush.verticalGradient(
            colors = colors
        )
    )
}

fun borderH(w: Dp, colors: List<Color>): BorderStroke {
    return BorderStroke(
        w,
        Brush.horizontalGradient(
            colors = colors,
        )
    )
}

fun gradientH(colors: List<Color>): Brush {
    return Brush.horizontalGradient(colors = colors)
}

fun gradientV(colors: List<Color>): Brush {
    return Brush.verticalGradient(colors = colors)
}

fun Modifier.shadowF(
    color: Color = Color.Black,
    cornersRadius: Dp = 0.dp,
    shadowBlurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = drawBehind {

    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = color.toArgb()
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            color.toArgb()
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}

fun Modifier.colorClickable(): Modifier =
    composed {
        clickable(indication = null,
            role = Role.Button,
            interactionSource = remember { MutableInteractionSource() }) {
        }
    }

fun Modifier.textGradient(brush: Brush) = this
    .graphicsLayer(alpha = 0.99f)
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush, blendMode = BlendMode.SrcAtop)
        }
    }