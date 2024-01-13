package com.app.church.screens.calendar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen() {
    val currentMonth = remember  { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library

    val daysOfWeek = remember { daysOfWeek() }
    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalCalendar(
            state = state,
            dayContent = {day ->
                Day(
                    day = day,
                    isSelected = selectedDate == day.date
                )
                {
                    selectedDate = if (selectedDate == day.date) null else day.date
                }
            },
            monthHeader = { month ->
                Column {
                    MonthHeader(month)
                    DaysOfWeekTitle(daysOfWeek = daysOfWeek)
                }
            },
            monthBody = {_, content ->
               Box(
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(top = 4.dp)
                ){
                     Column {
                          content()
                     }
                }

            },
            monthContainer = { _, container ->
                val configuration = LocalConfiguration.current
                val screenWidth = configuration.screenWidthDp.dp
                Box(
                    modifier = Modifier
                        .width(screenWidth * 1f)
                        .clip(shape = RoundedCornerShape(8.dp))
                ){
                        container()
                    }

            }
        )

        Divider()

        EventCard()
    }
    
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Day(day: CalendarDay, isSelected: Boolean, onClick: (CalendarDay) -> Unit = {}) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(color = if (isSelected) Color.Green else Color.Transparent)
            .clickable(
                enabled = day.position == DayPosition.MonthDate
            ) {
                onClick(day)
                Log.e("Calendar", "Day: ${day.date}")
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = if (day.position == DayPosition.MonthDate) Color.Black else Color.LightGray,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MonthHeader(calendarMonth: CalendarMonth) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 8.dp, start = 8.dp, end = 16.dp),
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = calendarMonth.yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
            )
        }
    }
}

@Composable
fun EventCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Event Title",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Event Description",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen()
}