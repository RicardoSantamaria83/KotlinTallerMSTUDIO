package com.mobilestudio.mypetshop.detailproduct

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mobilestudio.mypetshop.databinding.ActivityDetailProductBinding

class DetailProductActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailProductBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailProductViewModel by viewModels()

    var name = ""
    var price = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent?.extras?.let { bundle ->
            name = bundle.getString(KEY_NAME, "")
            price = bundle.getString(KEY_PRICE, "")
            binding.txtProductName.text = name
            binding.txtProductPrice.text = "$price"
        }

        setUpUI()
        setUpActions()
        setUpObservables()

    }

    private fun setUpObservables() {
        viewModel.quantity.observe(this) { quantity ->
            Log.e("Quantity", "---> $quantity")
            quantity?.let {
                setQuantityProduct(it)
            }
        }
    }

    private fun setUpActions() {
        binding.btnAddQuantity.setOnClickListener {
            viewModel.addQuantity()
        }

        binding.btnMinusQuantity.setOnClickListener {
            viewModel.minusQuantity()
        }

        binding.btnProductAddCart.setOnClickListener {
            viewModel.addProductToCart(name, price)
        }
    }

    private fun setUpUI() {

        setQuantityProduct(viewModel.quantity.value ?: 1)

        val toolbar = binding.toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setQuantityProduct(q: Int) {
        binding.txtQuantity.text = "$q"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }

    companion object {
        val KEY_NAME = "KEY_NAME"
        val KEY_PRICE = "KEY_PRICE"
    }

}