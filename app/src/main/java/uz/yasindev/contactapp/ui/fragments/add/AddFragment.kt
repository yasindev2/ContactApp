package uz.yasindev.contactapp.ui.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import uz.yasindev.contactapp.core.models.RvContactModel
import uz.yasindev.contactapp.databinding.FragmentAddBinding


class AddFragment : Fragment(), AddMVP.View {
    private val binding by lazy { FragmentAddBinding.inflate(layoutInflater) }
    private lateinit var list: ArrayList<String>
    var spinnerSelectedItem: String? = null
    private lateinit var presenter: AddPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = AddPresenter(this)

        setDataToSpinner()
        listenClicks()

    }

    private fun listenClicks() {

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Get the selected item text
                spinnerSelectedItem = parent.getItemAtPosition(position).toString()
                // Do something with the selected item

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }



        binding.saveBtn.setOnClickListener {

            val name = binding.nameUser.text.toString()
            val number = "$spinnerSelectedItem(${binding.numberUser.text})"

            if (name.isNotEmpty() && number.isNotEmpty()) {
                val model = RvContactModel(
                    0,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtuphMb4mq-EcVWhMVT8FCkv5dqZGgvn_QiA&s",
                    name,
                    number
                )
                binding.progressBar.visibility = View.VISIBLE
                binding.saveBtn.visibility = View.INVISIBLE
                presenter.addUser(model)

            } else Snackbar.make(binding.root, "Fill the gaps", 2000).show()
        }


        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.userImageInfo.setOnClickListener {
            findNavController().navigate(AddFragmentDirections.actionAddFragment2ToProfileImagesFragment())
        }

    }


    private fun setDataToSpinner() {
        list = ArrayList()

        list.add("+998")
        list.add("+123")
        list.add("+768")
        list.add("+234")
        list.add("+456")

        val arrayAdapter = ArrayAdapter<String>(
            findNavController().context,
            android.R.layout.simple_list_item_1,
            list
        )
        binding.spinner.adapter = arrayAdapter
    }

    override fun successAdd(message: String) {
        Snackbar.make(binding.root, message, 2000).show()

        binding.progressBar.visibility = View.INVISIBLE
        binding.saveBtn.visibility = View.VISIBLE

        binding.nameUser.text.clear()
        binding.numberUser.text.clear()
    }

    override fun setError(message: String) {
        Snackbar.make(binding.root, message, 2000).show()
    }
}