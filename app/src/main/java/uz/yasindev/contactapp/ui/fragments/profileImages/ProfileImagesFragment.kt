package uz.yasindev.contactapp.ui.fragments.profileImages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.yasindev.contactapp.databinding.FragmentProfileImagesBinding


class ProfileImagesFragment : Fragment() {

    private val binding by lazy { FragmentProfileImagesBinding.inflate(layoutInflater) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}