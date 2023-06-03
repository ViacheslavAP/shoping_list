package ru.perelyginva.shopinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.perelyginva.shopinglist.R
import ru.perelyginva.shopinglist.databinding.ShopItemFragmentBinding
import ru.perelyginva.shopinglist.domain.ShopItem
import ru.perelyginva.shopinglist.presentation.viewmodel.ShopItemViewModel

class ShopItemFragment: Fragment() {

    private lateinit var binding: ShopItemFragmentBinding
    private lateinit var viewModel: ShopItemViewModel

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShopItemFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        purseParam()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        addTextChangeListeners()
        launchMode()
        observeViewModel()
    }


    private fun observeViewModel(){
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it == true) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            binding.tilCount.error = message
        }

        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it == true) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            binding.tilCount.error = message
        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
           activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun launchMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addTextChangeListeners() {
        with(binding) {

            etName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.resetErrorInputName()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etCount.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    viewModel.resetErrorInputCount()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
        }
    }

    private fun launchEditMode() {
        viewModel.getShopItemId(shopItemId)
        viewModel.shopItem.observe(viewLifecycleOwner) {
            with(binding) {
                etName.setText(it.name)
                etCount.setText(it.count.toString())
            }
        }
        binding.btnSave.setOnClickListener {
            viewModel.editShopItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString()
            )
        }
    }

    private fun launchAddMode() {

        binding.btnSave.setOnClickListener {
            viewModel.addShopItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString()
            )
        }
    }

    private fun purseParam() {
        if (screenMode != MODE_EDIT && screenMode != MODE_ADD){
            throw RuntimeException("Param screen mode is absent")
        }

        if (screenMode == MODE_EDIT && shopItemId == ShopItem.UNDEFINED_ID) {
                throw RuntimeException("Param shop item id is absent")
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }

}