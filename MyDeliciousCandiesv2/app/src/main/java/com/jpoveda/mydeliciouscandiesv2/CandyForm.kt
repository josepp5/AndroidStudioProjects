package com.jpoveda.mydeliciouscandiesv2

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import com.jpoveda.mydeliciouscandiesv2.data.candy.Candy
import com.jpoveda.mydeliciouscandiesv2.data.candy.CandyFileDataSource
import com.jpoveda.mydeliciouscandiesv2.data.candy.ICandyDataSource
import com.jpoveda.mydeliciouscandiesv2.utils.PermissionManager
import kotlinx.android.synthetic.main.form_activity.*
import java.util.*
import kotlin.collections.ArrayList


class CandyForm : Fragment() {

    var items: ArrayList<Candy> = ArrayList()
    lateinit var dataSource: ICandyDataSource

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataSource = CandyFileDataSource(requireContext())
        //dataSource = CandyMockDataSource()
        items = dataSource.getList() as ArrayList<Candy>
        return inflater.inflate(R.layout.form_activity,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        btnCrear.setOnClickListener() {
            CrearCandy()
        }

        btnAddPicA.setOnClickListener {
            CameraForResult()
        }
    }

    fun CrearCandy() {
        val rb = if (rbMasticable.isChecked) "Masticable" else if(rbChicle.isChecked) "Chicle" else "No se ha seleccionado tipo"
        val rb2 = if (rbIndvidual.isChecked) "Individual" else if (rbBolsa.isChecked) "Bolsa" else "No se ha seleccionado formato"
        MakeDialog(
            Candy(
                UUID.randomUUID(),
                editTxtName.text.toString(),
                editTxtFabricante.text.toString(),
                Integer.parseInt(editTxtDulzor.text.toString()),
                rb,
                rb2,
                editTxtWeb.text.toString(),
                imgCandyA.drawToBitmap()
            )
        )
    }

    fun MakeDialog(candy: Candy) {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setMessage(getString(R.string.dialog_message))

            setPositiveButton(getString(R.string.dialog_button_positive)) { _, _ ->
                items.add(candy)
                dataSource.saveElements(items)

                Toast.makeText(
                    requireContext(),
                    "La informacion ha quedado registrada en el historico",
                    Toast.LENGTH_LONG
                ).show()
            }
            setNegativeButton(getString(R.string.dialog_button_negative)) { _, _ ->
                Toast.makeText(
                    requireContext(),
                    "La informacion no ha quedado registrada",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        alertDialog.show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            124 -> {
                if (resultCode == Activity.RESULT_OK){
                    imgCandyA?.setImageBitmap(data?.getParcelableExtra("data"))
                }
            }
        }
    }

    private fun CameraForResult(){
        val intent = Intent(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        startActivityForResult(intent,124)
        PermissionManager().checkPermissionForResult(
            requireActivity(),
            Manifest.permission.CAMERA,
            1246,
            intent,
            124,
        )
    }
}