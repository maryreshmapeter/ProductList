package com.example.productlist.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.productlist.database.ProductsDatabase
import com.example.productlist.database.model.CartItem
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CartItemsDaoTest : TestCase() {
    private lateinit var db: ProductsDatabase
    private lateinit var dao: CartItemsDao

    @Before
    public override fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ProductsDatabase::class.java,
        ).build()
        dao = db.getCartProductsDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertCartItem() = runBlocking {
        //arrange
        val cartItem = CartItem(
            12345,
            "Samsung Galaxy F22",
            "Samsung's new variant",
            899,
            1,
            "Samsung",
            "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
        )

        //act
        dao.insert(cartItem)

        val itemsList = dao.getAll().first()
        assertThat(itemsList.contains(cartItem)).isTrue()
    }

    @Test
    fun deleteCartItem() = runBlocking {
        //arrange
        val cartItem = CartItem(
            12345,
            "Samsung Galaxy F22",
            "Samsung's new variant",
            899,
            1,
            "Samsung",
            "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
        )

        //act
        dao.delete(cartItem)

        val itemsList = dao.getAll().first()
        assertThat(itemsList.contains(cartItem)).isFalse()
    }

    @Test
    fun getCartItems() =
        runBlocking {
            val cartItem1 = CartItem(
                12345,
                "Samsung Galaxy F22",
                "Samsung's new variant",
                899,
                1,
                "Samsung",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
            )
            val cartItem2 = CartItem(
                123456,
                "Samsung Galaxy F22",
                "Samsung's new variant",
                899,
                1,
                "Samsung",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
            )

            dao.insert(cartItem1)
            dao.insert(cartItem2)

            val itemsList = dao.getAll().first()
            assertThat(itemsList.contains(cartItem1) && itemsList.contains(cartItem2)).isTrue()
        }

}