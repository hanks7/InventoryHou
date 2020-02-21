package com.easyway.mismclient.utils.photoview;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.easyway.mismclient.R;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @author 侯建军 QQ:474664736
 * @time 2020/1/13 11:01
 * @class describe
 */
public class PhotoFragment extends Fragment {
    private String url;
    private PhotoView mPhotoView;
    private ProgressBar progressBar;

    /**
     * 获取这个fragment需要展示图片的url
     *
     * @param url
     * @return
     */
    public static PhotoFragment newInstance(String url) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("url");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_img, container, false);
        mPhotoView = view.findViewById(R.id.photoview);
        progressBar = view.findViewById(R.id.loading);

        //设置缩放类型，默认ScaleType.CENTER（可以不设置）
        mPhotoView.setScaleType(ImageView.ScaleType.CENTER);
        mPhotoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                UToast.showText("长按事件");
                return true;
            }
        });
        mPhotoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                ((Activity) getContext()).finish();
//                UToast.showText("点击事件，真实项目中可关闭activity");
            }
        });
        useGlide();
        return view;
    }

    private void useGlide() {
        Glide.with(getContext())
                .load(url)
                .fitCenter()
//                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .placeholder(R.mipmap.rc_image_error)
                .error(R.mipmap.rc_image_error)
                .into(new GlideDrawableImageViewTarget(mPhotoView) {
                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);

                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);

                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable>
                            animation) {
                        super.onResourceReady(resource, animation);
                        progressBar.setVisibility(View.GONE);

                    }
                });
    }

}
