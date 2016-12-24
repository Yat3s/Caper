package com.yat3s.demo.caper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.yat3s.library.adapter.BaseAdapter;
import com.yat3s.library.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yat3s on 22/12/2016.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class TabAnimationActivity extends AppCompatActivity {
    private static final int MOCK_ITEM_COUNT = 100;
    @BindView(R.id.content_rv)
    RecyclerView contentRv;
    @BindView(R.id.tab_layout)
    LinearLayout tabLayout;

    private boolean tabIsShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_animation);
        ButterKnife.bind(this);

        contentRv.setLayoutManager(new LinearLayoutManager(this));
        contentRv.setAdapter(new CardAdapter(this, generateMockData()));
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
    }

    private List<CardItem> generateMockData() {
        List<CardItem> cardItems = new ArrayList<>();
        for (int idx = 0; idx < MOCK_ITEM_COUNT; idx++) {
            cardItems.add(new CardItem("title " + idx));
        }
        return cardItems;
    }

    public static class CardItem {
        public String title;

        public CardItem(String title) {
            this.title = title;
        }
    }

    public static class CardAdapter extends BaseAdapter<CardItem> {

        public CardAdapter(Context context, List<CardItem> data) {
            super(context, data);
        }

        @Override
        protected void bindDataToItemView(BaseViewHolder holder, CardItem item, int position) {
            holder.setText(R.id.title_tv, item.title);
        }

        @Override
        protected int getItemViewLayoutId(int position, CardItem item) {
            return R.layout.item_card;
        }
    }
}
