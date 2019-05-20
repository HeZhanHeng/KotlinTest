package com.example.zhkj.mykotlin

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CpuUsageInfo
import android.os.Parcelable
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*;//加上此引入，不需要findviewbyId,对一些方法进行封装
import org.jetbrains.anko.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class MainActivity : AppCompatActivity(),View.OnClickListener,CompoundButton.OnCheckedChangeListener {
   val requestMa:Int=123
   private var itemlist=ArrayList<String>()
    private var adapter:PlanetListAdapter?=null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==requestMa&&resultCode== Activity.RESULT_OK&&data!=null)
        toast("${data.getStringExtra("phone")}")
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//      if (isChecked) toast("您勾选了复选框")
//        else toast("您未勾选复选框")
        toast("您${if (isChecked)"勾选了" else "没勾选"}复选框")
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn11->toast("您点击了${(v as Button).text}")
            R.id.btn22->toast("您点击了${(v as Button).text}")
            R.id.btn33->toast("您点击了${(v as Button).text}")
            R.id.rb_male-> toast("您选择了男性")
            R.id.rb_female-> toast("您选择了女性")
            else-> toast("没有了")
        }
    }

    var i:Double=666.0;//声明数据类型
    var intArray:IntArray= intArrayOf(1,2,3)
    var string_array:Array<String> =arrayOf("how","are","you")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn11.setOnClickListener(this)
        btn22.setOnClickListener(this)
        btn33.setOnClickListener(this)
        checkbox.setOnCheckedChangeListener(this)
        rb_male.setOnClickListener(this)
        rb_female.setOnClickListener(this)
        tv.text="我是第一个程序";//设置文本
//        字符串截取
        val org:String=tv.text.toString();
        var org_trim:String=org;
        if (org_trim.indexOf('个')>0){
            org_trim=org_trim.substring(0,org_trim.indexOf('个'))
            toast(org_trim);
        }

        btn.setOnClickListener { tv.text="您点击了一下哦" }//点击事件
        btn.setOnClickListener { toast("您点击了一下哦") }
        btn2.setOnLongClickListener { toast(i.toInt().toString());true }
        text2.text=intArray[0].toString()+string_array[2];
        btn3.setOnClickListener {
            var i:Int=0;
            var string:String="";
            while (i<string_array.size){
                string=string+string_array[i]+","
                i++;
            }
            text2.text=string;
            toast("字符串的长度：${string.length}");
//            toast("美元的金额为：${'$'}${string.length}");
        }
//mutableSet可变集合，Set集合
//        var可改变元素，val不可改变
        val goodsMutSet:Set<String> = setOf("iphoneX","FindX","P30pro","Note7 plus");
        btn_set.setOnClickListener {
            var desc="";
//            第一种：使用for in 来遍历数组
            for (item in goodsMutSet){
                desc="${desc}名称：${item}\n";
//                desc="名称：${item}\n";//此打印只会打印最后一个{名称：Note7 plus}
            }
            longToast("手机畅销榜包含以下几款手机：\n$desc")
//            第二种：使用迭代器来遍历
            var des="";
            var iterator=goodsMutSet.iterator();
            while (iterator.hasNext()){
                val item=iterator.next();
            des="${des}名称：${item}\n";
            }
            Log.d("手机畅销榜包含以下几款手机=","\n"+des);
//            第三种：精练到极致，forEach
//          forEach内部使用it指代每条记录
            var dess="";
            goodsMutSet.forEach {
                dess="${dess}名称是：${it}\n";
            }
            Log.d("手机畅销榜包含以下几款手机==\n",dess);
        }
        btn_list.setOnClickListener {
//       元素下标循环遍历
            val goodsMutList:List<String> = listOf("iphoneX","FindX","P30pro","Note7 plus")
            var desc="";
//            indices是队列的下标数组，如果队列大小为10，下标数组的取值就为0——9
            for (i in goodsMutList.indices){
            val  item=goodsMutList[i];
                desc="${desc}名称是：${item}\n"
            }
            Log.d("手机畅销榜包含以下${goodsMutList.size}款手机",desc);
        }
//        升序和降序(仅仅是对列表的排序)
        var sortAsc=false;
        btn_sort_list.setOnClickListener {
            val goodsMutList:List<String> = listOf("IphoneX","FindX","P30pro","Note7 plus")
            if (sortAsc){
//sortby表示升序，后面跟的是排序条件
                goodsMutList.sortedBy { it.length }
            }else{
                goodsMutList.sortedByDescending { it.length }
            }
            var desc="";
            for (item in goodsMutList){
                desc="${desc}列表是：${item}\n"
            }
            tv_list.text="手机排行榜已按照${if (sortAsc)"升序" else "降序"}重新排列\n$desc";
            sortAsc=!sortAsc;
        }
