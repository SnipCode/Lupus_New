/*
 * Copyright 2016 by FET
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

/*
 *  Project created by FET on 6th November 2015
 *  Code updated on 4th August 2016
 */

/*
 * Copyright 2016 by F43nd1r (https://github.com/F43nd1r)
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

package com.FET.leonardo.scurcola.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.FET.leonardo.scurcola.DataProvider;
import com.FET.leonardo.scurcola.R;

/**
 * Updated on 20.07.2016.
 *
 * @author F43nd1r
 */

public class GameOverFragment extends Fragment implements View.OnClickListener{

    private DataProvider provider;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        provider = (DataProvider) getActivity();
        View v;
        if(provider.isGoodEnd()) {
            v = inflater.inflate(R.layout.game_over_villagers, container, false);
            TextView save = (TextView) v.findViewById(R.id.textView4);
            if (save != null) {
                save.setText(String.format(getResources().getString(R.string.villagersWin), provider.getVillage()));
            }
        }else {
            v = inflater.inflate(R.layout.game_over_wolves, container, false);
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        SharedPreferences preferences = getActivity().getSharedPreferences("X", Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
        provider.getFragmentSwitcher().main();
    }
}
