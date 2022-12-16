package com.jpoveda.mydeliciouscandiesv2

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            cameraForResult()
        }
    }

    fun CrearCandy() {
        val rb = if (rbMasticable.isChecked) getString(R.string.MasticableType) else if(rbChicle.isChecked) getString(
                    R.string.ChicleFormat) else getString(R.string.NoTypeSelected)
        val rb2 = if (rbIndvidual.isChecked) getString(R.string.IndividualType) else if (rbBolsa.isChecked) getString(
                    R.string.BolsaFormat) else getString(R.string.NoFormatSelected)
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
                    getString(R.string.DialogAddCandyPositive),
                    Toast.LENGTH_LONG
                ).show()
            }
            setNegativeButton(getString(R.string.dialog_button_negative)) { _, _ ->
                Toast.makeText(
                    requireContext(),
                    getString(R.string.DialogAddCandyNegative),
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

    private fun cameraForResult(){
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