package ru.perelyginva.shopinglist.presentation.viewmodel


import androidx.lifecycle.ViewModel
import ru.perelyginva.shopinglist.data.ShopListRepositoryImpl
import ru.perelyginva.shopinglist.domain.DeleteShopItemUseCase
import ru.perelyginva.shopinglist.domain.EditShopItemUseCase
import ru.perelyginva.shopinglist.domain.GetShopListUseCase
import ru.perelyginva.shopinglist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopListUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnabledState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopListUseCase.editShopItem(newItem)

    }

}