package com.master_vision.trfihi.home.messages.viewmodel;

import android.content.Context;
import com.master_vision.trfihi.home.messages.adapter.MessageAdapter;
import com.master_vision.trfihi.home.messages.model.MessageModel;
import java.util.ArrayList;

public class MessageViewModel {

    public MessageAdapter adapter;
    public ArrayList<MessageModel> dataList;

    public MessageViewModel(Context context) {
        adapter = new MessageAdapter(context);
        dataList = new ArrayList<MessageModel>() {{
                for (int i = 0; i <= 5; i++) {
                    add(new MessageModel("0",
                            "Donia Mohamed",
                            "http://riptoned.com/wp-content/uploads/2017/01/no-user-image.png",
                            "1",
                            "How are you?"+i));
                } }
        };

    }

}
