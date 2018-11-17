package e.andressaldana.kotlin1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Operador lambda
        btn.setOnClickListener {
            val a = xet1.text.toString()
            val b = xet2.text.toString()
            val c = xet3.text.toString()
            //111
            if(a.isNotEmpty() && b.isNotEmpty() && c.isNotEmpty()){
                solve(a.toDouble(),b.toDouble(),c.toDouble())
            } else{
                //000
                if(a.isEmpty() && b.isEmpty() && c.isEmpty()){
                    solve(null,null,null)
                }
                //100
                else if(a.isNotEmpty() && b.isEmpty() && c.isEmpty()){
                    solve(a.toDouble(),null,null)
                }
                //010
                else if(a.isEmpty() && b.isNotEmpty() && c.isEmpty()){
                    solve(null,b.toDouble(),null)
                }
                //001
                else if(a.isEmpty() && b.isEmpty() && c.isNotEmpty()){
                    solve(null,null,c.toDouble())
                }
                //110
                else if(a.isNotEmpty() && b.isNotEmpty() && c.isEmpty()){
                    solve(a.toDouble(),b.toDouble(),null)
                }
                //011
                else if(a.isEmpty() && b.isNotEmpty() && c.isNotEmpty()){
                    solve(null,b.toDouble(),c.toDouble())
                }
                //101
                else if(a.isNotEmpty() && b.isEmpty() && c.isNotEmpty()){
                    solve(a.toDouble(),null,c.toDouble())
                }
                toast(a,b,c)
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

    //función de extension
    private fun AppCompatActivity.toast(a: String, b: String, c: String){
        var aux = "Campos vacios:"
        if(a.isEmpty()){
            aux+="\na, tomando como valor 1"
        }
        if(b.isEmpty()){
            aux+="\nb, tomando como valor 1"
        }
        if(c.isEmpty()){
            aux+="\nc, tomando como valor 1"
        }
        Toast.makeText(this,aux,Toast.LENGTH_SHORT).show()
    }
}