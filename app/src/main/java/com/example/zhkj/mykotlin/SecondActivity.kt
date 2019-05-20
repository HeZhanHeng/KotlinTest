package com.example.zhkj.mykotlin

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import org.jetbrains.anko.toast

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        获得请求参数的包裹
        var bundle=intent.extras
        var name=bundle.get("name")
        var age=bundle.get("age")
        tv_data.text="姓名：${name},年龄：${age}"
        var bundle1=intent.extras.getParcelable<MainActivity.MessageInfo>("message")
        if (bundle1!=null){
            var time=bundle1.time
            var title=bundle1.title
            tv_data1.text="$title$time"
        }
        btn_back.setOnClickListener {
           val intent= Intent()
            intent.putExtra("phone","18437963029")
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}
