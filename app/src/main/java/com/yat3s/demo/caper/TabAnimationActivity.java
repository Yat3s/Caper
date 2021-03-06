package com.yat3s.demo.caper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.yat3s.library.adapter.AnimationType;
import com.yat3s.library.adapter.BaseAdapter;
import com.yat3s.library.adapter.BaseViewHolder;
import com.yat3s.library.adapter.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yat3s on 22/12/2016.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class TabAnimationActivity extends AppCompatActivity {
    private static final int MOCK_ITEM_COUNT = 200;
    private static final int[] MOCK_ITEM_IMG_RES = {R.mipmap.img_1, R.mipmap.img_2, R.mipmap.img_3, R.mipmap.img_4, R.mipmap
            .img_5, R.mipmap.img_6, R.mipmap.img_7, R.mipmap.img_8};
    @BindView(R.id.content_rv)
    RecyclerView contentRv;
    @BindView(R.id.tab_layout)
    LinearLayout tabLayout;

    private boolean tabIsShow;
    private CardAdapter mCardAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_animation);
        ButterKnife.bind(this);


        // Find fragment.
//        HorizontalCollectionFragment horizontalCollectionFragment = (HorizontalCollectionFragment)
// getSupportFragmentManager().findFragmentById(R.id
//                .my_fragment);
//
//        horizontalCollectionFragment.setTitle("123123");
//
        // New instance
        HorizontalCollectionFragment horizontalCollectionFragment = HorizontalCollectionFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container_layout, horizontalCollectionFragment);
        transaction.commit();

        // OKay!

        mCardAdapter = new CardAdapter(this, generateMockData());
        contentRv.setLayoutManager(new LinearLayoutManager(this));
        contentRv.setAdapter(mCardAdapter);
        contentRv.addItemDecoration(new DividerItemDecoration(this));
        contentRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!tabIsShow) {
                        tabLayout.animate().translationY(0).setDuration(500).start();
                        tabIsShow = !tabIsShow;
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (tabIsShow) {
                        tabLayout.animate().translationY(300).setDuration(500).start();
                        tabIsShow = !tabIsShow;
                    }
                } else {
                    if (!tabIsShow) {
                        tabLayout.animate().translationY(0).setDuration(500).start();
                        tabIsShow = !tabIsShow;
                    }
                }
            }
        });
        mCardAdapter.setItemAnimation(AnimationType.SCALE);
    }

    @OnClick({R.id.one_tab, R.id.two_tab, R.id.three_tab, R.id.four_tab})
    public void tapTab(View view) {
        switch (view.getId()) {
            case R.id.one_tab:
                mCardAdapter.setItemAnimation(AnimationType.SLIDE_FROM_BOTTOM);
                break;
            case R.id.two_tab:
                mCardAdapter.setItemAnimation(AnimationType.SLIDE_FROM_LEFT);
                break;
            case R.id.three_tab:
                mCardAdapter.setItemAnimation(AnimationType.ALPHA);
                break;
            case R.id.four_tab:
                mCardAdapter.setItemAnimation(AnimationType.SLIDE_FROM_RIGHT);
                break;
        }
    }

    private List<CardItem> generateMockData() {
        List<CardItem> cardItems = new ArrayList<>();
        for (int idx = 0; idx < MOCK_ITEM_COUNT; idx++) {
            cardItems.add(new CardItem(MOCK_ITEM_IMG_RES[idx % MOCK_ITEM_IMG_RES.length]));
        }
        return cardItems;

    }

    public static class CardItem {
        public int imgResId;

        public CardItem(int imgResId) {
            this.imgResId = imgResId;
        }
    }

    public static class CardAdapter extends BaseAdapter<CardItem> {

        public CardAdapter(Context context, List<CardItem> data) {
            super(context, data);
        }

        @Override
        protected void bindDataToItemView(BaseViewHolder holder, CardItem item, int position) {
            holder.setImageResource(R.id.image_iv, item.imgResId);
        }

        @Override
        protected int getItemViewLayoutId(int position, CardItem item) {
            return R.layout.item_card;
        }
    }
}
