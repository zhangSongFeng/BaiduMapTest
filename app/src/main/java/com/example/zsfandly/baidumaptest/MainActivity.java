package com.example.zsfandly.baidumaptest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BaiduMap  baiduMap;
    private MapView mapView;
    private MyLocationListener myLocationListener;
    private LocationClient mLocationClient;
    private double currentLat;
    private double currentLng;
    private String currentAddr;
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView= (MapView) findViewById(R.id.bmapView);
        initBaiduMap();
    }

    private void initBaiduMap() {
        List<String> permissionList=new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }else {

            baiduMap = mapView.getMap();
            baiduMap.setMyLocationEnabled(true);//打开定位图层
            mLocationClient = new LocationClient(getApplicationContext());
            mLocationClient.registerLocationListener(myLocationListener);
            //注册监听函数
            myLocationListener = new MyLocationListener();
            LocationClientOption option = new LocationClientOption();
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            option.setCoorType("bd09ll");
            option.setScanSpan(5000);
            option.setIsNeedAddress(true);
            option.setNeedDeviceDirect(true);
            mLocationClient.setLocOption(option);
            mLocationClient.start();
            mLocationClient.requestLocation();//发起定位请求
        }
    }

    private class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation!=null && flag){
                flag=false;
                currentLat=bdLocation.getLatitude();
                currentLng=bdLocation.getLongitude();
                currentAddr=bdLocation.getAddrStr();
                MyLocationData.Builder builder=new MyLocationData.Builder();
                builder.latitude(bdLocation.getLatitude());
                builder.longitude(bdLocation.getLongitude());
                builder.accuracy(bdLocation.getRadius());
                builder.direction(bdLocation.getDirection());
                builder.speed(bdLocation.getSpeed());
                MyLocationData locationData=builder.build();
                baiduMap.setMyLocationData(locationData);// 把我的位置信息设置到地图上
                //配置我的位置
                LatLng latlng = new LatLng(currentLat, currentLng);
                //设置我的位置的配置信息: 模式:跟随模式,是否要显示方向,图标
                baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                        MyLocationConfiguration.LocationMode.FOLLOWING,
                        true, null));
                // 设置我的位置为地图的中心点(缩放级别为 3-20)
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(
                        latlng, 16));
            }


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mylocation:
                mylocation();
                break;
            case R.id.start_track:
                break;
            case R.id.end_track:
                break;
            case R.id.track_back:
                break;
            default:
                break;
        }
        return true;
    }

    private void mylocation() {
        Toast.makeText(MainActivity.this,"正在获取位置中", Toast.LENGTH_SHORT).show();
        flag=false;
        baiduMap.clear();//清楚图层
        //加载自己位置图层
        baiduMap.setMyLocationEnabled(true);
        //请求服务
        mLocationClient.requestLocation();
    }
}
