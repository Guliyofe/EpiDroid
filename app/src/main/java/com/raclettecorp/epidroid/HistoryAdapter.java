package com.raclettecorp.epidroid;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<History>
{
    public HistoryAdapter(Context context, List<History> histories) {
            super(context, 0, histories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_history,parent, false);
        }

        HistoryViewHolder viewHolder = (HistoryViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new HistoryViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.textTitleView);
            viewHolder.content = (TextView) convertView.findViewById(R.id.textContentView);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.imageHistoryView);
            convertView.setTag(viewHolder);
        }

        History history = getItem(position);

        viewHolder.title.setText(history.getTitle());
        viewHolder.content.setText(history.getContent());
        //viewHolder.avatar.setImageDrawable(new ColorDrawable(tweet.getColor()));
        Log.d("EpiDroid", history.getUser().getPicture());
        if (history.getUser().getPicture() != "null")
            new HomeFragment.ImageLoadTask(history.getUser().getPicture(), viewHolder.avatar).execute();
        else
            viewHolder.avatar.setImageResource(R.drawable.ic_person_24dp);

        return convertView;
    }

    private class HistoryViewHolder
    {
        public TextView title;
        public TextView content;
        public ImageView avatar;
    }
}
