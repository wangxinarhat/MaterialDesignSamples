package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.People;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.interfaces.RecyclerOnItemClickListener;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.HeaderAdapter;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.SearchPeopleAdapter;
import wang.wangxinarhat.materialdesignsamples.utils.MyToast;

/**
 * Created by wang on 2016/3/15.
 */
public class ToolbarSearchDemoActivity extends BaseActivity implements RecyclerOnItemClickListener.OnItemClickListener {


    @Bind(R.id.search_view)
    MaterialSearchView searchView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.activity_toolbar_search_recycler)
    RecyclerView recyclerView;
    private SearchPeopleAdapter mAdapter;
    private ArrayList<People> mPeopleList;

    public static Intent getStartIntent() {
        Intent intent = new Intent();
        intent.setClass(BaseApplication.getApplication(), ToolbarSearchDemoActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_search);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        initRecyclerView();


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setEllipsize(true);
//        searchView.setSuggestions(new String[]{"ib", "kaka", "modric"});
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                MyToast.showShortToast("search");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<People> filteredModelList = filter(mPeopleList, newText);

                //reset
                mAdapter.setFilter(filteredModelList);
                mAdapter.animateTo(filteredModelList);
                recyclerView.scrollToPosition(0);
                return true;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
               MyToast.showShortToast("?????");
            }

            @Override
            public void onSearchViewClosed() {
                //??
                mAdapter.setFilter(mPeopleList);
            }
        });
    }

    private List<People> filter(List<People> peoples, String query) {
        query = query.toLowerCase();

        final List<People> filteredModelList = new ArrayList<>();
        for (People people : peoples) {

            final String nameEn = people.getName().toLowerCase();
            final String desEn = people.getDescription().toLowerCase();
            final String name = people.getName();
            final String des = people.getDescription();

            if (name.contains(query) || des.contains(query) ||nameEn.contains(query) || desEn.contains(query)) {

                filteredModelList.add(people);

            }
        }
        return filteredModelList;
    }


    private void initRecyclerView() {


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mPeopleList = new ArrayList<>();

        String[] peopleName = {"Kaka", "Modric", "Rooney", "Ibla", "Bale", "死神", "Maurice Moss", "Roy Trenneman",};
        String[] peopleDes = {"The best player", "莫德里奇是最好的后腰", "鲁尼踢得不好", "伊贝拉是谁？", "贝尔跑得真快", "Aaron", "Oh, four, I mean five, I mean fire!", "哈哈"};

        for (int i = 0;i < peopleName.length;i++) {
            mPeopleList.add(new People(peopleName[i],peopleDes[i]));
        }

        mAdapter = new SearchPeopleAdapter(mPeopleList);
        recyclerView.setAdapter(mAdapter);


        HeaderAdapter headerAdapter = new HeaderAdapter(mAdapter);

        recyclerView.setAdapter(headerAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(this, recyclerView, this));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(View view, int position) {
        MyToast.showShortToast("Recycler short click");
    }

    @Override
    public void onItemLongClick(View view, int position) {
        MyToast.showShortToast("Recycler long click");

    }
}
