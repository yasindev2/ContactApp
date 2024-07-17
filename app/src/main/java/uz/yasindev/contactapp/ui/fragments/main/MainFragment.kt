package uz.yasindev.contactapp.ui.fragments.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import uz.yasindev.contactapp.core.adapters.RecyclerViewAdapter
import uz.yasindev.contactapp.core.models.RvContactModel
import uz.yasindev.contactapp.core.setItemStatusBarColor
import uz.yasindev.contactapp.databinding.FragmentMainBinding

class MainFragment : Fragment(), MainMVP.View {

    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val recyclerViewAdapter = RecyclerViewAdapter()
    private lateinit var presenter: MainMVP.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MainPresenter(this)
        presenter.getAllContacts()

        setItemStatusBarColor(Color.parseColor("#023FF4"), false)


        listenClicks()

    }


    private fun listenClicks() {
        binding.addBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddFragment2())
        }

        binding.searchBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
        }

        binding.optionsBtn.setOnClickListener {

        }

        recyclerViewAdapter.onClickListener = { rvModel ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToInfoFragment(
                    rvModel
                )
            )
        }
    }


    override fun setDataToAdapter(data: List<RvContactModel>) {
        binding.progressBar?.isVisible = false

        recyclerViewAdapter.setData(data as ArrayList)
        binding.contactRv.adapter = recyclerViewAdapter
    }

    override fun setError(message: String) {
        Snackbar.make(binding.root, message, 2000).show()
    }


}