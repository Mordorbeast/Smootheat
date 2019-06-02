package com.example.xavi.proyectoxavigimenez

import android.os.Parcel
import android.os.Parcelable

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Receta(var nombre: String, var autor: String, var descCorta: String, var ingredientes: String, var pasos: String, var imagen: Int, var video: String) : Parcelable {

    //hago que ingredientes sea un string porque no se hacerlo si es un array, lo que hare es cojer el string y transformarlo en un array (cada palabra sera un elemento del array)

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(autor)
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




