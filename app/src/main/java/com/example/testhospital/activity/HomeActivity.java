package com.example.testhospital.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testhospital.R;
import com.example.testhospital.adapter.BlueToothListAdapter;
import com.example.testhospital.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class HomeActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private ListView listView;
    LinearLayout ll_search_allergy;
    LinearLayout ll_doctor_nurse;
    LinearLayout ll_notice_admission;
    LinearLayout ll_cost_query;
    LinearLayout departments;

    View include_one;
    View include_two;


    // 5.0+n版本
    private BluetoothLeScanner mBluetoothScaner;
    private boolean mScanning;
    private Handler mHandler;
    BlueToothListAdapter mLeDeviceListAdapter;
    private List<BluetoothDevice> mLeDeviceList = new ArrayList<BluetoothDevice>();
    private BluetoothAdapter mBluetoothAdapter;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        waterDropAnimation();
        timer = (Chronometer) this.findViewById(R.id.chronometer);
        btStatus = (TextView) this.findViewById(R.id.bt_status);
        include_one = findViewById(R.id.include_infusion_status_one);
        include_two = findViewById(R.id.include_infusion_status_two);
        setOnClick();
        listView = (ListView) findViewById(R.id.device_bluetooth_name);
        mHandler = new Handler();
        version_5_plus = false;//android.os.Build.VERSION.SDK_INT >= 22;
