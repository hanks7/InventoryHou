package com.easyway.mismclient.base;

/**
 * Created by admin on 2018/4/10.
 */

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by cc on 16-7-9.
 */
public abstract class BaseFragment extends Fragment {
    /**
     * Fragment视图
     */
    protected View mainView;
    /**
     * 判断是否可见
     */
    protected boolean isVisible;
    /**
     * Fragment是否创建
     */
    private boolean isPrepared;
    /**
     * 判断是否第一次加载,目的是为了不去除懒加载.
     */
    private boolean isFirst = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        solveFragmentBug(savedInstanceState);
    }

    //--------------------------------method---------------------------//

    /**
     * 懒加载
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirst) return;
        isFirst = false;
    }


    /**
     * 判断fragment是否可见
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isPrepared) return;
        if (getUserVisibleHint()) {//可见
            isVisible = true;
            lazyLoad();
            onVisible();
        } else {//不可见
            isVisible = false;
            onInvisible();
        }
    }

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    /**
     * 解决fragment重影
     *
     * @param savedInstanceState
     */
    private void solveFragmentBug(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayout() <= 0) {
            mainView = createMainView();
        } else {
            mainView = inflater.inflate(getLayout(), container, false);
        }
        if (mainView == null)
            throw new NullPointerException("hey,you forget set a view for " + getClass().getName());
        ButterKnife.bind(this, mainView);
        return mainView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }


    protected View createMainView() {
        return null;
    }

    //--------------------------abstract method------------------------//

    /**
     * 初始化布局，请不要把耗时操作放在这个方法里，这个方法用来提供一个
     * 基本的布局而非一个完整的布局，以免ViewPager预加载消耗大量的资源。
     */
    @LayoutRes
    protected abstract int getLayout();

    /**
     * 初始化控件, 控件寻址
     */
    protected abstract void initView();

    /**
     * 第一次可见获取数据 这里获取数据，刷新界面
     */
    protected abstract void initData();

    /**
     * fragment被设置为可见时调用
     */
    protected abstract void onVisible();

    /**
     * fragment被设置为不可见时调用
     */
    protected abstract void onInvisible();


}

