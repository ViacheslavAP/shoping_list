package ru.perelyginva.shopinglist.domain

import ru.perelyginva.shopinglist.domain.repository.ShopListRepository

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun deleteShopItem(shopItem: ShopItem){
         shopListRepository.deleteShopItem(shopItem)
    }
}