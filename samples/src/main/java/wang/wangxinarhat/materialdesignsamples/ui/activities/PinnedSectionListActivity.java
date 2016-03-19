/*
 * Copyright (C) 2013 Sergej Shafarenka, halfbit.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.ui.widget.PinnedSectionListView;

public class PinnedSectionListActivity extends BaseActivity {



@Bind(R.id.activity_pinned_section_list_list)PinnedSectionListView listview;

    public static Intent getStartIntent() {
        Intent intent = new Intent();
        intent.setClass(BaseApplication.getApplication(), PinnedSectionListActivity.class);
        return intent;
    }


    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pinned_section_list);

        ButterKnife.bind(this);

        initData();
	}

    private void initData() {

        listview.setAdapter(new MyAdapter());


    }

    public class MyAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter{
        @Override
        public boolean isItemViewTypePinned(int viewType) {
            return true;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }


}