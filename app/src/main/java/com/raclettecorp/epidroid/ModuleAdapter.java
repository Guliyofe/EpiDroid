package com.raclettecorp.epidroid;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ModuleAdapter extends ArrayAdapter<Module>
{
    public ModuleAdapter(Context context, List<Module> modules) {
        super(context, 0, modules);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_modules,parent, false);
        }

        ModuleViewHolder viewHolder = (ModuleViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ModuleViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.textTitleViewProjects);
            viewHolder.module = (TextView) convertView.findViewById(R.id.textModuleViewProjects);
            viewHolder.grade = (TextView) convertView.findViewById(R.id.textGradeViewModules);
            viewHolder.year = (TextView) convertView.findViewById(R.id.textYearViewModules);
            viewHolder.date = (TextView) convertView.findViewById(R.id.textDateViewModules);
            viewHolder.credits = (TextView) convertView.findViewById(R.id.textCreditsViewModules);
            convertView.setTag(viewHolder);
        }

        Module module = getItem(position);

        viewHolder.title.setText(module.getTitle());
        viewHolder.module.setText(module.getCodeModule());
        viewHolder.grade.setText(module.getGrade());
        viewHolder.year.setText(String.valueOf(module.getScholarYear()));
        viewHolder.date.setText(module.getDateIns());
        viewHolder.credits.setText(String.valueOf(module.getCredits()));

        return convertView;
    }

    private class ModuleViewHolder
    {
        public TextView title;
        public TextView module;
        public TextView grade;
        public TextView year;
        public TextView date;
        public TextView credits;
    }
}
