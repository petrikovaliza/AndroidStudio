package com.example.calculateandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val buttonClear : Button = findViewById(R.id.button_clear)
        val buttonBracketLeft: Button = findViewById(R.id.button_bracket_left)
        val buttonBracketRight: Button = findViewById(R.id.button_bracket_right)
        val button0 : Button = findViewById(R.id.button_0)
        val button1 : Button = findViewById(R.id.button_1)
        val button2 : Button = findViewById(R.id.button_2)
        val button3 : Button = findViewById(R.id.button_3)
        val button4 : Button = findViewById(R.id.button_4)
        val button5 : Button = findViewById(R.id.button_5)
        val button6 : Button = findViewById(R.id.button_6)
        val button7 : Button = findViewById(R.id.button_7)
        val button8 : Button = findViewById(R.id.button_8)
        val button9 : Button = findViewById(R.id.button_9)

        val buttonDot: Button = findViewById(R.id.button_dot)
        val buttonDivision: Button = findViewById(R.id.button_division)
        val buttonMultiply: Button = findViewById(R.id.button_multiply)
        val buttonSubstraction: Button = findViewById(R.id.button_substraction)
        val buttonAddition: Button = findViewById(R.id.button_addition)

        val buttonEquals: Button = findViewById(R.id.button_equals)

        val inputText: TextView = findViewById(R.id.input)
        val outputText: TextView = findViewById(R.id.output)

        val buttons = listOf(
            button0,button1, button2,button3,button4,button5,button6,button7,button8,button9,
            buttonAddition,buttonDivision,buttonMultiply,buttonSubstraction,
            buttonClear, buttonEquals,buttonBracketLeft,buttonBracketRight,buttonDot
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                val buttonValue = when (button) {
                    buttonBracketLeft -> "("
                    buttonBracketRight -> ")"
                    buttonDot -> "."
                    buttonDivision ->"/"
                    buttonMultiply -> "x"
                    buttonSubstraction -> "-"
                    buttonAddition -> "+"
                    else -> button.text.toString()
                }
                inputText.text = addToInputText(buttonValue)
            }
        }

        buttonClear.setOnClickListener()
        {
            inputText.text = ""
            outputText.text = ""
        }

        buttonEquals.setOnClickListener {
            showResult()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun addToInputText(buttonValue: String): String {
        val inputText: TextView = findViewById(R.id.input)
        val currentText = inputText.text.toString()
        return if (buttonValue in listOf("+","x","/") && currentText.isEmpty()) {
            currentText
        } else {
            "$currentText$buttonValue"
        }
    }

    private fun getInputExpression(): String {
        val inputText: TextView = findViewById(R.id.input)
        var expression = inputText.text.replace(Regex("/", "/"))
        expression = expression.replace(Regex("x", "*"))   
        return expression
    }

    private fun showResult() {
        val output: TextView = findViewById(R.id.output)

        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNan()) {
                outputText.text = "Error"
                outputText.setTextColor(ContextCompat.getColor(this,R.color.red))
            } else {
                outputText.text = DecimalFormat("0.#####").format(result).toString()
                outputText.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        } catch (e: Exception) {
            outputText.text = "Error"
            outputText.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }
}