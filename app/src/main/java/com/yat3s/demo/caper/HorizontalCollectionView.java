package com.yat3s.demo.caper;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
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
public class HorizontalCollectionView extends FrameLayout {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.content_rv)
    RecyclerView mContentRv;
    private CollectionAdapter mCollectionAdapter;

    private String mTitle;

    public HorizontalCollectionView(Context context) {
        this(context, null);
    }

    public HorizontalCollectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalCollectionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // Obtain custom attr from layout xml.
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalCollectionView);
        mTitle = typedArray.getString(R.styleable.HorizontalCollectionView_Title);
        typedArray.recycle();

        initialize();
    }

    private void initialize() {

        // Attach layout to this view.
        /**
         *     * @param root Optional view to be the parent of the generated hierarchy (if
         *        <em>attachToRoot</em> is true), or else simply an object that
         *        provides a set of LayoutParams values for root of the returned
         *        hierarchy (if <em>attachToRoot</em> is false.)
         *       @param attachToRoot Whether the inflated hierarchy should be attached to
         *        the root parameter? If false, root is only used to create the
         *        correct subclass of LayoutParams for the root view in the XML.
         */
        LayoutInflater.from(getContext()).inflate(R.layout.view_horizontal_collection, this, true);

        // Butter knife bind this for bind view.
        ButterKnife.bind(this);

        mContentRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        // Set title content from layout attr.
        if (null != mTitle) {
            setTitle(mTitle);
        }
    }

    public void setTitle(CharSequence title) {
        mTitleTv.setText(title);
    }

    public void setCollectionData(ArrayList<String> data) {
        mCollectionAdapter = new CollectionAdapter(getContext(), data);
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
