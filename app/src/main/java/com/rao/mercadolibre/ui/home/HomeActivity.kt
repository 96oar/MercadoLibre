package com.rao.mercadolibre.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rao.mercadolibre.R
import com.rao.mercadolibre.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity() {

    private lateinit var searchViewModel: HomeViewModel
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initRecyclerView()

        searchViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        searchViewModel.productList.observe(this, Observer {
            adapter.setDataList(it.results)
            adapter.notifyDataSetChanged()
        })

        search_product?.setOnKeyListener((View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                if (searchViewModel.productList.value == null) {
                    searchViewModel.searchProduct(search_product.text.toString())
                    val inputMethod  = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethod.hideSoftInputFromWindow(v.applicationWindowToken,0)
                }
                return@OnKeyListener true
            }
            false
        }))
    }

    //region private functions

    private fun initRecyclerView() {
        adapter = ProductAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    //endregion

}
