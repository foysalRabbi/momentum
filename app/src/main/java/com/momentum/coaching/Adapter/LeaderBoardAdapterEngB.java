package com.momentum.coaching.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.momentum.coaching.R;
import com.momentum.coaching.model.UserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeaderBoardAdapterEngB extends RecyclerView.Adapter<LeaderBoardAdapterEngB.ViewHolderUsers> {

    private List<UserModel> mUsers;
    private Context mContext;
    ArrayList<Map.Entry<String, Integer>> list;

    public LeaderBoardAdapterEngB(Context context, List<UserModel> fireUsers) {
        mUsers = fireUsers;
        mContext = context;
    }
    @Override
    public ViewHolderUsers onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 1){
            return new ViewHolderUsers(mContext, LayoutInflater.from(parent.getContext()).inflate(R.layout.list_leaderboard_gold, parent, false));
        }
        if(viewType == 2){
            return new ViewHolderUsers(mContext, LayoutInflater.from(parent.getContext()).inflate(R.layout.list_leaderboard_row_silver, parent, false));
        }
        if(viewType == 3){
            return new ViewHolderUsers(mContext, LayoutInflater.from(parent.getContext()).inflate(R.layout.list_leaderboard_row_bronze, parent, false));
        }
        else {
            return new ViewHolderUsers(mContext, LayoutInflater.from(parent.getContext()).inflate(R.layout.list_leaderboard_row, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        if (position == 1) return 2;
        if (position == 2) return 3;
        else return 4;
    }

    @Override
    public void onBindViewHolder(ViewHolderUsers holder, int position) {
        sortingScore();
        Map.Entry<String, Integer> fireUser =  list.get(position);
        {
            holder.getUserDisplayName().setText(fireUser.getKey());
            //compFundaScore
            holder.getUserCompMarks().setText("" + fireUser.getValue());
        }
    }

    public void sortingScore() {
        HashMap<String, Integer> getNameWithScore = new HashMap<>();

        for(int i=0;i<mUsers.size();i++) {
            UserModel fireUser = mUsers.get(i);
            //if(fireUser.getCompMarks()>0) {
            getNameWithScore.put(fireUser.getDisplayName(), fireUser.getEnglishMarksB());
            //}
        }

        Set<Map.Entry<String, Integer>> set = getNameWithScore.entrySet();
        list = new ArrayList<Map.Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void refill(UserModel users) {
        mUsers.add(users);
        notifyDataSetChanged();
    }

    public void changeUser(int index, UserModel user) {
        mUsers.set(index,user);
        notifyDataSetChanged();
    }

    public void clear() {
        mUsers.clear();
    }


    public class ViewHolderUsers extends RecyclerView.ViewHolder  {

        TextView mUserDisplayName;
        TextView scoreCompFunda;
        Context mContextViewHolder;

        public ViewHolderUsers(Context context, View itemView) {
            super(itemView);
            mUserDisplayName = (TextView) itemView.findViewById(R.id.userName);
            scoreCompFunda = (TextView) itemView.findViewById(R.id.score);
            mContextViewHolder = context;
        }

        View view = itemView;

        public View holderView() {
            return view;
        }

        public TextView getUserDisplayName() {
            return mUserDisplayName;
        }

        public TextView getUserCompMarks() {
            return scoreCompFunda;
        }
    }
}

