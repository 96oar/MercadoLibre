package com.rao.mercadolibre.ui.home

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rao.mercadolibre.R
import com.rao.mercadolibre.adapter.ProductAdapter
import com.rao.mercadolibre.common.Connection
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity() {

    private lateinit var searchViewModel: HomeViewModel
    private lateinit var adapter: ProductAdapter
    private lateinit var conManager : ConnectivityManager
    private var connection:Connection = Connection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initRecyclerView()

        searchViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        searchViewModel.productList.observe(this, Observer {
            adapter.setDataList(it.results)
            adapter.notifyDataSetChanged()
        })

        searchViewModel.message.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        })

        search_product?.setOnKeyListener((View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {

                conManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                v
                if(connection.isOnline(conManager)) {
                    searchViewModel.searchProduct(search_product.text.toString())
                }else{
                    Toast.makeText(this,R.string.no_connection,Toast.LENGTH_LONG).show()
                }
                    val inputMethod  = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethod.hideSoftInputFromWindow(v.applicationWindowToken,0)
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
