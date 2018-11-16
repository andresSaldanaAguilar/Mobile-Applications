package e.andressaldana.kotlin1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*;
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //operador lambda
        btn.setOnClickListener {
            val a = xet1.text.toString()
            val b = xet1.text.toString()
            val c = xet1.text.toString()
            if(a.isNotEmpty() || b.isNotEmpty() || c.isNotEmpty()){
                solve(a.toDouble(),b.toDouble(),c.toDouble())
            }
            else{
                if(a.isEmpty()){
                    //Toast.makeText(this,"Hay un valor vacio",Toast.LENGTH_SHORT).show()
                    toast()
                    solve(null,null,null)
                }
            }
        }
    }

    //llamada segura
    private fun solve(a: Double?, b: Double?, c: Double?){
        val intent = Intent(this, Main2Activity::class.java)
        //operador elvis
        intent.putExtra("r1", root1(a?:1.0,b?:1.0,c?:1.0))
        intent.putExtra("r2", root2(a?:1.0,b?:1.0,c?:1.0))
        startActivity(intent)
    }

    private fun root1(a: Double, b: Double, c: Double): String{
        val d = (b * b - 4 * a * c)
        val re = -b / (2 * a)
        val df = DecimalFormat("####0.00")
        var aux: String

        if (d >= 0) {
            aux = "" + df.format(Math.sqrt(d) / (2 * a) + re)
        }else{
            aux = df.format(re) + " + " + df.format(Math.sqrt(-d) / (2 * a)) + "i"
        }
        return aux
    }

    private fun root2(a: Double, b: Double, c: Double): String{
        val d = (b * b - 4 * a * c)
        val re = -b / (2 * a)
        val df = DecimalFormat("####0.00")
        var aux: String

        if (d >= 0) {
            aux = "" + df.format(-Math.sqrt(d) / (2 * a) + re)
        }else{
            aux = df.format(re) + " - " + df.format(Math.sqrt(-d) / (2 * a)) + "i"
        }

        return aux
    }

    fun AppCompatActivity.toast(){
        if()
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
