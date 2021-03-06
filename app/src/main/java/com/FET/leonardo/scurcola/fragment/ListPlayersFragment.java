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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.FET.leonardo.scurcola.CharactersAdapter;
import com.FET.leonardo.scurcola.DataProvider;
import com.FET.leonardo.scurcola.Player;
import com.FET.leonardo.scurcola.R;

import java.util.ArrayList;

/**
 * Updated on 20.07.2016.
 *
 * @author F43nd1r
 */

public class ListPlayersFragment extends Fragment implements View.OnClickListener {

    private DataProvider provider;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        provider = (DataProvider) getActivity();
        View v = inflater.inflate(R.layout.list_players, container, false);
        View root = v.getRootView();

        if (provider.isNight()) {
            // Night starts
            root.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkBlue));
        } else {
            // Day starts
            root.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lightBlue));
        }

        final ArrayList<Player> players = new ArrayList<>(provider.getAlivePlayers());
        for (Player player : players) {
            player.setCount(0);
        }
        final RecyclerView myList = (RecyclerView) v.findViewById(R.id.playersVote);
        myList.setLayoutManager(new LinearLayoutManager(getActivity()));
        final CharactersAdapter adapter = new CharactersAdapter(players);

        adapter.setOnEntryClickListener(new CharactersAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                //TODO Maybe add info about the character selected
            }
        });
        myList.setAdapter(adapter);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backToGame:
                provider.getFragmentSwitcher().back();
                System.out.println("Back executed.");
                break;
            case R.id.restartButton:
                RestartDialog restartDialog = new RestartDialog();
                restartDialog.show(getFragmentManager(), "RestartDialog");
                break;
            }
        }
    }


