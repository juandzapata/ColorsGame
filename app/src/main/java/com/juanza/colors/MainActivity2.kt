package com.juanza.colors

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import org.w3c.dom.Text

class MainActivity2 : AppCompatActivity() {
    private var lblTargetColor: TextView? = null
    private var lblProposedColor: TextView? = null
    private var sbrRed: SeekBar? = null
    private var sbrGreen: SeekBar? = null
    private var sbrBlue: SeekBar? = null
    private var lblRedValue: TextView? = null
    private var lblGreenValue: TextView? = null
    private var lblBlueValue: TextView? = null
    private var btnGetScore: Button? = null
    private var btnNewColor: Button? = null
    private var colorsGame: ColorsGame? = null

    fun initViews(){
        lblTargetColor = findViewById(R.id.lblTargetColor)
        lblProposedColor = findViewById(R.id.lblProposedColor)
        sbrRed = findViewById(R.id.sbrRed)
        sbrBlue = findViewById(R.id.sbrBlue)
        sbrGreen = findViewById(R.id.sbrGreen)
        lblRedValue = findViewById(R.id.lblRedValue)
        lblBlueValue = findViewById(R.id.lblBlueValue)
        lblGreenValue = findViewById(R.id.lblGreenValue)
        btnGetScore = findViewById(R.id.btnGetScore)
        btnNewColor = findViewById(R.id.btnNewColor)
    }

    fun restartGame(){
        colorsGame!!.restartGame()
    }

    fun updateValues(){
        val redValue = sbrRed!!.progress
        val greenValue = sbrGreen!!.progress
        val blueValue = sbrBlue!!.progress
        val newBackColor = Color.rgb(redValue, greenValue, blueValue)
        colorsGame!!.proposedBackColor = newBackColor
    }

    fun showScore(){
        val RED = "Red"
        val GREEN = "Green"
        val VERY_LOW = "Very low"
        val LOW = "Low"
        val VERY_HIGH = "Very high"
        val targetColor = colorsGame!!.targetBackColor
        val proposedColor = colorsGame!!.proposedBackColor
        val alert = AlertDialog.Builder(this)
        val text = StringBuilder()
        val tips = StringBuilder()
        val redDiff = Color.red(targetColor) - Color.red(proposedColor)
        val greenDiff = Color.green(targetColor) - Color.green(proposedColor)
        val blueDiff = Color.blue(targetColor) - Color.blue(proposedColor)
        text.append("Your score is {colorsGame!!.score.toString()}")
        if(redDiff > 10){
            tips.append("\n")
            tips.append("{RED.lowercase(Locale.getDefault())} is {VERY_LOW?.lowercase(Locale.getDefault()}")
        } else if(redDiff < -10){
            tips.append("\n")
            tips.append("{RED.lowercase(Locale.getDefault())} is {LOW?.lowercase(Locale.getDefault()}")
        } else if(redDiff < 0){
            tips.append("\n")
            tips.append("{RED.lowercase(Locale.getDefault())} is {HIGH?.lowercase(Locale.getDefault()}")
        } else if(greenDiff > 10){
            tips.append("\n")
            tips.append("{GREEN.lowercase(Locale.getDefault())} is {VERY_LOW?.lowercase(Locale.getDefault()}")
        } else if(greenDiff < -10){
            tips.append("\n")
            tips.append("{GREEN.lowercase(Locale.getDefault())} is {LOW?.lowercase(Locale.getDefault()}")
        } else if(greenDiff < 0){
            tips.append("\n")
            tips.append("{GREEN.lowercase(Locale.getDefault())} is {HIGH?.lowercase(Locale.getDefault()}")
        } else if(blueDiff > 10){
            tips.append("\n")
            tips.append("{BLUE.lowercase(Locale.getDefault())} is {VERY_LOW?.lowercase(Locale.getDefault()}")
        } else if(blueDiff < -10){
            tips.append("\n")
            tips.append("{BLUE.lowercase(Locale.getDefault())} is {LOW?.lowercase(Locale.getDefault()}")
        } else if(blueDiff < 0){
            tips.append("\n")
            tips.append("{BLUE.lowercase(Locale.getDefault())} is {HIGH?.lowercase(Locale.getDefault()}")
        }
        if(tips.length > 0){
            text.append("\n\n")
            text.append("Tips")
            text.append(": ")
            text.append(tips)
        }
        alert.setMessage(text)
        alert.setPositiveButton(getString(R.string.Close), null)
        alert.show()
    }

    fun initEvents(){
        val seekBars = arrayOf(sbrRed, sbrGreen, sbrBlue)
        for(seekBar in seekBars){
            seekBar!!.setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean){
                    updateValues()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
            btnGetScore!!.setOnClickListener { view: View? -> showScore() }
            btnNewColor!!.setOnClickListener {view: View? -> restartGame()}
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        colorsGame = ColorsGame()
        colorsGame!!.onChangeTargetColorListener = ColorsGame.OnChangeTargetColorListener{
            newBackColor: Int, newTextColor: Int ->
            lblTargetColor!!.setBackgroundColor(newBackColor)
            lblTargetColor!!.setTextColor(newTextColor)
        }

        colorsGame!!.onChangeProposedColorListener = ColorsGame.OnChangeProposedColorListener{
            newBackColor: Int, newTextColor: Int ->
            lblProposedColor!!.setBackgroundColor(newBackColor)
            lblProposedColor!!.setTextColor(newTextColor)
            sbrRed!!.progress = Color.red(newBackColor)
            lblRedValue!!.text = Color.red(newBackColor).toString()
            sbrGreen!!.progress = Color.green(newBackColor)
            lblGreenValue!!.text = Color.green(newBackColor).toString()
            sbrBlue!!.progress = Color.blue(newBackColor)
            lblBlueValue!!.text = Color.blue(newBackColor).toString()
        }
        restartGame()
        initEvents()
    }
}