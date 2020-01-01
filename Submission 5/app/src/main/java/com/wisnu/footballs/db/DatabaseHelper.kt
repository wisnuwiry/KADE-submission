package com.wisnu.footballs.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            FavoriteDB.TABLE_FAVORITE, true,
            FavoriteDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteDB.EVENT_ID to TEXT + UNIQUE,
            FavoriteDB.EVENT_NAME to TEXT,
            FavoriteDB.HOME_TEAM to TEXT,
            FavoriteDB.AWAY_TEAM to TEXT,
            FavoriteDB.HOME_SCORE to TEXT,
            FavoriteDB.AWAY_SCORE to TEXT,
            FavoriteDB.DATE_EVENT to TEXT,
            FavoriteDB.ID_HOME_TEAM to TEXT,
            FavoriteDB.ID_AWAY_TEAM to TEXT
        )
        db?.createTable(
            TeamDB.TABLE_FAVORITE, true,
            TeamDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamDB.ID_TEAM to TEXT + UNIQUE,
            TeamDB.TEAM_NAME to TEXT,
            TeamDB.TEAM_LEAGUE to TEXT,
            TeamDB.TEAM_DESCRIPTION to TEXT,
            TeamDB.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(FavoriteDB.TABLE_FAVORITE, true)
    }

}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)