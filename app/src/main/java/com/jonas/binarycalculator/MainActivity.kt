package com.jonas.binarycalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val calculator = Calculator()
        var input: TextView = input
        var output: TextView = output

        var addButton: Button = add
        addButton.setOnClickListener {
            output.text = ""
            output.append("+ " + input.text)
            input.text = ""
        }

        var zeroButton: Button = zero
        zeroButton.setOnClickListener { input.append("0") }

        var oneButton: Button = one
        oneButton.setOnClickListener { input.append("1") }

        var submitButton: Button = submit
        submitButton.setOnClickListener {
            if (output.text.isNotEmpty() && input.text.isNotEmpty()) {
                var inputBinary = input.text.toString()
                var outputBinary = output.text.toString().substring(2, output.text.length)
                when (output.text.toString()[0]) {
                    '*' -> {
                        Toast.makeText(
                            this, calculator.add(
                                inputBinary,
                                outputBinary
                            ),
                            Toast.LENGTH_SHORT
                        ).show()
                        input.text = ""
                        output.text = "= " + calculator.multiply(inputBinary, outputBinary)
                    }
                    '+' -> {
                        Toast.makeText(
                            this, calculator.add(
                                inputBinary,
                                outputBinary
                            ),
                            Toast.LENGTH_SHORT
                        ).show()
                        input.text = ""
                        output.text = "= " + calculator.add(inputBinary, outputBinary)
                    }
                    '-' -> {
                        Toast.makeText(
                            this, calculator.add(
                                inputBinary,
                                outputBinary
                            ),
                            Toast.LENGTH_SHORT
                        ).show()
                        input.text = ""
                        output.text = "= " + calculator.subtract(inputBinary, outputBinary)
                    }
                }


            } else {
                Toast.makeText(this, "are you trying to stress me?", Toast.LENGTH_SHORT).show()
                input.text = ""
                output.text = ""
            }
        }

        var deleteButton: Button = delete
        deleteButton.setOnClickListener {
            if (input.text.isNotEmpty()) {
                input.text = input.text.substring(1, input.text.length)
            }
        }
        var base10Button: Button = base10
        base10Button.setOnClickListener {
            if (output.text.isNotEmpty()) {
                Toast.makeText(
                    this,
                    calculator.calculateBase10(output.text.substring(2, output.text.length)),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Are you trying to stress me?",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        var multiplyButton: Button = multiply
        multiplyButton.setOnClickListener {
            output.text = ""
            output.append("* " + input.text)
            input.text = ""
        }


        var subtractButton: Button = subtract
        subtractButton.setOnClickListener {
            output.text = ""
            output.append("- " + input.text)
            input.text = ""
        }
        var divideButton: Button = divide
        var arr: ArrayList<Button> = ArrayList()
        arr.add(divideButton)
        arr.forEach { button: Button ->
            button.setOnClickListener {
                Toast.makeText(this, "not supported yet!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
