package com.bytedance.tiktok.fragment;

import android.widget.RadioGroup;
import androidx.fragment.app.Fragment;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseFragment;
import java.util.ArrayList;

public class MainFragment extends BaseFragment {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private RadioGroup radioGroup;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {
        radioGroup = rootView.findViewById(R.id.radiogroup);

        setFragmentSwitch();
    }

    private void setFragmentSwitch() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.btn_location) {
                getChildFragmentManager().beginTransaction().add(R.id.framelayout, new CurrentLocationFragment()).commit();
            } else if (checkedId == R.id.btn_recommend) {
                getChildFragmentManager().beginTransaction().add(R.id.framelayout, new RecommendFragment()).commit();
            }
        });
    }

}
