package bogdandonduk.androidlibs.recyclerviewutilsandroid

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

interface RecyclerViewHost {

    fun <T : RecyclerView.Adapter<out RecyclerView.ViewHolder>> initializeList(
        recyclerView: RecyclerView,
        adapter: T,
        tag: String? = null,
        persistableHost: RecyclerViewPersistableHost? = null,
        layoutManager: RecyclerView.LayoutManager? = null,
        changeAnimationsEnabled: Boolean = false,
        canReuseUpdatedViewHolder: Boolean = true
    ) {
        with(recyclerView) {
            if(tag != null && persistableHost != null) persistableHost.addListToMap(tag, recyclerView)

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

    fun <T : RecyclerView.Adapter<out RecyclerView.ViewHolder>> updateLists(vararg adapters: T) {
        adapters.forEach {
            CoroutineScope(Main).launch {
                for(i in 0 until it.itemCount) {
                    it.notifyItemChanged(i)
                }
            }
        }
    }
}