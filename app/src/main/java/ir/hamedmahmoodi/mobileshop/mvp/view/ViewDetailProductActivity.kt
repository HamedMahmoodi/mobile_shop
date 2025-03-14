package ir.hamedmahmoodi.mobileshop.mvp.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.hamedmahmoodi.mobileshop.R
import ir.hamedmahmoodi.mobileshop.adapter.recycler.CommentsRecyclerAdapter
import ir.hamedmahmoodi.mobileshop.adapter.recycler.SpecificationsRecyclerAdapter
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.androidWrapper.DeviceInfo
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.SendRequests
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.ProductDetailModel
import ir.hamedmahmoodi.mobileshop.data.remote.ext.OthersUtilities
import ir.hamedmahmoodi.mobileshop.databinding.ActivityDetailProductBinding
import ir.hamedmahmoodi.mobileshop.databinding.CustomDialogSellBinding
import ir.hamedmahmoodi.mobileshop.mvp.ext.ToastUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelDetailProductActivity

class ViewDetailProductActivity : FrameLayout {

    private lateinit var actUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        activityUtils: ActivityUtils,
    ) : super(contextInstance) {
        actUtils = activityUtils
    }

    val binding = ActivityDetailProductBinding.inflate(LayoutInflater.from(context))

    @SuppressLint("SetTextI18n")
    fun setData(detail: ProductDetailModel, sendRequests: SendRequests) {

        binding.viewPagerSlider.layoutDirection = View.LAYOUT_DIRECTION_RTL
        actUtils.setViewPagerFragment(binding.viewPagerSlider, detail.gallery)

        binding.recyclerSpecifications.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerSpecifications.adapter =
            SpecificationsRecyclerAdapter(detail.specifications)

        binding.txtTitle.text = detail.title
        binding.txtDesc.text = detail.content
        binding.txtRate.text = detail.rate.rate.toString()

        if (detail.bookmark)
            binding.imgFavorite.setImageResource(R.drawable.ic_actived_favorite)
        else
            binding.imgFavorite.setImageResource(R.drawable.ic_favorite)

        if (detail.commentCount > 0)
            binding.commentCount.visibility = View.VISIBLE
        else
            binding.commentCount.visibility = View.INVISIBLE
        binding.txtCommentCount.text = detail.commentCount.toString()

        var bookmark = detail.bookmark

        binding.imgFavorite.setOnClickListener {

            var action = ""

            if (bookmark) {
                action = ModelDetailProductActivity.ACTION_UN_FAVORITE
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite)
                bookmark = false
            } else {
                action = ModelDetailProductActivity.ACTION_FAVORITE
                binding.imgFavorite.setImageResource(R.drawable.ic_actived_favorite)
                bookmark = true
            }

            sendRequests.startSendFavorite(
                DeviceInfo.getDeviceID(context),
                DeviceInfo.getPublicKey(context),
                DeviceInfo.getApi(context),
                action
            )

        }

        binding.btnSendComment.getView().setOnClickListener {

            val text = binding.edtComment.text.toString()
            val rate = binding.ratingComment.rating

            if (text.isEmpty() || (text.length < 2)) {
                ToastUtils.toast(context, "نظر شما نمیتواند کمتر از 2 حرف باشد")
                return@setOnClickListener
            }

            binding.btnSendComment.enableProgress()

            sendRequests.sendComment(
                DeviceInfo.getDeviceID(context),
                DeviceInfo.getPublicKey(context),
                DeviceInfo.getApi(context),
                text,
                rate,
                detail.id
            )

        }

        if (detail.comments != null) {

            binding.recyclerComments.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            binding.recyclerComments.adapter =
                CommentsRecyclerAdapter(detail.comments)

        }

        binding.txtMainPrice.text = OthersUtilities.changePrice(detail.price)

        if (detail.hasDiscount) {

            binding.txtMainPrice.paintFlags =
                binding.txtMainPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            binding.txtMainPrice.setTextColor(Color.GRAY)

            binding.off.visibility = View.VISIBLE

            binding.txtOffPrice.text = OthersUtilities.changePrice(detail.salePrice)
            binding.txtOffCount.text = detail.discountPercent110n

        } else
            binding.off.visibility = View.GONE

        binding.btnShop.getView().setOnClickListener {

            binding.btnShop.enableProgress()

            val view = CustomDialogSellBinding.inflate(LayoutInflater.from(context))
            val dialog = Dialog(context)

            view.txtPriceBased.text = OthersUtilities.changePrice(detail.price)

            setPriceWithCount(view, detail)

            if (detail.hasDiscount) {

                view.offViews.visibility = View.VISIBLE
                /*   view.txtPriceOff.text = OthersUtilities.changePrice(detail.salePrice)*/
                view.txtOff18n.text = "(${detail.discountPercent110n})"

                view.txtPriceTotal.paintFlags =
                    view.txtPriceTotal.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                view.txtPriceTotal.setTextColor(Color.GRAY)

            } else {
                view.offViews.visibility = View.GONE
                view.txtPriceTotalOff.visibility = View.GONE
            }

            dialog.setContentView(view.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            view.btnContinueSell.getView().setOnClickListener {

            }

            /*     view.rdbSellNormal.setOnClickListener {

                     view.txtCount.text = "1"
                     setPriceWithCount(view, detail)

                     view.rdbSellNormal.isChecked = true
                     view.rdbSellBig.isChecked = false

                     view.viewNormal.setBackgroundResource(R.drawable.back_radio_sell_white)
                     view.viewBig.setBackgroundResource(R.drawable.back_radio_sell_transparent)

                 }

                 view.rdbSellBig.setOnClickListener {

                     view.txtCount.text = "10"
                     setPriceWithCount(view, detail)

                     view.rdbSellNormal.isChecked = false
                     view.rdbSellBig.isChecked = true

                     view.viewNormal.setBackgroundResource(R.drawable.back_radio_sell_transparent)
                     view.viewBig.setBackgroundResource(R.drawable.back_radio_sell_white)

                 }*/

            view.viewPlus.setOnClickListener {

                var count = view.txtCount.text.toString().toInt()
                if (count < 10)
                    count++
                /* if (view.rdbSellNormal.isChecked)
                     if (count < 10)
                         count++*/

                /* if (view.rdbSellBig.isChecked)
                     if (count < 100)
                         count += 5*/

                view.txtCount.text = count.toString()
                setPriceWithCount(view, detail)

            }

            view.viewMin.setOnClickListener {

                var count = view.txtCount.text.toString().toInt()
                if (count > 1)
                    count--
               /* if (view.rdbSellNormal.isChecked)
                    if (count > 1)
                        count--

                if (view.rdbSellBig.isChecked)
                    if (count > 10)
                        count -= 5*/

                view.txtCount.text = count.toString()
                setPriceWithCount(view, detail)

            }

            dialog.setOnCancelListener {
                binding.btnShop.disableProgress()
            }

        }

        //TODO بایستی بخش محصولات مشابه رو ایجاد کنی

    }

    private fun setPriceWithCount(view: CustomDialogSellBinding, detail: ProductDetailModel) {

        val count = view.txtCount.text.toString().toInt()
        var salePrice = 0

        if (detail.bulkPrice.isNotEmpty()) {
            detail.bulkPrice.forEach {
                if (it.amount > count) {
                    salePrice = it.salePrice
                    return@forEach
                }
            }
        }

        val priceTo = view.txtCount.text.toString().toInt() * detail.price
        view.txtPriceTotal.text = OthersUtilities.changePrice(priceTo)
        view.offViews.visibility = View.VISIBLE
        val priceTotalOff = view.txtCount.text.toString().toInt() * salePrice
        view.txtPriceTotalOff.text = OthersUtilities.changePrice(priceTotalOff)

    }

    fun disableButtonProgress() {
        binding.btnSendComment.disableProgress()
    }

    fun startGetData() {
        binding.allViews.visibility = View.INVISIBLE
        binding.bottomViews.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun onBack() {
        binding.customAppBar.getBackIcon().setOnClickListener {
            actUtils.finished()
        }
    }

    fun endGetData() {
        binding.allViews.visibility = View.VISIBLE
        binding.bottomViews.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}