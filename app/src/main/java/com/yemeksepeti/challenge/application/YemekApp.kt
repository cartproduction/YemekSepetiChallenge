package com.yemeksepeti.challenge.application

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.yemeksepeti.challenge.helpers.CalendarHelper
import com.yemeksepeti.challenge.R
import java.text.SimpleDateFormat
import java.util.*

class YemekApp : Application() {

    companion object {

        val applicationID = "30"
        var TOKEN: String? = null
        val BASE_URL = "http://baseUrl.com"
        val LOGINTEST = "Mobil/LoginTest?"
        val LOGIN = "Mobil/Login"
        val SAVE_RANDEVU = "Mobil/SaveRandevu"
        val SAVE_KAM = "Mobil/PostKampanyaBasvuru"
        val POST = "Mobil/PostBildirimGeriDonus"
        val NOTIFICATION = "Mobil/GetBildirim"
        val NOTIFICATIONS = "Mobil/ListBildirim"
        val CONFIGURATIONS = "Mobil/GetKonfigurasyon"
        val CONSTANTS = "Mobil/GetSabit"
        val DATE = "Mobil/GetRandevu"
        val DATES = "Mobil/ListRandevu"
        val CAMPAIGNS = "Mobil/ListKampanya"
        val CAMPAIGN = "Mobil/GetKampanyaDetay"
        val KONUM = "Mobil/ListKonumGecmis"
        val CAR = "Mobil/GetArac"
        val PROFILE = "Mobil/GetKullaniciProfil"
        val SYMBOLS = "Mobil/ListSembol"

        /*var siparisler: ArrayList<ProductItem> = object : ArrayList<ProductItem>(){
            init {
                add(ProductItem("3 Adet Ürün", "50.000 TL", "Çek", R.mipmap.ordericon, R.mipmap.ic_launcher, R.mipmap.ic_launcher))
                add(ProductItem("2 Adet Ürün", "30.000 TL", "Çek", R.mipmap.ordergray, R.mipmap.ic_launcher, R.mipmap.ic_launcher))

            }
        }*/

        fun showDatePicker(viewtext: TextView, context: Context) {
            // DatePicker

            viewtext.text = SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())

            var cal = Calendar.getInstance()

            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                viewtext.text = sdf.format(cal.time)
            }

            viewtext.setOnClickListener {

                val dialog = DatePickerDialog(context, dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH))
                dialog.datePicker.maxDate = CalendarHelper.getCurrentDateInMills()
                dialog.show()
            }
        }


        fun addFragment(fragmentManager: FragmentManager, fragment: Fragment, addToBackStack: Boolean, tag: String) {
            val ft = fragmentManager.beginTransaction()
            if (addToBackStack) {
                ft.addToBackStack(tag)
            }
            ft.replace(R.id.rootView, fragment, tag)
            ft.commitAllowingStateLoss()
        }
    }

}