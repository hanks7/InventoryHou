package com.easyway.mismclient.dal;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyway.mismclient.R;

public class MyAdapter extends BaseAdapter {
	private List<ProductInventoryDetail> list;
	
	private Context context;

	public MyAdapter(List<ProductInventoryDetail> list,Context context) {
		this.list = list;
		this.context = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public ProductInventoryDetail getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		  ViewHolder viewHolder;
	        if (convertView == null) {
	            viewHolder = new ViewHolder();
	            convertView = View.inflate(context,R.layout.item_inventory_detail, null);
	            
	            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.ite_invent_detail_description);
	            viewHolder.tvRealtimeamout = (TextView) convertView.findViewById(R.id.ite_invent_detail_realtimeamout);
//	            viewHolder.vhAmountView = (com.easyway.ui.AmountView) convertView.findViewById(R.id.ite_invent_detail_amountView);
	            convertView.setTag(viewHolder);// ͨ��setTag��ViewHolder��convertView��
	        }  else {
	            viewHolder = (ViewHolder) convertView.getTag(); // ��ȡ��ͨ��ViewHolder�ҵ���Ӧ�Ŀؼ�
	        }
	        ProductInventoryDetail bean = list.get(position);
	        viewHolder.tvDescription.setText( bean.getDescription());
	        viewHolder.tvRealtimeamout.setText(bean.getRealTimeAmount()+"");
//	        viewHolder.vhAmountView.setGoods_storage(1 );
	        return convertView;
	    }

	    class ViewHolder {
//	    	com.easyway.ui.AmountView vhAmountView;
	        TextView tvRealtimeamout;
	        TextView tvDescription;
	    }
}
