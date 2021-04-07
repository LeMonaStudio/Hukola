package com.nativecitizens.hukola.introslider

import android.os.Parcel
import android.os.Parcelable

data class SlideDetails(
    var slideNumber: Int,
    var slideTitleStringId: Int,
    var slideImageId: Int,
    var slideInfoStringId: Int) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(slideNumber)
        parcel.writeInt(slideTitleStringId)
        parcel.writeInt(slideImageId)
        parcel.writeInt(slideInfoStringId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SlideDetails> {
        override fun createFromParcel(parcel: Parcel): SlideDetails {
            return SlideDetails(parcel)
        }

        override fun newArray(size: Int): Array<SlideDetails?> {
            return arrayOfNulls(size)
        }
    }
}


/*
Parcelable {

    constructor(parcel: Parcel) : this(
    parcel.readSpannableString(),
    parcel.readInt(),
    parcel.readSpannableString(),
    parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSpannableString(slideTitle, flags)
        parcel.writeInt(slideImageId)
        parcel.writeSpannableString(slideInfo, flags)
        parcel.writeByte(if (isSlideComplete) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SlideDetails> {
        override fun createFromParcel(parcel: Parcel): SlideDetails {
            return SlideDetails(parcel)
        }

        override fun newArray(size: Int): Array<SlideDetails?> {
            return arrayOfNulls(size)
        }
    }
}

//Extension functions to convert SpannableString to Parcel and vice-versa
fun Parcel.writeSpannableString(spannableString: SpannableString, flags: Int){
    TextUtils.writeToParcel(spannableString,this,flags)
}
fun Parcel.readSpannableString(): SpannableString{
    return TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this) as SpannableString
}*/
