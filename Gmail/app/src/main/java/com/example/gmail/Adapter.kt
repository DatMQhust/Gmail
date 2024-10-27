package com.example.gmail

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class Adapter(val mails : List<EmailModel>) : RecyclerView.Adapter<Adapter.myViewHolder>() {
    class myViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val firstLetter : TextView
        val next : Button
        val subject : TextView
        val content : TextView
        val time : TextView
        val star : Button
        val from : TextView
        init {
            from = itemView.findViewById(R.id.from)
            firstLetter = itemView.findViewById(R.id.shortcut)
            next = itemView.findViewById(R.id.button3)
            subject = itemView.findViewById(R.id.subject)
            content = itemView.findViewById(R.id.firstline)
            time = itemView.findViewById(R.id.time)
            star = itemView.findViewById(R.id.star)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.gmail,parent,false)
        Log.d("Adapter", "Mails size: ${mails.size}")
        return myViewHolder(itemView)
    }

    override fun getItemCount(): Int = mails.size

        override fun onBindViewHolder(holder: myViewHolder, position: Int) {
            val mail = mails[position]
            holder.firstLetter.text = mail.mailFrom[0].toString()
            holder.from.text = mail.mailFrom
            holder.subject.text = mail.subject
            holder.content.text = mail.content
            holder.time.text = mail.time
            setRandomColor(holder.firstLetter)
            holder.star.setOnClickListener(){
                // Lấy drawable hiện tại
                val currentBackground = it.background

                // So sánh với drawable bằng cách sử dụng ContextCompat
                val outlineStar = ContextCompat.getDrawable(it.context, R.drawable.baseline_star_outline_24)
                val filledStar = ContextCompat.getDrawable(it.context, R.drawable.baseline_star_24)

                if (currentBackground.constantState == outlineStar?.constantState) {
                    it.setBackgroundResource(R.drawable.baseline_star_24) // Đặt drawable đầy
                } else {
                    it.setBackgroundResource(R.drawable.baseline_star_outline_24) // Đặt drawable rỗng
                }
            }
    }
}
private fun setRandomColor(tx : TextView) {
    val randomColor = getRandomColor()
    // Cập nhật màu nền cho nút
    val drawable =tx.background as GradientDrawable
    drawable.setColor(randomColor)
}

private fun getRandomColor(): Int {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color.rgb(red, green, blue)
}
