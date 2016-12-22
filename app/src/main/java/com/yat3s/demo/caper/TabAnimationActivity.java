package com.yat3s.demo.caper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yat3s.demo.caper.util.SystemBarTintManager;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_animation);
        SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
        systemBarTintManager.setStatusBarTintEnabled(true);
        systemBarTintManager.setNavigationBarTintEnabled(true);

        ButterKnife.bind(this);

        contentRv.setLayoutManager(new LinearLayoutManager(this));
        contentRv.setAdapter(new CardAdapter(this, generateMockData()));
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
