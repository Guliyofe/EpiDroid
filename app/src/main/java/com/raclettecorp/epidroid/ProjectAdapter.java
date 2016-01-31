package com.raclettecorp.epidroid;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProjectAdapter extends ArrayAdapter<ApiIntraProjects.Project>
{
    public ProjectAdapter(Context context, List<ApiIntraProjects.Project> projects) {
        super(context, 0, projects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_projects,parent, false);
        }

        ProjectViewHolder viewHolder = (ProjectViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ProjectViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.textTitleViewProjects);
            viewHolder.year = (TextView) convertView.findViewById(R.id.textYearViewProjects);
            viewHolder.titleModule = (TextView) convertView.findViewById(R.id.textModuleViewProjects);
            viewHolder.codeModule = (TextView) convertView.findViewById(R.id.textCodeViewProjects);
            viewHolder.begin = (TextView) convertView.findViewById(R.id.textBeginViewProjects);
            viewHolder.end = (TextView) convertView.findViewById(R.id.textEndViewProjects);
            convertView.setTag(viewHolder);
        }

        ApiIntraProjects.Project project = getItem(position);

        viewHolder.title.setText(project.getActiTitle());
        viewHolder.year.setText(project.getScolarYear());
        viewHolder.titleModule.setText(project.getTitleModule());
        viewHolder.codeModule.setText(project.getCodeModule());
        viewHolder.begin.setText(project.getBeginActi());
        viewHolder.end.setText(project.getEndActi());

        return convertView;
    }

    private class ProjectViewHolder
    {
        public TextView title;
        public TextView year;
        public TextView titleModule;
        public TextView codeModule;
        public TextView begin;
        public TextView end;
    }

}
