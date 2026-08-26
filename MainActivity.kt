package com.pozo.deliveryagent.feature.orders

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.pozo.deliveryagent.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerCountry: Spinner
    private lateinit var etReason: EditText
    private lateinit var btnSubmit: TextView
    private lateinit var cvResult: CardView
    private lateinit var tvCountryName: TextView
    private lateinit var tvCurrentTime: TextView
    private lateinit var tvTimeZone: TextView
    private lateinit var tvQuote: TextView
    private lateinit var tvReason: TextView
    private lateinit var ivCountryImage: ImageView
    private lateinit var ivBackground: ImageView

    private val countries = listOf(
        "Select Country",
        "United States", "United Kingdom", "India", "Japan", "Australia",
        "France", "Italy", "Germany", "Canada", "Brazil", "South Africa",
        "Egypt", "Mexico", "China", "Russia", "South Korea", "Spain",
        "Netherlands", "Switzerland", "New Zealand", "Singapore", "UAE"
    )

    // Country data: timezone, image URL, and quotes
    private val countryData = mapOf(
        "United States" to CountryInfo(
            "America/New_York",
            "https://images.unsplash.com/photo-1501594907352-04cda38ebc29?w=400&h=300&fit=crop",
            arrayOf(
                "The American Dream is alive and well.",
                "In America, everything is possible.",
                "Land of the free, home of the brave."
            )
        ),
        "United Kingdom" to CountryInfo(
            "Europe/London",
            "https://images.unsplash.com/photo-1513635269975-59663e0ac1ad?w=400&h=300&fit=crop",
            arrayOf(
                "London is a city of dreams.",
                "The UK: Where history meets modernity.",
                "God save the Queen!"
            )
        ),
        "India" to CountryInfo(
            "Asia/Kolkata",
            "https://images.unsplash.com/photo-1524492412937-b28074a5d7da?w=400&h=300&fit=crop",
            arrayOf(
                "Incredible India!",
                "India is a land of diversity.",
                "Unity in diversity is India's strength."
            )
        ),
        "Japan" to CountryInfo(
            "Asia/Tokyo",
            "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?w=400&h=300&fit=crop",
            arrayOf(
                "Japan: Where tradition meets technology.",
                "The land of the rising sun.",
                "Perfection is a Japanese art."
            )
        ),
        "Australia" to CountryInfo(
            "Australia/Sydney",
            "https://images.unsplash.com/photo-1506973035872-a4ec16b8e8d9?w=400&h=300&fit=crop",
            arrayOf(
                "G'day mate! Welcome to Australia.",
                "The land down under.",
                "Australia: Beauty beyond imagination."
            )
        ),
        "France" to CountryInfo(
            "Europe/Paris",
            "https://images.unsplash.com/photo-1502602898656-3e91760cbb34?w=400&h=300&fit=crop",
            arrayOf(
                "Vive la France!",
                "Paris, the city of love.",
                "France: Art, culture, and romance."
            )
        ),
        "Italy" to CountryInfo(
            "Europe/Rome",
            "https://images.unsplash.com/photo-1523906834658-6e24ef2386f9?w=400&h=300&fit=crop",
            arrayOf(
                "Italy: Where food is art.",
                "The eternal city of Rome.",
                "La dolce vita!"
            )
        ),
        "Germany" to CountryInfo(
            "Europe/Berlin",
            "https://images.unsplash.com/photo-1516534775068-ba3e7458af70?w=400&h=300&fit=crop",
            arrayOf(
                "Germany: Precision and perfection.",
                "The heart of Europe.",
                "Willkommen in Deutschland!"
            )
        ),
        "Canada" to CountryInfo(
            "America/Toronto",
            "https://images.unsplash.com/photo-1503614472-8c93d56e92ce?w=400&h=300&fit=crop",
            arrayOf(
                "Canada: The true north strong and free.",
                "Maple leaf forever!",
                "Canada: Nature's paradise."
            )
        ),
        "Brazil" to CountryInfo(
            "America/Sao_Paulo",
            "https://images.unsplash.com/photo-1483729558449-99ef09a8c325?w=400&h=300&fit=crop",
            arrayOf(
                "Brazil: Land of samba and football.",
                "Ole, ole, ole!",
                "Brazil: Carnival and joy."
            )
        ),
        "South Africa" to CountryInfo(
            "Africa/Johannesburg",
            "https://images.unsplash.com/photo-1580060839138-9bfe7a0c08bb?w=400&h=300&fit=crop",
            arrayOf(
                "South Africa: The rainbow nation.",
                "Ubuntu - I am because we are.",
                "A world in one country."
            )
        ),
        "Egypt" to CountryInfo(
            "Africa/Cairo",
            "https://images.unsplash.com/photo-1539768942893-daf53e448371?w=400&h=300&fit=crop",
            arrayOf(
                "Egypt: Gift of the Nile.",
                "Ancient wonders of the world.",
                "Where civilization began."
            )
        ),
        "Mexico" to CountryInfo(
            "America/Mexico_City",
            "https://images.unsplash.com/photo-1518638150340-f706e86654de?w=400&h=300&fit=crop",
            arrayOf(
                "Mexico: Land of colors and flavors.",
                "Viva Mexico!",
                "Where culture meets tradition."
            )
        ),
        "China" to CountryInfo(
            "Asia/Shanghai",
            "https://images.unsplash.com/photo-1508804185872-d7badad00f7d?w=400&h=300&fit=crop",
            arrayOf(
                "China: Land of dragons.",
                "The Great Wall stands strong.",
                "A civilization of thousands of years."
            )
        ),
        "Russia" to CountryInfo(
            "Europe/Moscow",
            "https://images.unsplash.com/photo-1536240478700-b869070f9279?w=400&h=300&fit=crop",
            arrayOf(
                "Russia: Land of the Tsars.",
                "From Russia with love.",
                "The largest country in the world."
            )
        ),
        "South Korea" to CountryInfo(
            "Asia/Seoul",
            "https://images.unsplash.com/photo-1517154421773-0529f29ea451?w=400&h=300&fit=crop",
            arrayOf(
                "South Korea: The land of morning calm.",
                "K-Pop and Kimchi!",
                "Technology and tradition blend."
            )
        ),
        "Spain" to CountryInfo(
            "Europe/Madrid",
            "https://images.unsplash.com/photo-1539037116277-4db20889f2d4?w=400&h=300&fit=crop",
            arrayOf(
                "Spain: Where passion meets art.",
                "Viva España!",
                "Flamenco, fiesta, and food."
            )
        ),
        "Netherlands" to CountryInfo(
            "Europe/Amsterdam",
            "https://images.unsplash.com/photo-1580587771525-78b9dba3b914?w=400&h=300&fit=crop",
            arrayOf(
                "Netherlands: Land of tulips.",
                "Dutch innovation and design.",
                "Beyond the dykes."
            )
        ),
        "Switzerland" to CountryInfo(
            "Europe/Zurich",
            "https://images.unsplash.com/photo-1530124566582-a618bc2615dc?w=400&h=300&fit=crop",
            arrayOf(
                "Switzerland: Precision and beauty.",
                "The heart of the Alps.",
                "Peace and chocolate."
            )
        ),
        "New Zealand" to CountryInfo(
            "Pacific/Auckland",
            "https://images.unsplash.com/photo-1507699622108-4be3abd695ad?w=400&h=300&fit=crop",
            arrayOf(
                "New Zealand: Middle Earth.",
                "Kia ora! Welcome.",
                "Land of the long white cloud."
            )
        ),
        "Singapore" to CountryInfo(
            "Asia/Singapore",
            "https://images.unsplash.com/photo-1525625293386-3f8f99389edd?w=400&h=300&fit=crop",
            arrayOf(
                "Singapore: The Lion City.",
                "Where Asia meets the world.",
                "A garden city."
            )
        ),
        "UAE" to CountryInfo(
            "Asia/Dubai",
            "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?w=400&h=300&fit=crop",
            arrayOf(
                "UAE: Where luxury meets tradition.",
                "Dubai - City of dreams.",
                "From desert to paradise."
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupCountrySpinner()
        setupSubmitButton()
    }

    private fun initViews() {
        spinnerCountry = findViewById(R.id.spinnerCountry)
        etReason = findViewById(R.id.etReason)
        btnSubmit = findViewById(R.id.btnSubmit)
        cvResult = findViewById(R.id.cvResult)
        tvCountryName = findViewById(R.id.tvCountryName)
        tvCurrentTime = findViewById(R.id.tvCurrentTime)
        tvTimeZone = findViewById(R.id.tvTimeZone)
        tvQuote = findViewById(R.id.tvQuote)
        tvReason = findViewById(R.id.tvReason)
        ivCountryImage = findViewById(R.id.ivCountryImage)
        ivBackground = findViewById(R.id.ivBackground)
    }

    private fun setupCountrySpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCountry.adapter = adapter
    }

    private fun setupSubmitButton() {
        btnSubmit.setOnClickListener {
            val selectedCountry = spinnerCountry.selectedItem.toString()
            val reason = etReason.text.toString().trim()

            if (selectedCountry == "Select Country") {
                Toast.makeText(this, "Please select a country", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (reason.isEmpty()) {
                etReason.error = "Please tell us why you love this country"
                return@setOnClickListener
            }

            displayCountryInfo(selectedCountry, reason)
        }
    }

    private fun displayCountryInfo(country: String, reason: String) {
        val info = countryData[country]

        if (info != null) {
            cvResult.visibility = CardView.VISIBLE

            // Display country name
            tvCountryName.text = country

            // Display current time
            val timeZone = TimeZone.getTimeZone(info.timezone)
            val calendar = Calendar.getInstance(timeZone)
            val dateFormat = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
            dateFormat.timeZone = timeZone
            val currentTime = dateFormat.format(calendar.time)

            tvCurrentTime.text = "🕐 Current Time: $currentTime"
            tvTimeZone.text = "🌍 Timezone: ${info.timezone.split("/").last()}"

            // Display a random quote
            val randomQuote = info.quotes.random()
            tvQuote.text = "💭 \"$randomQuote\""

            // Display reason
            tvReason.text = "❤️ Reason: $reason"

            // Load country image using Glide
            Glide.with(this)
                .load(info.imageUrl)
                .placeholder(R.drawable.ic_shield_red)
                .error(R.drawable.ic_shield_red)
                .into(ivCountryImage)

            // Load background image using Glide
            Glide.with(this)
                .load(info.imageUrl)
                .placeholder(R.drawable.ic_shield_red)
                .error(R.drawable.ic_shield_red)
                .into(ivBackground)

            Toast.makeText(this, "✅ Country information loaded!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "❌ Information not available for this country", Toast.LENGTH_SHORT).show()
        }
    }

    data class CountryInfo(
        val timezone: String,
        val imageUrl: String,
        val quotes: Array<String>
    )
}