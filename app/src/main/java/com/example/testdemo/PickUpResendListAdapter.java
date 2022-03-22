package com.example.testdemo;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.List;


public class PickUpResendListAdapter implements ExpandableListAdapter {

    //数据
    private List<String> group;
    private List<List<String>> child;

    private Context context;
    private GroupViewHolder groupViewHolder;
    private ChildViewHolder childViewHolder;

    public PickUpResendListAdapter(List<String> group, List<List<String>> child, Context context) {
        this.group = group;
        this.child = child;
        this.context = context;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    /***
     * 一级列表个数
     * @return
     */
    @Override
    public int getGroupCount() {
        return group.size();
    }

    /***
     * 每个二级列表的个数
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    /***
     * 一级列表中单个item
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition-1);
    }

    /***
     * 二级列表中单个item
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition-1).get(childPosition-1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /***
     * 每个item的id是否固定，一般为true
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /***
     * 填充一级列表
     * @param groupPosition
     * @param isExpanded 是否展开
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_pickup_resend_code_users_list_layout, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tv_group = convertView.findViewById(R.id.tv_group);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        //设置显示数据
        groupViewHolder.tv_group.setText(group.get(groupPosition));
        return convertView;
    }

    /***
     * 填充二级列表
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_pickup_resend_code_boxs_list_layout, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tv_child = convertView.findViewById(R.id.tv_child);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        if((childPosition<child.get(groupPosition).size()) && (groupPosition<child.size())){childViewHolder.tv_child.setText(child.get(groupPosition).get(childPosition));}
        return convertView;
    }

    /***
     * 二级列表中每个能否被选中，如果有点击事件一定要设为true
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    class GroupViewHolder {
        TextView tv_group;
    }

    class ChildViewHolder {
        TextView tv_child;
    }
}
