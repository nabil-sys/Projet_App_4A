package com.example.android4a.presentation.main


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.R
import com.example.android4a.data.local.models.ExerciceImage



class ListAdapter // Provide a suitable constructor (depends on the kind of dataset)
    (
    private val values: List<ExerciceImage>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: ExerciceImage?)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var txtHeader: TextView
        var txtFooter: TextView
        var layout: View

        init {
            layout = v
            txtHeader = v.findViewById(R.id.firstLine)
            txtFooter = v.findViewById(R.id.secondLine)
        }
    }

   /* fun add(position: Int, item: ExerciceImage) {
        values.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }*/

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder { // create a new view
        val inflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.row_layout, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val currentExerciceImage = values[position]
        holder.txtHeader.text = currentExerciceImage.id
        holder.txtFooter.text = currentExerciceImage.image
        holder.itemView.setOnClickListener { listener.onItemClick(currentExerciceImage) }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return values.size
    }

}