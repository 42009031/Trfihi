/*
 * Copyright (c) 2018 Phunware Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.master_vision.trfihi.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.master_vision.trfihi.home.events.adapter.EventAdapter;
import com.master_vision.trfihi.home.events.model.EventModel;
import com.master_vision.trfihi.home.games.adapter.GamesAdapter;
import com.master_vision.trfihi.home.games.model.GameModel;
import com.master_vision.trfihi.profile.adapter.ReviewAdapter;
import com.master_vision.trfihi.profile.model.ReviewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gregory Rasmussen on 7/26/17.
 */
public class RecyclerViewDataBinding {

    @BindingAdapter({"app:adapter_review", "app:data_review"})
    public void bind(RecyclerView recyclerView, ReviewAdapter adapter, ArrayList<ReviewModel> data) {
        recyclerView.setAdapter(adapter);
        adapter.updateData(data);
    }

    @BindingAdapter({"bind:adapter_event", "bind:data_event"})
    public void bind(RecyclerView recyclerView, EventAdapter adapter, ArrayList<EventModel> data) {
        recyclerView.setAdapter(adapter);
        adapter.updateData(data);
    }

    @BindingAdapter({"bind:adapter_game", "bind:data_game"})
    public void bind(RecyclerView recyclerView, GamesAdapter adapter, ArrayList<GameModel> data) {
        recyclerView.setAdapter(adapter);
        adapter.updateData(data);
    }
}
