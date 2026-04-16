package com.example.tendawifi;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiManager != null) {
            // Retrieve WiFi Information
            String ssid = wifiManager.getConnectionInfo().getSSID();
            String bssid = wifiManager.getConnectionInfo().getBSSID();
            int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

            // Display network information
            textView.setText("SSID: " + ssid + "\nBSSID: " + bssid + "\nIP Address: " + intToIp(ipAddress));

            // Retrieve list of connected clients
            List<String> clients = getConnectedClients();
            textView.append("\nConnected Clients: " + clients.toString());
        }
    }

    private String intToIp(int ipInt) {
        return (ipInt & 0xFF) + "." + ((ipInt >> 8) & 0xFF) + "." + ((ipInt >> 16) & 0xFF) + "." + ((ipInt >> 24) & 0xFF);
    }

    private List<String> getConnectedClients() {
        // Placeholder for actual implementation to retrieve connected clients
        return List.of("Client1", "Client2", "Client3");
    }
}