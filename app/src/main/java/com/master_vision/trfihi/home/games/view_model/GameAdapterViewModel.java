package com.master_vision.trfihi.home.games.view_model;

import android.databinding.ObservableField;
import android.view.View;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.home.games.model.GameModel;

public class GameAdapterViewModel {

    public ObservableField<String> profileImageUrl = new ObservableField<>();
    public ObservableField<String> createdName = new ObservableField<>();
    public ObservableField<Float> evaluation = new ObservableField<>();
    public ObservableField<String> gameData = new ObservableField<>();

    private GameModel model;

    public GameAdapterViewModel( GameModel model) {

        this.model = model;

//        {creator_name} need to play {game_name} with {player_no} person, from {from} to {to}, at {city_name}.

        String eventDataStr = TrfihiApp.context.getString(R.string.game_data_template);
        eventDataStr = eventDataStr.replace("{creator_name}", model.getDisplayName());
        eventDataStr = eventDataStr.replace("{game_name}", model.getGameTitle());
        eventDataStr = eventDataStr.replace("{player_no}", model.getInitiatorUser());
        eventDataStr = eventDataStr.replace("{from}", model.getStartTime());
        eventDataStr = eventDataStr.replace("{to}", model.getEndTime());
        eventDataStr = eventDataStr.replace("{city_name}", model.getLocation());
        gameData.set(eventDataStr);
        profileImageUrl.set(model.getProfileImageURL());
        evaluation.set(Float.parseFloat(model.getEvaluation()));
        createdName.set(model.getDisplayName());
    }

    public void onJoinEventClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }

    public void onDeleteEventClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }

    public void onGameClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
    }



}
