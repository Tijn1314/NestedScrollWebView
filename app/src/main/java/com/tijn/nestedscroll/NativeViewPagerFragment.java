package com.tijn.nestedscroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tijn.nestedscroll.view.HPPtrClassicFrameLayout;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class NativeViewPagerFragment extends Fragment {
    private HPPtrClassicFrameLayout mPtrFrame;

    public static NativeViewPagerFragment create() {
        NativeViewPagerFragment fragment = new NativeViewPagerFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.prt_content_native, null);
        initView(view);
        return view;
    }

    private void initView(View mRootView) {
        mPtrFrame = mRootView.findViewById(R.id.view_pager_ptr_frame);
        mPtrFrame.disableWhenHorizontalMove(true);
        mPtrFrame.setPtrHandler(new PtrHandler() {

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 1500);
            }
        });
    }

    public void setPtrFrameEnabled(boolean enabled) {
        if (mPtrFrame != null) {
            if(enabled){
                mPtrFrame.setMode(PtrFrameLayout.Mode.BOTH);
            }else {
                mPtrFrame.setMode(PtrFrameLayout.Mode.LOAD_MORE);
            }

        }
    }
}
