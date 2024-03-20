package com.loc.newsapp.di

import android.app.Application
import androidx.room.Room
import com.loc.newsapp.NewsApplication
import com.loc.newsapp.data.repository.NewsRepositoryImpl
import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.data.local.NewsDatabase
import com.loc.newsapp.data.local.NewsTypeConvertor
import com.loc.newsapp.data.manager.LocalUserManagerImpl
import com.loc.newsapp.data.remote.RssApi
import com.loc.newsapp.data.remote.dto.NewsApi
import com.loc.newsapp.data.repository.RssRepositoryImpl
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.repository.RssRepository
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCases

import com.loc.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.loc.newsapp.domain.usecases.app_entry.ReadUserApi
import com.loc.newsapp.domain.usecases.app_entry.RemoveUserApi
import com.loc.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.loc.newsapp.domain.usecases.app_entry.SaveUserApi
import com.loc.newsapp.domain.usecases.news.DeleteArticle
import com.loc.newsapp.domain.usecases.news.GetNews
import com.loc.newsapp.domain.usecases.news.GetSavedArticle
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import com.loc.newsapp.domain.usecases.news.SearchNews

import com.loc.newsapp.domain.usecases.news.SelectArticles
import com.loc.newsapp.domain.usecases.news.UpsertArticle
import com.loc.newsapp.domain.usecases.rss.AddFeed
import com.loc.newsapp.domain.usecases.rss.FollowFeed
import com.loc.newsapp.domain.usecases.rss.GetFeeds
import com.loc.newsapp.domain.usecases.rss.GetFollowedFeeds
import com.loc.newsapp.domain.usecases.rss.GetPosts
import com.loc.newsapp.domain.usecases.rss.GetUser
import com.loc.newsapp.domain.usecases.rss.RssUseCases
import com.loc.newsapp.domain.usecases.rss.SetUser
import com.loc.newsapp.domain.usecases.rss.UnfollowFeed
import com.loc.newsapp.util.Constants.BASE_URL
import com.loc.newsapp.util.Constants.NEWS_DATABASE_NAME
import com.loc.newsapp.util.Constants.RSS_BASE_URL

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager),
        saveUserApi = SaveUserApi(localUserManager),
        removeUserApi = RemoveUserApi(localUserManager),
        readUserApi = ReadUserApi(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository, newsDao: NewsDao): NewsUseCases {
        return NewsUseCases(
            selectArticle = GetSavedArticle(newsDao),
            selectArticles = SelectArticles(newsRepository),
            deleteArticle = DeleteArticle(newsDao),
            upsertArticle = UpsertArticle(newsDao),
            searchNews = SearchNews(newsRepository = newsRepository),
            getNews = GetNews(newsRepository = newsRepository)
        )
    }

    @Provides
    @Singleton
    fun providesApplication(): NewsApplication {
        return NewsApplication()
    }

    @Provides
    @Singleton

    fun providesNewsRepository(newsApi: NewsApi, newsDao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(newsApi = newsApi, newsDao)
    }

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            name = NEWS_DATABASE_NAME,
            klass = NewsDatabase::class.java
        ).addTypeConverter(NewsTypeConvertor()).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

    @Provides
    @Singleton
    fun provideRssApi(): RssApi {
        return Retrofit.Builder().baseUrl(RSS_BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(RssApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRssRepository(rssApi: RssApi): RssRepository = RssRepositoryImpl(rssApi = rssApi)

    @Provides
    @Singleton
    fun provideRssUseCases(rssRepository: RssRepository): RssUseCases {
        return RssUseCases(
            setUser = SetUser(rssRepository), getUser = GetUser(rssRepository),
            getPosts = GetPosts(rssRepository),
            addFeed = AddFeed(rssRepository),
            unfollowFeed = UnfollowFeed(rssRepository),
            getFeeds = GetFeeds(rssRepository),
            getFollowedFeeds = GetFollowedFeeds(rssRepository),
            followFeed = FollowFeed(rssRepository)
        )
    }

}
