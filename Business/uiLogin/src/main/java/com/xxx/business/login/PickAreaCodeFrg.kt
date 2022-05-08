package com.xxx.business.login

import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.xxx.common.router.XRouterConfig
import com.xxx.business.login.LetterView.Companion.setOnLettersListViewListener
import com.xxx.business.ui.login.R
import com.xxx.common.base.BaseFragment
import com.xxx.common.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.frg_pick_area.*
import java.util.*

/**
 * 选择分区号界面
 */
@Route(path = XRouterConfig.FRAGMENT_URL_PICK_AREA_CODE)
class PickAreaCodeFrg : BaseFragment<LoginViewModel>(), LetterView.OnLettersListViewListener {

    companion object {
        const val KEY = "chosenCode"
        fun newInstance(chosenCode: String): PickAreaCodeFrg {
            val args = Bundle()
            args.putString(KEY, chosenCode)
            val fragment = PickAreaCodeFrg()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var adapter: CarListAdapter
    private lateinit var mList: List<LettersModel>
    private var resultList: MutableList<LettersModel> = mutableListOf()
    override fun layoutId(): Int {
        return R.layout.frg_pick_area
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        et_search.doOnTextChanged { text, start, before, count ->
            if (text?.trim()?.isNotBlank() == true) {
                searchResult(text)
                adapter.updateData(resultList)
            }
        }
        setOnLettersListViewListener(letters!!, this)
        tv_chosen_code.text = arguments?.get(KEY).toString()
        mList = parsingData()
        //对字母进行排序A-Z #
        Collections.sort(mList, LettersSorting())
        //加载适配器
        adapter = CarListAdapter(mActivity, mList)
        //设置数据
        mListView.adapter = adapter
        mListView.setOnItemClickListener { adapterView, view, pos, _ ->
            //todo,返回上个界面，将数据回传
//            mList[pos]

        }
    }


    private fun searchResult(c: CharSequence) {
        resultList.clear()
        (mList.indices)
            .filter { mList[it].name.contains(c.toString()) }
            .mapTo(resultList) { mList[it] }
    }


    private fun parsingData(): List<LettersModel> {
        val areaJson: String = getJson(mActivity, "areacode.json")
        val jsonToList = Gson().fromJson(
            areaJson,
            Array<AreaCodeModel>::class.java
        )  //JsonUtils.jsonToList(areaJson)

        val listModel: MutableList<LettersModel> = mutableListOf()


        for (item: AreaCodeModel in jsonToList) {
            val model = LettersModel()
            //first letter
            val letter = item.pinyin.substring(0, 1).uppercase()
            model.letter = letter
            model.name = "${item.name}(+${item.tel})"
            listModel.add(model)
        }
        return listModel
    }

    override fun onLettersListener(s: String) {
        //对应的位置
        val position = getFirstPosition(mList, s)
        //移动
        mListView?.setSelection(position)
    }
}
