package com.example.xavi.proyectoxavigimenez

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(private var activity : Activity, private var items : ArrayList<ListViewAlimentos>) : BaseAdapter(){

   private class ViewHolder(row : View?){
       var nombre : TextView? = null

       init{
           this.nombre = row?.findViewById(R.id.nombreAlimento)
       }
   }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.activity_fila_lista_compra, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var listViewAlimentos = items[position]
        viewHolder.nombre?.text = listViewAlimentos.nombre     //ver video este en minuto 3:00  https://youtu.be/KFo1bO05Jho

        return view as View
    }

    override fun getItem(i: Int): ListViewAlimentos {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }


}