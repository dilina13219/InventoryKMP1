package org.example.project // ඔයාගේ නිවැරදි package එක මෙතනට දාන්න

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}