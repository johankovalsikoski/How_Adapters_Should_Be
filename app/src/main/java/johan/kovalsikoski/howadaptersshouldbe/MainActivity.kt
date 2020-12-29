package johan.kovalsikoski.howadaptersshouldbe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import johan.kovalsikoski.howadaptersshouldbe.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = MainAdapter(mutableListOf("Item"))
        binding.recyclerview.adapter = adapter

        binding.buttonAdd.setOnClickListener {
            adapter.addItem("Added Item")
        }

    }
}
