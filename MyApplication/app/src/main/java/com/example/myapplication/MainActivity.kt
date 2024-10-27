package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Tao data
        var mails = mutableListOf<EmailModel>()
        val mail1 = EmailModel("Chris Abad","Help make Campaign Monitor better",
            "Let us your thoughts! No images mskae jdsfk jkslf sgfth","11:22 AM")
        val mail2 = EmailModel("John Doe", "Welcome to the Team!", "We're excited to have you join us. Here’s what you need to know...", "9:15 AM")
        val mail3 = EmailModel("Sarah Lee", "Weekly Report Available", "Your weekly performance report is now ready. Check it out!", "Yesterday")
        val mail4 = EmailModel("Campaign Monitor", "Exclusive Offers Inside", "Don’t miss out on these exclusive deals just for you.", "1:35 PM")
        val mail5 = EmailModel("Marketing Team", "September Newsletter", "Check out our latest updates and news for September!", "2:45 PM")
        val mail6 = EmailModel("Alice Smith", "Meeting Confirmation", "Your meeting with the project team is confirmed for 3 PM.", "8:00 AM")
        val mail7 = EmailModel("Linda Brown", "Happy Birthday!", "Wishing you a fantastic day filled with joy and surprises.", "5:50 PM")
        val mail8 = EmailModel("Paul Adams", "Project Update", "Here’s the latest update on the XYZ project. Let me know your thoughts.", "10:12 AM")
        val mail9 = EmailModel("Customer Support", "Ticket #12345 Resolved", "Your support ticket has been successfully resolved.", "3:47 PM")
        val mail10 = EmailModel("Company HR", "Annual Leave Reminder", "Just a reminder about your annual leave balance for this year.", "12:30 PM")
        val mail11 = EmailModel("Finance Team", "Invoice Due", "Your invoice for the recent services is due. Please review.", "6:10 PM")
        val mail12 = EmailModel("Jane Cooper", "New Design Ideas", "I’ve attached the latest design ideas for the project. Let me know your feedback.", "Yesterday")
        mails.add(mail1)
        mails.add(mail2)
        mails.add(mail3)
        mails.add(mail4)
        mails.add(mail5)
        mails.add(mail6)
        mails.add(mail7)
        mails.add(mail8)
        mails.add(mail9)
        mails.add(mail10)
        mails.add(mail11)
        mails.add(mail12)
        Log.d("Adapter", "Mails size: ${mails.size}")

        // Adapter
        val adapter = Adapter(mails)
        // recycleview
        val recyclerView = findViewById<RecyclerView>(R.id.recycle)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}