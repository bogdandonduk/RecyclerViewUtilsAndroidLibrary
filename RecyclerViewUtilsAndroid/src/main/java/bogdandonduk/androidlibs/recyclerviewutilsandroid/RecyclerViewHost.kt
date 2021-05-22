package bogdandonduk.androidlibs.recyclerviewutilsandroid

import androidx.recyclerview.widget.RecyclerView

interface RecyclerViewHost {

    fun <T : RecyclerView.Adapter<out RecyclerView.ViewHolder>> initializeList(
        recyclerView: RecyclerView,
        adapter: T,
        layoutManager: RecyclerView.LayoutManager? = null,
        changeAnimationsEnabled: Boolean = false,
        canReuseUpdatedViewHolder: Boolean = true
    ) {
        RecyclerViewUtils.initializeList(recyclerView, adapter, layoutManager, changeAnimationsEnabled, canReuseUpdatedViewHolder)
    }

    fun <T : RecyclerView.Adapter<out RecyclerView.ViewHolder>> updateLists(adapters: MutableList<T>) {
        RecyclerViewUtils.updateLists(adapters)
    }
}