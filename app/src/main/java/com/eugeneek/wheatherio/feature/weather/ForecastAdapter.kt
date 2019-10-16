package com.eugeneek.wheatherio.feature.weather

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eugeneek.wheatherio.R
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * This code is part of WheatheRio project
 * Created by X-INFOTECH 2019.
 */
class ForecastAdapter : ListAdapter<WeatherUiModel.ForecastUiModel,
        ForecastAdapter.ForecastViewHolder>(ForecastUiModelDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ForecastViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_forecast, parent, false)
    )

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: WeatherUiModel.ForecastUiModel) = with(itemView) {
            tvDate.apply {
                text = item.date
                setTextColor(item.color)
            }
            tvTemperature.apply {
                text = item.temperature
                setTextColor(item.color)
            }

            ivWeather.setImageResource(item.weatherIconRes)
            ImageViewCompat.setImageTintList(ivWeather, ColorStateList.valueOf(item.color))
        }
    }

    private class ForecastUiModelDC : DiffUtil.ItemCallback<WeatherUiModel.ForecastUiModel>() {
        override fun areItemsTheSame(
            oldItem: WeatherUiModel.ForecastUiModel,
            newItem: WeatherUiModel.ForecastUiModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WeatherUiModel.ForecastUiModel,
            newItem: WeatherUiModel.ForecastUiModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}