package com.example.studentplanner.models

import java.time.LocalDate

object SampleData {
    val tasks = listOf(
        Task("Math Assignment", LocalDate.now().plusDays(2)),
        Task("Science Lab Report", LocalDate.now().plusDays(5)),
        Task("History Notes", LocalDate.now().plusDays(1))
    )
}