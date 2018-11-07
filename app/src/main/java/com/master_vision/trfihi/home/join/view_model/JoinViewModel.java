package com.master_vision.trfihi.home.join.view_model;

import android.content.Context;
import com.master_vision.trfihi.home.join.adapter.JoinAdapter;
import com.master_vision.trfihi.home.join.model.JoinModel;
import java.util.ArrayList;

public class JoinViewModel {

    public JoinAdapter adapter;
    public ArrayList<JoinModel> dataList;

    public JoinViewModel(Context context) {
        adapter = new JoinAdapter(context);
        dataList = new ArrayList<JoinModel>() {{
                for (int i = 0; i <= 5; i++) {
                    add(new JoinModel("12:00 PM",
                            "00",
                            "Game title",
                            "01:00 PM",
                            "12:00 PM",
                            "Naser City",
                            "10.000",
                            "10.001",
                            "3",
                            "amado",
                            "01090217251",
                            "Amado Knight",
                            "http://riptoned.com/wp-content/uploads/2017/01/no-user-image.png",
                            "true",
                            "false"
                            ));
                } }
        };

    }

}
