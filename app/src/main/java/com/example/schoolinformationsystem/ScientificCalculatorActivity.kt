package com.example.schoolinformationsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat
import java.text.NumberFormat

class ScientificCalculatorActivity : AppCompatActivity() {

    var calculation: TextView? = null
    var answer: TextView? = null
    var sCalculation = ""
    var sAnswer: String? = ""
    var number_one = ""
    var number_two = ""
    var current_oprator = ""
    var prev_ans: String? = ""
    var RorD = "RAD"
    var sin_inv: String? = null
    var cos_inv: String? = null
    var tan_inv: String? = null
    var function: String? = null
    var Result = 0.0
    var numberOne = 0.0
    var numberTwo = 0.0
    var temp = 0.0
    var dot_present = false
    var number_allow = true
    var root_present = false
    var invert_allow = true
    var power_present = false
    var factorial_present = false
    var constant_present = false
    var function_present = false
    var value_inverted = false
    var format: NumberFormat? = null
    var longformate: NumberFormat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scientific_calculator)



        calculation = findViewById(R.id.calculation)
        calculation?.setMovementMethod(ScrollingMovementMethod())
        answer = findViewById(R.id.answer)
        format = DecimalFormat("#.####")
        longformate = DecimalFormat("0.#E0")
        sin_inv = Html.fromHtml("sin<sup><small>-1</small></sup>").toString()
        cos_inv = Html.fromHtml("cos<sup><small>-1</small></sup>").toString()
        tan_inv = Html.fromHtml("tan<sup><small>-1</small></sup>").toString()
        val btn_RorD = findViewById<Button>(R.id.btn_RorD)
        btn_RorD.setOnClickListener {
            RorD = btn_RorD.text.toString()
            RorD = if (RorD == "RAD") "DEG" else "RAD"
            btn_RorD.text = RorD
        }
    }

    fun onClickNumber(v: View) {
        if (number_allow) {
            val bn = v as Button
            sCalculation += bn.text
            number_one += bn.text
            numberOne = number_one.toDouble()
            if (function_present) {
                calculateFunction(function)
                return
            }
            if (root_present) {
                numberOne = Math.sqrt(numberOne)
            }
            when (current_oprator) {
                "" -> temp = if (power_present) {
                    Result + Math.pow(numberTwo, numberOne)
                } else {
                    Result + numberOne
                }
                "+" -> temp = if (power_present) {
                    Result + Math.pow(numberTwo, numberOne)
                } else {
                    Result + numberOne
                }
                "-" -> temp = if (power_present) {
                    Result - Math.pow(numberTwo, numberOne)
                } else {
                    Result - numberOne
                }
                "x" -> temp = if (power_present) {
                    Result * Math.pow(numberTwo, numberOne)
                } else {
                    Result * numberOne
                }
                "/" -> try {
                    temp = if (power_present) {
                        Result / Math.pow(numberTwo, numberOne)
                    } else {
                        Result / numberOne
                    }
                } catch (e: Exception) {
                    sAnswer = e.message
                }
            }
            sAnswer = format!!.format(temp).toString()
            updateCalculation()
        }
    }

    fun onClickOprator(x: View) {
        val ob = x as Button
        if (sAnswer !== "") {
            if (current_oprator !== "") {
                val c = getcharfromLast(sCalculation, 2)
                if (c == '+' || c == '-' || c == 'x' || c == '/') {
                    sCalculation = sCalculation.substring(0, sCalculation.length - 3)
                }
            }
            sCalculation = """$sCalculation
${ob.text} """
            number_one = ""
            Result = temp
            current_oprator = ob.text.toString()
            updateCalculation()
            number_two = ""
            numberTwo = 0.0
            dot_present = false
            number_allow = true
            root_present = false
            invert_allow = true
            power_present = false
            factorial_present = false
            constant_present = false
            function_present = false
            value_inverted = false
        }
    }

    private fun getcharfromLast(s: String, i: Int): Char {
        return s[s.length - i]
    }

    fun onClickClear(v: View?) {
        cleardata()
    }

    fun cleardata() {
        sCalculation = ""
        sAnswer = ""
        current_oprator = ""
        number_one = ""
        number_two = ""
        prev_ans = ""
        Result = 0.0
        numberOne = 0.0
        numberTwo = 0.0
        temp = 0.0
        updateCalculation()
        dot_present = false
        number_allow = true
        root_present = false
        invert_allow = true
        power_present = false
        factorial_present = false
        function_present = false
        constant_present = false
        value_inverted = false
    }

    fun updateCalculation() {
        calculation!!.text = sCalculation
        answer!!.text = sAnswer
    }

    fun onDotClick(view: View?) {
        if (!dot_present) {
            if (number_one.length == 0) {
                number_one = "0."
                sCalculation += "0."
                sAnswer = "0."
                dot_present = true
                updateCalculation()
            } else {
                number_one += "."
                sCalculation += "."
                sAnswer += "."
                dot_present = true
                updateCalculation()
            }
        }
    }

    fun onClickEqual(view: View?) {
        showresult()
    }

    fun showresult() {
        if (sAnswer !== "" && sAnswer !== prev_ans) {
            sCalculation += "\n= $sAnswer\n----------\n$sAnswer "
            Result = temp
            prev_ans = sAnswer
            updateCalculation()
            dot_present = true
            power_present = false
            number_allow = false
            factorial_present = false
            constant_present = false
            function_present = false
            value_inverted = false
        }
    }

    fun onModuloClick(view: View?) {
        if (sAnswer !== "" && getcharfromLast(sCalculation, 1) != ' ') {
            sCalculation += "% "
            when (current_oprator) {
                "" -> temp = temp / 100
                "+" -> temp = Result + Result * numberOne / 100
                "-" -> temp = Result - Result * numberOne / 100
                "x" -> temp = Result * (numberOne / 100)
                "/" -> try {
                    temp = Result / (numberOne / 100)
                } catch (e: Exception) {
                    sAnswer = e.message
                }
            }
            sAnswer = format!!.format(temp).toString()
            if (sAnswer!!.length > 9) {
                sAnswer = longformate!!.format(temp).toString()
            }
            Result = temp
            showresult()
        }
    }

    fun removeuntilchar(str: String, chr: Char) {
        var str = str
        val c = getcharfromLast(str, 1)
        if (c != chr) {
            //remove last char
            str = removechar(str, 1)
            sCalculation = str
            updateCalculation()
            removeuntilchar(str, chr)
        }
    }

    fun removechar(str: String, i: Int): String {
        val c = str[str.length - i]
        if (c == '.' && !dot_present) {
            dot_present = false
        }
        if (c == '^') {
            power_present = false
        }
        return if (c == ' ') {
            str.substring(0, str.length - (i - 1))
        } else str.substring(0, str.length - i)
    }

    fun onRootClick(view: View) {
        val root = view as Button
        //first we check if root is present or not
        if (sAnswer === "" && Result == 0.0 && !root_present && !function_present) {
            sCalculation = root.text.toString()
            root_present = true
            invert_allow = false
            updateCalculation()
        } else if (getcharfromLast(sCalculation, 1) == ' ' && current_oprator !== "" && !root_present) {
            sCalculation += root.text.toString()
            root_present = true
            invert_allow = false
            updateCalculation()
        }
    }

    fun onPowerClick(view: View) {
        val power = view as Button
        if (sCalculation !== "" && !root_present && !power_present && !function_present) {
            if (getcharfromLast(sCalculation, 1) != ' ') {
                sCalculation += power.text.toString()
                //we need second variable for the power
                number_two = number_one
                numberTwo = numberOne
                number_one = ""
                power_present = true
                updateCalculation()
            }
        }
    }

    fun onSquareClick(view: View?) {
        if (sCalculation !== "" && sAnswer !== "") {
            if (!root_present && !function_present && !power_present && getcharfromLast(sCalculation, 1) != ' ' && getcharfromLast(sCalculation, 1) != ' ') {
                numberOne = numberOne * numberOne
                number_one = format!!.format(numberOne).toString()
                if (current_oprator === "") {
                    if (number_one.length > 9) {
                        number_one = longformate!!.format(numberOne)
                    }
                    sCalculation = number_one
                    temp = numberOne
                } else {
                    when (current_oprator) {
                        "+" -> temp = Result + numberOne
                        "-" -> temp = Result - numberOne
                        "x" -> temp = Result * numberOne
                        "/" -> try {
                            temp = Result / numberOne
                        } catch (e: Exception) {
                            sAnswer = e.message
                        }
                    }
                    removeuntilchar(sCalculation, ' ')
                    if (number_one.length > 9) {
                        number_one = longformate!!.format(numberOne)
                    }
                    sCalculation += number_one
                }
                sAnswer = format!!.format(temp)
                if (sAnswer?.length!! > 9) {
                    sAnswer = longformate!!.format(temp)
                }
                updateCalculation()
            }
        }
    }

    fun onClickFactorial(view: View?) {
        if (sAnswer != "" && !factorial_present && !root_present && !dot_present && !power_present && !function_present) {
            if (getcharfromLast(sCalculation, 1) != ' ') {
                for (i in 1 until number_one.toInt()) {
                    numberOne *= i.toDouble()
                }
                if (numberOne == 0.0) {
                    numberOne = 1.0
                }
                number_one = format!!.format(numberOne).toString()
                when (current_oprator) {
                    "" -> Result = numberOne
                    "+" -> Result += numberOne
                    "-" -> Result -= numberOne
                    "x" -> Result *= numberOne
                    "/" -> try {
                        Result /= numberOne
                    } catch (e: Exception) {
                        sAnswer = e.message
                    }
                }
                sAnswer = Result.toString()
                temp = Result
                sCalculation += "! "
                factorial_present = true
                number_allow = false
                updateCalculation()
            }
        }
    }

    fun onClickInverse(view: View?) {
        if (sAnswer != "" && !factorial_present && !root_present && !dot_present && !power_present && !function_present) {
            if (getcharfromLast(sCalculation, 1) != ' ') {
                numberOne = Math.pow(numberOne, -1.0)
                number_one = format!!.format(numberOne).toString()
                when (current_oprator) {
                    "" -> {
                        temp = numberOne
                        sCalculation = number_one
                    }
                    "+" -> {
                        temp = Result + numberOne
                        removeuntilchar(sCalculation, ' ')
                        sCalculation += number_one
                    }
                    "-" -> {
                        temp = Result - numberOne
                        removeuntilchar(sCalculation, ' ')
                        sCalculation += number_one
                    }
                    "x" -> {
                        temp = Result * numberOne
                        removeuntilchar(sCalculation, ' ')
                        sCalculation += number_one
                    }
                    "/" -> try {
                        temp = Result / numberOne
                        removeuntilchar(sCalculation, ' ')
                        sCalculation += number_one
                    } catch (e: Exception) {
                        sAnswer = e.message
                    }
                }
                sAnswer = format!!.format(temp).toString()
                updateCalculation()
            }
        }
    }

    fun onClickPIorE(view: View) {
        val btn_PIorE = view as Button
        number_allow = false
        if (!root_present && !dot_present && !power_present && !factorial_present && !constant_present && !function_present) {
            var str_PIorE = btn_PIorE.text.toString() + " "
            if (str_PIorE != "e ") {
                str_PIorE = "\u03A0" + " "
            }
            if (sCalculation === "") {
                number_one = str_PIorE
                numberOne = if (str_PIorE == "e ") {
                    Math.E
                } else {
                    Math.PI
                }
                temp = numberOne
            } else {
                numberOne = if (str_PIorE == "e ") {
                    //use ternary operation
                    if (getcharfromLast(sCalculation, 1) == ' ') Math.E else number_one.toDouble() * Math.E
                } else {
                    if (getcharfromLast(sCalculation, 1) == ' ') Math.PI else number_one.toDouble() * Math.PI
                }
                when (current_oprator) {
                    "" -> temp = Result + numberOne
                    "+" -> temp = Result + numberOne
                    "-" -> temp = Result - numberOne
                    "x" -> temp = Result * numberOne
                    "/" -> try {
                        temp = Result / numberOne
                    } catch (e: Exception) {
                        sAnswer = e.message
                    }
                }
            }
            sCalculation += str_PIorE
            sAnswer = format!!.format(temp).toString()
            updateCalculation()
            constant_present = true
        }
    }

    fun onClickFunction(view: View) {
        val func = view as Button
        function = func.hint.toString() //  sin_inv is not in the text
        if (!function_present && !root_present && !power_present && !factorial_present && !dot_present) {
            calculateFunction(function)
        }
    }

    fun calculateFunction(function: String?) {
        function_present = true
        if (current_oprator !== "" && getcharfromLast(sCalculation, 1) == ' ') {
            sCalculation += when (function) {
                "sin_inv" -> "$sin_inv("
                "cos_inv" -> "$cos_inv("
                "tan_inv" -> "$tan_inv("
                else -> "$function("
            }
            updateCalculation()
        } else {
            when (current_oprator) {
                "" -> {
                    if (sCalculation == "") {
                        sCalculation += when (function) {
                            "sin_inv" -> "$sin_inv( "
                            "cos_inv" -> "$cos_inv( "
                            "tan_inv" -> "$tan_inv( "
                            else -> "$function( "
                        }
                    } else {
                        when (function) {
                            "log" -> {
                                temp = Result + Math.log10(numberOne)
                                sCalculation = "log( $number_one"
                            }
                            "ln" -> {
                                temp = Result + Math.log(numberOne)
                                sCalculation = "ln( $number_one"
                            }
                            "sin" -> {
                                if (RorD == "DEG") {
                                    numberOne = Math.toDegrees(numberOne)
                                }
                                temp = Result + Math.sin(numberOne)
                                sCalculation = "sin( $number_one"
                            }
                            "sin_inv" -> {
                                if (RorD == "DEG") {
                                    numberOne = Math.toDegrees(numberOne)
                                }
                                temp = Result + Math.asin(numberOne)
                                sCalculation = "$sin_inv( $number_one"
                            }
                            "cos" -> {
                                if (RorD == "DEG") {
                                    numberOne = Math.toDegrees(numberOne)
                                }
                                temp = Result + Math.cos(numberOne)
                                sCalculation = "cos( $number_one"
                            }
                            "cos_inv" -> {
                                if (RorD == "DEG") {
                                    numberOne = Math.toDegrees(numberOne)
                                }
                                temp = Result + Math.acos(numberOne)
                                sCalculation = "$cos_inv( $number_one"
                            }
                            "tan" -> {
                                if (RorD == "DEG") {
                                    numberOne = Math.toDegrees(numberOne)
                                }
                                temp = Result + Math.tan(numberOne)
                                sCalculation = "tan( $number_one"
                            }
                            "tan_inv" -> {
                                if (RorD == "DEG") {
                                    numberOne = Math.toDegrees(numberOne)
                                }
                                temp = Result + Math.atan(numberOne)
                                sCalculation = "$tan_inv( $number_one"
                            }
                        }
                    }
                    sAnswer = temp.toString()
                    updateCalculation()
                }
                "+" -> {
                    removeuntilchar(sCalculation, ' ')
                    when (function) {
                        "log" -> {
                            temp = Result + Math.log10(numberOne)
                            sCalculation += "log($number_one"
                        }
                        "ln" -> {
                            temp = Result + Math.log(numberOne)
                            sCalculation += "ln($number_one"
                        }
                        "sin" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result + Math.sin(numberOne)
                            sCalculation += "sin($number_one"
                        }
                        "sin_inv" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result + Math.asin(numberOne)
                            sCalculation += "$sin_inv($number_one"
                        }
                        "cos" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result + Math.cos(numberOne)
                            sCalculation += "cos($number_one"
                        }
                        "cos_inv" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result + Math.acos(numberOne)
                            sCalculation += "$cos_inv($number_one"
                        }
                        "tan" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result + Math.tan(numberOne)
                            sCalculation += "tan($number_one"
                        }
                        "tan_inv" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result + Math.atan(numberOne)
                            sCalculation += "$tan_inv($number_one"
                        }
                    }
                    sAnswer = temp.toString()
                    updateCalculation()
                }
                "-" -> {
                    removeuntilchar(sCalculation, ' ')
                    when (function) {
                        "log" -> {
                            temp = Result - Math.log10(numberOne)
                            sCalculation += "log($number_one"
                        }
                        "ln" -> {
                            temp = Result - Math.log(numberOne)
                            sCalculation += "ln($number_one"
                        }
                        "sin" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result - Math.sin(numberOne)
                            sCalculation += "sin($number_one"
                        }
                        "sin_inv" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result - Math.asin(numberOne)
                            sCalculation += "$sin_inv($number_one"
                        }
                        "cos" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result - Math.cos(numberOne)
                            sCalculation += "cos($number_one"
                        }
                        "cos_inv" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result - Math.acos(numberOne)
                            sCalculation += "$cos_inv($number_one"
                        }
                        "tan" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result - Math.tan(numberOne)
                            sCalculation += "tan($number_one"
                        }
                        "tan_inv" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result - Math.atan(numberOne)
                            sCalculation += "$tan_inv($number_one"
                        }
                    }
                    sAnswer = temp.toString()
                    updateCalculation()
                }
                "x" -> {
                    removeuntilchar(sCalculation, ' ')
                    when (function) {
                        "log" -> {
                            temp = Result * Math.log10(numberOne)
                            sCalculation += "log($number_one"
                        }
                        "ln" -> {
                            temp = Result * Math.log(numberOne)
                            sCalculation += "ln($number_one"
                        }
                        "sin" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result * Math.sin(numberOne)
                            sCalculation += "sin($number_one"
                        }
                        "sin_inv" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result * Math.asin(numberOne)
                            sCalculation += "$sin_inv($number_one"
                        }
                        "cos" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result * Math.cos(numberOne)
                            sCalculation += "cos($number_one"
                        }
                        "cos_inv" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result * Math.acos(numberOne)
                            sCalculation += "$cos_inv($number_one"
                        }
                        "tan" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result * Math.tan(numberOne)
                            sCalculation += "tan($number_one"
                        }
                        "tan_inv" -> {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result * Math.atan(numberOne)
                            sCalculation += "$tan_inv($number_one"
                        }
                    }
                    sAnswer = temp.toString()
                    updateCalculation()
                }
                "/" -> {
                    removeuntilchar(sCalculation, ' ')
                    when (function) {
                        "log" -> try {
                            temp = Result / Math.log10(numberOne)
                            sCalculation += "log($number_one"
                        } catch (e: Exception) {
                            sAnswer = e.message
                        }
                        "ln" -> try {
                            temp = Result / Math.log(numberOne)
                            sCalculation += "ln($number_one"
                        } catch (e: Exception) {
                            sAnswer = e.message
                        }
                        "sin" -> try {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result / Math.sin(numberOne)
                            sCalculation += "sin($number_one"
                        } catch (e: Exception) {
                            sAnswer = e.message
                        }
                        "sin_inv" -> try {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result / Math.asin(numberOne)
                            sCalculation += "$sin_inv($number_one"
                        } catch (e: Exception) {
                            sAnswer = e.message
                        }
                        "cos" -> try {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result / Math.cos(numberOne)
                            sCalculation += "cos($number_one"
                        } catch (e: Exception) {
                            sAnswer = e.message
                        }
                        "cos_inv" -> try {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result / Math.acos(numberOne)
                            sCalculation += "$cos_inv($number_one"
                        } catch (e: Exception) {
                            sAnswer = e.message
                        }
                        "tan" -> try {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result / Math.tan(numberOne)
                            sCalculation += "tan($number_one"
                        } catch (e: Exception) {
                            sAnswer = e.message
                        }
                        "tan_inv" -> try {
                            if (RorD == "DEG") {
                                numberOne = Math.toDegrees(numberOne)
                            }
                            temp = Result / Math.atan(numberOne)
                            sCalculation += "$tan_inv($number_one"
                        } catch (e: Exception) {
                            sAnswer = e.message
                        }
                    }
                    sAnswer = temp.toString()
                    updateCalculation()
                }
            }
        }
    }

    fun onClickDelete(view: View?) {
        if (function_present) {
            DeleteFunction()
            return
        }
        if (root_present) {
            removeRoot()
            return
        }
        if (power_present) {
            removePower()
            return
        }
        if (sAnswer !== "") {
            if (getcharfromLast(sCalculation, 1) != ' ') {
                if (number_one.length < 2 && current_oprator !== "") {
                    number_one = ""
                    temp = Result
                    sAnswer = format!!.format(Result).toString()
                    sCalculation = removechar(sCalculation, 1)
                    updateCalculation()
                } else {
                    when (current_oprator) {
                        "" -> {
                            if (value_inverted) {
                                sAnswer = sAnswer!!.substring(1, sAnswer!!.length)
                                sCalculation = sCalculation.substring(1, sAnswer!!.length)
                                updateCalculation()
                            }
                            if (sCalculation.length < 2) {
                                cleardata()
                            } else {
                                if (getcharfromLast(sCalculation, 1) == '.') {
                                    dot_present = false
                                }
                                number_one = removechar(number_one, 1)
                                numberOne = number_one.toDouble()
                                temp = numberOne
                                sCalculation = number_one
                                sAnswer = number_one
                                updateCalculation()
                            }
                        }
                        "+" -> {
                            if (value_inverted) {
                                numberOne = numberOne * -1
                                number_one = format!!.format(numberOne).toString()
                                temp = Result + numberOne
                                sAnswer = format!!.format(temp).toString()
                                removeuntilchar(sCalculation, ' ')
                                sCalculation += number_one
                                updateCalculation()
                                value_inverted = if (value_inverted) false else true
                            }
                            if (getcharfromLast(sCalculation, 1) == '.') {
                                dot_present = false
                            }
                            number_one = removechar(number_one, 1)
                            if (number_one.length == 1 && number_one === ".") {
                                numberOne = number_one.toDouble()
                            }
                            numberOne = number_one.toDouble()
                            temp = Result + numberOne
                            sAnswer = format!!.format(temp).toString()
                            sCalculation = removechar(sCalculation, 1)
                            updateCalculation()
                        }
                        "-" -> {
                            if (value_inverted) {
                                numberOne = numberOne * -1
                                number_one = format!!.format(numberOne).toString()
                                temp = Result - numberOne
                                sAnswer = format!!.format(temp).toString()
                                removeuntilchar(sCalculation, ' ')
                                sCalculation += number_one
                                updateCalculation()
                                value_inverted = if (value_inverted) false else true
                            }
                            if (getcharfromLast(sCalculation, 1) == '.') {
                                dot_present = false
                            }
                            number_one = removechar(number_one, 1)
                            if (number_one.length == 1 && number_one === ".") {
                                numberOne = number_one.toDouble()
                            }
                            numberOne = number_one.toDouble()
                            temp = Result - numberOne
                            sAnswer = format!!.format(temp).toString()
                            sCalculation = removechar(sCalculation, 1)
                            updateCalculation()
                        }
                        "x" -> {
                            if (value_inverted) {
                                numberOne = numberOne * -1
                                number_one = format!!.format(numberOne).toString()
                                temp = Result * numberOne
                                sAnswer = format!!.format(temp).toString()
                                removeuntilchar(sCalculation, ' ')
                                sCalculation += number_one
                                updateCalculation()
                                value_inverted = if (value_inverted) false else true
                            }
                            if (getcharfromLast(sCalculation, 1) == '.') {
                                dot_present = false
                            }
                            number_one = removechar(number_one, 1)
                            if (number_one.length == 1 && number_one === ".") {
                                numberOne = number_one.toDouble()
                            }
                            numberOne = number_one.toDouble()
                            temp = Result * numberOne
                            sAnswer = format!!.format(temp).toString()
                            sCalculation = removechar(sCalculation, 1)
                            updateCalculation()
                        }
                        "/" -> {
                            try {
                                if (value_inverted) {
                                    numberOne = numberOne * -1
                                    number_one = format!!.format(numberOne).toString()
                                    temp = Result / numberOne
                                    sAnswer = format!!.format(temp).toString()
                                    removeuntilchar(sCalculation, ' ')
                                    sCalculation += number_one
                                    updateCalculation()
                                    value_inverted = if (value_inverted) false else true
                                }
                                if (getcharfromLast(sCalculation, 1) == '.') {
                                    dot_present = false
                                }
                                number_one = removechar(number_one, 1)
                                if (number_one.length == 1 && number_one === ".") {
                                    numberOne = number_one.toDouble()
                                }
                                numberOne = number_one.toDouble()
                                temp = Result / numberOne
                                sAnswer = format!!.format(temp).toString()
                                sCalculation = removechar(sCalculation, 1)
                            } catch (e: Exception) {
                                sAnswer = e.message
                            }
                            updateCalculation()
                        }
                    }
                }
            }
        }
    }

    fun removePower() {
        if (sAnswer !== "" && sCalculation !== "") {
            when (current_oprator) {
                "" -> if (getcharfromLast(sCalculation, 1) == '^') {
                    sCalculation = removechar(sCalculation, 1)
                    number_one = number_two
                    numberOne = number_one.toDouble()
                    number_two = ""
                    numberTwo = 0.0
                    updateCalculation()
                } else if (getcharfromLast(sCalculation, 2) == '^') {
                    number_one = ""
                    numberOne = 0.0
                    temp = numberTwo
                    sAnswer = format!!.format(temp).toString()
                    sCalculation = removechar(sCalculation, 1)
                    updateCalculation()
                } else {
                    if (getcharfromLast(sCalculation, 1) == '.') {
                        dot_present = false
                    }
                    number_one = removechar(number_one, 1)
                    numberOne = number_one.toDouble()
                    temp = Math.pow(numberTwo, numberOne)
                    sAnswer = format!!.format(temp).toString()
                    sCalculation = removechar(sCalculation, 1)
                    updateCalculation()
                }
                "+" -> if (getcharfromLast(sCalculation, 1) == '^') {
                    sCalculation = removechar(sCalculation, 1)
                    number_one = number_two
                    numberOne = number_one.toDouble()
                    number_two = ""
                    numberTwo = 0.0
                    updateCalculation()
                } else if (getcharfromLast(sCalculation, 2) == '^') {
                    number_one = ""
                    numberOne = 0.0
                    temp = Result + numberTwo
                    sAnswer = format!!.format(temp).toString()
                    sCalculation = removechar(sCalculation, 1)
                    updateCalculation()
                } else {
                    if (getcharfromLast(sCalculation, 1) == '.') {
                        dot_present = false
                    }
                    number_one = removechar(number_one, 1)
                    numberOne = number_one.toDouble()
                    temp = Result + Math.pow(numberTwo, numberOne)
                    sAnswer = format!!.format(temp).toString()
                    sCalculation = removechar(sCalculation, 1)
                    updateCalculation()
                }
                "-" -> if (getcharfromLast(sCalculation, 1) == '^') {
                    sCalculation = removechar(sCalculation, 1)
                    number_one = number_two
                    numberOne = number_one.toDouble()
                    number_two = ""
                    numberTwo = 0.0
                    updateCalculation()
                } else if (getcharfromLast(sCalculation, 2) == '^') {
                    number_one = ""
                    numberOne = 0.0
                    temp = Result - numberTwo
                    sAnswer = format!!.format(temp).toString()
                    sCalculation = removechar(sCalculation, 1)
                    updateCalculation()
                } else {
                    if (getcharfromLast(sCalculation, 1) == '.') {
                        dot_present = false
                    }
                    number_one = removechar(number_one, 1)
                    numberOne = number_one.toDouble()
                    temp = Result - Math.pow(numberTwo, numberOne)
                    sAnswer = format!!.format(temp).toString()
                    sCalculation = removechar(sCalculation, 1)
                    updateCalculation()
                }
                "x" -> if (getcharfromLast(sCalculation, 1) == '^') {
                    sCalculation = removechar(sCalculation, 1)
                    number_one = number_two
                    numberOne = number_one.toDouble()
                    number_two = ""
                    numberTwo = 0.0
                    updateCalculation()
                } else if (getcharfromLast(sCalculation, 2) == '^') {
                    number_one = ""
                    numberOne = 0.0
                    temp = Result * numberTwo
                    sAnswer = format!!.format(temp).toString()
                    sCalculation = removechar(sCalculation, 1)
                    updateCalculation()
                } else {
                    if (getcharfromLast(sCalculation, 1) == '.') {
                        dot_present = false
                    }
                    number_one = removechar(number_one, 1)
                    numberOne = number_one.toDouble()
                    temp = Result * Math.pow(numberTwo, numberOne)
                    sAnswer = format!!.format(temp).toString()
                    sCalculation = removechar(sCalculation, 1)
                    updateCalculation()
                }
                "/" -> {
                    try {
                        if (getcharfromLast(sCalculation, 1) == '^') {
                            sCalculation = removechar(sCalculation, 1)
                            number_one = number_two
                            numberOne = number_one.toDouble()
                            number_two = ""
                            numberTwo = 0.0
                            updateCalculation()
                        } else if (getcharfromLast(sCalculation, 2) == '^') {
                            number_one = ""
                            numberOne = 0.0
                            temp = Result / numberTwo
                            sAnswer = format!!.format(temp).toString()
                            sCalculation = removechar(sCalculation, 1)
                            updateCalculation()
                        } else {
                            if (getcharfromLast(sCalculation, 1) == '.') {
                                dot_present = false
                            }
                            number_one = removechar(number_one, 1)
                            numberOne = number_one.toDouble()
                            temp = Result / Math.pow(numberTwo, numberOne)
                            sAnswer = format!!.format(temp).toString()
                            sCalculation = removechar(sCalculation, 1)
                            updateCalculation()
                        }
                    } catch (e: Exception) {
                        sAnswer = e.message
                    }
                    updateCalculation()
                }
            }
        }
    }

    fun removeRoot() {
        if (getcharfromLast(sCalculation, 1) != ' ') {
            if (getcharfromLast(sCalculation, 1).toString() == "\u221A") {
                sCalculation = removechar(sCalculation, 1)
                root_present = false
                invert_allow = true
                updateCalculation()
            }
            if (sAnswer !== "") {
                if (number_one.length < 2 && current_oprator !== "") {
                    number_one = ""
                    numberOne = Result
                    temp = Result
                    sAnswer = format!!.format(Result).toString()
                    sCalculation = removechar(sCalculation, 1)
                    updateCalculation()
                } else {
                    when (current_oprator) {
                        "" -> if (sCalculation.length <= 2) {
                            cleardata()
                        } else {
                            if (getcharfromLast(sCalculation, 1) == '.') {
                                dot_present = false
                            }
                            number_one = removechar(number_one, 1)
                            numberOne = number_one.toDouble()
                            numberOne = Math.sqrt(numberOne)
                            temp = numberOne
                            sAnswer = format!!.format(temp).toString()
                            sCalculation = "\u221A" + number_one
                            updateCalculation()
                        }
                        "+" -> {
                            if (getcharfromLast(sCalculation, 1) == '.') {
                                dot_present = false
                            }
                            number_one = removechar(number_one, 1)
                            if (number_one.length == 1 && number_one === ".") {
                                numberOne = number_one.toDouble()
                            }
                            numberOne = number_one.toDouble()
                            numberOne = Math.sqrt(numberOne)
                            temp = Result + numberOne
                            sAnswer = format!!.format(temp).toString()
                            sCalculation = removechar(sCalculation, 1)
                            updateCalculation()
                        }
                        "-" -> {
                            if (getcharfromLast(sCalculation, 1) == '.') {
                                dot_present = false
                            }
                            number_one = removechar(number_one, 1)
                            if (number_one.length == 1 && number_one === ".") {
                                numberOne = number_one.toDouble()
                            }
                            numberOne = number_one.toDouble()
                            numberOne = Math.sqrt(numberOne)
                            temp = Result - numberOne
                            sAnswer = format!!.format(temp).toString()
                            sCalculation = removechar(sCalculation, 1)
                            updateCalculation()
                        }
                        "x" -> {
                            if (getcharfromLast(sCalculation, 1) == '.') {
                                dot_present = false
                            }
                            number_one = removechar(number_one, 1)
                            if (number_one.length == 1 && number_one === ".") {
                                numberOne = number_one.toDouble()
                            }
                            numberOne = number_one.toDouble()
                            numberOne = Math.sqrt(numberOne)
                            temp = Result * numberOne
                            sAnswer = format!!.format(temp).toString()
                            sCalculation = removechar(sCalculation, 1)
                            updateCalculation()
                        }
                        "/" -> {
                            try {
                                if (getcharfromLast(sCalculation, 1) == '.') {
                                    dot_present = false
                                }
                                number_one = removechar(number_one, 1)
                                if (number_one.length == 1 && number_one === ".") {
                                    numberOne = number_one.toDouble()
                                }
                                numberOne = number_one.toDouble()
                                numberOne = Math.sqrt(numberOne)
                                temp = Result + numberOne
                                sAnswer = format!!.format(temp).toString()
                                sCalculation = removechar(sCalculation, 1)
                            } catch (e: Exception) {
                                sAnswer = e.message
                            }
                            updateCalculation()
                        }
                    }
                }
            }
        }
    }

    fun DeleteFunction() {
        if (current_oprator === "") {
            if (getcharfromLast(sCalculation, 1) == ' ') {
                cleardata()
            } else if (getcharfromLast(sCalculation, 2) == ' ') {
                cleardata()
            } else {
                sCalculation = removechar(sCalculation, 1)
                number_one = removechar(number_one, 1)
                numberOne = number_one.toDouble()
                calculateFunction(function)
            }
            updateCalculation()
        } else {
            if (getcharfromLast(sCalculation, 1) == '(') {
                removeuntilchar(sCalculation, ' ')
                function_present = false
            } else if (getcharfromLast(sCalculation, 2) == '(') {
                sCalculation = removechar(sCalculation, 1)
                number_one = ""
                temp = Result
                sAnswer = format!!.format(Result).toString()
            } else {
                sCalculation = removechar(sCalculation, 1)
                number_one = removechar(number_one, 1)
                numberOne = number_one.toDouble()
                calculateFunction(function)
            }
            updateCalculation()
        }
    }
}