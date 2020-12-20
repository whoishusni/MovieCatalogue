package id.husni.moviecatalogue.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.viewmodel.MyCustomViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_series.*
import kotlinx.android.synthetic.main.fragment_series.progressBar

class SeriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_series, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity!=null){
            val adapters = SeriesAdapter()
            val factory = MyCustomViewModel.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[SeriesViewModel::class.java]
            showProgressBar(true)
            viewModel.getSeries().observe(viewLifecycleOwner, Observer { series->
                showProgressBar(false)
                adapters.setSeries(series)
                adapters.notifyDataSetChanged()
            })
            rvSeries.apply {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter = adapters
            }
        }
    }

    private fun showProgressBar(isShow: Boolean){
        if (isShow){
            progressBar.visibility = View.VISIBLE
        }
        else{
            progressBar.visibility = View.GONE
        }
    }
}