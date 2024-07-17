package uz.yasindev.contactapp.ui.fragments.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import uz.yasindev.contactapp.core.AllData
import uz.yasindev.contactapp.core.adapters.SearchRvAdapter
import uz.yasindev.contactapp.core.models.RvContactModel
import uz.yasindev.contactapp.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
    private val searchRvAdapter = SearchRvAdapter()
    private val adapterData = ArrayList<RvContactModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        listenEnteringTexts()
        listenClicks()
    }

    private fun listenClicks() {
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }


        searchRvAdapter.itemClickListener = { itemContact ->

            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToInfoFragment(
                    itemContact
                )
            )
            findNavController().popBackStack()

        }


    }

    private fun listenEnteringTexts() {

        binding.editText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val allData = AllData.getAllData()
                if (allData.isNotEmpty()) {
                    allData.forEach { itemContact ->
                        if (itemContact.contactName.contains(s.toString(), true)) {
                            adapterData.clear()
                            adapterData.add(itemContact)
                            searchRvAdapter.setData(adapterData)
                            binding.searchRv.adapter = searchRvAdapter
                            adapterData.clear()

                            Log.d("TAGll", "onTextChanged:  1 Working Edit Text")
                        } else {
                            adapterData.clear()
                            searchRvAdapter.setData(adapterData)

                            Log.d("TAGll", "onTextChanged:  2 Working Edit Text")
                        }
                    }
                } else Snackbar.make(binding.root, "Data is empty", 2000).show()

            }

            override fun afterTextChanged(s: Editable?) {
                AllData.getAllData().forEach { itemContact ->
                    if (itemContact.contactName.contains(s.toString(), ignoreCase = true)) {
                        adapterData.add(itemContact)
                        searchRvAdapter.setData(adapterData)
                        binding.searchRv.adapter = searchRvAdapter

                        Log.d("TAGll", "afterTextChanged:  Edit Text Not Empty")
                    }
                }
                if (s!!.isEmpty()) {
                    adapterData.clear()
                    Log.d("TAGll", "afterTextChanged:  Edit Text Empty")
                    searchRvAdapter.setData(adapterData)
                }
            }

        })

    }

}