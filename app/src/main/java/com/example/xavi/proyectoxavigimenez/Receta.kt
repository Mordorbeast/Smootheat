package com.example.xavi.proyectoxavigimenez

import android.os.Parcel
import android.os.Parcelable
import java.lang.StringBuilder

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Receta(var nombre: String, var autor: String, var descBrebe: String, var descLarga: String, var imagen: Int, var video: String) : Parcelable {
    constructor(parcel: Parcel) : this(
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
        parcel.writeString(descBrebe)
        parcel.writeString(descLarga)
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