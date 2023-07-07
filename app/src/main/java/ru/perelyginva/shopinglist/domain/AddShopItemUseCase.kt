package ru.perelyginva.shopinglist.domain

import ru.perelyginva.shopinglist.domain.repository.ShopListRepository

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem){

        shopListRepository.addShopItem(shopItem)
    }
}