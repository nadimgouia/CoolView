package nadim.ndroid.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ndroid.CoolButton;
import com.ndroid.CoolEditText;
import com.ndroid.CoolImage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CoolButton coolButton = (CoolButton) findViewById(R.id.coolButton);

        //custom button border ...
        // set the border color
        coolButton.setBorderColor("#FFD42626");
        // set the border radius
        coolButton.setBorderRadius(20);
        // set the border stroke
        coolButton.setBorderStroke(4);

        CoolEditText coolEditText = (CoolEditText) findViewById(R.id.coolEditText);

        //custom editText border ...
        // set the border color
        coolEditText.setBorderColor("#000000");
        // set the border radius
        coolEditText.setBorderRadius(20);
        // set the border stroke
        coolEditText.setBorderStroke(4);


        CoolImage coolImage = (CoolImage) findViewById(R.id.coolImage);
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
