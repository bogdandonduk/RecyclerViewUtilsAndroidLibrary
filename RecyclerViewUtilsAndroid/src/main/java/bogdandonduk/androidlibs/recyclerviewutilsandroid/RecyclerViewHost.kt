package bogdandonduk.androidlibs.recyclerviewutilsandroid

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

interface RecyclerViewHost {
    fun initializeList(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
        layoutManager: RecyclerView.LayoutManager? = null,
        changeAnimationsEnabled: Boolean = false
    ) {
        with(recyclerView) {
            if(layoutManager != null) this.layoutManager = layoutManager

            this.adapter = adapter

            (itemAnimator as DefaultItemAnimator).supportsChangeAnimations = changeAnimationsEnabled
        }
    }

    fun updateList(vararg adapters: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {
        adapters.forEach {
            CoroutineScope(Main).launch {
                for(i in 0 until it!!.itemCount) {
                    it.notifyItemChanged(i)
                }
            }
        }
    }
}