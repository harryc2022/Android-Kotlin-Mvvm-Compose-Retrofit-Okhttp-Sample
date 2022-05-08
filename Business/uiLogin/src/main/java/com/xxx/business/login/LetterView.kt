package com.xxx.business.login

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.xxx.business.ui.login.R
import com.xxx.framework.tool.sp2px


/**
 * Created by liyayu on 2018/9/12.
 * 字母侧边栏
 */
class LetterView : View {
    //TAG
    private val TAG = "LettersView"
    //字母数组,#代表未知，比如数字开头
    private val strChars = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    //画笔
    private var mPaint: Paint? = null
    //选中字母的下标
    private var checkIndex: Int = 0
    //字母提示的TextView,需要set/get动态设置显示内容
    private var mTextView: TextView? = null
    //接口回调
    private var onLettersListViewListener: OnLettersListViewListener? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        //实例化画笔
        mPaint = Paint()
        //设置style
//        mPaint!!.typeface = Typeface.DEFAULT_BOLD
        //设置抗锯齿
        mPaint?.isAntiAlias = true
    }

    /**
     * 绘制
     *
     * @param canvas
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        /**
         * 为了排列26个字母，我们可以用坐标点来计算，X居中，Y为 1/27 的递加计算
         * 首先获取到我们View的宽高
         */
        val viewWidth = width
        val viewHeight = height
        //计算一个字母的高度
        val singleHeight = viewHeight / strChars.size
        //循环绘制字母
        for (i in strChars.indices) {
            mPaint?.run {
                //设置选中字母的颜色
                if (i == checkIndex) {
                    color = resources.getColor(R.color.teal_200)
                    textSize = context.sp2px(23F).toFloat()
                } else {
                    color = resources.getColor(R.color.purple_200)
                    //设置字体大小
                    textSize = context.sp2px(18F).toFloat()
                }
                /**
                 * 绘制字母
                 * x: （view的宽度 - 文本的宽度）/ 2
                 * y:  singleHeight * x + singleHeight  //单个字母的高度 + 最上面的字幕空白高度
                 */
                val lettersX = (viewWidth - measureText(strChars[i])) / 2
                val lettersY = (singleHeight * i + singleHeight).toFloat()
                //绘制
                canvas.drawText(strChars[i], lettersX, lettersY, this)
                //重绘
                reset()
            }


        }
    }

//    fun getmTextView(): TextView? {
//        return mTextView
//    }
//
//    fun setmTextView(mTextView: TextView) {
//        this.mTextView = mTextView
//    }

    /**
     * 事件分发
     *
     * @param event
     * @return
     */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        //判断手势
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                setBackgroundResource(R.drawable.round_letter_bg)
                //获取点击的Y坐标，以此来判断选中的字母
                val y = event.y
                Log.i(TAG, "y:" + y)
                //第一次被选中的下标
                val oldCheckIndex = checkIndex
                /**
                 * 计算选中的字母
                 * strChars[当前Y / View的高度 * 字母个数]
                 */
                val c = (y / height * strChars.size).toInt()
                Log.i(TAG, "c:" + c)
                //判断移动
                if (oldCheckIndex != c) {
                    //不能越界
                    if (c >= 0 && c < strChars.size) {
                        //效果联动
                        onLettersListViewListener?.onLettersListener(strChars[c])

                        mTextView?.run {
                            visibility = View.VISIBLE
                            text = strChars[c]
                        }

                    }
                }
                checkIndex = c
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                //设置透明背景
                setBackgroundResource(android.R.color.transparent)
                //恢复不选中
                checkIndex = -1
                invalidate()
                //是否显示
//                mTextView?.visibility = View.INVISIBLE
            }
        }
        return true
    }

    /**
     * 接口回调/ListView联动
     */
    interface OnLettersListViewListener {
        fun onLettersListener(s: String)
    }

    companion object {
        fun setOnLettersListViewListener(letterView: LetterView, onLettersListViewListener: OnLettersListViewListener) {
            letterView.onLettersListViewListener = onLettersListViewListener
        }

        fun getOnLettersListViewListener(letterView: LetterView): OnLettersListViewListener? {
            return letterView.onLettersListViewListener
        }
    }
}



