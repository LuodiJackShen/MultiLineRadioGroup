package jack.view.demo.multilineradiogroupdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_multi_rg.setOnCheckedChangeListener { group, buttonId, isChecked ->
            if (isChecked)
                Toast.makeText(applicationContext,
                        group.findViewById<RadioButton>(buttonId).text,
                        Toast.LENGTH_SHORT).show()
        }
    }
}
