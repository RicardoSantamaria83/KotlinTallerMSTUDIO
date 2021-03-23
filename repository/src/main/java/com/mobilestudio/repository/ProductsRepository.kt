package com.mobilestudio.repository

import android.app.Application
import android.util.Log
import com.mobilestudio.localstorage.PetChopDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.mobilestudio.localstorage.entities.Product as ProductDB

class ProductsRepository(application: Application) {

    val db by lazy {
        PetChopDatabase.getInstance(application)
    }

    suspend fun getAllProductsInCart(): List<Product>? = withContext(Dispatchers.IO) {
        Log.i("REPO", "START CALL")
        val listProducts = db?.productDao()?.getAllProducts()?.map {
            it.toProduct()
        }
        return@withContext listProducts
    }

    private fun ProductDB.toProduct(): Product {
        return Product(name, price)
    }

    suspend fun addProductToCart(name: String, price: String, quantity: Int = 1) = withContext(Dispatchers.IO) {
        db?.productDao()?.addProduct(
            ProductDB(0, name, 100, "", "", quantity, "", false)
        )
    }

}

data class Product(val name: String, val price: Int)