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
	//����һ����̬�����listView
	private ListView lv;
	ArrayList<HashMap<String,Object>>listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        
        //�ҵ�listView  �õ�һ��MyAdapter����   �ɶ����LV��ȥ  ��ΪLV��ӵ���¼�
        lv=(ListView)findViewById(R.id.lv);
        MyAdapter mAdapter = new MyAdapter(this);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.v("MyListViewBase","������ListView��Ŀ"+position);//��LOGCAT����ʾ�����Ϣ
			}
		});
    }

    	//����һ���õ�Ϊ��̬����õ����ݵķ���������ʹ��     ������ʵ�ֶ�̬�������ݵ����
    //getDAte�Ƿ�����ArrayList<HashMap<String, Object>>���Ƿ���ֵ������   ��ϵC����
    private ArrayList<HashMap<String, Object>> getDate(){
    	ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String,Object>>();
    	//ΪlistItem�������
    	for(int i=0;i<30;i++){
    		//�߸�map�ǲ�������һ�����˹���Ϊÿ��View������ݣ�
    		HashMap<String, Object> map=new HashMap<String, Object>();
    		map.put("ItemTitle", "���ǵ�"+(i+1)+"��");
    		map.put("ItemText", "���ǵ�"+(i+1)+"��");
    		listItem.add(map);//��map�ĵ���������ӵ�Ҫ���ص���ͼ��������ȥ
    	}
    	return listItem;
    }
    	//�½�һ����̳�BaseAdpater,ʵ����ͼ�����ݵİ�   ����������layoutInfalter���������벼��
    private class MyAdapter extends BaseAdapter{
    	private LayoutInflater myInflater;
    	//ʲô���ã�
    	public MyAdapter(Context context){
    		this.myInflater=LayoutInflater.from(context);
    	}

			@Override
			public int getCount() {
				return getDate().size();//��������ĳ���
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
					convertView.setTag(holder);//��ViewHolder����
				}
				else{
					holder=(ViewHolder)convertView.getTag();//ȡ��viewHolder����
				}
				holder.title.setText(getDate().get(position).get("ItemTitle").toString());
				holder.text.setText(getDate().get(position).get("ItemText").toString());
				holder.bt.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						Log.v("MyListViewBase", "�����˰�ť");
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
