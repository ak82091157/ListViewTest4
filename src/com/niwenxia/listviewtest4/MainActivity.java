package com.niwenxia.listviewtest4;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {
	//定义一个动态数组和listView
	private ListView lv;
	ArrayList<HashMap<String,Object>>listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        
        //找到listView  得到一个MyAdapter对象   吧对象绑到LV上去  再为LV添加点击事件
        lv=(ListView)findViewById(R.id.lv);
        MyAdapter mAdapter = new MyAdapter(this);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.v("MyListViewBase","你点击了ListView条目"+position);//在LOGCAT中显示点击信息
			}
		});
    }

    	//构造一个得到为动态数组得到数据的方法，方便使用     在其中实现动态数组数据的添加
    //getDAte是方法，ArrayList<HashMap<String, Object>>这是返回值的类型   联系C语言
    private ArrayList<HashMap<String, Object>> getDate(){
    	ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String,Object>>();
    	//为listItem添加数据
    	for(int i=0;i<30;i++){
    		//者个map是不是用作一个搬运工，为每个View添加数据？
    		HashMap<String, Object> map=new HashMap<String, Object>();
    		map.put("ItemTitle", "这是第"+(i+1)+"行");
    		map.put("ItemText", "这是第"+(i+1)+"行");
    		listItem.add(map);//把map的到的数据添加到要返回的视图的载体上去
    	}
    	return listItem;
    }
    	//新建一个类继承BaseAdpater,实现视图与数据的绑定   并在其中用layoutInfalter对象来导入布局
    private class MyAdapter extends BaseAdapter{
    	private LayoutInflater myInflater;
    	//什么作用？
    	public MyAdapter(Context context){
    		this.myInflater=LayoutInflater.from(context);
    	}

			@Override
			public int getCount() {
				return getDate().size();//返回数组的长度
			}

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public long getItemId(int position) {
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder holder;
				Log.v("MyListViewBase", "getView"+position+" "+convertView);
				if(convertView==null){
					convertView =myInflater.inflate(R.layout.activity_main, null);
					holder = new ViewHolder();
					holder.title=(TextView)convertView.findViewById(R.id.tv1);
					holder.text=(TextView)convertView.findViewById(R.id.tv2);
					holder.bt=(Button)convertView.findViewById(R.id.button);
					convertView.setTag(holder);//绑定ViewHolder对象
				}
				else{
					holder=(ViewHolder)convertView.getTag();//取出viewHolder对象
				}
				holder.title.setText(getDate().get(position).get("ItemTitle").toString());
				holder.text.setText(getDate().get(position).get("ItemText").toString());
				holder.bt.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						Log.v("MyListViewBase", "你点击了按钮");
					}
					
				});
				return convertView;
			}
    	
    }
    public final class ViewHolder{
    	public TextView title;
    	public TextView text;
    	public Button bt;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