//        映射map/MultableMap
//        to方式初始化映射
        val fruitMap:Map<String,String> = mapOf("苹果" to "apple","香蕉" to "banana","梨" to "pear","橙子" to "orange","葡萄" to "grape");
//       Pair方式映射
        val fruitMap1:Map<String,String> = mapOf(Pair("苹果","apple"), Pair("香蕉","banana"),Pair("梨","pear"), Pair("橙子","orange"), Pair("葡萄","grape"));
        btn_map.setOnClickListener {
            var desc="";
            fruitMap.forEach {
                 desc="${desc}获取的数据：${it.key},${it.value}\n"
            }
            Log.d("测试一下==",desc);
            var desc2="";
            for (item in fruitMap){
                desc2="${desc2}${item.key},${item.value};"
                tv_list.text=desc2;
            }
        }
//        单个分支
        var is_change:Boolean=true;
        btn_judge.setOnClickListener {
            tv_judge.text=if (is_change)"凉风有信-->讽" else "秋月无边-->二"
                is_change=!is_change
        }
//        多个分支switch/case替换为when/else
        var count:Int=0
        btn_judge2.setOnClickListener {
//                when(count){
//                    0 ->tv_judge.text="凉风有信-->讽";
//                    1 ->tv_judge.text="秋月无边-->二";
//                    else ->tv_judge.text="这是一句好诗"
//                }
//            count=(count+1)%3;
            tv_judge.text=when(count){
                0 ->"凉风有信-->讽";
                1 ->"秋月无边-->二";
                else ->"这是一句好诗"
            }
            count=(count+1)%3;
        }
//        数据类型进行判断
        var countType:Number;
        var cout:Int=0
        btn_judge3.setOnClickListener {
            count=(cout+1)%3
            countType=when(count){
                0->count.toLong()
                1->count.toDouble()
                else->count.toFloat();
            }
            tv_judge.text=when(countType){
                is Long->"此恨绵绵无绝期"
                is Float->"树上的鸟儿成双对"
                else->"门泊东吴万里船"
            }
        }
//        遍历循环结果
        val poemArray:Array<String> = arrayOf("朝辞白帝彩云间","千里江陵一日还","两岸猿声啼不住","轻舟已过万重山")
        btn_all.setOnClickListener {
        var poem:String="";
//            for (item in poemArray){
//                    poem="${poem}${item},\n"
//            }
//            tv_show_all.text=poem;
            for (i in poemArray.indices){
                if (i%2==0){
                    poem="${poem}${poemArray.get(i)},\n"
                }else{
                    poem="${poem}${poemArray[i]}.\n"
                }
            }
            tv_show_all.text=poem;
        }
//        跳出循环
        val poemArray1:Array<String?> = arrayOf("朝辞白帝彩云间",null,"千里江陵一日还","","两岸猿声啼不住","  ","轻舟已过万重山","送孟浩然之广陵")
        var poem1:String="";
        var pos1:Int=-1;
        var count1:Int=0;
        btn_continue.setOnClickListener {
            while (pos1<=poemArray1.size){
                pos1++
//            若发现该行是空串或者空格串，则忽略此行
                if (poemArray1[pos1].isNullOrBlank())
                    continue
                if (count1%2==0){
                    poem1="$poem1${poemArray1[pos1]},\n"
                }else{
                    poem1="${poem1}${poemArray1[pos1]}.\n"
                }
                count1++
//            若合法行执行到4，则直接跳出
                if (count1==4){
                    break
                }
            }
            tv_show_all.text=poem1;
        }
    var strNull:String="";
        if (strNull.isNullOrEmpty()){
            Log.d("测试是否为空","true")
        }else{
            Log.d("测试是否为空","false")
        }
        var str1:String="安卓"
        var str2:String="按桌"
        if (str1==str2){
            Log.d("String类型是否相等","true")
        }else{
            Log.d("String类型是否相等","false")
        }
//        函数调用
        btn_fun.setOnClickListener {
//            postData("McDog","男",25)
            tv_show_fun.text=getFourBigDefault("古代四大发明")
        }
//        对函数进行修改
        var state:Boolean=true;
        btn_refun.setOnClickListener {
            if (state)
            tv_show_fun.text=getFourBigDefault("四大发明",second = "活字印刷术")
            else{
                tv_show_fun.text=getFourBigDefault("七大发明","造纸术","印刷术","火药","指南针",arrayOf("国画","中医","武术"))
            }
            state=!state;
        }
//        泛型函数
        var click:Int=0
        btn_Tfun.setOnClickListener {
            when(click){
                0->tv_show_fun.text=appendString("古代四大发明","造纸术","印刷术","火药","指南针")
                1->tv_show_fun.text=appendString<Int>("10以内的素数",2,3,5,7)
                else->tv_show_fun.text=appendString<Double>("烧钱的日子",5.20,6.18,11.11,12.12)
            }
            click++
        }
//        简化函数
        btn_simplefun.setOnClickListener {
            toast("待开发。。。。")
        }
