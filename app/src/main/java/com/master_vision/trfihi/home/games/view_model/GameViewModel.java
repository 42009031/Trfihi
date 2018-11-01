package com.master_vision.trfihi.home.games.view_model;

import com.master_vision.trfihi.home.games.adapter.GamesAdapter;
import com.master_vision.trfihi.home.games.model.GameModel;
import java.util.ArrayList;

public class GameViewModel {

    public GamesAdapter adapter;
    public ArrayList<GameModel> dataList;

    public GameViewModel() {
        adapter = new GamesAdapter();

        dataList = new ArrayList<GameModel>() {{
            for (int i = 0; i < 5; i++) {
                add(new GameModel("3.5",
                        "01/01/2019 12:00 PM",
                        "0.000",
                        "0.000",
                        "00",
                        "Game title",
                        "Mohamed",
                        "Naser City",
                        "01/01/2019 12:00 PM",
                        "user name",
                        "display name",
                        "http://riptoned.com/wp-content/uploads/2017/01/no-user-image.png",
                        "01001526337",
                        "true",
                        "false"));
            }
        }};
    }


}
