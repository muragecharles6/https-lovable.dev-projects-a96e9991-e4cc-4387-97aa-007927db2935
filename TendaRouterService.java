package com.example.tendawifi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TendaRouterService {
    
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );
    
    @GET("status")
    Call<RouterStatus> getRouterStatus();
    
    @GET("clients")
    Call<ClientListResponse> getConnectedClients();
    
    @GET("wifi/status")
    Call<WiFiStatus> getWiFiStatus();
    
    @GET("sysinfo")
    Call<SystemInfo> getSystemInfo();
    
    @GET("network/stats")
    Call<NetworkStats> getNetworkStats();
    
    @POST("logout")
    Call<LogoutResponse> logout();
    
    @FormUrlEncoded
    @POST("wifi/enable")
    Call<WiFiResponse> enableWiFi(@Field("band") String band);
    
    @FormUrlEncoded
    @POST("wifi/disable")
    Call<WiFiResponse> disableWiFi(@Field("band") String band);
    
    @FormUrlEncoded
    @POST("wifi/channel")
    Call<WiFiResponse> changeChannel(@Field("band") String band, @Field("channel") int channel);
}