package com.xxx.business.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.xxx.business.ui.login.R



class CarListAdapter(private val context: Context, private var data: List<LettersModel>) : BaseAdapter() {

    private var vHolder: ViewHolder? = null

    fun updateData(data: List<LettersModel>){
        this.data = data
        notifyDataSetChanged()
    }
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {
        var convertView = view
        if (convertView == null) {
            vHolder = ViewHolder()
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null)
            vHolder?.tvLetters = convertView.findViewById(R.id.tvLetters)
            vHolder?.tvName = convertView.findViewById(R.id.tvName)
            convertView.tag = vHolder
        } else {
            vHolder = convertView.tag as ViewHolder?
        }
        val model = data[position]
//        val firstPos = getFirstPosition(data, model.letter[0])
//        if (firstPos == position) {
//            vHolder?.tvLetters?.visibility = View.VISIBLE
//            vHolder?.tvLetters?.text = model.letter
//        } else {
//            vHolder?.tvLetters?.visibility = View.GONE
//        }
        vHolder?.tvName?.text = model.name
        return convertView
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    inner class ViewHolder {
        var tvLetters: TextView? = null
        var tvName: TextView? = null
    }

}