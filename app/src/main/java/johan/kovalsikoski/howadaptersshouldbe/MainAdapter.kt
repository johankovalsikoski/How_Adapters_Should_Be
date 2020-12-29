package johan.kovalsikoski.howadaptersshouldbe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import johan.kovalsikoski.howadaptersshouldbe.databinding.ItemBinding

class MainAdapter(private val itemList: MutableList<String>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var binding: ItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun addItem(itemName: String) {
        itemList.add(itemName + " #${itemList.size}")
        notifyItemInserted(itemList.size - 1)
    }

    class MainViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemName: String) {
            var initValue = 0

            binding.apply {
                textItemName.text = itemName

                buttonAdd.setOnClickListener {
                    initValue += 1
                    binding.inputValue.setText(initValue.toString())
                }

                buttonRemove.setOnClickListener {
                    initValue -= 1
                    binding.inputValue.setText(initValue.toString())
                }

                inputValue.doOnTextChanged { text, start, before, count ->
                    initValue = text.toString().toInt()
                }

            }
        }

    }
}
