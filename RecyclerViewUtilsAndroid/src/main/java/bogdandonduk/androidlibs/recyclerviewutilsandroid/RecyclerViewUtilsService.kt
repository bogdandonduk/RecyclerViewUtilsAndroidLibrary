package bogdandonduk.androidlibs.recyclerviewutilsandroid

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object RecyclerViewUtilsService {
    fun <T : RecyclerView.Adapter<out RecyclerView.ViewHolder>> initializeList(
        recyclerView: RecyclerView,
        adapter: T,
        layoutManager: RecyclerView.LayoutManager? = null,
        changeAnimationsEnabled: Boolean = false,
        canReuseUpdatedViewHolder: Boolean = true
    ) {
        with(recyclerView) {
            if(layoutManager != null) this.layoutManager = layoutManager

            this.adapter = adapter

            itemAnimator = object : DefaultItemAnimator() {
                override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean {
                    return canReuseUpdatedViewHolder
                }
            }

            (itemAnimator as DefaultItemAnimator).run {
                supportsChangeAnimations = changeAnimationsEnabled
            }
        }
    }

    fun <T : RecyclerView.Adapter<out RecyclerView.ViewHolder>> updateLists(adapters: MutableList<T>) {
        adapters.forEach {
            CoroutineScope(Dispatchers.Main).launch {
                for(i in 0 until it.itemCount) {
                    it.notifyItemChanged(i)
                }
            }
        }
    }
}