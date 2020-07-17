package test.library.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<In, Vh : BaseViewHolder<In>> : RecyclerView.Adapter<Vh>() {
    companion object{
        private const val NO_INDEX = -1
    }
    private var list = mutableListOf<In>()

    override fun onBindViewHolder(holder: Vh, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
    override fun getItemCount(): Int = list.size

    fun getItems(): List<In>? = list

    fun setItems(list: List<In>?) {
        list?.let {
            this.list.clear()
            this.list = it.toMutableList()
            notifyDataSetChanged()
        }
    }

    fun addItem(item: In) {
        list.add(item)
        notifyItemInserted(list.lastIndex)
    }

    fun updateItem(item: In, position: Int) {
        list[position] = item
        notifyItemChanged(position)
    }

    open fun getItem(position: Int): In? = list.getOrNull(position)

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeItem(item: In) {
        val position = list.indexOf(item)
        if (position != NO_INDEX) {
            removeItem(position)
        }
    }
}