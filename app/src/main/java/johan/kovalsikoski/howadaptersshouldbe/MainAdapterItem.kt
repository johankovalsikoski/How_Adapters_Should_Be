package johan.kovalsikoski.howadaptersshouldbe

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import johan.kovalsikoski.howadaptersshouldbe.databinding.MainAdapterItemBinding

private const val MIN_VALUE = 0
private const val MAX_VALUE = 100

class MainAdapterItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: MainAdapterItemBinding =
        MainAdapterItemBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.main_adapter_item, this)
        )

    private var value = 0

    init {

        binding.apply {
            buttonAdd.setOnClickListener {
                if (value < MAX_VALUE)
                    value += 1

                setValue(value)
            }

            buttonRemove.setOnClickListener {
                if (value > MIN_VALUE)
                    value -= 1

                setValue(value)
            }

            inputValue.doOnTextChanged { text, start, before, count ->
               updateValue(text.toString())
            }

            parentLayout.setOnLongClickListener {
                Toast.makeText(context, getItemNameAndValue(), Toast.LENGTH_SHORT).show()
                true
            }

        }
    }

    fun setItemName(name: String) {
        binding.textItemName.text = name
    }

    private fun getItemName() = binding.textItemName.text.toString()

    private fun getItemQuantity() = value

    private fun getItemNameAndValue() = "${getItemName()} - ${getItemQuantity()}"

    private fun setValue(value: Int) {
        if (value in MIN_VALUE..MAX_VALUE) {
            binding.inputValue.setText(value.toString())
        }
    }

    private fun updateValue(value: String) {
        if (value.isNotEmpty() && value.toInt() in MIN_VALUE..MAX_VALUE) {
            this.value = value.toInt()
        } else {
            setLastState()
        }
    }

    private fun setLastState() {
        binding.inputValue.setText(value.toString())
    }

}
