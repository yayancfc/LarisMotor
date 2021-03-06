package com.yayanheryanto.larismotor.view.pending;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.yayanheryanto.larismotor.R;
import com.yayanheryanto.larismotor.adapter.MotorAdapter;
import com.yayanheryanto.larismotor.fragment.PendingBeliFragment;
import com.yayanheryanto.larismotor.fragment.PendingJualFragment;
import com.yayanheryanto.larismotor.model.Motor;
import com.yayanheryanto.larismotor.model.PendingBeli;
import com.yayanheryanto.larismotor.retrofit.ApiClient;
import com.yayanheryanto.larismotor.retrofit.ApiInterface;
import com.yayanheryanto.larismotor.view.owner.MotorActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yayanheryanto.larismotor.config.config.DEBUG;
import static com.yayanheryanto.larismotor.config.config.ID_USER;
import static com.yayanheryanto.larismotor.config.config.MY_PREFERENCES;

public class PendingTransaksiActivity extends AppCompatActivity {


    private ViewPager view_pager;
    private TabLayout tab_layout;
    private int currentPage = 0;
    private SearchView searchView;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_transaksi);


        initComponent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pending, menu);
        menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setIconifiedByDefault(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tambah:

                if (currentPage == 0) {
                    Intent intent = new Intent(PendingTransaksiActivity.this, AddPendingBeliActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(PendingTransaksiActivity.this, AddpendingJualActivity.class);
                    startActivity(intent);
                }
                return true;

            case R.id.action_search:
                int pos = view_pager.getCurrentItem();
                if (pos == 0) {
                    Intent intent = new Intent(PendingTransaksiActivity.this, SearchPendingBeliActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(PendingTransaksiActivity.this, SearchPendingJualActivity.class);
                    startActivity(intent);
                }
                return true;

            case R.id.filter:
                pos = view_pager.getCurrentItem();
                if (pos == 0) {
                    Intent intent = new Intent(PendingTransaksiActivity.this, FilteredPendingBeliActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(PendingTransaksiActivity.this, FilteredPendingJualActivity.class);
                    startActivity(intent);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        searchView.setIconifiedByDefault(true);
        menuItem.collapseActionView();
        searchView.onActionViewExpanded();
    }

    private void initComponent() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(view_pager);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        tab_layout.setupWithViewPager(view_pager);
    }

    private void setupViewPager(ViewPager viewPager) {
        PendingTransaksiActivity.SectionsPagerAdapter adapter = new PendingTransaksiActivity.SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PendingBeliFragment(), "Pembeli");
        adapter.addFragment(new PendingJualFragment(), "Penjual");
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(DEBUG, "page selected " + position);
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
