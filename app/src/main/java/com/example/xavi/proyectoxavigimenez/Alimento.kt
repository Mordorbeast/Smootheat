package com.example.xavi.proyectoxavigimenez

import android.os.Parcel
import android.os.Parcelable

class Alimento(var alimento: String, var tipo: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString()  ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(alimento)
        parcel.writeString(tipo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Alimento> {
        override fun createFromParcel(parcel: Parcel): Alimento {
            return Alimento(parcel)
        }

        override fun newArray(size: Int): Array<Alimento?> {
            return arrayOfNulls(size)
        }
    }

}