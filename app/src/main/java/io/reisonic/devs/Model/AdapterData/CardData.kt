package io.reisonic.devs.Model.AdapterData

class CardData(_id:String,_title:String, _text: String, _image:String,
               _sort:Int,_date:String){

    val id:String
    val title:String
    val text:String
    val image:String
    val sort:Int
    val date:String

    init {
        id = _id
        title = _title
        text = _text
        image = _image
        sort = _sort
        date = _date
    }
}