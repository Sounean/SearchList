package com.example.testdemo.utils;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;

public class SearchListUtil {

    /*
    *   对SearchView原生的UI进行一些图标的隐藏
    *  传入searchView对象
    * */
    public static void hint(SearchView searchView){
        ImageView searchButton=searchView.findViewById(androidx.appcompat.R.id.search_button);
        searchButton.setVisibility(View.GONE);
        ImageView goBtn=searchView.findViewById(androidx.appcompat.R.id.search_go_btn); // 是scrollview的右边的箭头
        goBtn.setVisibility(View.GONE);
        ImageView searchMagIcon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon); // 是scrollview的左边的搜索框
        searchMagIcon.setVisibility(View.GONE);
    }

    /*
    * 对提醒字的修改
    * */
    public static void setQueryHint(SearchView searchView,String s){
        searchView.setQueryHint(s);
    }

    /*
    *三种默认展开搜索框的设置方式
    *设置搜索框直接展开显示。左侧有放大镜(在搜索框中) 右侧有叉叉 可以关闭搜索框:mSearchView.setIconified(false);
    *设置搜索框直接展开显示。左侧有放大镜(在搜索框外) 右侧无叉叉 有输入内容后有叉叉 不能关闭搜索框mSearchView.setIconifiedByDefault(false);
    *设置搜索框直接展开显示。左侧有无放大镜(在搜索框中) 右侧无叉叉 有输入内容后有叉叉 不能关闭搜索框mSearchView.onActionViewExpanded();
    *  */
    public static void iconExpandChanged(SearchView searchView,boolean b1,boolean b2,boolean b3){
        if (!b1) searchView.setIconified(b1);
        if (!b2) searchView.setIconifiedByDefault(b2);
        if (!b3) searchView.onActionViewExpanded();

    }
}
