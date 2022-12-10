package com.jpoveda.mydeliciouscandiesv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.jpoveda.mydeliciouscandiesv2.data.candy.Candy
import com.jpoveda.mydeliciouscandiesv2.data.candy.CandyFileDataSource
import com.jpoveda.mydeliciouscandiesv2.data.candy.CandyMockDataSource
import com.jpoveda.mydeliciouscandiesv2.data.candy.ICandyDataSource
import kotlinx.android.synthetic.main.form_activity.*

class CandyForm : Fragment() {

    var items = ArrayList<Candy>()
    lateinit var dataSource: ICandyDataSource

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.form_activity,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataSource = CandyMockDataSource()
        setListener()
    }

    private fun setListener() {
        btnCrear.setOnClickListener() {
            CrearCandy()
        }

        btnAddPicA.setOnClickListener {
            //CameraForResult()
        }
    }

    fun CrearCandy() {
        val rb = if (rbMasticable.isChecked) "Masticable" else if(rbChicle.isChecked) "Chicle" else "No se ha seleccionado tipo"
        val rb2 = if (rbIndvidual.isChecked) "Individual" else if (rbBolsa.isChecked) "Bolsa" else "No se ha seleccionado formato"
        MakeDialog(
            Candy(
                editTxtName.text.toString(),
                editTxtFabricante.text.toString(),
                Integer.parseInt(editTxtDulzor.text.toString()),
                rb,
                rb2,
                editTxtWeb.text.toString()
            )
        )
        Toast.makeText(requireContext(), "Golosina creada correctamente", Toast.LENGTH_SHORT)
            .show()
    }

    fun MakeDialog(candy: Candy) {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setMessage(getString(R.string.dialog_message))

            setPositiveButton(getString(R.string.dialog_button_positive)) { _, _ ->
                items.add(candy)
                dataSource.saveElement(items)

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
}