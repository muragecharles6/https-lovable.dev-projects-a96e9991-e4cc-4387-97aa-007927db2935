import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiConfiguration;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.os.Build;
import android.text.TextUtils;

public class WifiClientManager {
    private WifiManager wifiManager;
    private ConnectivityManager connectivityManager;

    public WifiClientManager(Context context) {
        this.wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isWifiEnabled() {
        return wifiManager.isWifiEnabled();
    }

    public void setWifiEnabled(boolean enabled) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Starting from API level 29, use the WifiManager API to enable/disable Wi-Fi
            wifiManager.setWifiEnabled(enabled);
        } else {
            // For lower APIs, use reflection to change Wi-Fi state
            try {
                Class<?> wifiManagerClass = Class.forName("android.net.wifi.WifiManager");
                java.lang.reflect.Method setWifiEnabledMethod = wifiManagerClass.getMethod("setWifiEnabled", boolean.class);
                setWifiEnabledMethod.invoke(wifiManager, enabled);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void connectToWifi(String ssid, String password) {
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", ssid);
        wifiConfig.preSharedKey = String.format("\"%s\"", password);

        int netId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
    }

    public String getCurrentWifiSSID() {
        if (isWifiEnabled()) {
            wifiManager.startScan();
            return wifiManager.getConnectionInfo().getSSID();
        }
        return null;
    }

    public NetworkInfo getActiveNetworkInfo() {
        return connectivityManager.getActiveNetworkInfo();
    }
}