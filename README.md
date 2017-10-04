# CoolView
An Android Library that help you to customise views (Button, EditText, ImageView), by adding border with the size and color that you want, and give it  the corner radius that you seems cool, and you can also make an imageview looks like a circle.

# Gradle
compile on your dependencies
<pre>
<code>
dependencies {
      compile 'com.ndroid:cool-view:1.0'
}
</code>
</pre>

# ScreenShot
<img src="https://image.ibb.co/fcYFVb/Screen_Shot_2017_10_03_at_20_52_30.png" width="350"/>

# Sample Example
<a href="https://github.com/nadimgouia/CoolView/tree/master/app">Sample Module</a>

# Usage

To create a new CoolButton (its the same way for the CoolEditText)
<pre>
<code>
   &lt;com.ndroid.CoolButton
            android:layout_width="100dp"
            android:layout_height="wrap_content"
            android:background="#26d64c"
            android:drawableLeft="@drawable/phone"
            android:text="Call Now"
            android:textColor="#fff"
            custom:border_color="#262626"
            custom:border_radius="20"
            custom:border_stroke="5" /&gt;
</code>
</pre>

To set the border_radius, border_stroke, border_color from the activity class..
<pre>
<code>
        CoolButton coolButton = (CoolButton) findViewById(R.id.coolButton);
        //custom button border ...
        // set the border color
        coolButton.setBorderColor("#FFD42626");
        // set the border radius
        coolButton.setBorderRadius(20);
        // set the border stroke
        coolButton.setBorderStroke(4);
</code>
</pre>

Create a bordered Cool ImageView..
<pre>
<code>
   &lt;com.ndroid.CoolImageView
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:background="#26d64c"
            android:drawableLeft="@drawable/phone"
            android:text="Call Now"
            android:textColor="#fff"
            custom:border_color="#262626"
            custom:border_radius="20"
            custom:border_stroke="5" /&gt;
</code>
</pre>

Make the CoolImageView Circle ...
<pre>
<code>
   &lt;com.ndroid.CoolImageView
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:background="#26d64c"
            android:drawableLeft="@drawable/phone"
            android:text="Call Now"
            android:textColor="#fff"
            custom:border_color="#262626"
            custom:border_stroke="5"
            custom:is_circle="true"
            /&gt;
</code>
</pre>

Customize the ImageView From Activity class ...
<pre>
<code>
CoolImage coolImage = (CoolImage) findViewById(R.id.coolImage);
        //custom ImageView border ...
        // set the border color
        coolImage.setBorderColor("#000000");
        // set the border radius
        coolImage.setBorderRadius(20);
        // set the border stroke
        coolImage.setBorderStroke(4);
        // make the imageview circle 
        coolImage.setCircle(true);
        
</code>
</pre>