//        =================基础控件练习====================》
//        跑马灯文本
        tv_marque.text="今天是周六，下午将进行国际陆港第二节篮球锦标赛，希望参赛者能准时参加此次活动，谢谢大家的支持。2019/05/18"
        tv_marque.setTextColor(Color.BLACK)
        tv_marque.setBackgroundColor(Color.WHITE)
        tv_marque.ellipsize=TextUtils.TruncateAt.MARQUEE//从左向右滚动的跑马灯
        tv_marque.setSingleLine(true)
       tv_marque.isFocusable=true
        tv_marque.isFocusableInTouchMode=true
//        页面跳转传递参数，一种是to,一种是Pair
        btn_jump.setOnClickListener{
            startActivityForResult<SecondActivity>(requestMa,
                    "name" to "HZH",
                    "age" to "25"
            )
        }
//        传序列化数据
        val request=MessageInfo("现在的时间","14:58:00")
        btn_jump_Parcelable.setOnClickListener {
            startActivity<SecondActivity>(
            "message" to  request
            )
        }
//        调用系统弹窗
        btn_dialog.setOnClickListener {
//            var builder=AlertDialog.Builder(this);
//            builder.setIcon(R.mipmap.ic_launcher);
//            builder.setTitle("温馨提示")
//            builder.setMessage("您真的要卸载我么")
//            builder.setPositiveButton("sure"){
//                dialog, which ->
//            }
//            builder.setNegativeButton("cancel"){
//                dialog, which ->
//            }
          alert("您真的要卸载我么?","温馨提示") {
              positiveButton("残忍卸载"){
              }
              negativeButton("稍安勿躁，再等等"){
              }
              icon(R.mipmap.ic_launcher)
          }.show();
        }
//下拉列表Spinner
    initSpinner();
        val part= listOf("生产部","技术部","质量监督部","销售部","后勤部")
        tv_spinner.text=part[0]
        tv_spinner.setOnClickListener {
            selector("请选择您所在的部门",part){
                i-> tv_spinner.text=part[i]
                toast("您选择的部门是${tv_spinner.text}")
            }
        }
//        列表视图
       itemlist.getData()
        adapter= PlanetListAdapter(itemlist,this)
        listview.adapter=adapter
        listview.setOnItemClickListener { parent, view, position, id ->
            toast("您点击了$position")
        }
    }
    fun ArrayList<String>.getData(){
        for (i in 1..20){
            this.add("这是第${i}条数据")
        }
    }
    //        序列化数据
    @Parcelize
data class MessageInfo(val title:String,val time:String):Parcelable{
}

    class PlanetListAdapter(val itemlist:ArrayList<String>,var context: Context):BaseAdapter(){
        override fun getCount(): Int {
            return itemlist.size;
        }

        override fun getItemId(position: Int): Long {
            return position.toLong();
        }

        override fun getItem(position: Int): Any {
        return  itemlist[position]
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
                var view=convertView;
                val holder:Viewholder
            if (convertView==null){
                view=View.inflate(context,R.layout.activity_main_item,null)
                holder=Viewholder(view)
                view.tag=holder;
            }else{
                view=convertView
                holder=view.tag as Viewholder;
            }
            holder.str.text=itemlist.get(position)
            return view
        }
class Viewholder(val viewItem:View){
    var str:TextView=viewItem.findViewById(R.id.tv) as TextView
}
    }
private fun initSpinner(){
    val spinAdapter=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,starArray);
    spinAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
//    android 8.0之后findviewbyid后添加View才可进行类型转换操作
    val spinner=findViewById<View>(R.id.sp) as Spinner
    spinner.prompt="请选择行星"
    spinner.adapter=spinAdapter;
    spinner.setSelection(0)
    sp.onItemSelectedListener=MySelectedListener()
}

private val starArray= arrayOf("水星","金星","木星","火星","地球","天王星","海王星","冥王星")
    internal  inner class MySelectedListener:AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            toast("您选择的行星是：${starArray[position]}")
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {


        }
    }
}




//    fun postData(name:String,male:String,age:Int){
//        tv_show_fun.text="姓名:${name},性别:${male},年龄:${age}"
//    }
//    vararg可变参数
    fun getFourBigDefault(general:String,first:String="造纸术",second:String="印刷术",third:String="火药",fourth:String="指南针",vararg otherArray2: Array<String>):String{
        var answer:String="$general:$first,$second,$third,$fourth";
//    循环取出数组
    for (array in otherArray2){
//        没有下面二次遍历，出来是个数组
        for (item in array){
            answer="$answer,$item"
        }
    }
        return  answer;
}
//    泛型函数
    fun <T>appendString(tag:String,vararg otherInfo:T?):String{
    var str:String="$tag"
//    遍历可变参数中的泛型变量，将其转换为字符串拼接到一起
    for (item in otherInfo){
        str="$str${item.toString()},"
    }
    return str;
}

