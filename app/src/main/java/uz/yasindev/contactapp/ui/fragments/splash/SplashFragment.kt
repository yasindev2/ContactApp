package uz.yasindev.contactapp.ui.fragments.splash

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import uz.yasindev.contactapp.R
import uz.yasindev.contactapp.core.adapters.ViewPagerAdapter
import uz.yasindev.contactapp.core.models.VpModel
import uz.yasindev.contactapp.core.setItemStatusBarColor
import uz.yasindev.contactapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }
    private val viewPagerAdapter = ViewPagerAdapter()
    private val data = ArrayList<VpModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setItemStatusBarColor(Color.parseColor("#FFFFFF"), true)

        setAdapter()
        setData()

    }

    private fun setData() {
        data.add(VpModel(1, R.drawable.img, "Adding contacts", "You can add your contacts easily"))
        data.add(VpModel(2, R.drawable.img1, "Removing contacts", "You can remove your contacts"))
        data.add(
            VpModel(
                3, R.drawable.img_2, "Calling contacts", "You can call your contacts easily"
            )
        )

        viewPagerAdapter.setData(data)
    }

    private fun setAdapter() {
        binding.viewPager.adapter = viewPagerAdapter

        val springDotsIndicator = binding.wormDotsIndicator
        val viewPager = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        springDotsIndicator.attachTo(viewPager)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Check if the ViewPager has reached the last item
                if (position == viewPager.adapter?.itemCount?.minus(1)) {
                    // ViewPager has reached the last item
                    // Handle your logic here
                    // For example, you can show a message or load more data
                    binding.startBtn.visibility = View.VISIBLE

                    binding.startBtn.setOnClickListener {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
                    }

                }
            }
        })
    }


}