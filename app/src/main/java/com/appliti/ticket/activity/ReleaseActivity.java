package com.appliti.ticket.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.appliti.ticket.R;
import com.appliti.ticket.activity.adapter.ReleasePagerAdapter;
import com.appliti.ticket.activity.menu.BottomNavigationActivity;
import com.appliti.ticket.model.Position;
import com.appliti.ticket.service.CheckCodeClient;
import com.appliti.ticket.service.MarkItemsReleasedClient;
import com.appliti.ticket.service.response.Comment;
import com.appliti.ticket.service.response.Order;
import com.appliti.ticket.service.response.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ReleaseActivity extends BottomNavigationActivity {

    protected Gson gson = new Gson();
    protected BottomNavigationView navigationView;
    private Order order;
    private TabLayout tabLayout;
    private String actionData;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String jsonString = extras.getString(CheckCodeClient.CHECK_CODE_DATA);
            actionData = extras.getString(MarkItemsReleasedClient.ORDER_ACTION_DATA);

            order = gson.fromJson(jsonString, Order.class);
        }

        tabLayout = findViewById(R.id.release_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.release_data));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.release_comments));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        initView();
    }

    public List<Position> getProducts() {
        if (order != null) {
            final ArrayList<Position> items = new ArrayList<>();
            final ArrayList<Product> products = order.getProducts();
            for (final Product product : products) {
                if (product.isVirtual()) {
                    items.add(new Position(product.getName(), product.getCount()));
                }
            }
            return items;
        } else {
            return new ArrayList<>();
        }
    }

    public List<String> getPersonData() {
        if (order != null) {
            final List<String> personDataList = new ArrayList<>();
            personDataList.add(order.getFirstName());
            personDataList.add(order.getLastName());
            personDataList.add(order.getMail());
            personDataList.add(order.getPhone());
            personDataList.add(order.getTotalValue() + " zł.");
            return personDataList;
        } else {
            return new ArrayList<>();
        }
    }

    public List<Comment> getComments() {
        if (order != null) {
            return order.getComments();
        } else {
            return new ArrayList<>();
        }
    }

    public boolean isReleasedMode() {
        return order.getReleased() == 0;
    }

    public int getOrderId() {
        return order.getId();
    }

    public void initView() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.release_pager);
        final ReleasePagerAdapter adapter = new ReleasePagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(final TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(final TabLayout.Tab tab) {

            }
        });
    }
}
