package ru.perelyginva.shopinglist.presentation.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ru.perelyginva.shopinglist.data.ShopListRepositoryImpl
import ru.perelyginva.shopinglist.domain.DeleteShopItemUseCase
import ru.perelyginva.shopinglist.domain.EditShopItemUseCase
import ru.perelyginva.shopinglist.domain.GetShopListUseCase
import ru.perelyginva.shopinglist.domain.ShopItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopListUseCase = EditShopItemUseCase(repository)
    private val scope = CoroutineScope(Dispatchers.IO)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        scope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnabledState(shopItem: ShopItem) {
        scope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopListUseCase.editShopItem(newItem)
        }

    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}