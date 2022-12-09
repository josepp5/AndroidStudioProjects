package com.example.mydeliciouscandies

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable


class Candy(
    val name: String,
    val fabricante: String,
    val dulzor: Int,
    val tipo: String,
    val formato: String,
    val web: String,
   // val image: Bitmap
): Parcelable {
    private var nombre: String? = null
    private var www: String? = null
    private var fabric: String? = null
    private var tip: String? = null
    private var format: String? = null
    private var dulz: Int? = null
  //  private var img: Bitmap? = null

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
     //   parcel.readValue(Bitmap::class.java.classLoader) as Bitmap
    ) {
        nombre = parcel.readString()
        www = parcel.readString()
        fabric = parcel.readString()
        tip = parcel.readString()
        format = parcel.readString()
        dulz = parcel.readValue(Int::class.java.classLoader) as? Int
   //     img = parcel.readValue(Bitmap::class.java.classLoader) as? Bitmap
    }

    init {
        this.nombre = name
        this.www = web
        this.fabric = fabricante
        this.tip = tipo
        this.format = formato
        this.dulz = dulzor
   //     this.img = image
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(fabricante)
        parcel.writeInt(dulzor)
        parcel.writeString(tipo)
        parcel.writeString(formato)
        parcel.writeString(web)
        parcel.writeString(nombre)
        parcel.writeString(www)
        parcel.writeString(fabric)
        parcel.writeString(tip)
        parcel.writeString(format)
        parcel.writeValue(dulz)
    //    parcel.writeValue(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Candy> {
        override fun createFromParcel(parcel: Parcel): Candy {
            return Candy(parcel)
        }

        override fun newArray(size: Int): Array<Candy?> {
            return arrayOfNulls(size)
        }
    }
}