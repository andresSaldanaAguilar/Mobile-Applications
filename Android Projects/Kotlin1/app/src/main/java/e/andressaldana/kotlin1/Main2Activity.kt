package e.andressaldana.kotlin1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        res.setText(intent.getStringExtra("r1")+"  /  "+intent.getStringExtra("r2"))
    }
}
