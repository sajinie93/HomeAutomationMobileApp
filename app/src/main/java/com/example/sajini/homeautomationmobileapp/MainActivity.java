package com.example.sajini.homeautomationmobileapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;


public class MainActivity extends AppCompatActivity {

    static String MQTTHOST ="tcp://iot.eclipse.org:1883";
    static String USERNAME ="Gayashan";
    static String PASSWORD ="abc123";
    String topicStr="Bulb1";
    static int num=0;
    static MqttAndroidClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this, "connected", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this, "connection failed", Toast.LENGTH_LONG).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }


        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        TextView fontAwesomeStudyRoomIcon = (TextView) findViewById(R.id.font_awesome_pencil_icon);
        TextView fontAwesomeBathRoomIcon = (TextView) findViewById(R.id.font_awesome_bathRoom_icon);
        TextView fontAwesomeCubesIcon = (TextView) findViewById(R.id.font_awesome_tv_icon);


        TextView fontAwesomeAndroidIcons = (TextView) findViewById(R.id.font_awesome_cutlery_icon);
        TextView fontAwesomeAreaChartIcons = (TextView) findViewById(R.id.font_awesome_bed_icon);
        TextView fontAwesomeCubesIcons = (TextView) findViewById(R.id.font_awesome_cab_icon);
//        TextView fontAwesomeMobilePhoneIcons = (TextView) findViewById(R.id.font_awesome_mobile_phone_icon);

        fontAwesomeStudyRoomIcon.setTypeface(fontAwesomeFont);
        fontAwesomeBathRoomIcon.setTypeface(fontAwesomeFont);
        fontAwesomeCubesIcon.setTypeface(fontAwesomeFont);

        fontAwesomeAndroidIcons.setTypeface(fontAwesomeFont);
        fontAwesomeAreaChartIcons.setTypeface(fontAwesomeFont);
        fontAwesomeCubesIcons.setTypeface(fontAwesomeFont);



    }

    public void goInside(View view){
        Intent intent = new Intent(this,DeviceActivity.class);
        startActivity(intent);
    }

    public MqttAndroidClient getClient(){
        return client;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
