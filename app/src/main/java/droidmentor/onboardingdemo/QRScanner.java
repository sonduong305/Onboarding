package droidmentor.onboardingdemo;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import droidmentor.onboardingdemo.MainPackage.RecyclerViewDataAdapter;
import droidmentor.onboardingdemo.MainPackage.SectionDataModel;
import droidmentor.onboardingdemo.MainPackage.SingleItemModel;
import droidmentor.onboardingdemo.OrderPackage.OrderActivity;

public class QRScanner extends AppCompatActivity {

    int soBan;
    Socket soc;
    DataOutputStream dos;
    DataInputStream dis;


    SurfaceView cameraPreview;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    WifiManager wifi;
    WifiConfiguration wc;
    WifiInfo wifiInfo;
    ConnectivityManager connManager;
    NetworkInfo mWifi;
    RecyclerView list;
    ArrayList<SectionDataModel> allSampleData;
    RecyclerViewDataAdapter adapter;
    ArrayList<SingleItemModel> singleItemModels;


    final int RequestCameraPermissionID = 1001;



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        wifi = (WifiManager) super.getApplicationContext().getSystemService(android.content.Context.WIFI_SERVICE);
        connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        list = (RecyclerView) findViewById(R.id.recycler_view_list);
        allSampleData = new ArrayList<>();
        adapter = new RecyclerViewDataAdapter(allSampleData, this);
        singleItemModels = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_scanner);

        cameraPreview = (SurfaceView) findViewById(R.id.cameraPreview);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();
        //Add Event
        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //Request permission
                    ActivityCompat.requestPermissions(QRScanner.this,
                            new String[]{Manifest.permission.CAMERA},RequestCameraPermissionID);
                    return;
                }
                try {
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();

            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if(qrcodes.size() != 0)
                {

                    soBan = Integer.parseInt(qrcodes.valueAt(0).rawValue.toString());
                    try{
                        WifiConfiguration wificonfiguration = new WifiConfiguration();
                        StringBuffer stringbuffer = new StringBuffer("\"");
                        stringbuffer.append((new StringBuilder(String.valueOf("AndroidAP"))).append("\"").toString());
                        wificonfiguration.SSID = stringbuffer.toString();
                        wificonfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                        wificonfiguration.allowedAuthAlgorithms.set(0);
                        wificonfiguration.status = 2;
                        wificonfiguration.preSharedKey = "\"" + "kcww7763"+ "\"";

                        int networkId_ = wifi.addNetwork(wificonfiguration);

                        if(networkId_>-1){

                            boolean status = wifi.enableNetwork(networkId_, true);

                            if(status)
                                try {
                                    soc = new Socket("192.168.43.109", 2000);
                                    dos = new DataOutputStream(soc.getOutputStream());
                                    dis = new DataInputStream(soc.getInputStream());
                                    dos.writeInt(soBan);

                                    int menu_length = dis.readInt();
                                    System.out.println(menu_length);
                                    MonAn menu[] = new MonAn[menu_length];
                                    String data = "";

                                    for(int i=0; i<menu_length; i++){
                                        System.out.println("i=" + i);
                                        int id = dis.readInt();
                                        String name = dis.readUTF();
                                        int gia = dis.readInt();
                                        String mota = dis.readUTF();
                                        System.out.println("Go");
                                        menu[i] = new MonAn(id,name,gia,mota);
                                    }
//                                    dm.setAllItemInSection(singleItemModels);
//                                    allSampleData.add(dm);

                                    //Gui category
                                    int category_length = dis.readInt();
                                    Category category[] = new Category[category_length];
                                    for(int i=0; i<category_length; i++){
                                        System.out.println("OK "+i);
                                        int len = dis.readInt();
                                        byte picture[] = new byte[len];
                                        dis.readFully(picture);
                                        System.out.println("Picture "+i);
                                        String name = dis.readUTF();
                                        System.out.println("Name "+i);
                                        int category_monan_length = dis.readInt();
                                        System.out.println("Mon an length"+i);
                                        int monan[] = new int[category_monan_length];
                                        for(int k=0; k<category_monan_length; k++)
                                            monan[k] = dis.readInt();
                                        System.out.println("Done "+i);

                                        category[i] = new Category(picture, name, monan);

                                    }

                                    Intent intent  = new Intent(getApplicationContext(),OrderActivity.class);
                                    intent.putExtra("menu_length", menu.length );
                                    for(int i=0; i<menu_length; i++){
                                        intent.putExtra("id"+i, menu[i].id);
                                        intent.putExtra("name"+i, menu[i].name);
                                        intent.putExtra("gia"+i, menu[i].gia);
                                        intent.putExtra("mota"+i, menu[i].mota);
                                    }
                                    intent.putExtra("category_length", category.length );
                                    for(int i=0; i<category_length; i++){
                                        intent.putExtra("lenPic"+i, category[i].picture.length);
                                        intent.putExtra("picCategory"+i, category[i].picture);
                                        intent.putExtra("nameCategory"+i, category[i].name);
                                        intent.putExtra("monanCategory"+i, category[i].monAn);
                                    }
                                    startActivity(intent);

                                }catch (Exception e){

                                }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }
}