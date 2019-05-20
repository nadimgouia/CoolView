package nadim.ndroid.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.ndroid.CoolButton
import com.ndroid.CoolEditText
import com.ndroid.CoolImage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //custom button border ...
        // set the border color
        coolButton.setBorderColor("#FFD42626")
        // set the border radius
        coolButton.setBorderRadius(20f)
        // set the border stroke
        coolButton.setBorderStroke(4)

        //custom editText border ...
        // set the border color
        coolEditText.setBorderColor("#000000")
        // set the border radius
        coolEditText.setBorderRadius(20f)
        // set the border stroke
        coolEditText.setBorderStroke(4)

        //custom ImageView border ...
        /*
        // set the border color
        coolImage.setBorderColor("#000000");
        // set the border radius
        coolImage.setBorderRadius(20);
        // set the border stroke
        coolImage.setBorderStroke(4);
        coolImage.setCircle(true);
        */
    }
}