//        Toast.makeText(this, "SDK_INT = " + android.os.Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();

        // 检查当前手机是否支持ble 蓝牙,如果不支持退出程序
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            finish();
        }

        // 初始化 Bluetooth adapter, 通过蓝牙管理器得到一个参考蓝牙适配器(API必须在以上android4.3或以上和版本)
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        if (version_5_plus) {
            if (false == mBluetoothAdapter.isEnabled()) {
//        	mBluetoothAdapter.enable();
//        	registerReceiver(mBluetoothStateReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
                Toast.makeText(this, R.string.error_bluetooth_not_enabled, Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
            mBluetoothScaner = mBluetoothAdapter.getBluetoothLeScanner();
//            mBluetoothScaner.stopScan(mScanCallback);
        } else {
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }

        // 检查设备上是否支持蓝牙
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, R.string.error_bluetooth_not_supported, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        mLeDeviceListAdapter = new BlueToothListAdapter(this, mLeDeviceList);
        listView.setAdapter(mLeDeviceListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent = getIntent();
                mDeviceName = mLeDeviceListAdapter.getItem(i).getName();
                mDeviceAddress = mLeDeviceListAdapter.getItem(i).getAddress();
                Intent gattServiceIntent = new Intent(HomeActivity.this, BluetoothLeService.class);
                bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
                include_one.setVisibility(View.VISIBLE);
                include_two.setVisibility(View.GONE);
            }
        });
        scanLeDevice(true);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    private void setOnClick() {
        ll_search_allergy = findViewById(R.id.ll_search_allergy);
        ll_search_allergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        ll_doctor_nurse = findViewById(R.id.ll_doctor_nurse);
        ll_doctor_nurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SaveRecordActivity.class);
                startActivity(intent);
            }
        });
        ll_notice_admission = findViewById(R.id.ll_notice_admission);
        ll_notice_admission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HospitalizationNotesActivity.class);
                startActivity(intent);
            }
        });
        ll_cost_query = findViewById(R.id.ll_cost_query);
        ll_cost_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DetailedListActivity.class);
                startActivity(intent);
            }
        });
        departments = findViewById(R.id.departments);
        departments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DeviceScannActivity.class);
                startActivity(intent);
            }
        });
    }


    private boolean version_5_plus;//什么定义?


    private void scanLeDevice(final boolean enable) {
        if (enable) {
            mLeDeviceList.clear();
            mBluetoothAdapter.startDiscovery();
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mBluetoothAdapter.isDiscovering()) {
                        mBluetoothAdapter.cancelDiscovery();
                    }
                    mScanning = false;
                    if (version_5_plus) {
//                	mBluetoothScaner.stopScan(mScanCallback);
                    } else {
                        mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    }
                    // searchBar.setVisibility(View.GONE);
                }
            }, 10000);

            mScanning = true;
            if (version_5_plus) {
//        	mBluetoothScaner.startScan(mScanCallback);
            } else {
                mBluetoothAdapter.startLeScan(mLeScanCallback);
            }
            //searchBar.setVisibility(View.VISIBLE);
        } else {
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
            mScanning = false;
            if (version_5_plus) {
//        	mBluetoothScaner.stopScan(mScanCallback);
            } else {
                mBluetoothAdapter.stopLeScan(mLeScanCallback);
            }
            //searchBar.setVisibility(View.GONE);
        }
    }

    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLeDeviceListAdapter.addDevice(device);
                    mLeDeviceListAdapter.notifyDataSetChanged();
                }
            });
        }
    };


    /**
     * @ 获取当前手机屏幕的尺寸(单位:像素)
     */
    public static float getPingMuSize(Context mContext) {
        int densityDpi = mContext.getResources().getDisplayMetrics().densityDpi;
        float scaledDensity = mContext.getResources().getDisplayMetrics().scaledDensity;
        float density = mContext.getResources().getDisplayMetrics().density;
        float xdpi = mContext.getResources().getDisplayMetrics().xdpi;
        float ydpi = mContext.getResources().getDisplayMetrics().ydpi;
        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int height = mContext.getResources().getDisplayMetrics().heightPixels;

        // 这样可以计算屏幕的物理尺寸
        float width2 = (width / xdpi) * (width / xdpi);
        float height2 = (height / ydpi) * (width / xdpi);

        return (float) Math.sqrt(width2 + height2);
    }

    private ImageView mImageView;
    ObjectAnimator animator = null;
    Long currentPlayTime;

    private void waterDropAnimation() {
        mImageView = (ImageView) findViewById(R.id.water_drop);
        // 创建动画作用对象：此处以Button为例

        float curTranslationY = mImageView.getTranslationY();
        // 获得当前按钮的位置
        animator = ObjectAnimator.ofFloat(mImageView, "translationY", curTranslationY, 120);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
        // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
        animator.setDuration(5000);
        animator.setInterpolator(new AccelerateInterpolator(3f));
        animator.setRepeatCount(ValueAnimator.INFINITE);
        // animator.setStartDelay(1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mImageView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

    /**
     * 恢复
     */
    private void animatorResume() {
        animator.start();
        animator.setCurrentPlayTime(currentPlayTime);
    }

    /**
     * 暂停
     */
    private void animatorPause() {
        currentPlayTime = animator.getCurrentPlayTime();
        animator.cancel();
    }


    private final static String TAG = "Test";

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

    public static final String KEY_RING = "ring_check";
    public static final String KEY_VIBRATE = "vibrate_check";

    private static final long VIBRATE_DURATION = 200L;

    private static final boolean DEFAULT_VIBRATE = true;
    private static final boolean DEFAULT_RING = true;

    private static final int MSG_ENABLE_NOTIFY = 10010;
    private static final int MSG_DEVICE_CONNECT = 10011;
    private static final int MSG_DEVICE_DISCONNECT = 10012;

    private static final long START_TIME = 3 * 1000; //ms
    private static final long DELAY_TIME = 3 * 1000; //ms
    private static final long DELAY_CONNECT_TIME_OUT = 4 * 1000; // ms

    private static final int DEFAULT_BATTERY_LEVEL = 6;

    // private TextView titleView;

    // private WindowManager mWindowManager;
    // private View mView;

    //    private SoundPool soundPool;
    private MediaPlayer mPlayer;
    private Vibrator vibrator;
    private long[] pattern = {300, 200, 300, 200}; // OFF/ON/OFF/ON

    //    private TextView mConnectionState;
//    private TextView mDataField;
    private String mDeviceName;
    private String mDeviceAddress;

    private FrameLayout connect_layout;

    private BluetoothLeService mBluetoothLeService;
    private BluetoothGattCharacteristic mNotifyCharacteristic;
    private SharedPreferences mPreferences;

    boolean isRing = DEFAULT_RING;
    boolean isVibrate = DEFAULT_VIBRATE;


    private Chronometer timer;
    private Button btnEnd;
    private TextView btStatus;

    //写数据
    private BluetoothGattCharacteristic characteristic;
    private BluetoothGattService mnotyGattService;
    ;
    //读数据
    private BluetoothGattCharacteristic readCharacteristic;
    private BluetoothGattService readMnotyGattService;

    private boolean isConnect = false;
    private boolean isOnPause = false;
    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            btStatus.setText(R.string.bt_status_3);
            Log.d("Test", "蓝牙连接状态：" + HomeActivity.this.getString(R.string.bt_status_3));
            registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            btStatus.setText(R.string.bt_status_2);
            mBluetoothLeService = null;
        }
    };

    private Timer mTimer = new Timer(true);

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            Log.i(TAG, "run...");
            Message msg = mHandlerr.obtainMessage(MSG_DEVICE_CONNECT);
            msg.sendToTarget();
        }

    }

    // 任务
    private TimerTask mTask = new MyTimerTask();

    private void stopTimerTask() {
        if (mTimer != null && mTask != null) {
            mTask.cancel();
        }
    }

    private void startTimerTask() {
        stopTimerTask();
        mTask = new MyTimerTask();
        mTimer.schedule(mTask, START_TIME, DELAY_TIME);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandlerr = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case MSG_ENABLE_NOTIFY: {
                    final int charaProp = readCharacteristic.getProperties();
                    if ((charaProp & BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                        mNotifyCharacteristic = readCharacteristic;
                        mBluetoothLeService.setCharacteristicNotification(
                                readCharacteristic, true);
                        if (false == isEnd) {
                            Log.d("Test", HomeActivity.this.getString(R.string.infusion_status_ing));
                        }
                    }
                }
                break;
                case MSG_DEVICE_CONNECT:
                    connect();
                    break;
                case MSG_DEVICE_DISCONNECT:
                    disconnect();
                    break;
                default:
                    break;
            }
        }
    };

    // Handles various events fired by the Service.
    // ACTION_GATT_CONNECTED: connected to a GATT server.
    // ACTION_GATT_DISCONNECTED: disconnected from a GATT server.
    // ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.
    // ACTION_DATA_AVAILABLE: received data from the device.  This can be a result of read
    //                        or notification operations.
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                updateConnectionState(HomeActivity.this.getString(R.string.bt_status_1));
                // connect_layout.setVisibility(View.GONE);
                Log.d("Test", "hide connect_layout");

                if (false == isEnd && false == isConnect) {
                    //停止定时器
                    stopTimerTask();

                    if (mToastDialog != null && mToastDialog.isShowing())
                        mToastDialog.dismiss();

                    timer.setBase(SystemClock.elapsedRealtime());//计时器清零
                    timer.start();
                    Log.d("Test", "计时器清零");
                    Log.d("Test", HomeActivity.this.getString(R.string.infusion_status_ing));
                }
                isConnect = true;

            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                updateConnectionState(HomeActivity.this.getString(R.string.bt_status_2));
                timer.stop();
                Log.d("Test", "记时停止");
                stopAlarm();
                if (false == isEnd)
                    Log.d("Test", HomeActivity.this.getString(R.string.infusion_status_stop));
                isConnect = false;
                //启动定时器
                startTimerTask();

            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Show all the supported services and characteristics on the user interface.
