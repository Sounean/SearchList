package com.example.testdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;


import com.example.testdemo.dao.PickUpResendEntity2;
import com.example.testdemo.utils.RegularUtiil;
import com.example.testdemo.utils.SearchListUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{
    private SearchView searchView;  // 搜索框是原生就有的，为了满足ui效果，只能在点击的时候也调用一次隐藏
    //private ListView elv;
    //定义自动完成的列表
    private final String[] mStrings = {"测试1","测试2","测试3"};
    public static String TAG = "测试点:";
    //private ArrayAdapter adapter;
    // 数据测试
    public List<String> groupEntity = new ArrayList<String>();
    public List<List<String>> entityBoxs =new ArrayList<List<String>>();    // 把所有人的相关的快递都获取出来
    List<PickUpResendEntity2> resendEntities = new ArrayList<PickUpResendEntity2>();
    // 新的adapter
    private PickUpResendListAdapter mPickUpResendListAdapter;
    // 新的view
    private ExpandableListView elv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView(); // 控件的提取和SearchViewicon按照ui的=进行的隐藏
        initListener(); // 老项目的监听点击事件
        initPickUpresendListener(); // 新的项目的监听list的点击事件
        initPickUpResendAdapter();  // 新的项目的exlist的适配器的设置   初始化不为空是这里出问题了
        elv.setTextFilterEnabled(true);
    }


    private void initPickUpResendAdapter() {
        try {
            mPickUpResendListAdapter = new PickUpResendListAdapter(groupEntity, entityBoxs, this);
            elv.setAdapter(mPickUpResendListAdapter);
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "没有该用户的", Toast.LENGTH_SHORT).show();
        }

    }




    private void initListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //点击搜索按钮时触发
            @Override
            public boolean onQueryTextSubmit(String query) {    //此处添加查询开始后的具体时间和方法
                if ((RegularUtiil.isEmail(query)) || (RegularUtiil.isPhoneNumber(query))){
                    Toast.makeText(MainActivity.this,"你所要搜索的号码/邮箱:" + query,Toast.LENGTH_SHORT).show();
                    if (groupEntity.size() == 0){
                        Toast.makeText(MainActivity.this,"并没有搜索到相关号码/邮箱的快递！" + query,Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this,"有相关的号码/快递" + query,Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this,"请检查您输入的格式，输入正确的号码或者邮箱！",Toast.LENGTH_SHORT).show();
                }
                SearchListUtil.hint(searchView);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                //如果newText长度不为0
                if (TextUtils.isEmpty(newText)){
                    elv.clearTextFilter(); // 调用filter来刷新数据
                    List<PickUpResendEntity2> resultEntities = findByStringMock(newText.toString());    // 被筛选后得到的对象
                    findEntities(resultEntities);
                    initPickUpResendAdapter();
                }else{
                    elv.clearTextFilter();// 我也不知道有没有用：调用filter来刷新数据
                    List<PickUpResendEntity2> resultEntities = findByStringMock(newText.toString());    // 被筛选后得到的对象
                    findEntities(resultEntities);
                    initPickUpResendAdapter();
                }
                SearchListUtil.hint(searchView);
                return true;
            }
        });
        elv.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>  parent, View view, int position, long id) {
                //Object string = adapter.getItem(position);
                Object string = mPickUpResendListAdapter.getGroup(position);
                searchView.setQuery(string.toString(),true);
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.e(TAG, "被子控件消费了！@！！！！！");
            }
        });
    }

    private void initPickUpresendListener() {
        //设置一级列表的点击事件
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(MainActivity.this, groupEntity.get(groupPosition), Toast.LENGTH_SHORT).show();
                //返回false 否则一级列表不会展开
                return false;
            }
        });

        //设置二级列表的点击事件
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Toast.makeText(MainActivity.this, entityBoxs.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void initView() {
        searchView = (SearchView) findViewById(R.id.sv);
        elv = (ExpandableListView) findViewById(R.id.pickup_resend_boxs_lv);
        SearchListUtil.iconExpandChanged(searchView,false,false,false);
        searchView.setSubmitButtonEnabled(true);
        SearchListUtil.hint(searchView);// 因为ui需要，左侧右侧均无初始图标
        SearchListUtil.setQueryHint(searchView,"Enter Phone number");//设置默认提示文字
        //配置监听器
    }


    /*
     * 获取entities并获得对应的pickupResendList的父、子列表。
     * */
    private void findEntities(List<PickUpResendEntity2> resendEntities) {
            groupEntity = new ArrayList<>();
            entityBoxs = new ArrayList<>();
            for(int i = 0;i<resendEntities.size();i++){ // 把每个Entity都给过一遍
                groupEntity.add(resendEntities.get(i).getPhoneNumber());
                List<String> IEntityBoxs = resendEntities.get(i).getBoxInWhere();   // 具体某个号码的快递柜
                entityBoxs.add(IEntityBoxs);
            }

    }


    /*
     * 获取entities并获得对应的pickupResendList的父、子列表。
     * */
    private void findEntitiesSearch(List<PickUpResendEntity2> resendEntities ) {
        for(int i = 0;i<resendEntities.size();i++){ // 把每个Entity都给过一遍
            groupEntity.add(resendEntities.get(i).getPhoneNumber());
            List<String> IEntityBoxs = resendEntities.get(i).getBoxInWhere();   // 具体某个号码的快递柜
            entityBoxs.add(IEntityBoxs);
        }
    }

    /*
    * 需要后台返回模糊搜索得到的PickUpResendEntity对象list
    * */
    private List<PickUpResendEntity2> findByString(String s){
        List<PickUpResendEntity2> resendEntities = new ArrayList<PickUpResendEntity2>();
        Toast.makeText(MainActivity.this, "需要后台提供关于"+s+"的模糊搜索的接口", Toast.LENGTH_SHORT).show();
        return resendEntities;
    }

    /*
    * 测试的本地模糊搜索
    * */
    private List<PickUpResendEntity2> findByStringMock(String s){
        List<PickUpResendEntity2> resendEntities = PickUpResendUtil.init();
        List<PickUpResendEntity2> resultEntities = new ArrayList<PickUpResendEntity2>();// 接受符合条件的PickUpResendEntity2对象
        if (!s.isEmpty()){
            for (int i = 0;i<resendEntities.size();i++){    // 遍历本地模拟的数据库，把符合条件的做成一个集合返回
                if (resendEntities.get(i).getPhoneNumber().startsWith(s)) {resultEntities.add(resendEntities.get(i));}
            }
            if (resultEntities.size() == 0){Toast.makeText(MainActivity.this, "需要后台提供关于"+s+"的模糊搜索的接口", Toast.LENGTH_SHORT).show();}
            else {findEntitiesSearch(resultEntities);}
        }

        return resultEntities;
    }

}