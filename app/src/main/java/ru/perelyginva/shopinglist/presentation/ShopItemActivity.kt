package ru.perelyginva.shopinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.perelyginva.shopinglist.R
import ru.perelyginva.shopinglist.databinding.ActivityShopItemBinding

class ShopItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityShopItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }



}