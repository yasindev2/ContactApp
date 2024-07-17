package uz.yasindev.contactapp.ui.fragments.info

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.snackbar.Snackbar
import uz.yasindev.contactapp.core.models.RvContactModel
import uz.yasindev.contactapp.core.setItemStatusBarColor
import uz.yasindev.contactapp.databinding.FragmentInfoBinding


class InfoFragment : Fragment(), InfoMVP.View {

    private val binding by lazy { FragmentInfoBinding.inflate(layoutInflater) }
    private val args: InfoFragmentArgs by navArgs()
    private lateinit var presenter: InfoPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = InfoPresenter(this)

        setItemStatusBarColor(Color.parseColor("#023FF4"), false)

        listenClicks()
        setDataToViews()


    }

    private fun setDataToViews() {

        binding.userImageInfo.load(args.model.image)
        binding.nameUser.text = args.model.contactName
        binding.numberUser.text = args.model.phoneNumber
    }

    private fun listenClicks() {
        binding.callBtn.setOnClickListener {
            openDialer(args.model.phoneNumber)
        }

        binding.messageBtn.setOnClickListener {
            openMessagingApp(args.model.phoneNumber, "")
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.deleteBtn.setOnClickListener {

            presenter.deleteUser(args.model.id)
            Snackbar.make(binding.root, "Successfully deleted", 2000).show()

        }

        binding.editBtn.setOnClickListener {
            val model = RvContactModel(args.model.id,args.model.image,args.model.contactName,args.model.phoneNumber)
            findNavController().navigate(InfoFragmentDirections.actionInfoFragmentToEditFragment(model))

        }


    }

    private fun openMessagingApp(phoneNumber: String, message: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:$phoneNumber")
            putExtra("sms_body", message)
        }
        startActivity(intent)
    }

    private fun openDialer(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(intent)
    }


    override fun successDelete() {
        Snackbar.make(binding.root, "Successfully deleted", 2000).show()
    }


    override fun deleteError(error: String) {
        Snackbar.make(binding.root, error, 2000).show()
    }


}