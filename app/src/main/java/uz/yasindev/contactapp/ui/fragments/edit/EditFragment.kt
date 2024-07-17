package uz.yasindev.contactapp.ui.fragments.edit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.snackbar.Snackbar
import uz.yasindev.contactapp.R
import uz.yasindev.contactapp.core.models.RvContactModel
import uz.yasindev.contactapp.databinding.FragmentEditBinding


class EditFragment : Fragment(), EditMVP.View {

    private val binding by lazy { FragmentEditBinding.inflate(layoutInflater) }
    private lateinit var presenter: EditMVP.Presenter
    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = EditPresenter(this)

        setDataToViews()
        listenClicks()

    }

    private fun setDataToViews() {

        binding.userImg.load(args.model.image)
        binding.nameEdit.setText(args.model.contactName)
        binding.numberEdit.setText(args.model.phoneNumber)

    }

    private fun listenClicks() {
        binding.saveBtn.setOnClickListener {
            val name = binding.nameEdit.text.toString()
            val number = binding.numberEdit.text.toString()

            val model = RvContactModel(0, args.model.image, name, number)
            presenter.editUserInfo(args.model.id, model)
            Log.d("TAGww", "listenClicks: ${args.model.id},${model}")

        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun successEdit(message: String) {
        Snackbar.make(binding.root, message, 2000).show()
    }

    override fun errorEdit(message: String) {
        Snackbar.make(binding.root, message, 2000).show()
    }

}