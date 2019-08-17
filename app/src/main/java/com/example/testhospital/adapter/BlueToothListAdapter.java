package com.example.testhospital.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testhospital.R;
import com.example.testhospital.data.PayServiceInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TL on 2019/8/1.
 */

public class BlueToothListAdapter extends BaseAdapter {
    private List<BluetoothDevice> mList = new ArrayList<BluetoothDevice>();
    private Context mContext;

    public BlueToothListAdapter(Context context, List<BluetoothDevice> data) {
        this.mContext = context;
        this.mList = data;
    }

    public void addDevice(BluetoothDevice device) {
        if (!mList.contains(device)) {
            mList.add(device);
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public BluetoothDevice getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bluetooth, viewGroup, false);
            holder = new ViewHolder();
            holder.blueToothName = (TextView) convertView.findViewById(R.id.tv_bluetooth_name);
            holder.connectBlueTooth = (TextView) convertView.findViewById(R.id.tv_connect);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BluetoothDevice device = mList.get(i);
        final String deviceName = device.getName();
        if (deviceName != null && deviceName.length() > 0)
            holder.blueToothName.setText(deviceName);
        else
            holder.blueToothName.setText(R.string.unknown_device);
//        holder.connectBlueTooth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("Test", "点击了Name:" + mList.get(i).getName() + "项");
//                Log.d("Test", "点击了Address:" + mList.get(i).getAddress() + "项");
//            }
//        });
        return convertView;
    }

    class ViewHolder {
        TextView blueToothName;
        TextView connectBlueTooth;
    }
}
