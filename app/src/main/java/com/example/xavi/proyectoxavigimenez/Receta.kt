package com.example.xavi.proyectoxavigimenez

import android.os.Parcel
import android.os.Parcelable

class Receta(var nombre: String, var descCorta: String, var ingredientes: String, var pasos: String, var imagen: Int, var video: String) : Parcelable {
    
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(descCorta)
        parcel.writeString(ingredientes)
        parcel.writeString(pasos)
        parcel.writeInt(imagen)
        parcel.writeString(video)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Receta> {
        override fun createFromParcel(parcel: Parcel): Receta {
            return Receta(parcel)
        }

        override fun newArray(size: Int): Array<Receta?> {
            return arrayOfNulls(size)
        }
    }

}




