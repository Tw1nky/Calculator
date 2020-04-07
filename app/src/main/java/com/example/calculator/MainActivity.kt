package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder


open class MainActivity : AppCompatActivity() {
    private var can = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttons = mutableListOf(tv1, tv2, tv3, tv4, tv5, tv6,tv7, tv8, tv9, tvDot,tv0)
        val button = mutableListOf(tvPlus, tvMinus, tvMultiply, tvShare)

        buttons.forEach { btn -> btn.setOnClickListener{addOnExpression(btn.text.toString(),true)} }

        button.forEach { btn -> btn.setOnClickListener{addOnExpression(btn.text.toString(),false)} }

        tvDel.setOnClickListener{
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvEqual.setOnClickListener{
            try{
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble()){
                    tvResult.text = longResult.toString()
                }
                    else {
                    tvResult.text = result.toString()
                }
            }
        catch (e:Exception){
        }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run{
            putString("tvExpression",tvExpression.text.toString())
            putString("tvResult",tvResult.text.toString())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        tvExpression.text = savedInstanceState.getString("tvExpression")
        tvResult.text = savedInstanceState.getString("tvResult")
    }


    private fun addOnExpression(append : String, clear : Boolean ){
        if(clear){
            tvResult.text = ""
            tvExpression.append(append)
            can = true
        }else
            if(can){
                tvExpression.append(tvResult.text)
                tvExpression.append(append)
                tvResult.text = ""
                can = false
        }
    }

}
