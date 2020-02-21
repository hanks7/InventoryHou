package com.easyway.mismclient.utils;

import android.app.Activity;
import android.content.Intent;

import com.easyway.mismclient.utils.photoview.ImagePagerActivity;

import java.util.ArrayList;

public class ShowPhotoView {
	
		/**
		 * 打开图片查看器(传ArrayList<String>)
		 * @param position
		 * @param urls2
		 * eg:ShowPhotoView.imageBrower(this, urls2.size()-3, urls2);
		 */
		public static void imageBrower(Activity context,int position, ArrayList<String> urls) {
			Intent intent = new Intent(context, ImagePagerActivity.class);
			// 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
			
			intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls);
			intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
			context.startActivity(intent);
			context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);  
		
	}
		/**
		 * 打开图片查看器(传String)
		 * @param position
		 * @param urls2
		 * eg:ShowPhotoView.imageBrower(this, urls2.size()-3, urls2);
		 */
		public static void imageBrower(Activity context,String url) {
			ArrayList<String> list=new ArrayList<String>();
			list.add(url);
			Intent intent = new Intent(context, ImagePagerActivity.class);
			// 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
			
			intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, list);
			intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, 0);
			context.startActivity(intent);
			context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);  
			
		}

}
