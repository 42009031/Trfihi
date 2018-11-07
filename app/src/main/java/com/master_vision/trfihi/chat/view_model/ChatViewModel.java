package com.master_vision.trfihi.chat.view_model;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import com.master_vision.trfihi.chat.adapter.ChatAdapter;
import com.master_vision.trfihi.chat.model.ChatModel;
import com.master_vision.trfihi.common.methods.Helper;
import java.util.ArrayList;

public class ChatViewModel {

    public ObservableField<String> message = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> profileImageUrl = new ObservableField<>();
    public ObservableField<Integer> scrollPosition = new ObservableField<>();
    public ChatAdapter adapter;
    public ArrayList<ChatModel> dataList;

    public Context context;
    public ChatViewModel(Context context){
        this.context = context;
        this.name.set("Dalia Mohamed");
        this.profileImageUrl.set("http://riptoned.com/wp-content/uploads/2017/01/no-user-image.png");
        this.adapter = new ChatAdapter(context);
        this.dataList = new ArrayList<ChatModel>(){{

            add(new ChatModel("0",
                    "1",
                    "Hi",
                    "19:00 PM",
                    "0",
                    "true"));

            add(new ChatModel("1",
                    "0",
                    "How are you",
                    "19:00 PM",
                    "0",
                    "true"));

            add(new ChatModel("0",
                    "1",
                    "I'm fineasfasdaDASDSDASDASDASDASDASDASDASDASDASD thanks",
                    "19:00 PM",
                    "0",
                    "true"));

            add(new ChatModel("1",
                    "0",
                    "what's your name?",
                    "19:00 PM",
                    "0",
                    "true"));

            add(new ChatModel("0",
                    "1",
                    "My name is Ahmed Dawoud",
                    "19:00 PM",
                    "0",
                    "true"));

            add(new ChatModel("1",
                    "0",
                    "WelcomeASDASDASDASDASDASDASDASDASDASDASDASDASD",
                    "19:00 PM",
                    "0",
                    "true"));

            add(new ChatModel("0",
                    "1",
                    "Welcome",
                    "19:00 PM",
                    "0",
                    "true"));
        }};
        scrollPosition.set(dataList.size()-1);
    }

    public void onSendClick(View view){
        view.startAnimation(Helper.BtnClickAnimation);
        if(!message.get().isEmpty()){
            dataList.add(new ChatModel("0",
                    "1",
                    message.get(),
                    "20:00 AM",
                    "",
                    ""));
            int newMsgPosition = dataList.size() - 1;
            adapter.notifyItemInserted(newMsgPosition);
            scrollPosition.set(newMsgPosition);
            message.set("");
        }
    }

    public void onVideoClick(View view){
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void onCallClick(View view){
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void onAttachmentClick(View view){
        view.startAnimation(Helper.BtnClickAnimation);

    }

    public void onStartEditing(View view){
        scrollPosition.set(dataList.size() - 1);
    }
}
