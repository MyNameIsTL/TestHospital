package com.example.testhospital.adapter;

import android.content.Context;
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

public class PayServiceAdapter extends BaseAdapter {
    private List<PayServiceInfo> mList = new ArrayList<PayServiceInfo>();
    private Context mContext;

    public PayServiceAdapter(Context context, List<PayServiceInfo> data) {
        this.mContext = context;
        this.mList = data;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pay_service, viewGroup, false);
            holder = new ViewHolder();
            holder.payService = (TextView) convertView.findViewById(R.id.tv_pay_service);
            holder.specifications = (TextView) convertView.findViewById(R.id.tv_specifications);
            holder.company = (TextView) convertView.findViewById(R.id.tv_company);
            holder.unitPrice = (TextView) convertView.findViewById(R.id.tv_unit_price);
            holder.number = (TextView) convertView.findViewById(R.id.tv_number);
            holder.amountMoney = (TextView) convertView.findViewById(R.id.tv_amount_money);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.payService.setText(mList.get(i).getmPayService());
        holder.specifications.setText(mList.get(i).getmSpecifications());
        holder.company.setText(mList.get(i).getmCompany());
        holder.unitPrice.setText(mList.get(i).getmUnitPrice());
        holder.number.setText(mList.get(i).getmNumber());
        holder.amountMoney.setText(mList.get(i).getmAmountMoney());
        return convertView;
    }

    class ViewHolder {
        TextView payService;
        TextView specifications;
        TextView company;
        TextView unitPrice;
        TextView number;
        TextView amountMoney;
    }
}
