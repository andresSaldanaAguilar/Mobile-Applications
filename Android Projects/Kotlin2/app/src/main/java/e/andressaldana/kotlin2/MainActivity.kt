package e.andressaldana.kotlin2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener{
            val et1: String?
            val et2: String?
            val et3: String?
            if(xet1.text.toString() == "")
                et1 = null
            else
                et1 = xet1.text.toString()
            if(xet2.text.toString() == "")
                et2 = null
            else
                et2 = xet2.text.toString()
            if(xet3.text.toString() == "")
                et3 = null
            else
                et3 = xet3.text.toString()

            try {
                if(et1!!.isNotEmpty() && et2!!.isNotEmpty() && et3!!.isNotEmpty()){
                    KotlinSingleton.nombre = et1
                    KotlinSingleton.pass = et3
                    KotlinSingleton.user = et2
                    res.setText("Nombre de la Base de Datos:"+KotlinSingleton.nombre+"\nNombre del usuario:"+KotlinSingleton.user+"\nContraseña:"+KotlinSingleton.pass)
                }
            }catch (e: NullPointerException) {
                Toast.makeText(this,"NullPointerException cachada",Toast.LENGTH_SHORT).show()
            }

        }
    }


    object KotlinSingleton {
        var nombre : String? = null
            get() = field
            set(value) {
                field = value
            }

        var user : String? = null
            get() = field
            set(value) {
                field = value
            }

        var pass : String? = null
            get() = field
            set(value) {
                field = value
            }
    }
}
