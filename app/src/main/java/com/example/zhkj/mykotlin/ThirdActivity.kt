package com.example.zhkj.mykotlin

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {
private val yearArray= arrayListOf<String>("鼠年","牛年","虎年","兔年","龙年","蛇年","马年","羊年","猴年","鸡年","狗年","猪年")
    private val adapter:RecyclerLinearAdapter?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = RecyclerLinearAdapter(this, yearArray)
        yearArray.getData()

        tl_head.title="第三页标题"
        tl_head.setTitleTextColor(Color.RED)
//        tl_head.setLogo(R.drawable.ic_launcher_background)
//        tl_head.subtitle="副标题"
//        tl_head.setBackgroundColor(Color.GREEN)
        setSupportActionBar(tl_head)
        tl_head.setNavigationIcon(R.drawable.ic_launcher_background)
//        这个方法必须放在setSupportActionBar之后
        tl_head.setNavigationOnClickListener{
            finish()
        }
        btn_snackbar.setOnClickListener {
            Snackbar.make(cl_main,"这是一个提示条",Toast.LENGTH_SHORT).show()
        }
    }
    fun ArrayList<String>.getData(){
        for (i in 1..50){
            this.add("这是第${i}条数据")
        }
    }

    //    recyclerview适配器
    class RecyclerLinearAdapter(private val context: Context, private val mlist:ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        val inflater: LayoutInflater = LayoutInflater.from(context)
//    点击事件自己写

        //    绑定每项的视图持有者
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val viewholder:ViewHolder=holder as ViewHolder
            viewholder.tv.text=mlist[position]
            viewholder.tv.setOnClickListener {
                Toast.makeText(context,"您点击了"+position,Toast.LENGTH_SHORT).show()
            }
        }
        //获得列表项的数目
        override fun getItemCount(): Int {
            return mlist.size
        }
        //创建整个布局的视图持有者
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view: View =inflater.inflate(R.layout.activity_main_item,parent,false)
            return ViewHolder(view)
        }
        class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
            val tv: TextView =itemView.findViewById(R.id.tv) as TextView
        }
    }

}
