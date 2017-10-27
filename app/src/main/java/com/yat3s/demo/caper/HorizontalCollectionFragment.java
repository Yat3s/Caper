package com.yat3s.demo.caper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yat3s.library.adapter.BaseAdapter;
import com.yat3s.library.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yat3s on 01/06/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class HorizontalCollectionFragment extends Fragment {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.content_rv)
    RecyclerView mContentRv;
    private HorizontalCollectionView.CollectionAdapter mCollectionAdapter;

    public static HorizontalCollectionFragment newInstance() {

        Bundle args = new Bundle();

        HorizontalCollectionFragment fragment = new HorizontalCollectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // NOT attach to parent, because fragment is not a VIEW GROUP.
        View rootView = inflater.inflate(R.layout.view_horizontal_collection, container, false);

        // Bind.
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void setTitle(CharSequence title) {
        mTitleTv.setText(title);
    }

    public void setCollectionData(ArrayList<String> data) {
        mCollectionAdapter = new HorizontalCollectionView.CollectionAdapter(getContext(), data);
        mContentRv.setAdapter(mCollectionAdapter);
    }

    static class CollectionAdapter extends BaseAdapter<String> {

        public CollectionAdapter(Context context, List<String> data) {
            super(context, data);
        }

        @Override
        protected void bindDataToItemView(BaseViewHolder holder, String item, int position) {

        }

        @Override
        protected int getItemViewLayoutId(int position, String item) {
            return R.layout.item_text;
        }
    }
}
