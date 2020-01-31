package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
        var can = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1.setOnClickListener{addOnExpression("1",true)}
        tv2.setOnClickListener{addOnExpression("2",true)}
        tv3.setOnClickListener{addOnExpression("3",true)}
        tv4.setOnClickListener{addOnExpression("4",true)}
        tv5.setOnClickListener{addOnExpression("5",true)}
        tv6.setOnClickListener{addOnExpression("6",true)}
        tv7.setOnClickListener{addOnExpression("7",true)}
        tv8.setOnClickListener{addOnExpression("8",true)}
        tv9.setOnClickListener{addOnExpression("9",true)}
        tv0.setOnClickListener{addOnExpression("0",true)}
        tvDot.setOnClickListener{addOnExpression(".",true)}


        tvPlus.setOnClickListener{addOnExpression("+",false)}
        tvMinus.setOnClickListener{addOnExpression("-",false)}
        tvMultiply.setOnClickListener{addOnExpression("*",false)}
        tvShare.setOnClickListener{addOnExpression("/",false)}

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

    fun addOnExpression(append : String, clear : Boolean ){
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
