package com.example.middentest.compose

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.example.middentest.R
import com.example.middentest.common.GENDER_FEMALE_RESPONSE
import com.example.middentest.common.GENDER_MALE_RESPONSE
import com.example.middentest.data.models.UserInfo
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun createToast(context: Context, label: String = "Próximamente") {
    Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
}

@Composable
fun castGender(gender: String): String{
    return when(gender.lowercase()){
        GENDER_FEMALE_RESPONSE -> stringResource(id = R.string.female_label)
        GENDER_MALE_RESPONSE -> stringResource(id = R.string.male_label)
        else -> stringResource(id = R.string.unspecified_label)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(date: String): String{
    val formatFromAPI = "yyyy-MM-dd"
    val desiredFormat = "dd/MM/yyyy"
    val dateFormatter = DateTimeFormatter.ofPattern(formatFromAPI)
    val dateParsed = LocalDate.parse(date.subSequence(0, 10), dateFormatter)
    return dateParsed.format(DateTimeFormatter.ofPattern(desiredFormat)).toString()
}

fun bitmapDescriptorFromVector(fragmentContext: Context, vectorResId: Int): BitmapDescriptor? =
// Some VectorDrawables do not display when using ContextCompat.
// Either ResourcesCompat or VectorDrawableCompat seem to work.
    // VectorDrawableCompat was chosen because it is backed by ResourcesCompat under the hood
    VectorDrawableCompat.create(fragmentContext.resources, vectorResId, fragmentContext.theme)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }

fun sortedUserList(userList: List<UserInfo>, input: String): List<UserInfo>{
    return userList.filter { userInfo -> userInfo.name.toString().contains(input, ignoreCase = true) || (userInfo.email?.contains(input, ignoreCase = true) == true) }.sortedBy { userInfo -> userInfo.name.toString() }
}