//                displayGattServices(mBluetoothLeService.getSupportedGattServices());
                //写数据的服务和characteristic
//            	mnotyGattService = mBluetoothLeService.getSupportedGattServices(UUID.fromString("00001800-0000-1000-8000-00805f9b34fb"));
                mnotyGattService = mBluetoothLeService.getSupportedGattServices(UUID.fromString("d973f2e0-b19e-11e2-9e96-0800200c9a66"));
                if (mnotyGattService != null) {
                    Log.d(TAG, SampleGattAttributes.lookup(mnotyGattService.getUuid().toString(), "---"));
//            	    characteristic = mnotyGattService.getCharacteristic(UUID.fromString("00002a00-0000-1000-8000-00805f9b34fb"));
                    characteristic = mnotyGattService.getCharacteristic(UUID.fromString("d973f2e2-b19e-11e2-9e96-0800200c9a66"));
                    if (characteristic != null)
                        Log.d(TAG, SampleGattAttributes.lookup(characteristic.getUuid().toString(), "---"));
                }
                //读数据的服务和characteristic
                readMnotyGattService = mBluetoothLeService.getSupportedGattServices(UUID.fromString("d973f2e0-b19e-11e2-9e96-0800200c9a66"));
                if (readMnotyGattService != null) {
                    Log.d(TAG, SampleGattAttributes.lookup(readMnotyGattService.getUuid().toString(), "---"));
                    readCharacteristic = readMnotyGattService.getCharacteristic(UUID.fromString("d973f2e1-b19e-11e2-9e96-0800200c9a66"));
                }
                if (readCharacteristic != null) {
                    Log.d(TAG, SampleGattAttributes.lookup(readCharacteristic.getUuid().toString(), "---"));
                    mHandlerr.sendEmptyMessage(MSG_ENABLE_NOTIFY);
                }
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                mHandlerr.removeMessages(MSG_DEVICE_DISCONNECT);
                mHandlerr.sendEmptyMessageDelayed(MSG_DEVICE_DISCONNECT, DELAY_CONNECT_TIME_OUT);
                displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            }
        }
    };

    public void initVieww() {
        mPreferences = this.getSharedPreferences("profiles", Context.MODE_PRIVATE);
        // titleView = (TextView) findViewById(R.id.titleLine);
        connect_layout = (FrameLayout) this.findViewById(R.id.connect_layout);
        btnEnd = (Button) this.findViewById(R.id.btn_end);
        Log.d("Test", "电量状态:" + DEFAULT_BATTERY_LEVEL);
        isRing = mPreferences.getBoolean(KEY_RING, DEFAULT_RING);
        isVibrate = mPreferences.getBoolean(KEY_VIBRATE, DEFAULT_VIBRATE);

        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        // titleView.setText(mDeviceName);
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    private void playRing() {

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }

        mPlayer = MediaPlayer.create(this, R.raw.ring);
        // mp.prepare();
        mPlayer.setLooping(true);
        mPlayer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        connect();
        isOnPause = false;
        scanLeDevice(true);
    }

    private void connect() {
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    private void disconnect() {
        if (mBluetoothLeService != null) {
            mBluetoothLeService.disconnect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isOnPause = true;
//        unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.stop();
        if (mPlayer != null)
            mPlayer.stop();
//        vibrator.cancel();
        stopTimerTask();
        unregisterReceiver(mGattUpdateReceiver);
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gatt_services, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_rename:
                showDialog(this);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog(Context context) {
        View layout = LayoutInflater.from(context).inflate(R.layout.bt_rename_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.menu_rename).setView(layout);
        final EditText btName = (EditText) layout.findViewById(R.id.bt_name);
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = btName.getText().toString();
                if (mBluetoothLeService != null && characteristic != null) {
                    if (newName != null && !newName.isEmpty()) {
                        mHandlerr.removeMessages(MSG_DEVICE_DISCONNECT);
                        mBluetoothLeService.writeCharacteristic(characteristic, newName);
                        // titleView.setText(newName);
                        mDeviceName = newName;
                    }
//		    mBluetoothLeService.readCharacteristic(characteristic);
                }
                dialog.dismiss();
            }
        }).show();
    }

    private AlertDialog mToastDialog;

    private void showToastDialog(Context context) {

        // WindowManager.LayoutParams para = new WindowManager.LayoutParams();
        // para.flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
        // WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
        // para.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        // para.height = WindowManager.LayoutParams.MATCH_PARENT;
        // para.width = WindowManager.LayoutParams.MATCH_PARENT;
        // para.format = PixelFormat.TRANSLUCENT;
        // mWindowManager.addView(mView, para);
        //
        // Button ok = (Button) mView.findViewById(R.id.btn_ok);
        // ok.setOnClickListener(new View.OnClickListener() {
        //
        // @Override
        // public void onClick(View v) {
        // // TODO Auto-generated method stub
        // android.util.Log.d("ManagementToolsReceiver", "btn_dismiss");
        // if (mWindowManager != null && mView != null) {
        // mWindowManager.removeViewImmediate(mView);
        // }
        // // mView = null;
        // }
        // });

        // KeyguardManager km = (KeyguardManager)
        // context.getSystemService(Context.KEYGUARD_SERVICE);
        // if (km.inKeyguardRestrictedInputMode()) {
        //
        // Intent alarmIntent = new Intent(context, Messagedialog.class);
        // alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // context.startActivity(alarmIntent);
        //
        // } else {
        if (mToastDialog == null) {//
            // View layout =
            // LayoutInflater.from(context).inflate(R.layout.bt_content_dialog,
            // null);
            mToastDialog = new AlertDialog.Builder(context).setTitle(getResources()
                    .getString(R.string.error_title))
                    .setIcon(android.R.drawable.ic_dialog_alert).setCancelable(false)
                    .setMessage(mDeviceName + " " + getResources().getString(R.string.infusion_status_end))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            stopAlarm();
                            mBluetoothLeService.disconnect();
                            dialog.dismiss();
                        }
                    }).create();
            mToastDialog.setCanceledOnTouchOutside(false);
        } else {
            mToastDialog.setMessage(mDeviceName + " " + getResources().getString(R.string.infusion_status_end));
        }
        mToastDialog.show();

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        if (!pm.isScreenOn()) {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP
                    | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "ble");
            wl.acquire();

            KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock kl = km.newKeyguardLock("ble");
            kl.disableKeyguard();
            //kl.reenableKeyguard();
            wl.release();

        }

        if (isOnPause) {
            Intent intent = new Intent(Intent.ACTION_MAIN)
                    .setClass(this, this.getClass())
                    .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
        // }
    }

    private void updateConnectionState(final String resourceId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                mConnectionState.setText(resourceId);
                btStatus.setText(resourceId);
                Log.d("Test", "updateConnectionState=" + resourceId);
            }
        });
    }

    private void startAlarm() {
        if (isRing) {
            playRing();
        }
        if (isVibrate) {
            vibrator.vibrate(VIBRATE_DURATION);
            vibrator.vibrate(pattern, 0);
        }
//        if(mBluetoothLeService != null)
//            mBluetoothLeService.disconnect();
    }

    private void pauseAlarm() {
        timer.stop();
        Log.d("Test", HomeActivity.this.getString(R.string.infusion_status_pause));
    }

    private void stopAlarm() {
        isConnect = false;
        if (isRing && mPlayer != null) {
            mPlayer.stop();
        }
        if (isVibrate) {
            // vibrator.cancel();
        }
    }

    private boolean isEnd = false;
    private int currentStatus = 0;

    private void displayData(String data) {
        if (data != null) {
            Log.d(TAG, "data = " + data);
            int status = str2Int(data);
            Log.d(TAG, "status = " + status);
            if (status == 11) {
//              soundPool.play(1,1, 1, 0, -1, 1);
                if (false == isEnd) {
                    isEnd = true;
                    Log.d("Test", HomeActivity.this.getString(R.string.infusion_status_end));
                    timer.stop();
                    startAlarm();
                    showToastDialog(this);
                }
            } else if (status == 10) {
                if (isEnd) {
                    isEnd = false;
                    stopAlarm();
                }
            } else if (status < 10) {

                if (currentStatus != status) {
                    Log.d("Test", "电量状态:" + status);
                }
                currentStatus = status;
            }
        }
    }

    public static int str2Int(String str) {
        int i = 0;
        byte[] bytes = str.getBytes();
        for (int j = 0; ; j++) {
            if (j >= bytes.length) {
                break;
            }
            i = i * 256 + (0xFF & bytes[(-1 + bytes.length - j)]);
        }
        return i;
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // TODO Auto-generated method stub
        switch (buttonView.getId()) {
            case R.id.ring:
                mPreferences.edit().putBoolean(KEY_RING, isChecked).apply();
                isRing = isChecked;
                break;

            case R.id.vibrate:
                mPreferences.edit().putBoolean(KEY_VIBRATE, isChecked).apply();
                isVibrate = isChecked;
                break;

            default:
                break;
        }
    }
}
