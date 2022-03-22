package com.example.testdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.testdemo.Entity.RESIDENTtest;
import com.example.testdemo.utils.RegularUtiil;


public class MainActivity extends AppCompatActivity{
    private SearchView searchView;  // 搜索框是原生就有的，为了满足ui效果，只能在点击的时候也调用一次隐藏
    private ListView listView;
    //定义自动完成的列表
    private final String[] mStrings = {"测试1","测试2","测试3"};
    private ImageView searchMagIcon;
    public static String TAG = "测试点:";
    public RESIDENTtest bean=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv);
        final ArrayAdapter adapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,mStrings);
        listView.setAdapter(adapter);
        //为ListView启动过滤
        listView.setTextFilterEnabled(true);
        searchView = (SearchView) findViewById(R.id.sv);
        searchView.setIconifiedByDefault(false);//设为true则搜索栏 缩小成俄日一个图标点击展开
        searchView.onActionViewExpanded();
        // 因为ui需要，左侧右侧均无初始图标

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.e(TAG, "被子控件消费了！@！！！！！");
            }
        });
        searchView.setSubmitButtonEnabled(true);
        //设置默认提示文字
        hintIcons();
        // 隐藏spinner初始的左边放大镜
        searchView.setQueryHint("Enter Phone number");
        //配置监听器


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //点击搜索按钮时触发
            @Override
            public boolean onQueryTextSubmit(String query) {    //此处添加查询开始后的具体时间和方法
                if ((RegularUtiil.isEmail(query)) || (RegularUtiil.isPhoneNumber(query))){
                    Toast.makeText(MainActivity.this,"你所要搜索的号码/邮箱:" + query,Toast.LENGTH_SHORT).show();
                    if (bean == null){
                        Toast.makeText(MainActivity.this,"并没有搜索到相关号码/邮箱的快递！" + query,Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this,"有相关的号码/快递" + query,Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this,"请检查您输入的格式，输入正确的号码或者邮箱！",Toast.LENGTH_SHORT).show();
                }


                hintIcons();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                //如果newText长度不为0
                if (TextUtils.isEmpty(newText)){
                    listView.clearTextFilter();
                }else{
                    //listView.setFilterText(newText);
                     adapter.getFilter().filter(newText.toString());
                }
               hintIcons();
                return true;
            }
        });
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>  parent, View view, int position, long id) {
                //searchMagIcon.setVisibility(View.GONE);
                Object string = adapter.getItem(position);
                searchView.setQuery(string.toString(),true);
                //hintIcons();
            }
        });
    }

    private void hintIcons() {
        ImageView searchButton=searchView.findViewById(androidx.appcompat.R.id.search_button);
        searchButton.setVisibility(View.GONE);
        ImageView goBtn=searchView.findViewById(androidx.appcompat.R.id.search_go_btn); // 是scrollview的右边的箭头
        goBtn.setVisibility(View.GONE);
        searchMagIcon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        searchMagIcon.setVisibility(View.GONE);
    }


}