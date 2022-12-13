package com.jpoveda.mydeliciouscandiesv2.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager {
    fun checkPermission(activity: Activity, permission: String, requestCode: Int, intent: Intent) {

        // 1 - Si el permiso esta concedido
        // 2 - Si el usuario ya lo ha rechazado
        //      2.1 - Si lo ha rechazado mostrar pop up con mensaje
        //      2.2 - Si el usuario es la primera vez se le mostrara el pop up permiso
        // 3 - Si ha dado el permiso ya


        // 1 - Si el permiso esta concedido
        if (ContextCompat.checkSelfPermission(
                activity, permission
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            // 2 - Si el usuario ya lo ha rechazado
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    permission
                )
            ) {
                // 2.1 - Si lo ha rechazado mostrar pop up con mensaje o volver a pedir permiso con el codigo siguiente
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(permission),
                    requestCode
                )
            } else {
                // 2.2 - Si el usuario es la primera vez se le mostrara el pop up permiso
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(permission),
                    requestCode
                )
            }
        } else {
            activity.startActivity(intent)
        }
    }

    fun checkPermissionForResult(activity: Activity, permission: String, requestCodePermission: Int, intent: Intent, requestCodeIntent: Int) {

        // 1 - Si el permiso esta concedido
        // 2 - Si el usuario ya lo ha rechazado
        //      2.1 - Si lo ha rechazado mostrar pop up con mensaje
        //      2.2 - Si el usuario es la primera vez se le mostrara el pop up permiso
        // 3 - Si ha dado el permiso ya


        // 1 - Si el permiso esta concedido
        if (ContextCompat.checkSelfPermission(
                activity, permission
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            // 2 - Si el usuario ya lo ha rechazado
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    permission
                )
            ) {
                // 2.1 - Si lo ha rechazado mostrar pop up con mensaje o volver a pedir permiso con el codigo siguiente
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(permission),
                    requestCodePermission
                )
            } else {
                // 2.2 - Si el usuario es la primera vez se le mostrara el pop up permiso
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(permission),
                    requestCodePermission
                )
            }
        } else {
            activity.startActivityForResult(intent,requestCodeIntent)
        }
    }
}